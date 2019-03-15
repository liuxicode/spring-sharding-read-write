package com.example.demo.aop;

import io.shardingjdbc.core.api.HintManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 切面用于事务方法中，读强制走主库
 *
 * @Auther: liuxi
 * @Date: 2019/3/15 14:22
 * @Description:
 */
@Aspect
@Component
public class TransationAop {

    @Pointcut("@annotation(org.springframework.transaction.annotation.Transactional)")
    public void controllerAspect(){

    }

    /*@Around("controllerAspect()")
    public Object proceed(ProceedingJoinPoint joinPoint) throws Throwable {

    }*/

    @Before("controllerAspect()")
    public void before(){
        // 强制路由主库
        HintManager.getInstance().setMasterRouteOnly();
    }

    @AfterReturning("controllerAspect()")
    public void after(){

    }
}
