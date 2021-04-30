package com.goose.user.pojo.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhangyoubao
 * @version 2021/4/29
 */
@Data
public class UltramanBaseBO {

    @ApiModelProperty(value = "english name", required = true)
    private String enName;

    @ApiModelProperty(value = "chinese name", required = true)
    private String cnName;

    private Integer height;
    private Integer weight;
    private Integer age;
    private Integer gender;
    private String extra;

    @ApiModelProperty(value = "ultraman combine ids")
    private String combineIds;
}
