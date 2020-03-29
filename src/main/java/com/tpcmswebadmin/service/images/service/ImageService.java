package com.tpcmswebadmin.service.images.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.service.images.domain.ImageDto;
import com.tpcmswebadmin.service.images.domain.ImageResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageService {

    private static final Cache<String, ImageDto> LOCAL_CACHE = CacheBuilder.newBuilder().expireAfterWrite(30, TimeUnit.MINUTES).build();

    public ImageResponseDto save(ImageDto imageDto, LoginUserDo loginUserDo) {
        String key = loginUserDo.getLoginOfficersCode() + ":" + loginUserDo.getLoginOfficerUnitNumber();

        if (LOCAL_CACHE.getIfPresent(key) == null) {
            LOCAL_CACHE.put(key, imageDto);

            log.info("Image is stored for user {}", loginUserDo.getLoginOfficersCode());
        }
        return ImageResponseDto.builder().status(true).message("lalaala").key(key).build();
    }

    public ImageResponseDto delete(ImageDto imageDto, LoginUserDo loginUserDo) {
      /*  if (LOCAL_CACHE.getIfPresent(imageDto.getKey()) == null) {
            return ImageResponseDto.builder().status("false").key(null).build();
        } else {
            LOCAL_CACHE.invalidate(imageDto.getKey());
            log.info("Image is deleted for user {}", loginUserDo.getLoginOfficersCode());


        } */

        return ImageResponseDto.builder().status(true).key(imageDto.getKey()).build();
    }

    public ImageResponseDto update(ImageDto imageDto, LoginUserDo loginUserDo) {
        if (LOCAL_CACHE.getIfPresent(imageDto.getKey()) == null) {
            return ImageResponseDto.builder().status(true).key(null).build();
        } else {
            LOCAL_CACHE.put(imageDto.getKey(), imageDto);
            log.info("Image is updated for user {}", loginUserDo.getLoginOfficersCode());

            return ImageResponseDto.builder().status(true).key(imageDto.getKey()).build();
        }
    }

    public List<ImageDto> getAll() {
        List<ImageDto> list = (List<ImageDto>) LOCAL_CACHE.getAllPresent(LOCAL_CACHE.asMap().keySet());

        return list;
    }
}
