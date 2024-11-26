package com.example.pawpaw.domain.survey.entity.questions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public enum Step2SurveyQuestion {
    GROSS_MOTOR_SKILLS(List.of(
            new Question(1, "계단의 가장 낮은 층에서 양발을 모아 바닥으로 뛰어내린다.", null),
            new Question(2, "서 있는 자세에서 팔을 들어 머리 위로 공을 앞으로 던진다.", null),
            new Question(3, "발뒤꿈치를 들어 네 걸음 이상 걷는다.", null),
            new Question(4, "난간을 붙잡고 한 계단에 양발을 모은 뒤 한발씩 한발씩 계단을 올라간다.", null),
            new Question(5, "아무것도 붙잡지 않고 한 발로 1초간 서 있다.", null),
            new Question(6, "아무것도 붙잡지 않고 한 계단에 양발을 모은 뒤 한발씩 내려간다.", null),
            new Question(7, "아무것도 붙잡지 않고 한발씩 번갈아 내딛으며 계단을 올라간다.", null),
            new Question(8, "큰 공을 던져주면 양팔과 가슴을 이용해 받는다.", null)
    )),
    FINE_MOTOR_SKILLS(List.of(
            new Question(1, "문 손잡이를 돌려서 연다.", null),
            new Question(2, "(색)연필의 아랫부분을 잡는다.", null),
            new Question(3, "유아용 가위를 주면 실제로 종이를 자르지는 못해도 한 손으로 종이를 잡고 다른 손으로 가위를 벌리고 오므리며 종이를 자르려고 시도한다.", null),
            new Question(4, "신발 끈 구멍이나 구슬 구멍에 끈을 끼운 후 빼낸다.", null),
            new Question(5, "수평선 그리는 시범을 보여주면 흉내 내어 그린다.", null),
            new Question(6, "엄지와 다른 손가락으로 (색연필, 크레용 또는 펜 등을 잡는다).", null),
            new Question(7, "자신의 옷이나 인형 옷의 단추를 푼다.", null),
            new Question(8, "원의 그려진 것을 보여주면 원을 그리되 과정의 시범을 보지 않고도 그려야 한다.", null)
    )),
    COGNITION(List.of(
            new Question(1, "빨간, 노란, 파란 토막들을 섞어 놓으면 같은 색의 토막들끼리 분류한다.", null),
            new Question(2, "'많다-적다'와 같은 양의 개념을 이해한다.", null),
            new Question(3, "여섯 조각으로 된 퍼즐을 맞춘다.", null),
            new Question(4, "두 개의 선 중 길이가 긴 것과 짧은 것을 구분한다.", null),
            new Question(5, "'둘'이라는 개념을 이해한다.", null),
            new Question(6, "크기가 다른 세 개의 사물을 놓고 '가장 큰 것', '중간 크기의 것', '가장 작은 것'을 구분한다.", null),
            new Question(7, "'안', '밖', '사이'와 같은 공간에 대한 개념을 이해한다.", null),
            new Question(8, "연관성이 없는 두 가지 지시사항을 기억하여 수행한다.", null)
    )),
    LANGUAGE(List.of(
            new Question(1, "손으로 가리키거나 동작으로 힌트를 주지 않아도, '식탁 위에 컵을 놓으세요.'라고 말하면 아이가 바르게 수행한다.", null),
            new Question(2, "'앞에', '위에', '밑에', '뒤에' 중에서 두 가지 이상의 뜻을 이해한다.", null),
            new Question(3, "그림책 속에 등장하는 사물의 이름을 말한다.", null),
            new Question(4, "'이름이 뭐예요?'하고 물으면, 성과 이름을 모두 말한다.", null),
            new Question(5, "'~했어요.', '~와' 같이 과거형으로 말한다.", null),
            new Question(6, "간단한 대화를 주고받는다.", null),
            new Question(7, "'예쁘다' 또는 '무섭다'의 뜻을 안다.", null),
            new Question(8, "'할머니야', '할아버지', '오빠(형)', '누나(언니)', '동생'과 같은 호칭을 정확하게 사용한다.", null)
    )),
    SOCIAL_SKILLS(List.of(
            new Question(1, "어른이 시키면 '미안해.', '고마워.'라는 말을 한다.", null),
            new Question(2, "다른 아이들의 행동을 보고 놀이의 규칙을 따른다.", null),
            new Question(3, "자신의 기분을 좋다고, 나쁘다고 표현할 수 있다.", null),
            new Question(4, "3~4명과 어울려서 숨바꼭질, 술래잡기 등을 한다.", null),
            new Question(5, "어른이 이끄는 집단 놀이에서 규칙을 따른다.", null),
            new Question(6, "자기 차례를 기다린다.", null),
            new Question(7, "놀이 중에 도움이 필요한 친구를 도와주고 달래준다.", null),
            new Question(8, "또래와 함께 이야기를 하며 흐름이 있는 놀이를 한다.", null)
    )),
    SELF_CARE(List.of(
            new Question(1, "음식을 먹다 흘리면 손이나 옷으로 닦지 않고 스스로 휴지나 냅킨으로 닦는다.", null),
            new Question(2, "바지를 입힐 때, 바지통에 발끝을 약간 넣어주면 허리까지 안전히 끌어 올린다.", null),
            new Question(3, "낮 동안 소변을 가린다.", null),
            new Question(4, "낮 동안 대변을 가린다.", null),
            new Question(5, "물을 틀어주거나 받아주면 혼자서 바르고 손을 씻는다.", null),
            new Question(6, "양말을 혼자서 신는다.", null),
            new Question(7, "도와주지 않아도 혼자서 밥을 먹는다.", null),
            new Question(8, "단추를 풀어 주면 스스로 내의로 벗는다.", null)
    ));

    private final List<Question> questions;
}
