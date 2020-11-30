package com.shusi.modules.menu_management.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.Date;

/**
 * 订餐人员: 新增对象
 *
 * @author linqb
 * @date 2020/11/20 11:14
 */
@ApiModel(value = "订餐人员: 新增对象")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserAddDto {
    private static final long serialVersionUID = 1L;

    /**
     * 报餐人员id
     */
    @ApiModelProperty(value = "报餐人员id", hidden = true)
    private String id;

    /**
     * 名字
     */
    @ApiModelProperty(value = "人员名称", position = 2)
    private String username;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 性别, 0为女, 1为男
     */
    private Integer sex;

    /**
     * 入职时间
     */
    private Date employmentDate;

    /**
     * 职位
     */
    private String position;

    /**
     * 省市区
     */
    private Long province;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机
     */
    private Long mobile;

    /**
     * 创建时间
     */
    private LocalDate gmtCreate;

    /**
     * 修改时间
     */
    private LocalDate gmtModify;
}
