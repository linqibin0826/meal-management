package com.shusi.modules.menu_management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shusi.modules.menu_management.base.BadRequestException;
import com.shusi.modules.menu_management.base.NotExistException;
import com.shusi.modules.menu_management.domain.TestDish;
import com.shusi.modules.menu_management.dao.DishDao;
import com.shusi.modules.menu_management.service.DishService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shusi.modules.menu_management.service.MenuAndDishService;
import com.shusi.modules.menu_management.util.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author linqb
 * @since 2020-11-23
 */
@Service
public class DishServiceImpl extends ServiceImpl<DishDao, TestDish> implements DishService {

    @Autowired
    private MenuAndDishService relationshipService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TestDish addDish(TestDish testDish) {

        if (DataUtil.isNull(testDish) ||
                DataUtil.isNull(testDish.getDishName())) {
            throw new BadRequestException("菜名不可为空");
        }
        String dishName = testDish.getDishName();
        //查询是否已存在该菜品
        QueryWrapper<TestDish> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dish_name", dishName);
        // 1: 已存在  2: 不存在可添加
        int count = baseMapper.selectCount(queryWrapper);
        if (count == 0) {
            //新增
            boolean flag = save(testDish);
            if (flag) {
                return testDish;
            }
            throw new BadRequestException("新增失败");
        }
        throw new BadRequestException("已存在该菜品,新增失败");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeDish(String dishId) {
        boolean flag = removeById(dishId);
        if (!flag) {
            throw new BadRequestException("删除失败");
        }

        //删除菜谱-菜品记录
        relationshipService.removeDish(dishId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TestDish updateDish(TestDish testDish) {
        String dishId = testDish.getId();
        if (DataUtil.isNull(dishId)) {
            throw new BadRequestException("没有菜品ID, 无法更新");
        }

        TestDish byId = getById(dishId);
        //exist or not
        if (byId == null) {
            throw new NotExistException(testDish.getId(), "不存在该菜品,无法更新");
        }

        //更新
        boolean flag = updateById(testDish);
        if (flag) {
            return testDish;
        }

        throw new BadRequestException("更新失败");
    }

    @Override
    public Map<String, Object> getDishes(Long current, Long limit) {
        Page<TestDish> dishPage = new Page<>(current, limit);
        IPage<TestDish> iPage = page(dishPage, null);
        List<TestDish> dishList = iPage.getRecords();
        long total = iPage.getTotal();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("total", total);
        hashMap.put("data", dishList);
        return hashMap;
    }
}
