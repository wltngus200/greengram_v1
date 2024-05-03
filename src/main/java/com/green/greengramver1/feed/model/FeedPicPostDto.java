package com.green.greengramver1.feed.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder //어레이리스트의 객체주소값 담아주지 X -> 아래 에노테이션
public class FeedPicPostDto {
    private long feedId;
    @Builder.Default
    private List<String> fileNames=new ArrayList();

}
