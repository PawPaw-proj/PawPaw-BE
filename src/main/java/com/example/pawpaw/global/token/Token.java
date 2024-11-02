package com.example.pawpaw.global.token;

import java.util.Date;

public record Token(String token, Date expiresIn) {
}
