package com.chenyl.exception;

import com.chenyl.enums.ResultEnum;

/**
 * Created by cookily on 2017/7/29.
 */
public class GirlException extends RuntimeException {

    private Integer code;


    public GirlException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
