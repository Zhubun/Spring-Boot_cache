package com.zhubun.spring_cache.mapper;

import com.zhubun.spring_cache.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface EmployeeMapper {
    Employee getEmpByParam(@Param("Employee") Employee employee);

    int updateEmp(@Param("Employee") Employee employee);

    int deleteEmp(@Param("id") Integer id);

    int insertEmp(@Param("Employee") Employee employee);

    List<Employee> getEmpList();
}
