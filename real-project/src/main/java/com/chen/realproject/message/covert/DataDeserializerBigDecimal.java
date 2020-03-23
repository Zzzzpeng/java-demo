package com.chen.realproject.message.covert;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class DataDeserializerBigDecimal extends JsonDeserializer<BigDecimal> {

    /**
     * 出参保留两位小数
     *
     * @param jsonParser
     * @param deserializationContext
     * @return
     * @throws IOException
     */
    @Override
    public BigDecimal deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if (Objects.isNull(jsonParser.getDecimalValue())) {
            return null;
        } else {
            // 这里取floor
            return jsonParser.getDecimalValue().setScale(5, RoundingMode.DOWN);
        }
    }


}


