package com.shusi.modules.menu_management.service;

import com.shusi.modules.menu_management.domain.TestMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author linqb
 * @since 2020-11-23
 */
public interface MenuService extends IService<TestMenu> {

    /**
     * Add menu menu.
     *
     * @param testMenu the menu
     * @return the menu
     * @author linqb
     * @date 2020 /11/20 16:04
     */
    TestMenu addMenu(TestMenu testMenu);

    /**
     * Remove menu.
     *
     * @param menuId the menu id
     * @author linqb
     * @date 2020 /11/20 16:05
     */
    void removeMenu(String menuId);

    /**
     * Update menu menu.
     *
     * @param testMenu the menu
     * @return the menu
     * @author linqb
     * @date 2020 /11/20 16:05
     */
    TestMenu updateMenu(TestMenu testMenu);

    Map<String, Object> getMenu(Long current, Long limit);
}
