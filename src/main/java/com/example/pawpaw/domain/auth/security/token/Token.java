package com.example.pawpaw.domain.auth.security.token;

import java.util.Date;

public record Token(String token, Date expiresIn) {
}
