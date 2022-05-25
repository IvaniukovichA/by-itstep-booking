package com.bookApp.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BaseResponse {
    private Integer code;
    private String message;

    public BaseResponse(Integer code,
                        String message
                        ) {
        this.code = code;
        this.message = message;
    }
}
