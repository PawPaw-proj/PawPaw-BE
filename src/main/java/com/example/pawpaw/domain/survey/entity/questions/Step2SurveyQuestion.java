package com.example.pawpaw.domain.survey.entity.questions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public enum Step2SurveyQuestion {

    // 대근육운동
    GROSS_MOTOR_SKILLS(List.of(
            new Question(1, "엎드린 자세에서 뒤집는다.", null),
            new Question(2, "등을 대고 누운 자세에서 엎드린 자세로 뒤집는다(팔이 몸통에 걸려 있지 않아야 한다).", null),
            new Question(3, "누워 있을 때 자기 발을 잡고 논다.", "https://i.ibb.co/pxtWpDM/image-14.png"),
            new Question(4, "앉혀주면 양손을 짚고 30초 이상 혼자 버티고 앉아 있다.", "https://i.ibb.co/BsC9VHr/image-15.png"),
            new Question(5, "배를 바닥에 대고 앞으로 긴다(배밀이). 이미 네 발기기를 잘 하면 '잘 할 수 있다'로 표기하세요.", "https://i.ibb.co/pb1J9LX/image-21.png"),
            new Question(6, "앉혀주면 손을 짚지 않고 안정하게 앉아 있다.", "https://i.ibb.co/b6cygYY/image-18.png"),
            new Question(7, "누워 있다가 혼자 앉는다.", null),
            new Question(8, "양손과 무릎으로 긴다(네발기기).", "https://i.ibb.co/7zD2cCn/image-19.png")
    )),

    // 소근육운동
    FINE_MOTOR_SKILLS(List.of(
            new Question(1, "앉은 자세로 안겨있을 때 양손을 모아 쥐거나 손가락을 만진다.", null),
            new Question(2, "장난감을 손에 쥐어 주면 흔든다.", null),
            new Question(3, "앉은 자세로 안겨있을 때 탁자 위의 장난감을 향해 손을 뻗는다(장난감이 실제로 손에 닿지 않아도 된다).", null),
            new Question(4, "작은 장난감을 집어들 때, 손바닥에 대고 손가락으로 감싸 쥔다.", "https://i.ibb.co/PhH3tbQ/image-17.png"),
            new Question(5, "딸랑이를 쥐고 있는 손에 다른 장난감을 주면 쥐고 있던 딸랑이를 떨어뜨리고 새 장난감을 잡는다.", null),
            new Question(6, "손을 뻗어 앞에 있는 물체를 잡는다.", null),
            new Question(7, "두 개의 물건을 양손에 각각 따로 쥔다.", null),
            new Question(8, "엄지와 다른 손가락을 이용해 작은 과자를 집는다.", "https://i.ibb.co/VVG6rFB/image-22.png")
    )),

    // 인지
    COGNITION(List.of(
            new Question(1, "어떤 소리를 듣고 있다가 새로운 소리가 들리면 거기로 관심을 돌린다.", null),
            new Question(2, "자기 손과 손가락을 자세히 바라본다.", null),
            new Question(3, "굴러가는 공을 따라서 계속 쳐다본다.", null),
            new Question(4, "딸랑이나 손가락과 같은 물건을 바닥에 두드리면서 논다.", null),
            new Question(5, "장난감이 떨어져 있는 곳을 쳐다본다.", null),
            new Question(6, "친숙한 어른이 안으려고 하면 팔을 벌린다.", null),
            new Question(7, "그림책에 재미있는 그림이 있으면 관심 있게 쳐다본다.", null),
            new Question(8, "리듬에 맞추어 몸을 움직인다.", null)
    )),

    // 언어
    LANGUAGE(List.of(
            new Question(1, "'아', '우', '이' 등 의미 없는 발성을 한다.", null),
            new Question(2, "아이를 어르거나 달래면 옹알이로 반응한다.", null),
            new Question(3, "웃을 때 소리를 내며 웃는다.", null),
            new Question(4, "두 입술을 떼어서 내는 투레질 소리(젖먹이가 하는 '뿌뿌' 같은 소리)를 낸다.", null),
            new Question(5, "'멍', '뽕', '포', '모'와 비슷한 소리를 낸다.", null),
            new Question(6, "'엄마' 또는 '아빠'와 비슷한 소리를 낸다(의미 없이 내는 소리도 포함된다).", null),
            new Question(7, "아이에게 '어딨니?', '잡고', '하고 해봐' 짧은 소리만으로 하던 행동을 멈추고 목소리에 반응한다.", null),
            new Question(8, "'무무', '바바바', '다다', '마마마' 등의 소리를 반복해서 발성한다.", null)
    )),

    // 사회성
    SOCIAL_SKILLS(List.of(
            new Question(1, "아이가 엄마(보호자)와 이야기를 하거나 놀 때 엄마(보호자)의 얼굴을 바라본다.", null),
            new Question(2, "어른들의 얼굴(머리카락, 코, 안경 등)을 만져 보거나 잡아당긴다.", null),
            new Question(3, "거울 속에 보이는 자신의 모습을 보고 웃거나 흥얼거린다.", null),
            new Question(4, "아이의 이름을 부르면 듣고 쳐다본다.", null),
            new Question(5, "가족 등 친숙한 사람을 보면 다가가려고 한다.", null),
            new Question(6, "낯가림을 한다(모르는 얼굴이 보이면 놀선 사람처럼 멈추는 행동이 정상적으로 나타나며, 이것을 '낯가림'이라고 합니다.", null),
            new Question(7, "친숙한 어른에게 안으라고 팔을 벌린다.", null),
            new Question(8, "어른을 따라서 손뼉을 치며 짝짝짝 놀이를 한다.", null)
    ));

    private final List<Question> questions;
}
