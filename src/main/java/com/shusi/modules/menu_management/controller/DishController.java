package com.shusi.modules.menu_management.controller;


import com.shusi.modules.menu_management.base.BaseResult;
import com.shusi.modules.menu_management.base.SimpleResult;
import com.shusi.modules.menu_management.domain.TestDish;
import com.shusi.modules.menu_management.service.DishService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.Log;

import java.util.List;
import java.util.Map;


/**
 * The type Dishes controller.
 *
 * @author linqb
 * @date 2020 /11/20 14:35
 */
@Api(tags = "菜品")
@RestController
@RequestMapping("/test/dish")
@CrossOrigin
public class DishController {

    @Autowired
    private DishService dishService;

    /**
     * Add new dash.
     *
     * @param testDish the dish
     * @return the simple result of the dish
     * @author linqb
     * @date 2020 /11/20 15:06
     */
    @ApiOperation(value = "新增菜品")
    @PostMapping
    public SimpleResult<TestDish> add(@RequestBody TestDish testDish) {
        return new SimpleResult<>(dishService.addDish(testDish));
    }

    /**
     * Remove dish by dish id.
     *
     * @param dishId the dish id
     * @return the base result
     * @author linqb
     * @date 2020 /11/20 15:14
     */
    @ApiOperation(value = "删除菜品")
    @DeleteMapping
    public BaseResult remove(@PathVariable String dishId) {
        dishService.removeDish(dishId);
        return BaseResult.success();
    }

    /**
     * Update dish.
     *
     * @param testDish the dish
     * @return the simple result of the dish
     * @author linqb
     * @date 2020 /11/20 15:33
     */
    @ApiOperation(value = "更新菜品")
    @PutMapping
    public SimpleResult<TestDish> update(@RequestBody TestDish testDish) {
        return new SimpleResult<>(dishService.updateDish(testDish));
    }

    @GetMapping("/{current}/{limit}")
    public SimpleResult<Map<String,Object>> getDishes(@PathVariable Long current, @PathVariable Long limit) {
        return new SimpleResult<>(dishService.getDishes(current, limit));
    }
}

