package com.shusi.modules.menu_management.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;

import java.time.LocalDate;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author linqb
 * @since 2020-11-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TestDish对象", description="")
public class TestDish implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    @ApiModelProperty(value = "菜品名称")
    private String dishName;

    @ApiModelProperty(value = "菜品图片")
    private String dishPhoto;


    @TableField(fill = FieldFill.INSERT)
    private LocalDate gmtCreate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDate gmtModify;
}
