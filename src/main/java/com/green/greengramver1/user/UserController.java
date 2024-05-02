package com.green.greengramver1.user;

import com.green.greengramver1.common.model.ResultDto;
import com.green.greengramver1.user.model.SingUpPostReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService service;

    @PostMapping
    public ResultDto<Integer> postUser(@RequestPart(required = false) MultipartFile pic,
                                       @RequestPart SingUpPostReq p){
        //@RequestPart(required = false) 사진을 무조건 보내야 한다
        log.info("pic: {}", pic);
        log.info("p: {}", p);

        int result=service.postUser(pic, p);
        //프로필 이미지파일 처리
        return ResultDto.<Integer>builder()
                .status(HttpStatus.OK)
                .resultMsg("회원가입 성공")
                .resultData(result)
                .build();

    }
}
