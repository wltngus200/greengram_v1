package com.green.greengramver1.feed;

import com.green.greengramver1.common.model.ResultDto;
import com.green.greengramver1.feed.model.FeedGetReq;
import com.green.greengramver1.feed.model.FeedGetRes;
import com.green.greengramver1.feed.model.FeedPostReq;
import com.green.greengramver1.feed.model.FeedPostRes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/feed")
@Tag(name="FEED", description = "FEED CRUD")
public class FeedController {
    private final FeedService service;

    @PostMapping
    public ResultDto<FeedPostRes> postFeed(@RequestPart List<MultipartFile> pics, @RequestPart FeedPostReq p){
        FeedPostRes result = service.postFeed(pics,p);

        return ResultDto.<FeedPostRes>builder()
                .status(HttpStatus.OK)
                .resultMsg("")
                .resultData(result)
                .build();
        //피드 인서트 1번 사진 인서트 1번
        //My batis 반복문
    }
    @GetMapping
    @Operation(summary="Feed 리스트", description = "loginUserId는 로그인한 사용자의 PK")
    public ResultDto<List<FeedGetRes>> getFeed(@ParameterObject @ModelAttribute FeedGetReq p){
        List<FeedGetRes> result = service.getFeed(p);

        return ResultDto.<List<FeedGetRes>>builder()
                .status(HttpStatus.OK)
                .resultMsg(HttpStatus.OK.toString())
                .resultData(result)
                .build();
    }

}
