package com.example.pawpaw.global.util.ai;

import com.example.pawpaw.domain.survey.entity.SurveyCategory;
import com.example.pawpaw.global.response.CustomException;
import com.example.pawpaw.global.response.ErrorCode;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GptApiClient {

    @Value("${gpt.api.model}")
    private String model;

    private final WebClient openAiWebClient;
    private final ObjectMapper objectMapper;
    private Logger logger = LoggerFactory.getLogger(GptApiClient.class);

    private static final String SYSTEM_MESSAGE = """
                You are a developmental play expert who recommends suitable activities for children based on their developmental needs.
                
                Here is a list of play activities:
                (id, name, developmental_effect) - Each play activity has a unique ID, name, and the developmental area it supports.
                
                Example of play activity list:
                1, "평균대 건너기 (길을 따라 빠르게, 느리게 정지!)", "GROSS_MOTOR_SKILLS"
                2, "장애물 넘기 (마녀를 피해라)", "GROSS_MOTOR_SKILLS"
                3, "페트병 볼링 놀이 (동물을 사냥해요)", "GROSS_MOTOR_SKILLS"
                4, "얼굴 포스트잇 떼기", "GROSS_MOTOR_SKILLS"
                5, "박스 골대 축구 놀이", "GROSS_MOTOR_SKILLS"
                6, "누가누가 높이 쌓나 (멋진 탑을 쌓아요)", "FINE_MOTOR_SKILLS"
                7, "후프 다리를 건너요", "FINE_MOTOR_SKILLS"
                8, "물건 쌓기 경연", "FINE_MOTOR_SKILLS"
                9, "구불구불 돌멩이 애벌레", "FINE_MOTOR_SKILLS"
                10, "점토 자르기 놀이", "FINE_MOTOR_SKILLS"
                11, "젤리 옮기기", "FINE_MOTOR_SKILLS"
                12, "얼굴 만들기", "FINE_MOTOR_SKILLS"
                13, "동물 흉내내기(상징하는 나무)", "COGNITION"
                14, "거울 보며 화장 흉내내기(나는 누구일까요)", "COGNITION"
                15, "이야기 이어 말하기", "COGNITION"
                16, "어린이집 가방 꾸미기", "COGNITION"
                17, "상자 안 호기심 놀이(이야기 이어 말하기)", "COGNITION"
                18, "이 동물은 누구지?(그림 보고 단어 말하기)", "LANGUAGE"
                19, "엄마랑 그림책 읽어요(동화책 읽고 질문하기)", "LANGUAGE"
                20, "등에 그림을 그려요", "LANGUAGE"
                21, "대상 찾기 놀이", "LANGUAGE"
                22, "같이 놀아요(함께 움직여요)", "SOCIAL_SKILLS"
                23, "아빠 흉내내기(신나는 역할극 놀이)", "SOCIAL_SKILLS"
                24, "협력해서 블록 쌓기", "SOCIAL_SKILLS"
                25, "인형을 먹여줄 거야", "SOCIAL_SKILLS"
                26, "신나는 생산 주문 받기", "SOCIAL_SKILLS"
                27, "배꼽 인사해요", "SOCIAL_SKILLS"
                28, "물건의 자리를 기억해요(스스로 정리하기)", "SELF_CARE"
                29, "나처럼 정리해요", "SELF_CARE"
                30, "양말을 쑥!(간단한 옷 입기)", "SELF_CARE"
                31, "봉글봉글 비누거품(아이와 손 씻기 놀이)", "SELF_CARE"
                32, "내 옷장에 넣어요", "SELF_CARE"
                33, "치카치카 양치해요(치카치카 양치하기)", "SELF_CARE"
                
                Input:
                - A prioritized list of developmental areas the child needs to improve: [e.g., "COGNITION", "GROSS_MOTOR_SKILLS"]
                
                Your task:
                - Recommend a **maximum of 5 play activities** that match each developmental area the child needs to improve.
                - Prioritize the recommendations based on the order of developmental areas provided. For example, recommend activities that match the first area before considering activities from the second area, and so on.
                - Include both the `id`, `name`, and `developmentalEffect` for each recommended play activity in JSON format.
                - (**IMPORTANT**) Format your response **strictly in JSON** and do not include any additional text, commentary, or explanation outside the JSON block.
                - (**IMPORTANT**) If there are more than 5 possible matches for a developmental area, **select only the first 5 activities** based on their order in the provided list.
                - The response must follow this format:
                
                Output format:
                {
                  "recommended_games": [
                    {
                      "id": 1,
                      "name": "평균대 건너기 (길을 따라 빠르게, 느리게 정지!)",
                      "developmental_effect": "GROSS_MOTOR_SKILLS"
                    },
                    {
                      "id": 2,
                      "name": "장애물 넘기 (마녀를 피해라)",
                      "developmental_effect": "GROSS_MOTOR_SKILLS"
                    }
                  ]
                }
                
                - Ensure that the list contains at most 5 activities, even if more matches exist.
                - Ensure the response is valid JSON and nothing else.
            """;

    public List<RecommendGameResponseDTO> askRecommendedGames(List<SurveyCategory> surveyCategories) {
        // JSON 요청 생성
        String categoriesToImprove = surveyCategories.stream()
                .map(Enum::name)
                .collect(Collectors.joining(", "));
        String userMessage = String.format("""
                The child needs to improve in the following prioritized developmental areas: [%s].

                Please recommend up to a total of 5 suitable play activities, prioritizing the developmental areas provided in order. Respond **only with JSON** in the specified format.
                """, categoriesToImprove);
        String jsonRequest = createRequestJson(userMessage);

        // GPT API 호출
        try {
            String gptResponse = openAiWebClient.post()
                    .bodyValue(jsonRequest)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            logger.info("GPT API 응답 ={}", gptResponse);
            return parseResponse(gptResponse);
        } catch (Exception e) {
            logger.error("GPT API 호출에 실패했습니다. ={}", e.getMessage());
        }
        return new ArrayList<>();
    }

    private String createRequestJson(String userMessage) {
        Map<String, Object> requestBody = Map.of(
                "model", model,
                "messages", List.of(
                        Map.of("role", "system", "content", SYSTEM_MESSAGE),
                        Map.of("role", "user", "content", userMessage)
                )
        );
        try {
            return objectMapper.writeValueAsString(requestBody);
        } catch (Exception e) {
            logger.error("GPT API 요청 JSON 생성에 실패했습니다. ={}", e.getMessage());
            throw new CustomException(ErrorCode.GPT_API_REQUEST, "GPT API 요청 JSON 생성에 실패했습니다.");
        }
    }

    private List<RecommendGameResponseDTO> parseResponse(String gptResponse) {
        List<RecommendGameResponseDTO> recommendedGames = new ArrayList<>();
        try {
            JsonNode rootNode = objectMapper.readTree(gptResponse);

            String content = rootNode.path("choices").get(0).path("message").path("content").asText();
            JsonNode gamesNode = objectMapper.readTree(content).path("recommended_games");

            if (gamesNode.isArray()) {
                for (JsonNode node : gamesNode) {
                    int id = node.path("id").asInt();
                    String name = node.path("name").asText();
                    String developmentalEffect = node.path("developmental_effect").asText();

                    recommendedGames.add(new RecommendGameResponseDTO(id, name, developmentalEffect));
                }
            }
        } catch (Exception e) {
            logger.error("GPT API 응답 파싱에 실패했습니다. ={}", e.getMessage());
        }
        return recommendedGames;
    }
}

