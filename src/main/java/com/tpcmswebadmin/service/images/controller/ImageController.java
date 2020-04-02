package com.tpcmswebadmin.service.images.controller;

import com.google.common.collect.ImmutableMap;
import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.service.images.domain.ImageDto;
import com.tpcmswebadmin.service.images.domain.ImageResponseDto;
import com.tpcmswebadmin.service.images.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "image/save")
    public ResponseEntity<ImageResponseDto> saveImage(@RequestParam(value = "fileBase64", required = false) String fileBase64,
                                                      @RequestParam(value = "fileName", required = false) String fileName,
                                                      @RequestParam(value = "fileType", required = false) String fileType,
                                                      @RequestParam(value = "fileSize", required = false) Long fileSize,
                                                      @RequestParam(value = "key", required = false) String key,
                                                      @RequestParam(value = "pageName", required = false) String pageName,
                                                      HttpServletRequest httpServletRequest) {

        LoginUserDo loginUserDo = LoginUserDo.makeLoginUser(httpServletRequest);

        ImageDto imageDto = ImageDto.builder()
                .fileBase64(fileBase64)
                .fileName(fileName)
                .fileSize(fileSize)
                .fileType(fileType)
                .key(key)
                .pageName(pageName)
                .build();

        return new ResponseEntity<>(imageService.save(imageDto, loginUserDo), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("image/delete")
    public ResponseEntity<ImageResponseDto> deleteImage(@RequestParam("fileName") String fileName,
                                                        @RequestParam(value = "pageName", required = false) String pageName,
                                                        HttpServletRequest httpServletRequest) {
        LoginUserDo loginUserDo = LoginUserDo.makeLoginUser(httpServletRequest);
        return new ResponseEntity<>(imageService.delete(fileName, pageName, loginUserDo), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("image")
    public ImmutableMap<String, Map<String, List<ImageDto>>> getImages(HttpServletRequest httpServletRequest) {
        return imageService.getAll();
    }
}
