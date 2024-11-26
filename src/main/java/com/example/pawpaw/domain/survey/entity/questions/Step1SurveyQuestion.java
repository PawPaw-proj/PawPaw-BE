package com.example.pawpaw.domain.survey.entity.questions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public enum Step1SurveyQuestion {
    GROSS_MOTOR_SKILLS(List.of(
            new Question(1, "뒤뚱거리며 달리다(이미 뒤뚱거리지 않고 자연스럽게 달린다면 ‘잘 할 수 있다’로 표시하세요).", null),
            new Question(2, "뒷걸음질할 수 있다.", null),
            new Question(3, "난간을 붙잡고 한 계단에 양발을 모은 뒤 한 발씩 한 발씩 계단을 올라간다(좌우 한발씩 번갈아 올라가는 것은 아님).", null),
            new Question(4, "정지되어 있는 공을 발로 찬다.", null),
            new Question(5, "쪼그리고 앉은 자세에서 아무것도 붙잡지 않고 혼자서 일어선다.", null),
            new Question(6, "난간을 붙잡고 한 계단에 양발을 모은 뒤 한 발씩 한 발씩 계단을 내려간다(좌우 한발씩 번갈아 내려가는 것은 아님).", null),
            new Question(7, "제자리에서 양발을 모아 동시에 깡충 뛴다.", null),
            new Question(8, "계단의 가장 낮은 층에 양발을 모아 바닥으로 뛰어내린다.", null)
    )),
    FINE_MOTOR_SKILLS(List.of(
            new Question(1, "(색)연필과 종이를 주면 선을 그리거나 낙서를 한다.", null),
            new Question(2, "블록을 두 개 쌓는다.", null),
            new Question(3, "손가락을 바르게 들어(음식물이 쏟아지지 않도록) 입에 가져간다.", null),
            new Question(4, "(색)연필의 중간 부분을 잡는다(색연필의 아래 부분을 잡으면 ‘잘 할 수 있다’로 표시하세요).", null),
            new Question(5, "블록을 네 개 쌓는다.", null),
            new Question(6, "블록 두 개 이상을 옆으로 나란히 줄을 세운다.", null),
            new Question(7, "손을 닿게 해주면 벽면 전등 스위치를 켜고 끈다.", null),
            new Question(8, "문손잡이를 돌려서 연다.", null)
    )),
    COGNITION(List.of(
            new Question(1, "다른 사람의 역할을 흉내 낸다.", null),
            new Question(2, "동그라미, 네모, 세모와 같은 간단한 도형 맞추기 판에 한 조각을 맞춘다.", null),
            new Question(3, "두 개의 연속적인 지시를 따른다.", null),
            new Question(4, "지시에 따라 신체 부위 세 곳을 가리킨다.", null),
            new Question(5, "그림책에 나온 그림과 같은 실제 사물을 찾는다.", null),
            new Question(6, "동물 그림과 동물 소리를 연결한다.", null),
            new Question(7, "두 개의 물건 중 큰 것과 작은 것을 구분한다.", null),
            new Question(8, "빨간, 노란, 파란 토막들을 섞어 놓으면 같은 색의 토막들끼리 분류한다.", null)
    )),
    LANGUAGE(List.of(
            new Question(1, "아이에게 익숙한 물건을 그리며 손으로 가리킨다.", null),
            new Question(2, "이름을 듣고 해당 동물의 그림이나 사진을 찾아낼 수 있다.", null),
            new Question(3, "‘엄마’, ‘아빠’ 외에 여섯 개 이상의 단어를 말한다.", null),
            new Question(4, "그림책 속에 등장하는 사물의 이름을 말한다.", null),
            new Question(5, "정확하지는 않더라도 두 단어로 된 문장을 따라 말한다.", null),
            new Question(6, "‘나’, ‘이것’, ‘저것’ 같은 대명사를 사용한다.", null),
            new Question(7, "다른 의미를 가진 두 개의 단어를 붙여 말한다.", null),
            new Question(8, "단어의 끝 어음을 높임으로써 질문의 형태로 말한다.", null)
    )),
    SOCIAL_SKILLS(List.of(
            new Question(1, "어른이 시키면 친숙한 어른들에게 인사를 한다.", null),
            new Question(2, "친숙한 사람의 전화 목소리를 구별한다.", null),
            new Question(3, "아이의 엄마(보호자)의 관심을 끌기 위해, 주변의 물건들이나 멀리 있는 사물을 손가락으로 가리킨다.", null),
            new Question(4, "‘아기(인형)에게 밥을 주세요.’하며 인형에게 먹이는 시늉을 한다.", null),
            new Question(5, "친숙한 사람이 슬퍼하면 다가가서 위로하려는 듯한 행동을 한다.", null),
            new Question(6, "사람들 앞에서 노래나 율동을 한다.", null),
            new Question(7, "즐겁게 하던 것을 못하게 하면, ‘싫다’라고 말하거나 동작으로 표현한다.", null),
            new Question(8, "어른이 시키면 ‘미안해,’ ‘고마워,’라는 말을 한다.", null)
    )),
    SELF_CARE(List.of(
            new Question(1, "어른이 이 닦는 것을 보고, 이 닦는 흉내를 낸다.", null),
            new Question(2, "어른을 따라서 손을 물에 담갔다가 얼굴을 문질러 씻는 흉내를 낸다.", null),
            new Question(3, "음식을 손으로 집어 먹지 않고 포크나 숟가락을 사용하여 먹는다.", null),
            new Question(4, "혼자서 모자를 쓰고 벗는다.", null),
            new Question(5, "신발 끈을 풀거나 느슨하게 해주면 혼자서 신발을 벗는다.", null),
            new Question(6, "손을 씻겨주고 나서 수건을 주면 혼자서 손의 물기를 닦는다.", null),
            new Question(7, "한 손으로 컵을 들고 마신다.", null),
            new Question(8, "먹을 수 없는 것과 먹을 수 없는 것(종이, 흙, 휴지 등)을 구별한다.", null)
    ));
    private final List<Question> questions;
}
