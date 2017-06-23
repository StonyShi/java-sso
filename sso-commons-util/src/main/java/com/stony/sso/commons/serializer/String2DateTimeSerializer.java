package com.stony.sso.commons.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.stony.sso.commons.DateUtils;

import java.io.IOException;
import java.util.Date;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/4/28 </p>
 * <p>Time: 9:22 </p>
 * <p>Version: 1.0 </p>
 * Jackson 反序列化字符串 yyyy-MM-dd HH:mm:ss 为日期
 */
public class String2DateTimeSerializer extends JsonDeserializer<Date> {
    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        String source = jsonParser.getText();
        return DateUtils.string2dateTime(source);
    }
}
