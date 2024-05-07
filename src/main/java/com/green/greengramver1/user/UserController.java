package com.green.greengramver1.user;

import com.green.greengramver1.common.model.ResultDto;
import com.green.greengramver1.common.model.SignInRes;
import com.green.greengramver1.user.model.SignInPostReq;
import com.green.greengramver1.user.model.SignUpPostReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/user")

@Tag(name="유저 컨트롤러", description="유저 CRUD, sign-in, sign-out")
public class UserController {
    private final UserService service;

    @PostMapping("sign-up")
    @Operation(summary = "회원가입", description = "프로필 사진은 필수가 아님")
    public ResultDto<Integer> postUser(@RequestPart(required = false) MultipartFile pic, //service에 로직이 없으면 메모리에 파일이 있다
                                       @RequestPart SignUpPostReq p){
        //@RequestPart(required = false) 사진을 무조건 보내지 않아도 된다
        //파일을 보낸다
        log.info("pic: {}", pic);
        log.info("p: {}", p);
        //랜덤한 파일 명을 DB에 저장

        int result=service.postSignUp(pic, p);
        //프로필 이미지파일 처리
        return ResultDto.<Integer>builder()
                .status(HttpStatus.OK)
                .resultMsg("회원가입 성공")
                .resultData(result)
                .build();
    }
    //사진 이름 PK값
    @PostMapping("sign-in")
    @Operation(summary = "인증처리", description = "")
    public ResultDto<SignInRes> postSignIn(@RequestBody SignInPostReq p){
        log.info("p: {}", p);
        SignInRes result=service.postSignIn(p);

        return ResultDto.<SignInRes>builder()
                .status(HttpStatus.OK)
                .resultMsg("인증 성공")
                .resultData(result)
                .build();
    }
}
