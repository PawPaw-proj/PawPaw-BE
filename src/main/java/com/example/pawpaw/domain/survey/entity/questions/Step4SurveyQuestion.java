package com.example.pawpaw.domain.survey.entity.questions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public enum Step4SurveyQuestion {

    GROSS_MOTOR_SKILLS(List.of(
            new Question(1, "누워 있다가 혼자 앉는다.", null),
            new Question(2, "양손과 무릎으로 긴다 (네발기기).", null),
            new Question(3, "가구를 붙잡고 일어선다.", null),
            new Question(4, "가구를 붙잡은 상태에서 넘어지지 않고 자세를 낮춘다.", null),
            new Question(5, "가구를 양손으로 붙잡고 옆으로 걷는다.", null),
            new Question(6, "가구나 벽에 손을 대고 5초 이상 혼자 서 있다.", null),
            new Question(7, "한 손으로 가구를 붙잡고 걷는다.", null),
            new Question(8, "아무것도 붙잡지 않고 혼자서 일어선다.", null)
    )),

    FINE_MOTOR_SKILLS(List.of(
            new Question(1, "딸랑이를 쥐고 있는 손에 다른 장난감을 주며 쥐고 있던 딸랑이를 떨어뜨리고 새 장난감을 잡는다.", null),
            new Question(2, "두 개의 물건을 양손에 각각 따로 든다.", null),
            new Question(3, "엄지와 다른 손가락을 이용해 작은 과자를 집는다.", null),
            new Question(4, "장난감을 한 손에서 다른 손으로 옮겨 든다.", null),
            new Question(5, "손잡이를 사용하여 컵을 잡는다.", null),
            new Question(6, "우유병을 혼자서 잡고 먹는다.", null),
            new Question(7, "엄지손가락과 집게손가락 끝을 '집기 모양'처럼 만들어 작은 알약 크기의 과자를 집는다.", null),
            new Question(8, "비커가 달린 장난감을 잡고 앞으로 굴러가도록 민다.", null)
    )),

    COGNITION(List.of(
            new Question(1, "친숙한 어른이 안으려고 하면 팔을 벌린다.", null),
            new Question(2, "그림책에 재미있는 그림이 있으면 관심 있게 쳐다본다.", null),
            new Question(3, "리듬에 맞추어 몸을 움직인다.", null),
            new Question(4, "상자 안에서 물건을 꺼낸다.", null),
            new Question(5, "아이가 내는 소리를 어른이 따라 하면, 아이가 다시 그 소리를 따라 한다.", null),
            new Question(6, "장난감에 있는 버튼을 눌러 소리가 나게 한다.", null),
            new Question(7, "자신이 좋아하는 한 개의 장난감을 가지고 3~4분 정도 논다.", null),
            new Question(8, "아이가 보는 앞에서 작은 장난감을 컵으로 덮고 감추면, 컵을 열어 장난감을 찾는다.", null)
    )),

    LANGUAGE(List.of(
            new Question(1, "아이에게 '안돼요.'라고 하면, 짧은 순간이라도 하던 행동을 멈추고 목소리에 반응한다.", null),
            new Question(2, "'무무', '바바바', '다다', '마마마' 등의 소리를 반복해서 발생한다.", null),
            new Question(3, "동작을 보여주지 않고 말로만 '빼이빼이', '짝짜꿍', '까꿍'을 시키면 최소한 한 가지를 한다.", null),
            new Question(4, "엄마에게 '엄마', 혹은 아빠에게 '아빠'라고 말한다.", null),
            new Question(5, "자음과 모음이 합쳐진 소리(자음 옹알이)를 낸다. (예: '다', '가', '모', '버', '더' 등)", null),
            new Question(6, "동작을 보여주지 않고 말로만 '주세요.', '오세요.', '가자.', '밥 먹자.'를 말하면 두 가지 이상을 이해한다.", null),
            new Question(7, "원하는 것을 손가락으로 가리킨다.", null),
            new Question(8, "'좋다(예).', '싫다(아니요).'를 고개를 끄덕이거나 몸을 흔들어 표현한다.", null)
    )),

    SOCIAL_SKILLS(List.of(
            new Question(1, "낯가림을 한다(일정한 연령이 되면 낯선 사람을 꺼리는 행동이 정상적으로 나타나며, 이것을 '낯가림'이라고 합니다).", null),
            new Question(2, "친숙한 어른에게 안아달라고 팔을 벌린다.", null),
            new Question(3, "어른을 따라서 손뼉을 치며 짝짜꿍 놀이를 한다.", null),
            new Question(4, "다른 아이들 옆에서 논다(함께 놀이를 하지는 못해도 된다).", null),
            new Question(5, "어른을 따라서 까꿍 놀이를 한다.", null),
            new Question(6, "어른을 따라서 '빼이빼이'하며 손을 흔든다.", null),
            new Question(7, "어른의 관심을 끌기 위한 행동을 한다. (예: 어른이 못 본 척하면 '예쁜 짓'을 한다.)", null),
            new Question(8, "'짝짜꿍'이나 '곤지곤지' 같은 말을 들어도 양손을 움직거린다.", null)
    ));

    private final List<Question> questions;
}
