package com.goose.utils;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author zhangyoubao
 * @version 2020/11/13
 */
public class JacksonUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper()
            .configure(Feature.ALLOW_SINGLE_QUOTES, true) // 允许单引号
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false) // 忽略未知的JSON字段
            .setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")); // 格式化java bean中的时间

    public static ObjectNode newObjectNode() {
        return objectMapper.createObjectNode();
    }

    public static ArrayNode newArrayNode() {
        return objectMapper.createArrayNode();
    }

    /**
     *  json string -> java object/array
     */
    public static <T> T jsonString2Bean(String jsonString, Class<T> clazz) {
        if (jsonString == null || jsonString.length() == 0) {
            return null;
        }

        T t = null;
        try {
            t = objectMapper.readValue(jsonString, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     *  json string -> map
     */
    public static Map<String, Object> jsonString2Map(String jsonString) {
        if (jsonString == null || jsonString.length() == 0) {
            return null;
        }

        Map<String, Object> map = null;
        try {
            map = objectMapper.readValue(jsonString, new TypeReference<Map<String, Object>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     *  object -> map
     */
    public static Map<String, Object> obj2Map(Object obj) {
        if (obj == null) {
            return null;
        }

        Map<String, Object> map = null;
        try {
            map = objectMapper.convertValue(obj, new TypeReference<Map<String, Object>>() {});
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     *  json array - > list
     */
    public static <T> List<T> jsonArray2List(String jsonArray, Class<T> clazz) {
        if (jsonArray == null || jsonArray.length() == 0) {
            return null;
        }

        List<T> list = new ArrayList<>();
        try {
            list = objectMapper.readValue(jsonArray, objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     *  json array - > set
     */
    public static <T> Set<T> jsonArray2Set(String jsonArray, Class<T> clazz) {
        if (jsonArray == null || jsonArray.length() == 0) {
            return null;
        }

        Set<T> set = new HashSet<>();
        try {
            set = objectMapper.readValue(jsonArray, objectMapper.getTypeFactory().constructCollectionType(Set.class, clazz));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return set;
    }

    /**
     *  java object -> json
     */
    public static String obj2Json(Object obj) {
        if (obj == null) return null;

        String json = null;
        try {
            json = objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    /**
     *  json -> JsonNode
     */
    public static JsonNode jsonString2Node(String jsonString) {
        if (jsonString == null || jsonString.length() == 0) {
            return null;
        }

        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonNode;
    }
}
