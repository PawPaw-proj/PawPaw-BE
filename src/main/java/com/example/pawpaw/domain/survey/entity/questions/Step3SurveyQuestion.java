package com.example.pawpaw.domain.survey.entity.questions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public enum Step3SurveyQuestion {
    GROSS_MOTOR_SKILLS(List.of(
            new Question(1, "큰 공을 던져주면 양팔과 가슴을 이용해 받는다.", null),
            new Question(2, "세발자전거의 페달을 밟아서 앞으로 나갈 수 있다.", null),
            new Question(3, "제자리에서 양발을 모아 멀리뛰기를 한다.", null),
            new Question(4, "아무것도 붙잡지 않고 한발로 3초 이상 서 있을 수 있다.", null),
            new Question(5, "보조 바퀴가 있는 두발자전거를 탈 수 있다.", null),
            new Question(6, "한발로 두세 발짝 뛴다.", null),
            new Question(7, "서 있는 자세에서 팔을 들어 머리 위로 공을 20미터 이상 앞으로 멀리 던진다.", null),
            new Question(8, "아무것도 붙잡지 않고 한발씩 번갈아 내디디며 계단을 내려간다.", null)
    )),
    FINE_MOTOR_SKILLS(List.of(
            new Question(1, "자신의 옷이나 인형 옷의 단추를 푼다.", null),
            new Question(2, "원이 그려진 것을 보여주면 원을 그리라고 하는 과정의 시범을 보지 않고도 그려야 한다.", null),
            new Question(3, "종이를 두 번 접어야 하는 다섯장 종이를 정확히 접는다.", null),
            new Question(4, "그려진 점선을 따라 선을 그린다.", null),
            new Question(5, "사각형이 그려진 것을 보여주며 따라 그린다.", null),
            new Question(6, "가위를 직선 모양 따라 똑바로 오린다.", null),
            new Question(7, "산자락(삼각형) 안에 그려진 것을 보고 삼각형을 따라 그린다.", null),
            new Question(8, "블록으로 계단 모양을 쌓는다.", null)
    )),
    COGNITION(List.of(
            new Question(1, "과일, 탑 것, 가구가 그려진 그림카드를 섞어 놓았을 때, 이야기가 같은 종류끼리 분류한다.", null),
            new Question(2, "'가장 많은', '가장 적은'의 개념을 모두 이해한다.", null),
            new Question(3, "다른 사람이 한 말을 전달한다.", null),
            new Question(4, "다섯 가지 이상의 색깔을 정확하게 구분한다.", null),
            new Question(5, "물건을 하나씩(열)까지 센다.", null),
            new Question(6, "아침, 점심, 저녁, 오늘, 내일 등 시간을 이해한다.", null),
            new Question(7, "사람(예: 엄마, 아빠)을 그리고 그 사람의 신체 일부인 이상을 그린다.", null),
            new Question(8, "손에 닿지 않는 물건을 도구를 사용하여 가져온다.", null)
    )),
    LANGUAGE(List.of(
            new Question(1, "완전한 문장으로 이야기한다.", null),
            new Question(2, "같은 문맥에서 두 개의 이상한 단어를 설명한다.", null),
            new Question(3, "과거의 일을 자연스럽게 이야기한다.", null),
            new Question(4, "현재와 미래를 구분하여 문장을 말한다.", null),
            new Question(5, "그날 있었던 일을 이야기한다.", null),
            new Question(6, "단어의 뜻을 물어보면 설명한다.", null),
            new Question(7, "단순한 대화로 간단한 의사를 표현한다.", null),
            new Question(8, "단어의 끝 부분을 말하여 질문의 의미를 파악한다.", null)
    )),
    SOCIAL_SKILLS(List.of(
            new Question(1, "다른 사람에게 간단한 놀이의 규칙을 설명한다.", null),
            new Question(2, "다른 아이들과 있을 때, 차례를 지키고 놀잖을 나눠서 논다.", null),
            new Question(3, "다른 아이들의 행동에 대해 이야기한다.", null),
            new Question(4, "'학교놀이', '슬레검' 등 여러가지 놀이를 한다.", null),
            new Question(5, "자기보다 어린 아이들을 돌보는 행동을 한다.", null),
            new Question(6, "자기 차례를 기다린다.", null),
            new Question(7, "도움이 필요한 친구를 도와준다.", null),
            new Question(8, "다른 사람과 함께 이야기의 흐름에 맞추어 동작이나 놀이를 한다.", null)
    )),
    SELF_CARE(List.of(
            new Question(1, "장화(부츠)를 혼자 신는다.", null),
            new Question(2, "혼자서 간단한 음식 조리를 한다.", null),
            new Question(3, "혼자서 손을 씻는다.", null),
            new Question(4, "단추를 끼운다.", null),
            new Question(5, "외투를 입는다.", null),
            new Question(6, "입술을 닦는다.", null),
            new Question(7, "혼자서 스스로 머리를 감는다.", null),
            new Question(8, "식사를 다 한 후에는 정리한다.", null)
    ));

    private final List<Question> questions;
}
