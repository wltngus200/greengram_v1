package com.green.greengramver1.user;

import com.green.greengramver1.user.model.SingUpPostReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper mapper;

    public int postUser(MultipartFile pic, SingUpPostReq p){
        //이미지 처리를 위한 로직은 아직
        return mapper.postUser(p); //매퍼는 파일명을 데이터 베이스에 저장
    }
}
