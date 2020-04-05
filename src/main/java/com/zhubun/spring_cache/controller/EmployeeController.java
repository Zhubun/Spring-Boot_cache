package com.zhubun.spring_cache.controller;

import com.zhubun.spring_cache.pojo.Employee;
import com.zhubun.spring_cache.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "用户管理")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    //查询Employee表
    @ApiOperation("根据传入参数查询用户,id必须传入")
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
    @ApiOperation("根据id删除employee,id必须传入")

    public String deleteEmp(@RequestParam(value = "id",required = true)Integer id){
        employeeService.deleteEmp(id);
        return "删除成功";
    }
    //更新employee
    @GetMapping("/emp/update")
    @ApiOperation("更新employee")
    public Employee updateEmp(Employee employee){
        return employeeService.updateEmp(employee);
    }
    //插入employee
    @GetMapping("/emp/insert")
    @ApiOperation("插入employee")
    public Employee insertEmp(Employee employee){
        return employeeService.insertEmp(employee);
    }
    //查询employee表全部
    @GetMapping("/emp/queryall")
    @ApiOperation("查询employee表全部数据")
    public List<Employee> getEmpList(){
        return employeeService.getEmpList();
    }
}
