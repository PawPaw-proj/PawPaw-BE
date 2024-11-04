package com.example.pawpaw.global.util;

import java.time.LocalDate;
import java.time.Period;

public class DateUtils {

    /**
     * 두 날짜 사이의 개월 수를 계산하는 유틸리티 메서드
     */
    public static int calculateMonthsBetween(LocalDate startDate, LocalDate endDate) {
        Period period = Period.between(startDate, endDate);
        return period.getYears() * 12 + period.getMonths();
    }
}

