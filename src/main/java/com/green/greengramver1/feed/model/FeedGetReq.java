package com.green.greengramver1.feed.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.green.greengramver1.common.GlobalConst;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedGetReq {
    private int page;
    private long loginedUserId;

    //피드는 인피니티 스크롤이기 때문에 숫자는 필요 X
    @JsonIgnore
    private int startIdx;
    @JsonIgnore
    private int len;

    public void setPage(int page) {
        this.page = page;
        this.len= GlobalConst.FEED_PAGE_ITEM_LEN;
        this.startIdx=(this.page-1)*this.len;
    }
}
