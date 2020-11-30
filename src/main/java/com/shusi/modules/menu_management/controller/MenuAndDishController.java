package com.shusi.modules.menu_management.controller;


import com.shusi.modules.menu_management.base.BaseResult;
import com.shusi.modules.menu_management.base.SimpleResult;
import com.shusi.modules.menu_management.domain.TestMenuDish;
import com.shusi.modules.menu_management.domain.dto.MenuDto;
import com.shusi.modules.menu_management.service.MenuAndDishService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author linqb
 * @since 2020-11-23
 */

@Api(tags = "菜谱-菜品")
@RestController
@RequestMapping("/test/menu-dish")
@CrossOrigin
public class MenuAndDishController {
    @Autowired
    private MenuAndDishService menuAndDishesService;

    @ApiOperation("新增菜谱菜品关系")
    @PostMapping
    public SimpleResult<TestMenuDish> addRelationship(@RequestBody TestMenuDish testMenuDish) {
        return new SimpleResult<>(menuAndDishesService.addRelationship(testMenuDish));
    }

    @ApiOperation("移除菜谱菜品关系")
    @DeleteMapping("/{relationShipId}")
    public BaseResult removeRelationship(@PathVariable String relationShipId) {
        menuAndDishesService.removeRelationship(relationShipId);
        return BaseResult.success();
    }

    @ApiOperation("查询菜谱和菜品")
    @GetMapping("/{current}/{limit}")
    public SimpleResult<Map<String, Object>> getMenuAndDishes(@PathVariable Long current, @PathVariable Long limit) {
        return new SimpleResult<>(menuAndDishesService.getPage(current, limit));
    }

    @PostMapping("/save")
    public SimpleResult<MenuDto> addBatchRelationship(@RequestBody MenuDto menuDto) {
        return new SimpleResult<>(menuAndDishesService.saveBatchByMenuId(menuDto));
    }
}

