package com.shusi.modules.menu_management.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shusi.modules.menu_management.base.BadRequestException;
import com.shusi.modules.menu_management.base.NotExistException;
import com.shusi.modules.menu_management.base.PageResult;
import com.shusi.modules.menu_management.domain.TestMenu;
import com.shusi.modules.menu_management.domain.TestOrderPlan;
import com.shusi.modules.menu_management.dao.OrderPlanDao;
import com.shusi.modules.menu_management.domain.TestUser;
import com.shusi.modules.menu_management.domain.dto.OrderPlanDto;
import com.shusi.modules.menu_management.domain.dto.PlanQueryDto;
import com.shusi.modules.menu_management.service.MenuService;
import com.shusi.modules.menu_management.service.OrderPlanService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shusi.modules.menu_management.service.UserService;
import com.shusi.modules.menu_management.util.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.Query;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author linqb
 * @since 2020-11-23
 */
@Service
public class OrderPlanServiceImpl extends ServiceImpl<OrderPlanDao, TestOrderPlan> implements OrderPlanService {

    @Autowired
    private MenuService menuService;

    @Autowired
    private UserService userService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<TestOrderPlan> addOrderPlanes(List<TestOrderPlan> orderPlanList) {
        if (DataUtil.isNull(orderPlanList)) {
            throw new BadRequestException("报餐计划不可为空");
        }

        for (TestOrderPlan orderPlan : orderPlanList) {
            Integer planType = orderPlan.getPlanType();
            if (DataUtil.isNull(orderPlan.getMenuId()) || DataUtil.isNull(orderPlan.getMenuId())) {
                throw new BadRequestException("报餐记录中人员或菜谱为空");
            }

            //早餐7点之前可提交
            if (planType == 0
                    && LocalTime.now().isBefore(LocalTime.of(7, 00, 00))
                    && LocalTime.now().isAfter((LocalTime.of(00, 00, 00)))) {
                save(orderPlan);
            } else if (planType == 1
                    && LocalTime.now().isBefore(LocalTime.of(11, 00, 00))
                    && LocalTime.now().isAfter((LocalTime.of(7, 00, 00)))) {
                save(orderPlan);
            } else if (planType == 2
                    && LocalTime.now().isBefore(LocalTime.of(16, 00, 00))
                    && LocalTime.now().isAfter((LocalTime.of(12, 00, 00)))) {
                save(orderPlan);
            } else {
                throw new BadRequestException("订餐错误");
            }
        }
        return orderPlanList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TestOrderPlan updateOrderPlan(TestOrderPlan testOrderPlan) {
        if (DataUtil.isNull(testOrderPlan.getId())) {
            throw new BadRequestException("没有订餐计划ID,无法更新");
        }
        //获取用户
        TestOrderPlan byId = getById(testOrderPlan.getId());
        //判断用户是否存在
        if (byId == null) {
            throw new NotExistException(testOrderPlan.getId(), "没有该用户,无法更新");
        }

        //更新
        if (LocalTime.now().isBefore(LocalTime.of(8, 00, 00))
                && "早餐".equals(testOrderPlan.getPlanType())) {
            boolean flag = updateById(testOrderPlan);
            if (!flag) {
                throw new BadRequestException("更新失败");
            }
            return testOrderPlan;
        }

        if (LocalTime.now().isBefore(LocalTime.of(10, 00, 00))
                && "午餐".equals(testOrderPlan.getPlanType())) {
            boolean flag = updateById(testOrderPlan);
            if (!flag) {
                throw new BadRequestException("更新失败");
            }
            return testOrderPlan;
        }

        if (LocalTime.now().isBefore(LocalTime.of(16, 00, 00))
                && "晚餐".equals(testOrderPlan.getPlanType())) {
            boolean flag = updateById(testOrderPlan);
            if (!flag) {
                throw new BadRequestException("更新失败");
            }
            return testOrderPlan;
        }

        throw new BadRequestException("更新错误");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeOrderPlan(String orderPlanId) {
        boolean flag = removeById(orderPlanId);
        if (!flag) {
            throw new BadRequestException("删除失败");
        }
    }

    @Override
    public Page<OrderPlanDto> getOrderPlanes(Long current, Long limit, PlanQueryDto planQueryDto) {

        Page<TestOrderPlan> orderPlanDtoPage = new Page<>(current, limit);
        QueryWrapper<TestOrderPlan> wrapper = new QueryWrapper<>();
        List<OrderPlanDto> records = new ArrayList<>();
        //最终要返回的Page
        Page<OrderPlanDto> orderPlanPage = new Page<>();
        //解构查询条件
        Integer queryDtoPlanType = planQueryDto.getPlanType();
        String queryDtoMenuName = planQueryDto.getMenuName();
        LocalDate queryDtoCreateDate = planQueryDto.getCreateDate();

        //根据条件先查询记录
        if (!DataUtil.isNull(queryDtoCreateDate)) {
            wrapper.eq("gmt_create", queryDtoCreateDate);
        }

        if (!DataUtil.isNull(queryDtoMenuName)) {
            wrapper.eq("menu_name", queryDtoMenuName);
        }

        if (!DataUtil.isNull(queryDtoPlanType)) {
            wrapper.eq("plan_type", queryDtoPlanType);
        }


        IPage<TestOrderPlan> iPage = page(orderPlanDtoPage, wrapper);
        List<TestOrderPlan> plans = iPage.getRecords();
        //便利报餐记录表, 查询对应的菜品和职员信息
        for (TestOrderPlan plan : plans) {
            OrderPlanDto orderplanDto = new OrderPlanDto();
            String menuId = plan.getMenuId();
            String userId = plan.getUserId();
            TestMenu menuInfo = menuService.getById(menuId);
            TestUser userInfo = userService.getById(userId);
            BeanUtil.copyProperties(menuInfo, orderplanDto);
            BeanUtil.copyProperties(userInfo, orderplanDto);
            orderplanDto.setId(plan.getId());
            orderplanDto.setPlanType(plan.getPlanType());
            orderplanDto.setMenuId(menuId);
            orderplanDto.setUserId(userId);
            records.add(orderplanDto);
        }
        orderPlanPage.setRecords(records);
        orderPlanPage.setTotal(iPage.getTotal());
        return orderPlanPage;
    }
}
