package com.example.pawpaw.domain.auth.token;

import java.util.Date;

public record Token(String token, Date expiresIn) {
}
