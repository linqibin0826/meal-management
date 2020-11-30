package com.shusi.modules.menu_management.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shusi.modules.menu_management.base.BaseResult;
import com.shusi.modules.menu_management.base.PageResult;
import com.shusi.modules.menu_management.base.SimpleResult;
import com.shusi.modules.menu_management.domain.TestOrderPlan;
import com.shusi.modules.menu_management.domain.dto.OrderPlanDto;
import com.shusi.modules.menu_management.domain.dto.PlanQueryDto;
import com.shusi.modules.menu_management.service.OrderPlanService;
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

@Api(tags = "订餐计划")
@RestController
@RequestMapping("/test/plan")
@CrossOrigin
public class OrderPlanController {
    @Autowired
    private OrderPlanService orderPlanService;

    @ApiOperation("添加订餐计划")
    @PostMapping
    public SimpleResult<List<TestOrderPlan>> addOrderPlanes(@RequestBody List<TestOrderPlan> orderPlanList) {
        return new SimpleResult<>(orderPlanService.addOrderPlanes(orderPlanList));
    }

    @ApiOperation("删除订餐计划")
    @DeleteMapping("{orderPlanId}")
    public BaseResult removeOrderPlan(@PathVariable String orderPlanId) {
        orderPlanService.removeOrderPlan(orderPlanId);
        return BaseResult.success();
    }

    @PostMapping("/deleteOrders")
    public BaseResult removeOrderPlanBatch(@RequestBody List<String> orderPlanIds) {
        orderPlanService.removeByIds(orderPlanIds);
        return BaseResult.success();
    }

    @ApiOperation(("修改订餐计划"))
    @PutMapping
    public SimpleResult<TestOrderPlan> updateOrderPlan(@RequestBody TestOrderPlan testOrderPlan) {
        return new SimpleResult<>(orderPlanService.updateOrderPlan(testOrderPlan));
    }


    @PostMapping("/{current}/{limit}")
    public PageResult<OrderPlanDto> getOrderPlanes(@PathVariable Long current,
                                                   @PathVariable Long limit,
                                                   @RequestBody(required = false) PlanQueryDto planQueryDto) {
        Page<OrderPlanDto> orderPlanes = orderPlanService.getOrderPlanes(current, limit, planQueryDto);
        return new PageResult<>(orderPlanes);
    }
}

