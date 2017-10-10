package com.gzx.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.apache.log4j.Logger;

/**
 * AOP统一处理请求日志
 * Created by Administrator on 5/20/2017.
 */
@Aspect
@Component
public class HttpAspect {

    private final static Logger logger = Logger.getLogger(HttpAspect.class);

    @Pointcut("execution(public * com.gzx.controller.CalculatorController.*(..))")
    public void log(){
    }
    //在请求后记录日志
    @After("log()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        javax.servlet.http.HttpServletRequest request = attributes.getRequest();
        logger.info("url="+request.getRequestURI());
        logger.info("method="+request.getMethod());
        logger.info("ip="+request.getRemoteAddr());
        logger.info("class_method="+joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
    }

}
