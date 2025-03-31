package com.rightings.backed.model.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
* API 访问密钥表
* @TableName access_keys
*/
@TableName(value ="access_keys")
@Data
public class AccessKeys implements Serializable {

    /**
    * 主键 ID
    */
    @ApiModelProperty("主键 ID")
    private Long id;
    /**
    * 创建 AK/SK 的用户 ID
    */
    @ApiModelProperty("创建 AK/SK 的用户 ID")
    private Long userId;
    /**
    * 访问密钥 (AK)
    */
    @ApiModelProperty("访问密钥 (AK)")
    private String accessKey;
    /**
    * 安全密钥 (SK)
    */
    @ApiModelProperty("安全密钥 (SK)")
    private String secretKey;
    /**
    * 状态 (1: 启用, 0: 禁用, 2: 过期等)
    */
    @ApiModelProperty("状态 (1: 启用, 0: 禁用, 2: 过期等)")
    private Integer status;
    /**
    * 创建时间
    */
    @ApiModelProperty("创建时间")
    private Date createTime;
    /**
    * 更新时间
    */
    @ApiModelProperty("更新时间")
    private Date updateTime;

}
