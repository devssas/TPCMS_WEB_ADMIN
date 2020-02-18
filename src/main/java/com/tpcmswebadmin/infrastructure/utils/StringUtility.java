package com.tpcmswebadmin.infrastructure.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StringUtility {

    public static String concat(String... strings) {
        StringBuilder stringBuilder = new StringBuilder();

        for(String row : strings) {
            stringBuilder.append(row);
        }

        return stringBuilder.toString();
    }

    public static String makeFullName(String... strings) {
        StringBuilder stringBuilder = new StringBuilder();

        for(String row : strings) {
            stringBuilder.append(row).append(" ");
        }

        return stringBuilder.toString();
    }
}
