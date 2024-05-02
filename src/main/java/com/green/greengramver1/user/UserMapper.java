package com.green.greengramver1.user;

import com.green.greengramver1.user.model.SingUpPostReq;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

@Mapper
public interface UserMapper {
    int postUser(SingUpPostReq p);
}
