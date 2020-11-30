package com.shusi.modules.menu_management.domain.dto;

import com.shusi.modules.menu_management.domain.TestDish;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@ApiModel(value = "菜谱网络传输对象")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MenuDto {
    private static final long serialVersionUID = 1L;

    private String id;

    private String menuName;

    private String menuPhoto;

    private List<TestDish> data;
}
