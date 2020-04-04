package com.zhubun.spring_cache.controller;

import com.zhubun.spring_cache.pojo.Employee;
import com.zhubun.spring_cache.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    //查询Employee表
    @GetMapping("/emp/query")
    //必须传入id,其他可以不传入
    public Employee getEmployee(@RequestParam(value = "id",required = true) Integer id,
                                @RequestParam(value = "lastName",required = false)String lastName,
                                @RequestParam(value = "email",required = false)String email){
        Employee employee = new Employee();
        employee.setId(id);
        employee.setLastName(lastName);
        employee.setEmail(email);
        Employee emp = employeeService.getEmpByParam(employee);
        return emp;
    }
    //根据id删除employee
    @GetMapping("/emp/delete")
    public String deleteEmp(@RequestParam(value = "id",required = true)Integer id, Employee employee){
        employeeService.deleteEmp(id);
        return "删除成功";
    }
    //更新employee
    @GetMapping("/emp/update")
    public Employee updateEmp(@RequestParam(value = "id",required = true)Integer id, Employee employee){
        return employeeService.updateEmp(employee);
    }
    //插入employee
    @GetMapping("/emp/insert")
    public Employee insertEmp(@RequestParam(value = "id",required = true)Integer id, Employee employee){
        return employeeService.insertEmp(employee);
    }
    //查询employee表全部
    @GetMapping("/emp/queryall")
    public List<Employee> getEmpList(){
        return employeeService.getEmpList();
    }
}
