package com.example.pawpaw.domain.survey.repository.questions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public enum Step3SurveyQuestion {
    GROSS_MOTOR_SKILLS(List.of(
            new Question(1, "배를 바닥에 대고 앞으로 기어간다(배밀이). 이미 네 발기기를 잘 하면 '잘 할 수 있다'로 표시하세요.", null),
            new Question(2, "앉혀주면 손을 짚지 않고 안정하게 앉아 있는다.", null),
            new Question(3, "누워 있다가 혼자 앉는다.", null),
            new Question(4, "양손과 무릎으로 기어간다(네발기기).", null),
            new Question(5, "가구를 붙잡고 일어선다.", null),
            new Question(6, "가구를 붙잡은 상태에서 넘어지지 않고 자세를 낮춘다.", null),
            new Question(7, "가구를 양손으로 붙잡고 옆으로 걷는다.", null),
            new Question(8, "가구나 벽에서 손을 떼고 5초 이상 혼자 서 있다.", null)
    )),
    FINE_MOTOR_SKILLS(List.of(
            new Question(1, "장난감을 손에 쥐어 주면 흔든다.", null),
            new Question(2, "작은 장난감을 집어들 때, 손바닥에 대고 손가락으로 감싸 쥔다.", null),
            new Question(3, "딸랑이를 쥐고 있는 손에 다른 장난감을 주면 쥐고 있던 딸랑이를 떨어뜨리고 새 장난감을 잡는다.", null),
            new Question(4, "두 개의 물건을 양손에 각각 따로 쥔다.", null),
            new Question(5, "엄지와 다른 손가락을 이용해 작은 과자를 집는다.", null),
            new Question(6, "장난감을 한 손에서 다른 손으로 옮겨 준다.", null),
            new Question(7, "손잡이를 사용하여 컵을 잡는다.", null),
            new Question(8, "우유병을 혼자서 잡고 먹는다.", null)
    )),
    COGNITION(List.of(
            new Question(1, "딸랑이나 손가락과 같은 물건을 바닥에 두드리면서 논다.", null),
            new Question(2, "장난감이 떨어져 있는 곳을 쳐다본다.", null),
            new Question(3, "친숙한 어른이 안으려고 하면 팔을 벌린다.", null),
            new Question(4, "그림책에 재미있는 그림이 있으면 관심 있게 쳐다본다.", null),
            new Question(5, "리듬에 맞추어 몸을 움직인다.", null),
            new Question(6, "상자 안에서 물건을 꺼낸다.", null),
            new Question(7, "아이가 내는 소리를 어른이 따라 하면, 아이가 다시 그 소리를 따라 한다.", null),
            new Question(8, "장난감에 있는 버튼을 눌러 소리가 나게 한다.", null)
    )),
    LANGUAGE(List.of(
            new Question(1, "'범', '뽕', '포', '모'와 비슷한 소리를 낸다.", null),
            new Question(2, "'엄마' 또는 '아빠'와 비슷한 소리를 낸다 (의미 없이 내는 소리도 포함된다).", null),
            new Question(3, "아이에게 '안돼요.'라고 하면, 짧은 순간이라도 하던 행동을 멈추고 목소리에 반응한다.", null),
            new Question(4, "'무무', '바바바', '다다', '마마마' 등의 소리를 반복해서 발성한다.", null),
            new Question(5, "동작을 보여주지 않고 말로만 '빠이빠이', '짝짜꿍', '까꿍'을 시키면 최소한 한 가지를 한다.", null),
            new Question(6, "엄마에게 '엄마', 혹은 아빠에게 '아빠'라고 말한다.", null),
            new Question(7, "자음과 모음이 합쳐진 소리(자음 옹알이)를 낸다. (예: '다', '카', '모', '버', '더' 등)", null),
            new Question(8, "동작을 보여주지 않고 말로만 '주세요.', '오세요.', '가자.', '밥 먹자.'를 말하면 두 가지 이상은 뜻을 이해한다.", null)
    )),
    SOCIAL_SKILLS(List.of(
            new Question(1, "아이가 엄마(보호자)와 이야기를 하거나 놀 때 엄마(보호자)의 얼굴을 바라본다.", null),
            new Question(2, "아이의 이름을 부르면 듣고 쳐다본다.", null),
            new Question(3, "가족 등 친숙한 사람을 보면 다가가려고 한다.", null),
            new Question(4, "낯가림을 한다(일정한 연령이 되면 낯선 사람을 꺼리는 행동이 정상적으로 나타나며, 이것을 '낯가림'이라고 합니다.", null),
            new Question(5, "친숙한 어른에게 안아달라고 팔을 벌린다.", null),
            new Question(6, "어른을 따라서 손뼉을 치며 짝짜꿍 놀이를 한다.", null),
            new Question(7, "다른 아이들 옆에서 논다(함께 놀이를 하지는 못해도 된다).", null),
            new Question(8, "어른을 따라서 까꿍 놀이를 한다.", null)
    ));

    private final List<Question> questions;
}
