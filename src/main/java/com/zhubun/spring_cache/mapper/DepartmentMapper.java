package com.zhubun.spring_cache.mapper;

import com.zhubun.spring_cache.pojo.Department;
import com.zhubun.spring_cache.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
@Repository
public interface DepartmentMapper {
    Department getDepByParam(@Param("d_id") Integer d_id);

    int updateDep(@Param("Department")Department Department);

    int deleteDep(@Param("d_id") Integer d_id);

    int insertDep(@Param("Department")Department Department);

    List<Department> getDepList();
}
