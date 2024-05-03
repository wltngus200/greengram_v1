package com.green.greengramver1.feed;

import com.green.greengramver1.common.model.ResultDto;
import com.green.greengramver1.feed.model.FeedPostReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/feed")
@Tag(name="FEED", description = "FEED CRUD")
public class FeedController {
    private final FeedService service;

    @PostMapping
    public ResultDto<Long> postFeed(@RequestPart List<MultipartFile> pics, @RequestPart FeedPostReq p){
        long result = 0L;

        return ResultDto.<Long>builder()
                .status(HttpStatus.OK)
                .resultMsg("")
                .resultData(result)
                .build();
        //피드 인서트 1번 사진 인서트 1번
        //My batis 반복문
    }

}
