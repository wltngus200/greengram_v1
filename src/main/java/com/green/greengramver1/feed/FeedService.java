package com.green.greengramver1.feed;

import com.green.greengramver1.common.CustomFileUtils;
import com.green.greengramver1.feed.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FeedService {
    private final FeedMapper mapper;//주소값을 담을 멤버필드
    private final CustomFileUtils customFileUtils; //@Component로 빈등록 되어있음 DI

    @Transactional
    public FeedPostRes postFeed(List<MultipartFile> pics, FeedPostReq p){ //Long= PK값
        //pics에 저장될 수 있는 (주소)값의 수 : 0개 1개 =변수이기 때문 list라는 주소값을 1개 저장

        int result=mapper.postFeed(p);
        FeedPicPostDto picDto=FeedPicPostDto.builder().feedId(p.getFeedId()).build();
        try{
            String path=String.format("feed/%d", p.getFeedId());
            customFileUtils.makeFolders(path);
            //long + String 객체화 할 때 생성

            //넘어온 사진들 저장 picDto에 데이터를 계속 담음->테이블에 insert하기 위해
            for(MultipartFile pic:pics) {//파일개수만큼 반복+랜덤이름+올리기까지
                String saveFileName = customFileUtils.makeRandomFileName(pic);
                picDto.getFileNames().add(saveFileName);//파일 네임에 집어넣음 .add
                String target = String.format("%s/%s", path, saveFileName);
                customFileUtils.transferTo(pic, target);
            }
            int affectedRowsPics= mapper.postFeedPics(picDto);
        }catch(Exception e){
            log.error("{}", e);
            e.printStackTrace();
            throw new RuntimeException("Feed 등록 오류");
        }
        return FeedPostRes.builder()
                .feedId(p.getFeedId())
                .pics(picDto.getFileNames())
                .build();//리턴의 이유 업로드 하자마자 내가 바로 삭제 처리 등을 하고 싶다면 필요, 프론트에서 필요하기 때문
    }
    public List<FeedGetRes> getFeed(FeedGetReq p){
        List<FeedGetRes> list=mapper.getFeed(p);
        for(FeedGetRes res:list){
            List<String> pics=mapper.getFeedPicsByFeedId(res.getFeedId());
            res.setPics(pics);
        }
        return list;
    }
}
