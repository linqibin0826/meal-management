package com.shusi.modules.menu_management.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;

import java.time.LocalDate;
import java.util.Date;

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
@ApiModel(value="TestUser对象", description="")
public class TestUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id类型为字符串长度为36")
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    @ApiModelProperty(value = "姓名")
    private String username;

    @ApiModelProperty(value = "生日")
    private Date birthday;

    @ApiModelProperty(value = "用户头像")
    private String avatar;

    @ApiModelProperty(value = "性别: 0:女, 1男, 默认为男士")
    private Integer sex;

    @ApiModelProperty(value = "入职时间")
    private Date employmentDate;

    @ApiModelProperty(value = "职位")
    private String position;

    @ApiModelProperty(value = "省市区")
    private Long province;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "手机")
    private Long mobile;

    @TableField(fill = FieldFill.INSERT)
    private LocalDate gmtCreate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDate gmtModify;


}
