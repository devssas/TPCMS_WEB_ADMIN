package com.tpcmswebadmin.infrastructure.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Base64;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ImageUtility {

    public static String convertToBase64image(byte[] image) {
        return Base64.getEncoder().encodeToString(image);
    }
}
