package com.shusi.modules.menu_management.controller;


import com.shusi.modules.menu_management.base.BaseResult;
import com.shusi.modules.menu_management.base.SimpleResult;
import com.shusi.modules.menu_management.domain.TestMenu;
import com.shusi.modules.menu_management.service.MenuAndDishService;
import com.shusi.modules.menu_management.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author linqb
 * @since 2020-11-23
 */


@Api(tags = "菜谱")
@RestController
@RequestMapping("/test/menu")
@CrossOrigin
public class MenuController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private MenuAndDishService menuAndDishesService;

    /**
     * Add menu.
     *
     * @param testMenu the menu
     * @return the simple result of menu
     * @author linqb
     * @date 2020 /11/20 15:50
     */
    @ApiOperation(value = "新增菜谱")
    @PostMapping
    public SimpleResult<TestMenu> addMenu(@RequestBody TestMenu testMenu) {
        return new SimpleResult<>(menuService.addMenu(testMenu));
    }

    /**
     * Remove menu.
     *
     * @param menuId the menu id
     * @return the base result
     * @author linqb
     * @date 2020 /11/20 15:57
     */
    @ApiOperation(value = "删除菜谱")
    @DeleteMapping("/{menuId}")
    public BaseResult remove(@PathVariable String menuId) {
        menuService.removeMenu(menuId);
        return BaseResult.success();
    }

    /**
     * Update menu's name.
     *
     * @param testMenu the menu
     * @return the simple result
     * @author linqb
     * @date 2020 /11/20 16:17
     */
    @ApiOperation(value = "更新菜谱名称")
    @PutMapping
    public SimpleResult<TestMenu> updateMenu(@RequestBody TestMenu testMenu) {
        return new SimpleResult<>(menuService.updateMenu(testMenu));
    }

}

