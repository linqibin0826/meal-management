package com.shusi.modules.menu_management.domain.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PlanQueryDto {

    private String menuName;

    private Integer planType;

    private LocalDate createDate;
}
