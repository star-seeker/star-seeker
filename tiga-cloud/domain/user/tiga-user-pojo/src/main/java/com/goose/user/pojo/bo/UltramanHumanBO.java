package com.goose.user.pojo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhangyoubao
 * @version 2021/4/29
 */
@Data
@ApiModel(value = "human BO", description = "data from client put into this entity")
public class UltramanHumanBO {

    @ApiModelProperty(value = "human name", required = true)
    private String hmName;

    @ApiModelProperty(value = "human age", name = "age")
    private Integer age;

    @ApiModelProperty(value = "human gender", name = "gender")
    private Integer gender;

    @ApiModelProperty(value = "human extra", name = "extra")
    private String extra;
}
