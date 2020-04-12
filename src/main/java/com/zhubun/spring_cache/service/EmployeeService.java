package com.zhubun.spring_cache.service;

import com.zhubun.spring_cache.pojo.Employee;

import java.util.List;
public interface EmployeeService {

    Employee getEmpByParam(Employee employee);

    Employee updateEmp(Employee employee);

    Employee deleteEmp(Employee employee);

    Employee insertEmp(Employee employee);

    List<Employee> getEmpList(Employee employee);
}
