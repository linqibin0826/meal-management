package com.shusi.modules.menu_management.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shusi.modules.menu_management.base.PageResult;
import com.shusi.modules.menu_management.domain.TestOrderPlan;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shusi.modules.menu_management.domain.dto.OrderPlanDto;
import com.shusi.modules.menu_management.domain.dto.PlanQueryDto;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author linqb
 * @since 2020-11-23
 */
public interface OrderPlanService extends IService<TestOrderPlan> {

    List<TestOrderPlan> addOrderPlanes(List<TestOrderPlan> orderPlanList);

    TestOrderPlan updateOrderPlan(TestOrderPlan testOrderPlan);

    void removeOrderPlan(String orderPlanId);

    Page<OrderPlanDto> getOrderPlanes(Long current, Long limit, PlanQueryDto planQueryDto);
}
