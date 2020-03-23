package com.chen.tkmybatis.po.foodorder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    String id;
    String deskId;
    int totalPrice;
}
