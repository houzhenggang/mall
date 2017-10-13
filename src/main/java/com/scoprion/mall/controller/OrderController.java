package com.scoprion.mall.controller;

import com.scoprion.annotation.AccessSecret;
import com.scoprion.mall.domain.User;
import com.scoprion.mall.service.order.OrderService;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import com.scoprion.utils.EncryptUtil;
import com.scoprion.utils.IPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created on 2017/9/29.
 */
@Controller
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 我的订单
     *
     * @return
     */
    @AccessSecret
    @RequestMapping(value = "/my-order", method = RequestMethod.GET)
    public String myOrder() {
        return "my-order";
    }

    /**
     * 查询订单列表
     *
     * @param pageNo
     * @param pageSize
     * @param status
     * @param request
     * @return
     */
    @AccessSecret
    @ResponseBody
    @RequestMapping(value = "/order-list", method = RequestMethod.GET)
    public PageResult findByPage(int pageNo, int pageSize, String status, HttpServletRequest request) throws Exception {
        String tokenStr = request.getHeader("oauth");
        String decryptStr = EncryptUtil.aesDecrypt(tokenStr, "ScorpionMall8888");
        User user = (User) redisTemplate.opsForValue().get("Login:" + decryptStr);
        return orderService.findByPage(pageNo, pageSize, status, user.getId());
    }

    /**
     * 下单
     *
     * @param goodId
     * @param deliveryId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/order-confirm", method = RequestMethod.POST)
    public BaseResult orderConfirm(Long goodId, Long deliveryId, HttpServletRequest request) throws Exception {
        String ipAddress = IPUtil.getIPAddress(request);
        return orderService.orderConfirm(goodId, deliveryId, ipAddress);
    }


}