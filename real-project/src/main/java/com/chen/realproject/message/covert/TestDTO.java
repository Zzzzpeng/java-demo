package com.chen.realproject.message.covert;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.math.BigDecimal;


public class TestDTO {

    /**
     * com.fasterxml.jackson 处理BigDecimal时保留2位小数
     */
    @JsonDeserialize(using = DataDeserializerBigDecimal.class)
    @JsonSerialize(using = SerializerBigDecimal.class)
    private BigDecimal number;

    public TestDTO() {
    }

    public TestDTO(BigDecimal number) {
        this.number = number;
    }

    public BigDecimal getNumber() {
        return number;
    }

    public void setNumber(BigDecimal number) {
        this.number = number;
    }

}