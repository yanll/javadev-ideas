package com.yanll.framework.util.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * Created by YANLL on 2016/05/04.
 */
public class JSONObjectMapper extends ObjectMapper {

    public JSONObjectMapper() {
        super();
        this.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
        this.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 空值处理为空串
        this.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
            @Override
            public void serialize(Object value, JsonGenerator jg, SerializerProvider sp) throws IOException, JsonProcessingException {
                jg.writeString("");
            }
        });
    }
}
