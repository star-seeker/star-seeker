package com.goose.user.pojo;

import java.util.Date;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhangyoubao
 * @version 2021/4/29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UltramanHuman {

    @Id
    private String id;

    private String ultramanId;
    private String hmName;
    private Integer age;
    private Integer gender;
    private String extra;
    private Date createTime;
    private Date updateTime;
}
