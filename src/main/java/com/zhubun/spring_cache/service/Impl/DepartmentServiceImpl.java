package com.zhubun.spring_cache.service.Impl;

import com.zhubun.spring_cache.mapper.DepartmentMapper;
import com.zhubun.spring_cache.pojo.Department;
import com.zhubun.spring_cache.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@CacheConfig(cacheNames = "Dep")

public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentMapper departmentMapper;
    @Override
    //    @Cacheable(key = "'dep::['+#department.d_id+']'",unless = "#department.d_id==null")
    public Department getDepByParam(Department department) {
        Integer d_id = department.getD_id();
        return departmentMapper.getDepByParam(d_id);
    }

    @Override
    //    @CachePut(key = "'dep::['+#result.d_id+']'")
    public Department updateDep(Department department) {
        int i = departmentMapper.updateDep(department);
        if (i>0)
            return department;
        else
            return null;
    }

    @Override
    //    @CacheEvict(key = "'dep::['+#d_id+']'")

    public Department deleteDep(Department department) {
        Department depByParam = getDepByParam(department);
        Integer d_id = depByParam.getD_id();
        int i = departmentMapper.deleteDep(d_id);
        if (i>0)
            return depByParam;
        else
            return null;
    }

    @Override
    //    @Cacheable(key = "'dep::['+#department.d_id+']'",unless = "#department.d_id==null")

    public Department insertDep(Department department) {
        int i = departmentMapper.insertDep(department);
        if (i>0)
            return department;
        else
            return null;
    }

    @Override
    //    @Cacheable(key = "'dep::all")

    public List<Department> getDepList(Department department) {
        return departmentMapper.getDepList();
    }
}
