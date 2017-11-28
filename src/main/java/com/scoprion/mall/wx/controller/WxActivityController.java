package com.scoprion.mall.wx.controller;

import com.scoprion.mall.domain.OrderExt;
import com.scoprion.mall.wx.service.activity.WxActivityService;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import com.scoprion.utils.IPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by fk
 *
 * @created on 2017/11/12.
 */
@RestController
@RequestMapping("wx/activity")
public class WxActivityController {

    @Autowired
    private WxActivityService wxActivityService;

    /**
     * 拼团列表
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/findByGroup", method = RequestMethod.GET)
    public PageResult findByGroup(int pageNo, int pageSize) {
        return wxActivityService.findByGroup(pageNo, pageSize);
    }

    /**
     * 参加拼团
     *
     * @param orderExt
     * @param request
     * @return
     */
    @RequestMapping(value = "/group", method = RequestMethod.POST)
    public BaseResult group(@RequestBody OrderExt orderExt, HttpServletRequest request) {
        String ipAddress = IPUtil.getIPAddress(request);
        return wxActivityService.group(orderExt, ipAddress);
    }


    /**
     * 秒杀
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/secKill", method = RequestMethod.GET)
    public PageResult secKill(int pageNo, int pageSize) {
        return wxActivityService.secKill(pageNo, pageSize);
    }

    /**
     * 优选
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/preference", method = RequestMethod.GET)
    public PageResult preference(int pageNo, int pageSize) {
        return wxActivityService.preference(pageNo, pageSize);
    }

}
