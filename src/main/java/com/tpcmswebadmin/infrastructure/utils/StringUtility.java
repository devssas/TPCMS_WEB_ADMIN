package com.tpcmswebadmin.infrastructure.utils;

public class StringUtility {

    private StringUtility() {
    }

    public static String concat(String... strings) {
        StringBuilder stringBuilder = new StringBuilder();

        for(String row : strings) {
            stringBuilder.append(row);
        }

        return stringBuilder.toString();
    }


}
