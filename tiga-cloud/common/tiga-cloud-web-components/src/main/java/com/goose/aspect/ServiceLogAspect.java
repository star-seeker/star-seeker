package com.goose.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author zhangyoubao
 * @version 2021/4/29
 */
@Component
@Aspect
@Slf4j
public class ServiceLogAspect {

    @Around("execution(* com.goose..*.impl..*.*(..))")
    public Object recordTimeLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("====== service start {}.{} ======",
                joinPoint.getTarget().getClass(), joinPoint.getSignature().getName());

        long begin = System.currentTimeMillis();
        Object result = joinPoint.proceed();

        long end = System.currentTimeMillis();
        long takeTime = end - begin;

        log.info("service end, used time: {}ms", takeTime);

        return result;
    }
}
