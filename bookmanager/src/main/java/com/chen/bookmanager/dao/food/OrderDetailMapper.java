package com.chen.bookmanager.dao.food;

import com.chen.bookmanager.model.foodorder.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderDetailMapper {
    List<OrderDetail> getOrderDetailListByOrderId(String orderId);
}
