package com.zhubun.spring_cache.controller;

import com.zhubun.spring_cache.pojo.Department;
import com.zhubun.spring_cache.pojo.Employee;
import com.zhubun.spring_cache.service.DepartmentService;
import com.zhubun.spring_cache.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "部门管理")
public class DepartmentController {
    private Department department = new Department();
    @Autowired
    DepartmentService departmentService;
    //查询Department表
    @ApiOperation("根据传入参数查询部门,id必须传入")
    @GetMapping("/dep/{d_id}")
    //必须传入id,其他可以不传入
    public Department getDepartment(@PathVariable("d_id") Integer d_id){
        Department department = new Department();
        department.setD_id(d_id);
        return departmentService.getDepByParam(department);
        }
    //根据id删除Department
    @DeleteMapping("/dep/{d_id}")
    @ApiOperation("根据id删除Department,id必须传入")
    public Department deleteDep(@PathVariable("d_id") Integer d_id){
        department.setD_id(d_id);
        return departmentService.deleteDep(department);
    }
    //更新Department
    @PostMapping("/dep")
    @ApiOperation("更新Department")
    public Department updateDep(Department department){
        return departmentService.updateDep(department);
    }
    //插入Department
    @PutMapping("/dep")
    @ApiOperation("插入Department")
    public Department insertDep(Department department){
        return departmentService.insertDep(department);
    }
    //查询Department表全部
    @GetMapping("/dep/queryall")
    @ApiOperation("查询Department表全部数据")
    public List<Department> getDepList(){
        return departmentService.getDepList(department);
    }
}

