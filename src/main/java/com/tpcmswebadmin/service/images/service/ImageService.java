package com.tpcmswebadmin.service.images.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.ImmutableMap;
import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.service.images.domain.ImageDto;
import com.tpcmswebadmin.service.images.domain.ImageResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageService {

    private static final Cache<String, Map<String, List<ImageDto>>> LOCAL_CACHE = CacheBuilder.newBuilder().expireAfterWrite(30, TimeUnit.MINUTES).build();

    public ImageResponseDto save(ImageDto imageDto, LoginUserDo loginUserDo) {
        String key = makeKey(loginUserDo);
        imageDto.setFileBase64(formatImage(imageDto.getFileBase64()));

        if (LOCAL_CACHE.getIfPresent(key) == null) {
            Map<String, List<ImageDto>> saveMap = new HashMap<>();
            saveMap.put(imageDto.getPageName(), Collections.singletonList(imageDto));

            LOCAL_CACHE.put(key, saveMap);

            log.info("Image is stored for user {}", loginUserDo.getLoginOfficersCode());
        } else {
            Map<String,  List<ImageDto>> resultData = LOCAL_CACHE.getIfPresent(key);
            List<ImageDto> imageDtoList = new ArrayList<>();
            if (resultData == null) {
                resultData = new HashMap<>();
            }

            if (resultData.size() > 0) {
                imageDtoList = resultData.get(imageDto.getPageName());
                imageDtoList.add(imageDto);
            } else {
                imageDtoList.add(imageDto);
            }

            resultData.put(imageDto.getPageName(), imageDtoList);

            LOCAL_CACHE.put(key, resultData);

            log.info("Image is stored for user {}", loginUserDo.getLoginOfficersCode());
        }
        return ImageResponseDto.builder().status(true).message("lalaala").key(key).build();
    }

    public ImageResponseDto delete(String fileName, String pageName, LoginUserDo loginUserDo) {
        String key = makeKey(loginUserDo);

        if (LOCAL_CACHE.getIfPresent(key) == null) {
            return ImageResponseDto.builder().status(false).key(null).build();
        } else {
            Map<String, List<ImageDto>> resultData = LOCAL_CACHE.getIfPresent(key);

            if (resultData == null) {
                return ImageResponseDto.builder().status(true).key(key).build();
            }
            resultData.remove(pageName);

            log.info("Image is deleted for user {}", loginUserDo.getLoginOfficersCode());
        }

        return ImageResponseDto.builder().status(true).key(key).build();
    }

    public ImmutableMap<String, Map<String, List<ImageDto>>> getAll() {
        return LOCAL_CACHE.getAllPresent(LOCAL_CACHE.asMap().keySet());
    }

    public List<ImageDto> getByKeyAndPage(String key, String pageName) {
        if (LOCAL_CACHE.getIfPresent(key) == null)
            return Collections.emptyList();

        Map<String, List<ImageDto>> resultData = LOCAL_CACHE.getIfPresent(key);

        if (resultData == null)
            return Collections.emptyList();
        else
            return Objects.requireNonNull(LOCAL_CACHE.getIfPresent(key)).get(pageName);
    }

    public String makeKey(LoginUserDo loginUserDo) {
        return loginUserDo.getLoginOfficersCode() + ":" + loginUserDo.getLoginOfficerUnitNumber();
    }

    private String formatImage(String image) {
        return image.substring(image.lastIndexOf(",") + 1);
    }
}
