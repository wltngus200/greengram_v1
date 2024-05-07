package com.green.greengramver1.feed;

import com.green.greengramver1.feed.model.FeedGetReq;
import com.green.greengramver1.feed.model.FeedGetRes;
import com.green.greengramver1.feed.model.FeedPicPostDto;
import com.green.greengramver1.feed.model.FeedPostReq;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Mapper
public interface FeedMapper {
    int postFeed(FeedPostReq p); //영향받은 행 값
    int postFeedPics(FeedPicPostDto p);
    List<FeedGetRes> getFeed(FeedGetReq p);
    List<String> getFeedPicsByFeedId(long feedId);
}
