package com.green.greengramver1.common;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test") //yaml파일에서 프로필 설정 가능 ---구분자
class CustomFileUtilsTest {

    @Autowired//주소값담김 //DI를 해달라는 명령어
    CustomFileUtils utils;

    @Test
    void getUploadPath() {
        String result= utils.makeFolders("ddd3");//폴더이름
        System.out.println("result" +result);//결과
    }
    @Test
    void makeRandomFileName(){
        String result=utils.makeRandomFileName();
        System.out.println("makeRandomFileName: "+result);
    }
    @Test
    void getExt(){
        String ext=utils.getExt("adAERAGAekanjdnj.jpg");
        System.out.println(ext);
    }
}