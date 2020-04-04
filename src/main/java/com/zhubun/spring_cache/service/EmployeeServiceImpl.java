package com.zhubun.spring_cache.service;

import com.zhubun.spring_cache.mapper.EmployeeMapper;
import com.zhubun.spring_cache.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
//@CacheConfig(cacheNames = "emp")
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;

    @Override
//    @Cacheable(key = "'emp::['+#employee.id+']'",unless = "#employee.id==null")
    public Employee getEmpByParam(Employee employee) {
        Employee emp = employeeMapper.getEmpByParam(employee);

        return emp;
    }

    @Override
//    @CachePut(key = "'emp::['+#result.id+']'")
    public Employee updateEmp(Employee employee) {
        int i = employeeMapper.updateEmp(employee);
        if (i>0)
            return employee;
        else
            return null;
    }

    @Override
//    @CacheEvict(key = "'emp::['+#id+']'")
    public int deleteEmp(int id) {
        int i = employeeMapper.deleteEmp(id);
        if (i>0)
            return i;
        return 0;

    }

    @Override
    //    @Cacheable(key = "'emp::['+#employee.id+']'",unless = "#employee.id==null")
    public Employee insertEmp(Employee employee) {
        int i = employeeMapper.insertEmp(employee);
        if (i>0)
            return employee;
        else
            return null;
    }

    @Override
    //    @Cacheable(key = "'emp::all",unless = "#employee.id==null")

    public List<Employee> getEmpList() {
        List<Employee> empList = employeeMapper.getEmpList();
        return empList;
    }
}
