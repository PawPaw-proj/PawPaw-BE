package com.example.pawpaw.global.util.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.pawpaw.domain.survey.dto.SurveyAverageData;
import com.example.pawpaw.domain.survey.entity.SurveyCategory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class S3ImageService {

    @Value("${cloud.aws.s3.bucket-name}")
    private String bucketName;

    private final AmazonS3 amazonS3;
    private static final String RAW_DATA_DIRECTORY = "raw-data";

    public void addRawDataToCsvObject(String csvFileName, List<String> rawData) throws IOException {
        // S3에서 CSV 파일 다운로드
        String key = RAW_DATA_DIRECTORY + "/" + csvFileName;
        System.out.println("key: " + key);
        GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName, key);

        Path tempFile = Files.createTempFile("temp", ".csv");
        try (InputStream s3ObjectStream = amazonS3.getObject(getObjectRequest).getObjectContent()) {
            Files.copy(s3ObjectStream, tempFile, StandardCopyOption.REPLACE_EXISTING);
        }

        // 기존 CSV 읽기
        List<List<String>> allRows = new ArrayList<>();
        try (Reader reader = Files.newBufferedReader(tempFile, StandardCharsets.UTF_8)) {
            CSVFormat.DEFAULT.parse(reader).forEach(record -> {
                List<String> row = new ArrayList<>();
                record.forEach(row::add);
                allRows.add(row);
            });
        }

        // 새로운 데이터 추가
        allRows.add(rawData);

        // 수정된 CSV 파일 작성
        try (Writer writer = Files.newBufferedWriter(tempFile, StandardCharsets.UTF_8)) {
            CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT);
            printer.printRecords(allRows);
        }

        // 수정된 파일 S3에 업로드
        try (InputStream inputStream = Files.newInputStream(tempFile)) {
            ObjectMetadata metadata = new ObjectMetadata();
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, inputStream, metadata);
            amazonS3.putObject(putObjectRequest);
        }

        Files.delete(tempFile);
    }

    public List<SurveyAverageData> readSurveyResultsFromCsv() throws IOException {
        String csvFileName = "4-5months_results.csv";

        // S3에서 CSV 파일 다운로드
        String key = RAW_DATA_DIRECTORY + "/result/4-5months/" + csvFileName;
        log.error("Reading key: " + key);

        List<SurveyAverageData> results = new ArrayList<>();

        try (InputStreamReader reader = new InputStreamReader(
                amazonS3.getObject(new GetObjectRequest(bucketName, key)).getObjectContent(),
                StandardCharsets.UTF_8
        )) {
            CSVParser csvParser = CSVFormat.DEFAULT
                    .builder()
                    .setHeader() // 헤더를 첫 줄로 사용
                    .setSkipHeaderRecord(true) // 헤더 레코드는 건너뜀
                    .build()
                    .parse(reader);

            for (CSVRecord record : csvParser) {
                String category = record.get("area");
                double average = Double.parseDouble(record.get("average"));
                SurveyCategory surveyCategory = SurveyCategory.fromEnglishName(category);
                results.add(new SurveyAverageData(surveyCategory.getName(), average));
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to read CSV file from S3", e);
        }

        return results;
    }
}

