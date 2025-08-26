package org.springclass.springclassproject.annotation;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Aspect
@Component
@Slf4j
public class AppLogging {

    @Around("@annotation(auditFilter) && execution(public * *(..))")
    public Object logging(ProceedingJoinPoint pjp, AuditFilter auditFilter) throws Throwable {
        var requestId = UUID.randomUUID().toString();

        log.info("[{}] ===>> Request to controller start ", requestId);

        long startTime = System.currentTimeMillis();
        Object result = pjp.proceed();
        long endTime = System.currentTimeMillis();
        long executeTime = endTime - startTime;
        String className = pjp.getSignature().getDeclaringTypeName();
        String methodName = pjp.getSignature().getName();

        log.info("[{}] ===>> Execution Time Log: Class: {}, Method: {}, Request Spent: {}ms ", requestId, className, methodName, executeTime);

        return result;
    }
}
