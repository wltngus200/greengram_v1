package com.green.greengramver1.feed.model;

import lombok.*;

import java.util.List;

@Setter
@Getter
public class FeedGetRes {
    private long feedId;
    private long writerId;
    private String writerNm;
    private String writerPic;
    private String contents;
    private String location;
    private String createdAt;

    private List<String> pics;
    //피드별 사진 개수 제각각 n+1이슈 //현재 단계에선 Select분리
}
