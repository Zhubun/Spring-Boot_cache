package com.zhubun.spring_cache.controller;

import com.zhubun.spring_cache.pojo.Employee;
import com.zhubun.spring_cache.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "用户管理")
public class EmployeeController {
    private Employee employee = new Employee();
    @Autowired
    EmployeeService employeeService;
    //查询Employee表
    @ApiOperation("根据传入参数查询用户,id必须传入")
    @GetMapping("/emp/{id}")
    //必须传入id,其他可以不传入
    public Employee getEmployee(@PathVariable(value = "id",required = true) Integer id,
                                @RequestParam(value = "lastName",required = false)String lastName,
                                @RequestParam(value = "email",required = false)String email){
        Employee employee = new Employee();
        employee.setId(id);
        employee.setLastName(lastName);
        employee.setEmail(email);
        Employee empByParam = employeeService.getEmpByParam(employee);
        return empByParam;
    }
    //根据id删除employee
    @DeleteMapping("/emp/{id}")
    @ApiOperation("根据id删除employee,id必须传入")
    public Employee deleteEmp(@PathVariable("id") Integer id){
        employee.setId(id);
        return employeeService.deleteEmp(employee);
    }
    //更新employee
    @PostMapping("/emp")
    @ApiOperation("更新employee")
    public Employee updateEmp(@RequestParam("id")Integer id,
                              @RequestParam("lastName")String lastName,
                              @RequestParam("email")String email,
                              @RequestParam("gender")Integer gender,
                              @RequestParam("d_id")Integer d_id){
        Employee employee = new Employee();
        employee.setId(id);
        employee.setLastName(lastName);
        employee.setEmail(email);
        employee.setGender(gender);
        employee.setD_id(d_id);
        return employeeService.updateEmp(employee);
    }
    //插入employee
    @PutMapping("/emp")
    @ApiOperation("插入employee")
    public Employee insertEmp(@RequestParam("id")Integer id,
                              @RequestParam("lastName")String lastName,
                              @RequestParam("email")String email,
                              @RequestParam("gender")Integer gender,
                              @RequestParam("d_id")Integer d_id){
        Employee employee = new Employee();
        employee.setId(id);
        employee.setLastName(lastName);
        employee.setEmail(email);
        employee.setGender(gender);
        employee.setD_id(d_id);
        return employeeService.insertEmp(employee);
    }
    //查询employee表全部
    @GetMapping("/emp/queryall")
    @ApiOperation("查询employee表全部数据")
    public List<Employee> getEmpList(){
        return employeeService.getEmpList(employee);
    }
}
