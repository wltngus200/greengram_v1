package com.green.greengramver1.user;

import com.green.greengramver1.common.CustomFileUtils;
import com.green.greengramver1.common.model.SignInRes;
import com.green.greengramver1.user.model.SignInPostReq;
import com.green.greengramver1.user.model.SingUpPostReq;
import com.green.greengramver1.user.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper mapper;
    private final CustomFileUtils customFileUtils;//DI

    @Transactional//문제가 없다고 판단되면 insert
    public int postSignUp(MultipartFile pic, SingUpPostReq p){
        String saveFileName= customFileUtils.makeRandomFileName(pic);
        p.setPic(saveFileName);
        String hashedPw= BCrypt.hashpw(p.getUpw(),BCrypt.gensalt());
        p.setUpw(hashedPw);
        int result= mapper.postUser(p); //매퍼는 파일명을 데이터 베이스에 저장

        if(pic==null){return result;}
        try{
            String path=String.format("user/%d", p.getUserId());
            customFileUtils.makeFolders(path);
            String target=String.format("%s/%s", path,saveFileName);
            customFileUtils.transferTo(pic, target);
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("파일 오류~"); //에러가 터지면 롤백
        }
        return result;
    }
    public SignInRes postSignIn(SignInPostReq p){
        //아이디, 비밀번호 확인
        User user=mapper.getUserId(p.getUid());
        if(user==null){throw new RuntimeException("아이디를 확인해주세요");}
        else if(user.getUpw().equals(p.getUpw())) {throw new RuntimeException("비밀번호를 확인해주세요");}
        return SignInRes.builder()
                .userId(user.getUserId())
                .nm(user.getNm())
                .pic(user.getPic())
                .build();
    }
}
