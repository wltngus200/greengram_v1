package com.green.greengramver1.feed;

import com.green.greengramver1.common.CustomFileUtils;
import com.green.greengramver1.feed.model.FeedPicPostDto;
import com.green.greengramver1.feed.model.FeedPostReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedService {
    private final FeedMapper mapper;//주소값을 담을 멤버필드
    private final CustomFileUtils customFileUtils; //@Component로 빈등록 되어있음 DI

    @Transactional
    public long postFeed(List<MultipartFile> pics, FeedPostReq p){ //Long= PK값
        int result=mapper.postFeed(p);
        try{
            String path=String.format("feed?/%d", p.getFeedId());
            customFileUtils.makeFolders(path);
            //long + String 객체화 할 때 생성
            FeedPicPostDto picDto= FeedPicPostDto.builder().feedId(p.getFeedId()).build();
            for(MultipartFile pic:pics) {//파일개수만큼 반복+랜덤이름+올리기까지
                String saveFileName = customFileUtils.makeRandomFileName(pic);
                picDto.getFileNames().add(saveFileName);//파일 네임에 집어넣음 .add
                String target = String.format("%s%s", path, saveFileName);
                customFileUtils.transferTo(pic, target);
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("Feed 등록 오류");
        }
        return p.getFeedId();
    }
}
