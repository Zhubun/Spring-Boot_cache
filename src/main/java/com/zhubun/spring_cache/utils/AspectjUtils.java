package com.zhubun.spring_cache.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.reflect.Field;

public class AspectjUtils {
    public static String getParamName(JoinPoint joinPoint) throws IllegalAccessException {
        Object arg = joinPoint.getArgs()[0];
        Class<?> aClass = arg.getClass();
        return aClass.getName();
    }
    public static Object getParamField(JoinPoint joinPoint) throws IllegalAccessException {
        Object arg = joinPoint.getArgs()[0];
        Class<?> aClass = arg.getClass();
        Field field = aClass.getDeclaredFields()[0];
        field.setAccessible(true);
        return field.get(arg);
    }
}
