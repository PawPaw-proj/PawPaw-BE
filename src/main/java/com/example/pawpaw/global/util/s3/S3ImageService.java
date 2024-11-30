package com.example.pawpaw.global.util.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import com.example.pawpaw.global.response.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.*;

import static com.example.pawpaw.global.response.ErrorCode.BAD_REQUEST_RESOURCE;
import static com.example.pawpaw.global.response.ErrorCode.INTERNAL_SERVER_ERROR;

@Component
@RequiredArgsConstructor
@Slf4j
public class S3ImageService {

    @Value("${cloud.aws.s3.bucket-name}")
    private String bucketName;

    private final AmazonS3 amazonS3;
    private final List<String> allowedExtensions = Arrays.asList("jpg", "jpeg", "png", "gif");
    private static final long MAX_FILE_SIZE = 50 * 1024 * 1024; // 50MB
    private static final String IMAGE_MIME_TYPE_PREFIX = "image/";
    private static final String RAW_DATA_DIRECTORY = "raw-data";

    public List<String> upload(String directory, List<MultipartFile> images) {

        List<UploadFile> uploadFiles = new ArrayList<>();

        for (MultipartFile image : images) {
            validateImage(image);

            String s3FileName = generateS3FileName(directory, Objects.requireNonNull(image.getOriginalFilename()));
            byte[] bytes = getBytesFromMultipartFile(image);

            ObjectMetadata metadata = createObjectMetadata(bytes.length, getExtension(image.getOriginalFilename()));
            uploadFiles.add(new UploadFile(s3FileName, bytes, metadata));
        }

        return new ArrayList<>(uploadToS3(uploadFiles));
    }

    private void validateImage(MultipartFile image) {
        String filename = image.getOriginalFilename();

        if (image.isEmpty() || Objects.isNull(filename)) {
            throw new CustomException(BAD_REQUEST_RESOURCE, "EMPTY 파일은 업로드 불가능합니다.");
        }

        if (image.getSize() > MAX_FILE_SIZE) {
            throw new CustomException(BAD_REQUEST_RESOURCE, "파일 크기가 너무 큽니다.");
        }

        String extension = getExtension(filename);
        if (!allowedExtensions.contains(extension.toLowerCase())) {
            throw new CustomException(BAD_REQUEST_RESOURCE, String.format("%s은 유효하지 않은 파일 확장자 입니다.", extension));
        }
    }

    private String getExtension(String filename) {
        int lastDotIndex = filename.lastIndexOf(".");
        if (lastDotIndex == -1) {
            throw new CustomException(BAD_REQUEST_RESOURCE, "파일 확장자가 없습니다.");
        }
        return filename.substring(lastDotIndex + 1);
    }

    private String generateS3FileName(String directory, String originalFilename) {
        String extension = getExtension(originalFilename);
        return directory + "/" + UUID.randomUUID().toString().substring(0, 10) + "." + extension;
    }

    private byte[] getBytesFromMultipartFile(MultipartFile image) {
        try (InputStream is = image.getInputStream()) {
            return IOUtils.toByteArray(is);
        } catch (IOException e) {
            log.error("S3 이미지 업로드 중에 입출력 오류가 발생했습니다. ={}", e.getMessage());
            throw new CustomException(INTERNAL_SERVER_ERROR, "S3 이미지 업로드 중에 입출력 오류가 발생했습니다.");
        }
    }

    private ObjectMetadata createObjectMetadata(long contentLength, String extension) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(IMAGE_MIME_TYPE_PREFIX + extension.toLowerCase());
        metadata.setContentLength(contentLength);
        return metadata;
    }

    private List<String> uploadToS3(List<UploadFile> uploadFiles) {
        List<String> uploadedUrls = new ArrayList<>();

        for (UploadFile uploadFile : uploadFiles) {
            try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(uploadFile.fileBytes())) {
                PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, uploadFile.s3FileName(), byteArrayInputStream, uploadFile.metadata());
                amazonS3.putObject(putObjectRequest);
                uploadedUrls.add(amazonS3.getUrl(bucketName, uploadFile.s3FileName()).toString());
            } catch (Exception e) {
                log.error("S3 버킷에 이미지 넣기에 실패했습니다. ={}", e.getMessage());
                throw new CustomException(INTERNAL_SERVER_ERROR, "S3 버킷에 이미지 넣기에 실패했습니다.");
            }
        }
        return uploadedUrls;
    }

    public void deleteImagesFromS3(List<String> imageAddresses) {
        List<DeleteObjectsRequest.KeyVersion> keys = new ArrayList<>();
        for (String imageAddress : imageAddresses) {
            String key = getKeyFromImageAddress(imageAddress);
            keys.add(new DeleteObjectsRequest.KeyVersion(key));
        }
        try {
            DeleteObjectsRequest deleteObjectsRequest = new DeleteObjectsRequest(bucketName).withKeys(keys);
            amazonS3.deleteObjects(deleteObjectsRequest);
        } catch (Exception e) {
            log.error("S3 이미지 삭제 중에 오류가 발생했습니다. ={}", e.getMessage());
            throw new CustomException(INTERNAL_SERVER_ERROR, "S3 이미지 삭제 중에 오류가 발생했습니다.");
        }
    }

    private String getKeyFromImageAddress(String imageAddress) {
        try {
            URL url = new URL(imageAddress);
            return URLDecoder.decode(url.getPath().substring(1), "UTF-8");
        } catch (MalformedURLException | UnsupportedEncodingException e) {
            log.error("디코딩 중에 오류가 발생했습니다. ={}", e.getMessage());
            throw new CustomException(INTERNAL_SERVER_ERROR, "디코딩 중에 오류가 발생했습니다.");
        }
    }

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
}

