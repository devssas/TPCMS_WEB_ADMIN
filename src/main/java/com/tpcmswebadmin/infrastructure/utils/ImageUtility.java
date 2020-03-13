package com.tpcmswebadmin.infrastructure.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ImageUtility {

    public static String convertToBase64image(byte[] image) {
        if(image == null)
            return null;

        return Base64.getEncoder().encodeToString(image);
    }

    public static String convertToBase64image(MultipartFile image) throws IOException {
        if(image == null)
            return null;

        ByteArrayInputStream stream = new ByteArrayInputStream(image.getBytes());
        String myString = IOUtils.toString(stream, "UTF-8");

        return myString;
    }
}
