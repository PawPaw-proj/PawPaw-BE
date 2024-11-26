package com.example.pawpaw.domain.survey.entity.questions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public enum Step4SurveyQuestion {
    GROSS_MOTOR_SKILLS(List.of(
            new Question(1, "한발로 두세 발자국 뛴다.", null),
            new Question(2, "서 있는 자세에서 팔을 들어 머리 위로 공을 20미터 이상 앞으로 던진다.", null),
            new Question(3, "아무것도 붙잡지 않고 한발씩 번갈아 내디디며 계단을 내려간다.", null),
            new Question(4, "굴러가는 공을 발로 찬다.", null),
            new Question(5, "2미터 거리에서 테니스공 크기의 공을 던지면 두 손으로 잡는다.", null),
            new Question(6, "공을 바닥에 한 번 튕길 수 있다.", null),
            new Question(7, "무릎 아래 높이로 매어져 있는 줄을 뛰어넘을 수 있다.", null),
            new Question(8, "줄넘기를 1회 한다.", null)
    )),
    FINE_MOTOR_SKILLS(List.of(
            new Question(1, "색칠 공부의 그림 속에 색을 칠한다.", null),
            new Question(2, "종이에 그려진 네모를 가위로 오린다.", null),
            new Question(3, "블록으로 피라미드 모양을 쌓는다.", null),
            new Question(4, "엄지손가락과 다른 네 손가락을 차례로 맞붙게 한다.", null),
            new Question(5, "삼각형이 그려진 것을 보여주면 삼각형을 그린다.", null),
            new Question(6, "아이의 이름을 적어주면 따라 쓴다.", null),
            new Question(7, "종이에 그려진 동그라미를 가위로 오린다.", null),
            new Question(8, "간단한 자동차 모양을 종이에 그린다.", null)
    )),
    COGNITION(List.of(
            new Question(1, "아침, 점심, 저녁, 오늘, 내일 등 시간을 이해한다.", null),
            new Question(2, "손에 닿지 않는 물건을 도구를 사용하여 가져온다.", null),
            new Question(3, "자신이 원하는 TV 채널로 돌린다.", null),
            new Question(4, "동화책을 읽어주면 내용을 일부 이해하고 기억한다.", null),
            new Question(5, "엄마/보호자가 많이 쓰는 물건들이 어떤 용도로 사용되는지 알고 있다.", null),
            new Question(6, "1 더하기 2를 계산한다.", null),
            new Question(7, "자신의 왼쪽과 오른쪽을 구분할 수 있다.", null),
            new Question(8, "요일을 순서대로 알고 있다.", null)
    )),
    LANGUAGE(List.of(
            new Question(1, "그날 있었던 일을 이야기한다.", null),
            new Question(2, "친숙한 단어의 반대말을 말한다.", null),
            new Question(3, "간단한 농담이나 빗대어서 하는 말의 뜻을 알아차린다.", null),
            new Question(4, "단어의 뜻을 물어보면 설명한다.", null),
            new Question(5, "'만약~라면 무슨 일이 일어날까?' 같은 가상의 상황에 대해 상상력을 발휘한다.", null),
            new Question(6, "이름이나 쉬운 단어 2~3개를 보고 읽는다.", null),
            new Question(7, "가족이나 친구의 이름을 알고 있다.", null),
            new Question(8, "자기 이름이나 주소를 쓸 수 있다.", null)
    )),
    SOCIAL_SKILLS(List.of(
            new Question(1, "자기보다 어린 아동을 돌봐주는 행동을 한다.", null),
            new Question(2, "처음 만난 또래와 쉽게 어울린다.", null),
            new Question(3, "또래와 함께 차례나 규칙을 알아야 할 수 있는 놀이를 한다.", null),
            new Question(4, "자기 생각을 이야기하고 다른 아이의 말을 귀 기울여 듣는다.", null),
            new Question(5, "게임을 하는 방법에 대해 다른 아이와 이야기를 나눈다.", null),
            new Question(6, "다른 아이들과 적극적으로 어울리려고 한다.", null),
            new Question(7, "시키지 않아도 아는 사람에게 '안녕하세요?'라고 인사한다.", null),
            new Question(8, "친구에게 자기 집으로 와서 같이 놀자고 제안한다.", null)
    )),
    SELF_CARE(List.of(
            new Question(1, "혼자서 비누칠을 하여 손과 얼굴을 씻고 수건으로 닦는다.", null),
            new Question(2, "대소변을 볼 때 혼자서 옷을 벗고 입는다.", null),
            new Question(3, "밤에 자는 동안 대소변을 가린다.", null),
            new Question(4, "대소변을 본 후 화장실 물을 내린다.", null),
            new Question(5, "숟가락 등을 사용하여 뻥에 버터나 잼을 바른다.", null),
            new Question(6, "목욕한 후에 혼자서 몸을 수건으로 닦는다.", null),
            new Question(7, "윗옷의 지퍼를 혼자 끼워 올린다.", null),
            new Question(8, "옷이 더러워지면 스스로 알아서 갈아입는다.", null)
    ));
    ;

    private final List<Question> questions;
}
