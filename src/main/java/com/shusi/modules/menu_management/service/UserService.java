package com.shusi.modules.menu_management.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shusi.modules.menu_management.domain.dto.UserAddDto;
import com.shusi.modules.menu_management.domain.TestUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shusi.modules.menu_management.domain.dto.UserQueryDto;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author linqb
 * @since 2020-11-23
 */
public interface UserService extends IService<TestUser> {
    /**
     * Add user.
     *
     * @param userAddDto 用户数据
     * @return User
     * @author linqb
     * @date 2020/11/20 12:00
     */
    TestUser addUser(UserAddDto userAddDto);

    /**
     * Remove user.
     *
     * @param userId the user id
     * @author linqb
     * @date 2020 /11/20 13:59
     */
    void removeUser(String userId);

    /**
     * Update user.
     *
     * @param testUser the user
     * @return the user
     * @author linqb
     * @date 2020 /11/20 14:06
     */
    TestUser updateUser(TestUser testUser);

    /**
     * 获取用户
     * @author linqb
     * @date 2020/11/25 14:55
     */
    List<TestUser> getUser(UserQueryDto userQueryDto);

    Page<TestUser> listPage(Page<TestUser> page);
}
