package com.zhubun.spring_cache.service;

import com.zhubun.spring_cache.pojo.Department;

import java.util.List;

public interface DepartmentService {
    Department getDepByParam(Department Department);

    Department updateDep(Department Department);

    Department deleteDep(Department Department);

    Department insertDep(Department Department);

    List<Department> getDepList(Department Department);
}
