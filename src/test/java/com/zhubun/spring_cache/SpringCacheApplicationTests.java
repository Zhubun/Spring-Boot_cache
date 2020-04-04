package com.zhubun.spring_cache;

import com.zhubun.spring_cache.mapper.EmployeeMapper;
import com.zhubun.spring_cache.pojo.Employee;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;

//@SpringBootTest
class SpringCacheApplicationTests {
//
//    @Autowired
////    HashOperations hashOperations;
//    @Autowired
////    RedisTemplate redisTemplate;
//    @Autowired
//    EmployeeMapper employeeMapper;
    @Test
    void contextLoads() {
//        System.out.println(employeeMapper.getEmpById(1));
//        for (Employee employee : employeeMapper.getEmpList()) {
//            System.out.println(employee);
//        }
//        ArrayList arrayList = new ArrayList(10);
//        arrayList.set(5,1);
//        Employee employee = new Employee();
//        employee.setId(null);
//        employee.setLastName(123456789+"");
//        System.out.println(employee);
//        System.out.println(employeeMapper.getEmpByParam(employee));

//        String emp = (String) hashOperations.get("emp", "emp::1");
//        System.out.println(emp);
//        for (Employee employee : employeeMapper.getEmpList()) {
//            System.out.println(employee);
//        }
    }

}
