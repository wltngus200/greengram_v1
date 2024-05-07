package com.green.greengramver1.user;

import com.green.greengramver1.common.model.SignInRes;
import com.green.greengramver1.user.model.SignInPostReq;
import com.green.greengramver1.user.model.SignUpPostReq;

import com.green.greengramver1.user.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int postUser(SignUpPostReq p);
    SignInRes postSignIn(SignInPostReq p);
    User getUserId(String p);
}
