package com.zhubun.spring_cache.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class Employee implements Serializable {
    @ApiModelProperty("用户ID")Integer id;
    @ApiModelProperty("用户姓名")String lastName;
    @ApiModelProperty("用户邮箱")String email;
    @ApiModelProperty("用户性别,0:女,1:男")Integer gender;
    @ApiModelProperty("外键,连接部门表ID")Integer d_id;
    @ApiModelProperty("部门")private Department department;
}
