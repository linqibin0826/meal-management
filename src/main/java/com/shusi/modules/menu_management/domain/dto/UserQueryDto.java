package com.shusi.modules.menu_management.domain.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class UserQueryDto {

    private String username;

    private Integer sex;

    private List<LocalDate> employmentTime;
}
