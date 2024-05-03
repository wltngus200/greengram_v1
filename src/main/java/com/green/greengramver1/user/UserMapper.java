package com.green.greengramver1.user;

import com.green.greengramver1.common.model.SignInRes;
import com.green.greengramver1.user.model.SignInPostReq;
import com.green.greengramver1.user.model.SingUpPostReq;

import com.green.greengramver1.user.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

@Mapper
public interface UserMapper {
    int postUser(SingUpPostReq p);
    SignInRes postSignIn(SignInPostReq p);
    User getUserId(String p);
}
