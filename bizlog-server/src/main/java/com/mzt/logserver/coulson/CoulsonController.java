package com.mzt.logserver.coulson;

import com.mzt.logserver.IOrderService;
import com.mzt.logserver.pojo.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/")
public class CoulsonController {
    @Resource
    private IOrderService orderService;

    @GetMapping("findAll")
    void findAll() {
        Order order = new Order();
        order.setOrderNo("MT0000011");
        order.setProductName("超值优惠红烧肉套餐");
        order.setPurchaseName("张三");
        orderService.createOrders(order);
    }

}
