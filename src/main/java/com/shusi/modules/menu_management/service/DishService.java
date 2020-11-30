package com.shusi.modules.menu_management.service;

import com.shusi.modules.menu_management.domain.TestDish;
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
public interface DishService extends IService<TestDish> {

    /**
     * Add dish dish.
     *
     * @param testDish the dish
     * @return the dish
     * @author linqb
     * @date 2020 /11/20 15:06
     */
    TestDish addDish(TestDish testDish);

    /**
     * Remove dish.
     *
     * @param dishId the dish id
     * @author linqb
     * @date 2020 /11/20 15:11
     */
    void removeDish(String dishId);

    /**
     * Update dish dish.
     *
     * @param testDish the dish
     * @return the dish
     * @author linqb
     * @date 2020 /11/20 16:07
     */
    TestDish updateDish(TestDish testDish);

    Map<String, Object> getDishes(Long current, Long limit);
}
