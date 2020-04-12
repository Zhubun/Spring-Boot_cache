package com.zhubun.spring_cache.service.Impl;

import com.zhubun.spring_cache.mapper.EmployeeMapper;
import com.zhubun.spring_cache.pojo.Employee;
import com.zhubun.spring_cache.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
//@CacheConfig(cacheNames = "Emp")
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;

    @Override
//    @Cacheable(key = "'emp::['+#employee.id+']'",unless = "#employee.id==null")
    public Employee getEmpByParam(Employee employee) {
        return employeeMapper.getEmpByParam(employee);
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
    public Employee deleteEmp(Employee employee) {
        Employee empByParam = getEmpByParam(employee);
        Integer id = empByParam.getId();
        int i = employeeMapper.deleteEmp(id);
        if (i>0)
            return empByParam;
        return null;

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

    public List<Employee> getEmpList(Employee employee) {
        return employeeMapper.getEmpList();
    }
}
