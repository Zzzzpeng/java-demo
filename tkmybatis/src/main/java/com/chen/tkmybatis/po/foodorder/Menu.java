package com.chen.tkmybatis.po.foodorder;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu implements Serializable {
    String id;
    String name;
    BigDecimal price;
    boolean delete;
    String userId;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Table(name = "menu_desc")
    @Builder
    public static class Detail{
        String id;
        String descDetail;
    }
}


