package com.tpcmswebadmin.service.images.controller;

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
                                                      HttpServletRequest httpServletRequest) {
        log.info(fileBase64);
        log.info(fileName);
        LoginUserDo loginUserDo = LoginUserDo.makeLoginUser(httpServletRequest);

        ImageDto imageDto = new ImageDto();
        return new ResponseEntity<>(imageService.save(imageDto, loginUserDo), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("image/delete")
    public ResponseEntity<ImageResponseDto> deleteImage(@RequestParam("fileName") String fileName, HttpServletRequest httpServletRequest) {
        LoginUserDo loginUserDo = LoginUserDo.makeLoginUser(httpServletRequest);
        ImageDto imageDto = new ImageDto();

        return new ResponseEntity<>(imageService.delete(imageDto, loginUserDo), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("image/update")
    public ResponseEntity<ImageResponseDto> updateImage(@RequestBody ImageDto imageDto, HttpServletRequest httpServletRequest) {
        LoginUserDo loginUserDo = LoginUserDo.makeLoginUser(httpServletRequest);

        return new ResponseEntity<>(imageService.update(imageDto, loginUserDo), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("image")
    public List<ImageDto> getImages(HttpServletRequest httpServletRequest) {
        return imageService.getAll();
    }
}
