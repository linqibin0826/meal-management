package com.shusi.modules.menu_management.service;

import com.shusi.modules.menu_management.domain.TestMenuDish;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shusi.modules.menu_management.domain.dto.MenuDto;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author linqb
 * @since 2020-11-23
 */
public interface MenuAndDishService extends IService<TestMenuDish> {
    /**
     * 新增一条记录
     *
     * @param testMenuDish the menu and dish
     * @return the menu and dish
     * @author linqb
     * @date 2020 /11/20 16:26
     */
    TestMenuDish addRelationship(TestMenuDish testMenuDish);

    /**
     * 菜品被删除时,删除关系表中有该菜品的所有记录.
     *
     * @param dishId the dish id
     * @return the boolean
     * @author linqb
     * @date 2020 /11/20 17:21
     */
    void removeDish(String dishId);

    /**
     * 菜品被删除时,删除关系表中有该菜品的所有记录.
     *
     * @param menuID the menu id
     * @return the boolean
     * @author linqb
     * @date 2020 /11/20 17:24
     */
    void removeMenu(String menuID);

    /**
     * 根据id删除关系
     *
     * @param relationShipId the relation ship id
     * @author linqb
     * @date 2020 /11/20 18:57
     */
    void removeRelationship(String relationShipId);

    /**
     * Gets page.
     *
     * @param current the current
     * @param limit   the limit
     * @return the page
     * @author linqb
     * @date 2020 /11/26 14:44
     */
    Map<String, Object> getPage(Long current, Long limit);

    MenuDto saveBatchByMenuId(MenuDto menuDto);
}
