package com.yanll.framework.util.jackson;


import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

//import com.fasterxml.jackson.dataformat.xml.XmlFactory;
//import com.fasterxml.jackson.dataformat.xml.XmlMapper;

/**
 * 升级jackson 1.X版本至2.X版本，增加对XML的解析。
 * <p>
 * 默认的Jackson处理类 官方推荐使用缓存的JsonFactory、ObjectMapper
 * Created by
 * YAN on 2015/09/30.
 */
public class UtilJackson {
    private static final Logger logger = LoggerFactory.getLogger(UtilJackson.class);
    public static final ObjectMapper mapper = new ObjectMapper();
    public static final JsonFactory factory = mapper.getFactory();

    //解析XML时使用
    //public static final XmlMapper xml_mapper = new XmlMapper();
    //public static final XmlFactory xml_factory = xml_mapper.getFactory();

    static {
        /*

        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        SerializerProvider serializerProvider = mapper.getSerializerProvider();
        serializerProvider.setNullValueSerializer(new JsonSerializer<Object>() {
            @Override
            public void serialize(Object value, JsonGenerator jg, SerializerProvider sp) throws IOException, JsonProcessingException {
                jg.writeString("");
            }
        });
        */
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
     * 序列化输出（生产环境不推荐使用）
     *
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
     * 格式化输出（生产环境不推荐使用）
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
     * 解析XML
     *
     * @param content
     * @return
     */
    /*
    public static JsonNode readXmlTree(String content) {
        try {
            return xml_mapper.readTree(content);
        } catch (IOException e) {
            logger.error(String.format("JSON反序列化异常：【%s】", content), e);
        }
        return null;
    }
    */

    /**
     * 解析XML
     *
     * @param is
     * @return
     */
    /*
    public static JsonNode readXmlTree(InputStream is) {
        try {
            return xml_mapper.readTree(is);
        } catch (IOException e) {
            logger.error(String.format("JSON反序列化异常：【%s】", "InputStream"), e);
        }
        return null;
    }
    */

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

    /**
     * 复杂对象JSON转换
     *
     * @param content
     * @param valueTypeRef
     * @return
     */
    @SuppressWarnings("hiding")
    public static <T> T fromJSON(String content, TypeReference<?> valueTypeRef) {
        try {
            return mapper.readValue(content, valueTypeRef);
        } catch (Exception e) {
            logger.error("fromJSON error.", e);
            return null;
        }
    }

    public static <T> List<T> fromJSONtoList(String json, Class<T> clazz) {
        try {
            JavaType javaType = mapper.getTypeFactory().constructParametrizedType(List.class, clazz, clazz);
            List<T> list = (List<T>) mapper.readValue(json, javaType);
            return list;
        } catch (IOException e) {
            logger.error(String.format("JSON fromJSONtoList对象反序列化异常：【%s】【%s】", clazz.getName(), json), e);
            return null;
        }
    }

    public static <K, V> Map<K, V> fromJSONtoMap(String json, Class<K> class_key, Class<V> class_value) {
        try {
            JavaType javaType = mapper.getTypeFactory().constructMapType(Map.class, class_key, class_value);
            Map<K, V> map = (Map<K, V>) mapper.readValue(json, javaType);
            return map;
        } catch (IOException e) {
            logger.error(String.format("JSON fromJSONtoMap对象反序列化异常：【%s】【%s】【%s】", class_key.getName(), class_value.getName(), json), e);
            return null;
        }
    }


}
