package com.green.greengramver1.common;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.UUID;

@Component //빈등록
@Getter
public class CustomFileUtils {
    /*
    @Value("${file.dir}")//dir은 디렉터 yaml의 파일을 담겠다 네이밍 맞추기
    private String uploadPath; //여기에 =주소값 하려면 \\로 슬래시 넣기(이스케이프 문자)
    //내용이 자바안에 없다. 장점: 값을 바꿨을 때 컴파일이 필요 없다(값을 참조할 것이기 때문)
    //객체화가 되고 나서야 값을 넣을 수 있기 때문에 final은 붙일 수 없다
    */

    private final String uploadPath;
    public CustomFileUtils(@Value("${file.directory}")String uploadPath){
        //yaml과 분류 명, 파일 이름이 일치
        //빈등록을 해야되는데 클래스를 객체화 하려고 하면 파라미터로 뭘 넣을 지 모름
        //그걸 알려주는 게 Value
        this.uploadPath=uploadPath;
    }
    public String makeFolders(String path){
        File folder= new File(uploadPath, path);//디렉토리도 파일도 파일 /는 알아서 넣어줌
        //이 경로를 가진 파일 ?
        folder.mkdirs();
        //mkdirs는 경로명 yaml경로에 따라 큰 폴더를 만들고 그 안에 폴더 또 생성
        //mkdir는 경로명이 정확하게 일치해야만 가능 (리턴타입 boolean)
        return folder.getAbsolutePath();
        //폴더 만들기 오른쪽 폴더 제너레이트 테스트->커스텀파일 유틸테스트 클래스가 생김
    }
//파일 이름이 여러가지 언어가 섞여 있어 에러를 유발 할 수 있기 때문에 영어와 숫자로 변경
    //UUID랜덤 파일 명
    public String makeRandomFileName(){
        return UUID.randomUUID().toString();
    }

    //파일 명에서 확장자 얻어오기
    public String getExt(String fileName){//d2.dt3ra.jpg
        int idx=fileName.indexOf(".");//내가 찾는 문자열이 있다면 양수값 없으면 음수값
        //indexOf는 왼쪽에서부터 찾음 <-> lastIndexOf 오른쪽부터 찾음
        return fileName.substring(idx);
    }

    //랜덤 파일 명 with 확장자 만들기
    public String makeRandomFileName(String fileName){//파라미터로 원본의 파일명 날아옴
        int idx=fileName.lastIndexOf(".");
        String ext=fileName.substring(idx);
        String resultFileName=UUID.randomUUID().toString()+ext;
        return resultFileName;
    }
}
