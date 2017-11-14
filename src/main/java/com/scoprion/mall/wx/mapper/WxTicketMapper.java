package com.scoprion.mall.wx.mapper;

import com.github.pagehelper.Page;
import com.scoprion.mall.domain.Ticket;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author by Administrator
 * @created on 2017/11/2/002.
 */

@Mapper
public interface WxTicketMapper {

    /**
     *用户优惠券列表
     * @param userId
     * @return
     */
    Page<Ticket> findByList(@Param("userId") Long userId);



}
