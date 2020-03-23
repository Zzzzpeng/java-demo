package com.chen.tkmybatis.po.foodorder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail implements Serializable {
    String id;
    String orderId;
    String menuId;
    Menu menu;
    int menuCount;
}
