package com.chenyl.handle;

import com.chenyl.domain.Result;
import com.chenyl.exception.GirlException;
import com.chenyl.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by cookily on 2017/7/29.
 */
@ControllerAdvice
public class ExceptionHandle {
    private static final Logger logger= LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class) //申明捕获哪个类
    @ResponseBody
    public Result handle(Exception e) {
        if (e instanceof GirlException) {
            GirlException girlException = (GirlException) e;
            return ResultUtil.error(girlException.getCode(), girlException.getMessage());
        } else {
            logger.error("【系统异常】={}",e);
            return ResultUtil.error(-1, "未知错误");
        }
    }
}
