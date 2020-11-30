package com.shusi.modules.menu_management.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shusi.modules.menu_management.base.BaseResult;
import com.shusi.modules.menu_management.base.PageResult;
import com.shusi.modules.menu_management.base.SimpleResult;
import com.shusi.modules.menu_management.domain.dto.UserAddDto;
import com.shusi.modules.menu_management.domain.TestUser;
import com.shusi.modules.menu_management.domain.dto.UserQueryDto;
import com.shusi.modules.menu_management.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author linqb
 * @since 2020-11-23
 */

@Api(tags = "订餐人员")
@RestController
@RequestMapping("/test/user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;


    /**
     * Add new user.
     *
     * @param userAddDto 人员数据
     * @return SimpleResult<User>
     * @author linqb
     * @date 2020 /11/20 11:02
     */
    @ApiOperation(value = "新增订餐人员")
    @PostMapping
    public SimpleResult<TestUser> add(@RequestBody UserAddDto userAddDto) {
        return new SimpleResult<>(userService.addUser(userAddDto));
    }

    /**
     * Delete the user.
     *
     * @param userId the user id
     * @return the base result
     * @author linqb
     * @date 2020 /11/20 14:22
     */
    @ApiOperation(value = "删除订餐人员")
    @DeleteMapping(value = "/{userId}")
    public BaseResult remove(@PathVariable String userId) {
        userService.removeUser(userId);
        return BaseResult.success();
    }

    /**
     * Update the user.
     *
     * @param testUser the user
     * @return the simple result User
     * @author linqb
     * @date 2020 /11/20 14:26
     */
    @ApiOperation(value = "更新用户")
    @PutMapping
    public SimpleResult<TestUser> update(@RequestBody TestUser testUser) {
        return new SimpleResult<>(userService.updateUser(testUser));
    }

    @PostMapping("/getUser")
    public SimpleResult<List<TestUser>> getUser(@RequestBody UserQueryDto userQueryDto) {
        return new SimpleResult<>(userService.getUser(userQueryDto));
    }

    @ApiOperation(value = "分页查询")
    @GetMapping
    public PageResult<TestUser> listPage(@RequestParam(defaultValue = "1") long page, @RequestParam(defaultValue = "10") long limit) {
        return new PageResult<>(userService.listPage(new Page<>(page, limit)));
    }
}

