package com.web.upload.config;

import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @author Lcp
 * @version 1.0
 * @date 2023/5/15 13:29
 */
@Slf4j
@Aspect
@Component
public class AspectLog {

    @Pointcut("execution(public * com.web.upload.controller.*.*(..))")
    public void controllerLog() {
    }

    @Around("controllerLog()")
    public Object around(ProceedingJoinPoint pjp) {
        String methodName = pjp.getSignature().toShortString();
        List<Object> argList = Arrays.asList(pjp.getArgs());
        String args = JSONObject.toJSONString(argList);
        long l = System.currentTimeMillis();
        Object result = null;
        try {
            log.info("【{}】方法执行, args:{}", methodName, args);
            result = pjp.proceed();
            return result;
        } catch (Throwable t) {
            log.error("方法执行异常... {}", methodName, t);
            throw new RuntimeException(t);
        } finally {
            log.info("【{}】 方法执行后,args:{}, result:{}, 耗时：{}ms", methodName, args, JSONObject.toJSONString(result), System.currentTimeMillis() - l);
        }
    }

}
