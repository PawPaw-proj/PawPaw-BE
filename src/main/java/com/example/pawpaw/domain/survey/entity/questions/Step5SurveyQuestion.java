package com.example.pawpaw.domain.survey.entity.questions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public enum Step5SurveyQuestion {
    GROSS_MOTOR_SKILLS(List.of(
            new Question(1, "굴러가는 공을 발로 찬다.", null),
            new Question(2, "2미터 거리에서 테니스공 크기의 공을 던지면 두 손으로 잡는다.", "https://i.ibb.co/9ZvSvGB/5-2.png"),
            new Question(3, "공을 바닥에 한 번 튀길 수 있다.", null),
            new Question(4, "무릎 아래 높이로 매어져 있는 줄을 뛰어 넘을 수 있다.", null),
            new Question(5, "까끔발로 좌우 한발씩 번갈아 뛴다.", null),
            new Question(6, "줄넘기를 1회 한다.", null),
            new Question(7, "두 손으로 한 발을 잡고 닭새우 자세로 세 번 이상 점프한다.", null),
            new Question(8, "굴러오는 공을 발로 찰 수 있다.", null)
    )),
    FINE_MOTOR_SKILLS(List.of(
            new Question(1, "엄지손가락과 다른 네 손가락을 차례로 맞붙게 한다(반대편 네 손가락이 아니고 같은 손이어야 한다).", "https://i.ibb.co/KGtsBnp/5-1.png"),
            new Question(2, "삼각형이 그려진 것을 보여주면 삼각형을 그린다(그리는 과정의 시험을 보지 않고도 그려야 한다).", null),
            new Question(3, "종이에 그려진 동그라미를 가위로 오린다.", null),
            new Question(4, "간단한 자동차 모양을 흉내 내어 그린다.", null),
            new Question(5, "주전자나 물병의 물을 거의 흘리지 않고 컵에 붓는다.", null),
            new Question(6, "마름모가 그려진 것을 보여주면 마름모를 그린다.", null),
            new Question(7, "집, 나무, 동물 같은 사물을 알아볼 수 있게 그린다.", null),
            new Question(8, "리본 묶기를 한다.", null)
    )),
    COGNITION(List.of(
            new Question(1, "자신의 왼쪽과 오른쪽을 구분할 수 있다.", null),
            new Question(2, "요일을 순서대로 알고 있다.", null),
            new Question(3, "100원보다 500원짜리 동전이 더 가치가 있다는 것을 안다.", null),
            new Question(4, "자기 생일을 알고 있다.", null),
            new Question(5, "11부터 20까지 숫자 중에서 하나를 불러주면 받아쓴다.", null),
            new Question(6, "한 자리 숫자 뺄셈을 한다.", null),
            new Question(7, "엄마, 아빠, 혹은 보호자의 전화번호를 기억한다.", null),
            new Question(8, "달력에서 오늘 날짜(월, 일)를 바르게 가리킨다.", null)
    )),
    LANGUAGE(List.of(
            new Question(1, "친숙한 단어의 반대말을 말한다. (예: 덥다 → 춥다, 크다 → 작다)", null),
            new Question(2, "간단한 농담이나 빗대어서 하는 말의 뜻을 알아차린다.", null),
            new Question(3, "단어의 뜻을 물어보면 설명한다. (예: “신문이 뭐야?”라는 질문에 “백이 나갈 때 보는 거요.” 와 같은 대답을 할 수 있다.)", null),
            new Question(4, "‘만약~라면 무슨 일이 일어날까?’와 같이 가상의 상황에 대한 질문에 대답한다. (예: “동생이 있었으면 어떨까?”)", null),
            new Question(5, "끝말잇기를 한다.", null),
            new Question(6, "자기 이름이나 2~4개의 글자로 된 단어를 보지 않고 쓸 수 있다. (예: 동생, 신호등, 대한민국)", null),
            new Question(7, "간단한 농담을 말한다.", null),
            new Question(8, "간단한 속담을 이해하고 사용할 수 있다. (예: “우째 뗏기”와 같은 속담을 적절하게 사용한다.)", null)
    )),
    SOCIAL_SKILLS(List.of(
            new Question(1, "처음 만난 또래와 쉽게 어울린다.", null),
            new Question(2, "또래와 함께 체력내 규칙을 알아야 할 수 있는 놀이를 한다. (예: 훌라훌라, 보드게임)", null),
            new Question(3, "자기 생각을 이야기하고 다른 아이의 말을 귀 기울여 듣는다.", null),
            new Question(4, "게임을 하는 방법에 대해 다른 아이와 이야기를 나눈다.", null),
            new Question(5, "다른 아이들과 적극적으로 어울리려고 한다.", null),
            new Question(6, "시키지 않아도 아는 사람에게 “안녕하세요?”라고 인사한다.", null),
            new Question(7, "친구에게 자기 집으로 와서 같이 놀자고 하거나, 무슨 놀이를 하자고 제안한다.", null),
            new Question(8, "친구나 가족에게 전화를 건다(집 전화나 휴대전화 모두 해당).", null)
    )),
    SELF_CARE(List.of(
            new Question(1, "숟가락 등을 사용하여 뻥에 버터나 잼을 바른다.", null),
            new Question(2, "목욕한 후에 혼자서 몸을 수건으로 닦는다.", null),
            new Question(3, "윗옷의 지퍼를 혼자 끼워 올린다.", null),
            new Question(4, "옷이 더러워지면 스스로 알아서 갈아입는다.", null),
            new Question(5, "우유 종이팩을 어른의 도움 없이도 혼자서 연다.", null),
            new Question(6, "일정 기간(일주일 정도) 동안 집안일 하나를 맡아서 한다. (예: 수저 놓기, 장난감 정리 등)", null),
            new Question(7, "머리 감기를 제외하면 혼자서 목욕을 한다.", null),
            new Question(8, "대변을 본 뒤 휴지를 사용하여 혼자서 뒤처리를 할 수 있다.", null)
    ));

    private final List<Question> questions;
}
