package com.zhubun.spring_cache.config;

import com.zhubun.spring_cache.pojo.Employee;
import com.zhubun.spring_cache.service.EmployeeServiceImpl;
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
    @Around("execution(* com.zhubun.spring_cache.service.EmployeeServiceImpl.getEmpByParam(..))")
    public Object getEmpByParam(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //获取方法参数
        Employee employee = (Employee) proceedingJoinPoint.getArgs()[0];
        Integer id = employee.getId();
        //查询redis中是否有hash(emp,emp::id)
        if(hashOperations.hasKey("emp","emp::"+id)){
            logger.info("从redis中读取");
            Employee emp = (Employee) hashOperations.get("emp", "emp::" + id);
            return emp;
        }
        logger.info("从数据库中读取");
        //将EmployeeServiceImpl的方法调用返回结果缓存到redis中,使用proceed()
        Employee proceed = (Employee) proceedingJoinPoint.proceed();
        hashOperations.put("emp","emp::"+id,proceed);
        return proceed;
    }
    //方法成功后调用,参数中result类型必须与方法中返回类型一致,否则不切入
    //更新缓存
    @AfterReturning(value = "execution(* com.zhubun.spring_cache.service.EmployeeServiceImpl.*Emp(..))",returning = "result")
    public void updateCache(JoinPoint joinPoint,Employee result){
//        joinpoint.getSignature():(signature是信号,标识的意思):获取被增强的方法相关信息
        String methodName = joinPoint.getSignature().getName();
        logger.info("调用"+methodName);
        logger.info("更新缓存成功");
        Integer id = result.getId();
        hashOperations.put("emp","emp::"+id,result);
    }
    //方法成功后调用,参数中result类型必须与方法中返回类型一致,否则不切入
    //删除缓存
    @AfterReturning(value = "execution(* com.zhubun.spring_cache.service.EmployeeServiceImpl.deleteEmp(..))",returning = "result")
    public void deleteCache(JoinPoint joinPoint,int result){
        String methodName = joinPoint.getSignature().getName();
        Object id = joinPoint.getArgs()[0];
        logger.info("调用"+methodName);
        logger.info("删除缓存成功");
        hashOperations.delete("emp","emp::"+id);
    }
    //环绕切入
    @Around("execution(* com.zhubun.spring_cache.service.EmployeeServiceImpl.getEmpList(..))")
    public Object getEmpList(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        if(hashOperations.hasKey("emp","emp::all")){
            logger.info("从redis中读取");
            Object emp = hashOperations.get("emp", "emp::all");
            return emp;
        }
        logger.info("从数据库中读取");
        Object proceed = proceedingJoinPoint.proceed();
        hashOperations.put("emp","emp::all",proceed);
        return proceed;
    }
}
