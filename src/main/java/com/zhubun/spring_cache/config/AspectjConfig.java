package com.zhubun.spring_cache.config;

import com.zhubun.spring_cache.service.Impl.EmployeeServiceImpl;
import com.zhubun.spring_cache.utils.AspectjUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectjConfig {
    Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    @Autowired
    HashOperations hashOperations;

    //环绕切入
    @Around("execution(* com.zhubun.spring_cache.service.Impl..*ByParam(..))")
    public Object setCache(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //获取方法参数
//        Employee employee = (Employee) proceedingJoinPoint.getArgs()[0];
//        Integer id = employee.getId();
        String method = proceedingJoinPoint.getSignature().getName();
        String name = AspectjUtils.getParamName(proceedingJoinPoint);
        Object id = AspectjUtils.getParamField(proceedingJoinPoint);
        logger.info("调用了" + method);
        //查询redis中是否有hash(emp,emp::id)
        if (hashOperations.hasKey(name, "id:"+id)) {
            logger.info("从redis中读取");
            return hashOperations.get(name, "id:"+id);
        }
        logger.info("从数据库中读取");
        //将EmployeeServiceImpl的方法调用返回结果缓存到redis中,使用proceed()
        Object proceed = proceedingJoinPoint.proceed();
        hashOperations.put(name, "id:"+id, proceed);
        return proceed;
    }

    //方法成功后调用,参数中result类型必须与方法中返回类型一致,否则不切入
    //更新缓存
    @AfterReturning(pointcut = "execution(* com.zhubun.spring_cache.service.Impl..*Emp(.. )) || " +
            "execution(* com.zhubun.spring_cache.service.Impl..*Dep(..))", returning = "result")
    public void updateCache(JoinPoint joinPoint, Object result) throws IllegalAccessException {
//        joinpoint.getSignature():(signature是信号,标识的意思):获取被增强的方法相关信息
        String methodName = joinPoint.getSignature().getName();
        String name = AspectjUtils.getParamName(joinPoint);
        Object id = AspectjUtils.getParamField(joinPoint);
        logger.info("调用" + methodName);
        logger.info("更新缓存成功");
        hashOperations.delete(name, "id:"+id);
        hashOperations.delete(name, "all");
    }

    //环绕切入
    @Around("execution(* com.zhubun.spring_cache.service.Impl..*List(..))")
    public Object setAllCache(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        Object[] args = proceedingJoinPoint.getArgs();
//        String declaringTypeName = proceedingJoinPoint.getSignature().toLongString();
        String name = AspectjUtils.getParamName(proceedingJoinPoint);
        if (hashOperations.hasKey(name, "all")) {
            logger.info("从redis中读取");
            Object emp = hashOperations.get(name, "all");
            return emp;
        }
        logger.info("从数据库中读取");
        Object proceed = proceedingJoinPoint.proceed();
        hashOperations.put(name, "all", proceed);
        return proceed;
    }
}
