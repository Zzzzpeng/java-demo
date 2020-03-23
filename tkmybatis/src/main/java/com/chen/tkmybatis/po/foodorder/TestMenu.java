package com.chen.tkmybatis.po.foodorder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "menu")
public class TestMenu {
    String id;
    String name;
    BigDecimal price;
    boolean delete;
    String userId;

}
