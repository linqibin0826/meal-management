package com.shusi.modules.menu_management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shusi.modules.menu_management.base.BadRequestException;
import com.shusi.modules.menu_management.base.NotExistException;
import com.shusi.modules.menu_management.base.SimpleResult;
import com.shusi.modules.menu_management.domain.TestDish;
import com.shusi.modules.menu_management.domain.TestMenu;
import com.shusi.modules.menu_management.domain.TestMenuDish;
import com.shusi.modules.menu_management.dao.MenuAndDishDao;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shusi.modules.menu_management.domain.dto.MenuDto;
import com.shusi.modules.menu_management.service.DishService;
import com.shusi.modules.menu_management.service.MenuAndDishService;
import com.shusi.modules.menu_management.service.MenuService;
import com.shusi.modules.menu_management.util.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author linqb
 * @since 2020-11-23
 */
@Service
public class MenuAndDishServiceImpl extends ServiceImpl<MenuAndDishDao, TestMenuDish> implements MenuAndDishService {

    private static final int MAX_DISHES = 4;

    @Autowired
    private DishService dishService;

    @Autowired
    private MenuService menuService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TestMenuDish addRelationship(TestMenuDish testMenuDish) {
        //菜谱或菜品ID是否为空
        if (DataUtil.isNull(testMenuDish)
                || DataUtil.isNull(testMenuDish.getDishId())
                || DataUtil.isNull(testMenuDish.getMenuId())) {
            throw new BadRequestException("菜谱与菜品id不可为空");
        }

        String dishId = testMenuDish.getDishId();
        String menuId = testMenuDish.getMenuId();
        //是否存在该菜品
        TestDish testDish = dishService.getById(dishId);
        if (DataUtil.isNull(testDish)) {
            throw new NotExistException(dishId, "目标菜品不存在,新增失败");
        }

        //是否存在该菜谱
        TestMenu testMenu = menuService.getById(menuId);
        if (DataUtil.isNull(testMenu)) {
            throw new NotExistException(menuId, "目标菜谱不存在,新增失败");
        }

        //查询该菜谱下的菜品信息
        QueryWrapper<TestMenuDish> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("menu_id", menuId);
        List<TestMenuDish> testMenuDishes = baseMapper.selectList(queryWrapper);

        //遍历菜品信息,不可重复添加同一菜品
        for (TestMenuDish item : testMenuDishes) {
            if (dishId.equals(item.getDishId())) {
                throw new BadRequestException("菜谱下已存在该菜品,新增失败");
            }
        }

        //该菜谱已有菜品数量, 大于4则无法新增
        Integer dishes = testMenuDishes.size();
        if (dishes.equals(MAX_DISHES)) {
            throw new BadRequestException("菜谱已有4个菜品, 新增失败");
        }

        //新增一条记录
        boolean flag = save(testMenuDish);
        if (!flag) {
            throw new BadRequestException("新增失败");
        }

        //指定菜谱id与菜品id查询记录
        queryWrapper.eq("dish_id", dishId);
        TestMenuDish finalTestMenuDish = getOne(queryWrapper);

        return finalTestMenuDish;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeDish(String dishId) {
        if (DataUtil.isNull(dishId)) {
            throw new BadRequestException("菜品id不可为空");
        }
        QueryWrapper<TestMenuDish> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dish_id", dishId);
        boolean flag = remove(queryWrapper);
        if (!flag) {
            throw new BadRequestException("删除失败");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeMenu(String menuID) {
        if (DataUtil.isNull(menuID)) {
            throw new BadRequestException("菜品id不可为空");
        }

        QueryWrapper<TestMenuDish> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("menu_id", menuID);
        // 先查, 后删除
        List<TestMenuDish> menuDishes = list(queryWrapper);
        if (menuDishes.size() > 0) {
            boolean flag = remove(queryWrapper);
            if (!flag) {
                throw new BadRequestException("删除失败");
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeRelationship(String relationShipId) {
        boolean flag = removeById(relationShipId);
        if (!flag) {
            throw new BadRequestException("删除失败");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> getPage(Long current, Long limit) {

        HashMap<String, Object> finalMap = new HashMap<>();
        //从菜谱服务类中获取当前页菜谱的信息与菜谱总记录数
        Map<String, Object> menuMap = menuService.getMenu(current, limit);
        List<TestMenu> menuList = (List<TestMenu>) menuMap.get("data");
        //总菜谱数
        Long total = (Long) menuMap.get("total");
        finalMap.put("total", total);
        List<Object> data = new ArrayList<>();
        if (total > 0) {
            for (TestMenu menu : menuList) {
                HashMap<String, Object> menuHashMap = new HashMap<>();
                //根据菜谱ID分别查询对应的菜品
                QueryWrapper<TestMenuDish> wrapper = new QueryWrapper<>();
                //获取菜谱的名称、封面
                String menuId = menu.getId();
                String menuName = menu.getMenuName();
                String menuPhoto = menu.getMenuPhoto();
                ArrayList<Object> testDishes = new ArrayList<>();
                //封装查询条件
                wrapper.eq("menu_id", menuId);
                //获取到的是菜品的id列表
                List<TestMenuDish> dishIds = list(wrapper);
                if (dishIds.size() > 0) {
                    //查询菜品信息,并将菜品信息放入菜品Map中.
                    for (TestMenuDish menuDish : dishIds) {
                        HashMap<String, Object> dishHashMap = new HashMap<>();
                        TestDish dish = dishService.getById(menuDish.getDishId());
                        String dishName = dish.getDishName();
                        String dishPhoto = dish.getDishPhoto();
                        String id = dish.getId();
                        dishHashMap.put("dishName", dishName);
                        dishHashMap.put("dishPhoto", dishPhoto);
                        dishHashMap.put("id", id);
                        //将菜品信息放入菜品列表里.
                        testDishes.add(dishHashMap);
                    }
                }
                menuHashMap.put("id", menuId);
                menuHashMap.put("menuName", menuName);
                menuHashMap.put("menuPhoto", menuPhoto);
                //将菜谱下的所有菜品放入集合中,
                menuHashMap.put("data", testDishes);
                data.add(menuHashMap);
            }
        }
        finalMap.put("data", data);
        /*
         * 最终返回的数据格式为
         * {
         *   total: **,
         *   data: [
         *           {
         *               menuName: '',
         *               menuPhoto: '',
         *               [
         *                   {
         *                       dishName: '',
         *                       dishPhoto: ''
         *                   },
         *                   ...
         *               ]
         *           },
         *           ...
         *         ]
         * }
         * */
        return finalMap;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MenuDto saveBatchByMenuId(MenuDto menuDto) {
        if (DataUtil.isNull(menuDto) || DataUtil.isNull(menuDto.getId())) {
            throw new  BadRequestException("菜谱Id不可为空");
        }
        //获取当前菜谱ID
        String menuId = menuDto.getId();
        //获取当前DTO中包含的所有菜品
        List<TestDish> menuDtoDishes = menuDto.getData();
        //查询数据库获取此菜谱数据库中的菜品信息
        QueryWrapper<TestMenuDish> wrapper = new QueryWrapper<>();
        wrapper.eq("menu_id", menuId);
        List<TestMenuDish> menuDishes = list(wrapper);

        boolean flag = true;

        for (TestDish dish : menuDtoDishes) {
            for (TestMenuDish menuDish : menuDishes) {
                if (dish.getId().equals(menuDish.getDishId())) {
                    flag = false;
                    break;
                } else {
                    flag = true;
                }
            }
            //如果flag为true, 说明该菜谱下没有该菜品, 可以添加
            if (flag && menuDishes.size() < 4) {
                TestMenuDish menuDish = new TestMenuDish();
                menuDish.setDishId(dish.getId());
                menuDish.setMenuId(menuId);
                boolean save = save(menuDish);
                if (!save) {
                    throw new BadRequestException("添加失败");
                }
            }
        }

        return menuDto;
    }
}
