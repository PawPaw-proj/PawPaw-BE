package com.example.pawpaw.global.util.ai;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class GptApiClient {

    @Value("${gpt.api.model}")
    private String model;

    private final WebClient openAiWebClient;
    private final ObjectMapper objectMapper;

//    private static final String SYSTEM_MESSAGE = "You are an intelligent assistant. Recommend at least 3 relevant links based on the user's query. " +
//            "If the query is vague or unclear, make a reasonable guess and provide general recommendations related to the topic. " +
//            "Ensure your response is in JSON format with the following structure:\n" +
//            "[\n" +
//            "  {\n" +
//            "    \"name\": \"string\", // The name of the link\n" +
//            "    \"url\": \"string\", // The URL of the link\n" +
//            "    \"description\": \"string\" // A brief, natural, and conversational description in Korean\n" +
//            "  }\n" +
//            "]\n\n" +
//            "Even if the user provides a vague query, include at least 3 links by making educated guesses. Write friendly and conversational descriptions in Korean.";

//    public String askGpt(String userQuery) throws Exception {
//        // JSON 요청 생성
//        String jsonRequest = createRequestJson(userQuery);
//
//        // GPT API 호출
//        return openAiWebClient.post()
//                .bodyValue(jsonRequest)
//                .retrieve()
//                .bodyToMono(String.class)
//                .block();
//    }
//
//    private String createRequestJson(String userQuery) throws Exception {
//        Map<String, Object> requestBody = Map.of(
//                "model", model,
//                "messages", List.of(
//                        Map.of("role", "system", "content", SYSTEM_MESSAGE),
//                        Map.of("role", "user", "content", userQuery)
//                )
//        );
//        return objectMapper.writeValueAsString(requestBody);
//    }
//    private List<RecommendLinkResponseDTO> parseResponse(String gptResponse) {
//        List<RecommendLinkResponseDTO> links = new ArrayList<>();
//        try {
//            JsonNode rootNode = objectMapper.readTree(gptResponse);
//
//            String content = rootNode.path("choices").get(0).path("message").path("content").asText();
//
//            JsonNode linksNode = objectMapper.readTree(content);
//            if (linksNode.isArray()) {
//                for (JsonNode node : linksNode) {
//                    String name = node.path("name").asText();
//                    String url = node.path("url").asText();
//                    String description = node.path("description").asText();
//
//                    links.add(new RecommendLinkResponseDTO(name, url, description));
//                }
//            }
//        } catch (Exception e) {
//            throw new CustomException(BAD_REQUEST_RESOURCE, "GPT API 응답 파싱에 실패했습니다.");
//        }
//        return links;
//    }
}

