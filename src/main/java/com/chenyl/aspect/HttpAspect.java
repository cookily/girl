package com.chenyl.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by cookily on 2017/7/29.
 */
@Aspect
@Component
public class HttpAspect {
    private static final Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    //@Component 引入到spring容器

    /*@Before("execution(public * com.chenyl.controller.GirlController.findGirlList(..))")
    //两个点表示只要是这个方法，任何参数都会被拦截*/
   /* @Before("execution(public * com.chenyl.controller.GirlController.*(..))")
    /*//*表示这个Controller的所有方法  任何参数都会被拦截*/

    @Pointcut("execution(public * com.chenyl.controller.GirlController.*(..))")
    public void log() {
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //url
        logger.info("url={}", request.getRequestURL());
        //method (请求方式)
        logger.info("method={}", request.getMethod());
        //ip
        logger.info("ip={}", request.getRemoteAddr());
        //类方法 (类名.方法名)
        logger.info("class_method={}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        //参数
        logger.info("args={}", joinPoint.getArgs());
        // logger.info("111111111111111");
    }

    @After("log()")
    public void doAfter() {
        logger.info("222222222222");
    }

    @AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterReturning(Object object) {
        //获取后端数据后
        logger.info("response={}", object.toString());
    }


}
