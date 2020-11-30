package com.shusi.modules.menu_management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shusi.modules.menu_management.base.BadRequestException;
import com.shusi.modules.menu_management.base.NotExistException;
import com.shusi.modules.menu_management.domain.TestMenu;
import com.shusi.modules.menu_management.dao.MenuDao;
import com.shusi.modules.menu_management.service.MenuAndDishService;
import com.shusi.modules.menu_management.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class MenuServiceImpl extends ServiceImpl<MenuDao, TestMenu> implements MenuService {

    @Autowired
    private MenuAndDishService relationService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TestMenu addMenu(TestMenu testMenu) {
        if (DataUtil.isNull(testMenu) || DataUtil.isNull(testMenu.getMenuName())) {
            throw new BadRequestException("菜谱名不可为空");
        }
        //exist or not
        QueryWrapper<TestMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("menu_name", testMenu.getMenuName());
        int count = count(queryWrapper);
        if (count == 0) {
            //新增
            boolean flag = save(testMenu);
            if (flag) {
                return testMenu;
            }
            throw new BadRequestException("新增失败");
        }

        throw new BadRequestException("已存在该菜谱,新增失败");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeMenu(String menuId) {
        boolean flag = removeById(menuId);
        if (!flag) {
            throw new BadRequestException("删除失败");
        }

        //删除菜谱-菜谱关系
        relationService.removeMenu(menuId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TestMenu updateMenu(TestMenu testMenu) {
        String menuId = testMenu.getId();
        if (DataUtil.isNull(menuId)) {
            throw new BadRequestException("没有菜单id, 无法更新");
        }
        //获取菜谱
        TestMenu byId = getById(menuId);
        if (byId == null) {
            throw new NotExistException(menuId, "不存在该菜谱");
        }
        //update
        boolean flag = updateById(testMenu);
        if (flag) {
            return testMenu;
        }
        throw new BadRequestException("更新失败");
    }

    @Override
    public Map<String, Object> getMenu(Long current, Long limit) {
        Page<TestMenu> menuPage = new Page<>(current, limit);
        IPage<TestMenu> pageInfo = page(menuPage, null);
        List<TestMenu> records = pageInfo.getRecords();
        long total = pageInfo.getTotal();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("data", records);
        hashMap.put("total", total);
        return hashMap;
    }
}
