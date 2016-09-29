package com.cmp.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * 默认的Jackson处理类
 * Jackson官方推荐使用缓存的JsonFactory、ObjectMapper
 * 流式API性能更优
 * Created by YAN on 2015/09/30.
 */
public class UtilJackson {

    private static final Log logger = LogFactory.getLog(UtilJackson.class);
    public static final ObjectMapper mapper = new ObjectMapper();
    public static final JsonFactory factory = mapper.getFactory();

    static {
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        SerializerProvider serializerProvider = mapper.getSerializerProvider();
        serializerProvider.setNullValueSerializer(new JsonSerializer<Object>() {
            @Override
            public void serialize(Object value, JsonGenerator jg, SerializerProvider sp) throws IOException, JsonProcessingException {
                jg.writeString("");
            }
        });
    }

    /**
     * 复杂对象JSON转换
     *
     * @param content
     * @param valueTypeRef
     * @return
     */
    @SuppressWarnings("hiding")
    public static <Object> Object fromJSON(String content, TypeReference<?> valueTypeRef) {
        try {
            return mapper.readValue(content, valueTypeRef);
        } catch (Exception e) {
            logger.error("readValue", e);
            return null;
        }
    }

    /**
     * 创建一个新的ObjectNode对象
     *
     * @return
     */
    public static ObjectNode createObjectNode() {
        return mapper.createObjectNode();
    }

    /**
     * 创建一个新的ArrayNode对象
     *
     * @return
     */
    public static ArrayNode createArrayNode() {
        return mapper.createArrayNode();
    }

    /**
     * @param object
     * @return
     */
    public static String writeValueAsString(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            logger.error(String.format("JSON序列化异常【writeValueAsString】：%s", e), e);
        }
        return null;
    }

    /**
     * 格式化输出
     *
     * @param object
     * @return
     */
    public static String writerWithDefaultPrettyPrinter(Object object) {
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            logger.error(String.format("JSON序列化异常【writerWithDefaultPrettyPrinter】：%s", e), e);
        }
        return null;
    }

    /**
     * @param content
     * @param valueType
     * @param <T>
     * @return
     */
    public static <T> T readValue(String content, Class<T> valueType) {
        try {
            return mapper.readValue(content, valueType);
        } catch (IOException e) {
            logger.error(String.format("JSON反序列化异常：【%s】", content), e);
        }
        return null;
    }

    /**
     * @param content
     * @return
     */
    public static JsonNode readTree(String content) {
        try {
            return mapper.readTree(content);
        } catch (IOException e) {
            logger.error(String.format("JSON反序列化异常：【%s】", content), e);
        }
        return null;
    }

    public static JsonNode readTree(InputStream is) {
        try {
            return mapper.readTree(is);
        } catch (IOException e) {
            logger.error(String.format("JSON反序列化异常：【%s】", "InputStream"), e);
        }
        return null;
    }

    /**
     * 创建JSON处理器的静态方法
     *
     * @param content JSON字符串
     * @return
     */
    private static JsonParser createParser(String content) {
        try {
            return factory.createParser(content);
        } catch (IOException ioe) {
            logger.error(String.format("JsonParser构建异常【createParser】：%s", ioe), ioe);
        }
        return null;
    }

    /**
     * 创建JSON生成器的静态方法, 使用标准输出
     *
     * @return
     */
    private static JsonGenerator createGenerator(StringWriter sw) {
        try {
            return factory.createGenerator(sw);
        } catch (IOException e) {
            logger.error(String.format("JsonGenerator构建异常【createGenerator】：%s", e), e);
        }
        return null;
    }

    /**
     * JSON对象序列化
     */
    public static String toJSON(Object obj) {
        StringWriter sw = new StringWriter();
        JsonGenerator jsonGen = createGenerator(sw);
        if (jsonGen == null) {
            try {
                sw.close();
            } catch (IOException e) {
                logger.error(String.format("JSON IO关闭异常：%s", e), e);
            }
            return null;
        }
        try {
            jsonGen.writeObject(obj);
            jsonGen.flush();
            jsonGen.close();
            return sw.toString();
        } catch (JsonGenerationException jge) {
            logger.error(String.format("JSON JsonGenerationException：%s", jge.getMessage()), jge);
        } catch (IOException ioe) {
            logger.error(String.format("JSON对象序列化异常：%s", ioe), ioe);
        }
        return null;
    }

    /**
     * JSON对象反序列化
     */
    public static <T> T fromJSON(String json, Class<T> clazz) {
        try {
            JsonParser jp = createParser(json);
            return jp.readValueAs(clazz);
        } catch (JsonParseException jpe) {
            logger.error(String.format("JsonParseException：%s", jpe), jpe);
        } catch (JsonMappingException jme) {
            logger.error(String.format("JsonMappingException：%s", jme), jme);
        } catch (IOException ioe) {
            logger.error(String.format("JSON对象反序列化异常：【%s】", json), ioe);
        }
        return null;
    }

    public static <T> List<T> fromJSONtoList(String json, Class<T> clazz) {
        try {
            JavaType javaType = mapper.getTypeFactory().constructParametrizedType(List.class, clazz, clazz);
            List<T> list = (List<T>) mapper.readValue(json, javaType);
            return list;
        } catch (IOException e) {
            logger.error(String.format("JSON对象反序列化异常：【%s】【%s】", clazz.getName(), json), e);
            return null;
        }
    }

    public static String join(ArrayNode array, String field) {
        StringBuffer sb = new StringBuffer();
        Iterator<JsonNode> it = array.elements();
        int size = array.size();
        int i = 0;
        while (it.hasNext()) {
            i++;
            JsonNode node = it.next();
            sb.append(node.get(field).asText(""));
            if (i < size) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    public static String getTextValue(Map<String, Object> row, String key, String defaultValue) {
        if (row.get(key) == null) {
            return defaultValue;
        }
        return row.get(key).toString();
    }

    public static long getLongValue(Map<String, Object> row, String key, long defaultValue) {
        if (row.get(key) == null) {
            return defaultValue;
        }
        return Long.parseLong(row.get(key).toString());
    }


}
