package com.stony.sso.commons.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.stony.sso.commons.DateUtils;

import java.io.IOException;
import java.util.Date;

/**
 *
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/4/28 </p>
 * <p>Time: 9:22 </p>
 * <p>Version: 1.0 </p>
 * Jackson 日期格式序列化 yyyy-MM-dd HH:mm:ss
 * <pre class="code">
 *     @JsonSerialize(using = DateTime2StringSerializer.class)
 *     public Date getDate(){
 *         return date;
 *     }
 * </pre>
 */
public class DateTime2StringSerializer extends JsonSerializer<Date> {

    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeString(DateUtils.dateTime2string(date));
    }
}
