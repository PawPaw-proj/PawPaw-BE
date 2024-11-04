package com.example.pawpaw.global.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // 회원
    DUPLICATED_EMAIL(HttpStatus.CONFLICT, "이미 존재하는 회원입니다."),
    NOT_FOUND_MEMBER(HttpStatus.NOT_FOUND, "존재하지 않는 회원입니다."),

    // 자식
    NOT_FOUND_CHILD(HttpStatus.NOT_FOUND, "존재하지 않는 자식입니다."),
    BAD_REQUEST_CHILD(HttpStatus.BAD_REQUEST, "잘못된 자식 요청입니다."),

    //토큰
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다."),
    INVALID_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "유효하지 않은 Refresh 토큰입니다."),

    // 공통
    BAD_REQUEST_RESOURCE(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    NOT_FOUND_RESOURCE(HttpStatus.NOT_FOUND, "찾을 수 없는 리소스입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 에러입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
