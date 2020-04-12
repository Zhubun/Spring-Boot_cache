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
public class Department implements Serializable {
    @ApiModelProperty("部门编号")Integer d_id;
    @ApiModelProperty("部门名称")String departmentName;
}
