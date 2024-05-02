package com.green.greengramver1.common.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ResultDto<T> {
    private HttpStatus status;
    private String resultMsg;
    private T resultData;
}
