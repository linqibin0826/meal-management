package com.shusi.modules.menu_management.domain.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@ApiModel(value = "订单计划网络传输对象")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OrderPlanDto {

    private static final long serialVersionUID = 1L;

    private String id;

    private String menuId;

    private String menuName;

    private String menuPhoto;

    private String userId;

    private String username;

    private String avatar;

    private Long mobile;

    private Integer planType;

    private String email;

    private String position;


}
