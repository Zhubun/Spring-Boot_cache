package com.zhubun.spring_cache.service;

import com.zhubun.spring_cache.pojo.Employee;

import java.util.List;
public interface EmployeeService {

    Employee getEmpByParam(Employee employee);

    Employee updateEmp(Employee employee);

    int deleteEmp(int id);

    Employee insertEmp(Employee employee);

    List<Employee> getEmpList();
}
