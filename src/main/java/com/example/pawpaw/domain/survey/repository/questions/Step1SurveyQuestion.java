package com.example.pawpaw.domain.survey.repository.questions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public enum Step1SurveyQuestion {
    GROSS_MOTOR_SKILLS(List.of(
        new Question(1, "등을 대고 누운 자세에서 반쯤 뒤집는다.", null),
        new Question(2, "엎드려 놓으면 고개를 잠깐 들었다 내린다.", null),
        new Question(3, "누운 자세에서 두 팔을 잡고 앞으로 당길 때 목이 뒤로 처지지 않고 따라 올라온다.", null),
        new Question(4, "엎드린 자세에서 가슴을 들고 양팔을 버틴다.", null),
        new Question(5, "엎드린 자세에서 뒤집는다.", null),
        new Question(6, "등을 대고 누운 자세에서 엎드린 자세로 뒤집는다(팔이 몸통에 걸려 있지 않아야 한다).", null),
        new Question(7, "누워 있을 때 자기 발을 잡고 논다.", null),
        new Question(8, "앉혀주면 양손을 짚고 30초 이상 혼자 버티고 앉아 있다.", null)
    )),
    FINE_MOTOR_SKILLS(List.of(
            new Question(1, "등을 대고 누운 자세에서 두 손을 가슴 부근에 모은다.", null),
            new Question(2, "손에 딸랑이를 쥐어 주면 잠시 쥐고 있다.", null),
            new Question(3, "앉은 자세로 안겨있을 때 양손을 모아 치거나 손가락을 편다.", null),
            new Question(4, "손에 쥐고 있는 딸랑이를 자기 입으로 가져간다.", null),
            new Question(5, "딸랑이를 손 가까이 주면 잡는다.", null),
            new Question(6, "앉은 자세로 안겨있을 때 탁자 위의 장난감을 향해 손을 뻗는다.", null),
            new Question(7, "작은 장난감을 집어들 때, 손바닥에 대고 손가락으로 집는다.", null),
            new Question(8, "딸랑이를 쥐고 있는 손에 다른 장난감을 주며 쥐고 있던 딸랑이를 떨어뜨리고 새 장난감을 잡는다.", null)
    )),
    COGNITION(List.of(
            new Question(1, "소리 나는 곳을 쳐다본다.", null),
            new Question(2, "눈앞에서 장난감을 움직이면 시선이 장난감의 움직임을 따라간다.", null),
            new Question(3, "애들 소리를 듣고 있다가 새로운 소리가 들리면 그 쪽을 쳐다본다.", null),
            new Question(4, "자기 손가락을 자세히 바라본다.", null),
            new Question(5, "딸랑이를 흔들거나 바라보거나 입에 넣은 등 딸랑이를 가지고 논다.", null),
            new Question(6, "딸랑이나 손가락과 같은 물건을 바닥에 두드리면서 논다.", null),
            new Question(7, "장난감이 떨어져 있는 곳을 쳐다본다.", null),
            new Question(8, "친숙한 어른이 안으려고 하면 팔을 벌린다.", null)
    )),
    LANGUAGE(List.of(
            new Question(1, "'아', '우', '이' 등 의미 없는 발성을 한다.", null),
            new Question(2, "아이를 이르거나 달래면 옹알이로 반응한다.", null),
            new Question(3, "웃을 때 소리를 내며 웃는다.", null),
            new Question(4, "장난감이나 사람을 보고 소리를 내며 반응한다.", null),
            new Question(5, "두 입술을 떼어서 내는 투레질 소리(떡먹이가 하는 '뿌뿌' 같은 소리)를 낸다.", null),
            new Question(6, "'멍', '뽕', '포', '모'와 비슷한 소리를 낸다.", null),
            new Question(7, "'엄마' 또는 '아빠'와 비슷한 소리를 낸다 (의미 없이 내는 소리도 포함된다).", null),
            new Question(8, "아이에게 '어딨니?', '잡고', '하고 해봐' 짧은 소리만으로 하던 행동을 멈추고 목소리에 반응한다.", null)
    )),
    SOCIAL_SKILLS(List.of(
            new Question(1, "엄마(보호자)가 자리를 비웠다가 다시 나타나면 엄마(보호자)를 알아보고 웃음을 짓는다.", null),
            new Question(2, "아이의 입(손가락이나 아이가 할퀸 손 등)을 보고 엄마(보호자)의 얼굴을 바라본다.", null),
            new Question(3, "어른이 아이를 보면 말하거나 웃기 전에, 이를 보고 먼저 웃는다.", null),
            new Question(4, "어른의 얼굴(입가, 코, 안경 등)을 만져 보거나 잡아당긴다.", null),
            new Question(5, "거울 속에 보이는 자신의 모습을 보고 웃거나 흥얼거린다.", null),
            new Question(6, "아이의 이름을 부르면 듣고 쳐다본다.", null),
            new Question(7, "가족 등 친숙한 사람을 보면 다가가려고 한다.", null),
            new Question(8, "낯가림을 한다(모르는 얼굴이 보이면 놀선 사람처럼 멈추는 행동이 정상적으로 나타나며, 이것을 '낯가림'이라고 합니다.", null)
    ));
    ;

    private final List<Question> questions;
}
