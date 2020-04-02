package com.tpcmswebadmin.service.images.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageDto {

    private String fileBase64;

    private String fileName;

    private String fileType;

    private Long fileSize;

    private String key;

    private String pageName;

}
