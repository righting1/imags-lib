package com.rightings.backed.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户-图片点赞关系表
 * @TableName picture_like
 */
@TableName(value ="picture_like")
@Data
public class PictureLike implements Serializable {
    /**
     * 主键 ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户 ID，点赞的用户
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 图片 ID，被点赞的图片
     */
    @TableField("picture_id")
    private Long pictureId;

    /**
     * 状态: 1=已点赞, 0=取消点赞
     */
    private Integer status;

    /**
     * 点赞时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}