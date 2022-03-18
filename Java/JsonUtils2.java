package com.akg.utility;

import com.cs.greenchannel.exception.BadRequestException;
import com.cs.greenchannel.exception.JsonException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class JsonUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);

    private static ObjectMapper objectMapper;
    private static final XmlMapper XML_MAPPER = new XmlMapper();
    private static final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private static final Validator validator = factory.getValidator();

    static {
        try {
            objectMapper = initMapper();
        } catch (Exception e) {
            LOGGER.error("Problem while loading json config - {}", e.getMessage());
        }
    }

    private JsonUtils() {
    }

    private static ObjectMapper initMapper() {
        JsonMapper.Builder builder = JsonMapper.builder();
        return initMapperConfig(builder.build());
    }

    private static ObjectMapper initMapperConfig(ObjectMapper objectMapper) {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, false);

        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        objectMapper.enable(DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY);
        objectMapper.enable(JsonGenerator.Feature.IGNORE_UNKNOWN);
        objectMapper.enable(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN);

        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    public static <Type> Type jsonToObject(final String jsonAsString, final Class<Type> destinationClass) throws JsonProcessingException {
        return objectMapper.readValue(jsonAsString.replaceAll("\\\\", ""), destinationClass);
    }

    public static <Type> Type xmlByteToObject(final byte[] content, final Class<Type> destinationClass) throws IOException {
        return XML_MAPPER.readValue(new String(content), destinationClass);
    }

    public static <T> Set<ConstraintViolation<T>> validateRequest(T object) {

        List<String> errorMessage = new ArrayList<>();
        Set<ConstraintViolation<T>> violations = validator.validate(object);
        if (!violations.isEmpty()) {
            violations.forEach(violation -> errorMessage.add(violation.getMessage()));
            LOGGER.error(errorMessage.toString());
            throw new BadRequestException(errorMessage.toString());
        }
        LOGGER.info("Payload Request Validation Completed Successfully");
        return violations;
    }


    private static String getAsString(JsonNode jsonNode) {
        return jsonNode.isTextual() ? jsonNode.textValue() : jsonNode.toString();
    }

    private static <V> void add(JsonNode jsonNode, String key, V value) {
        if (value instanceof String) {
            ((ObjectNode) jsonNode).put(key, (String) value);
        } else if (value instanceof Short) {
            ((ObjectNode) jsonNode).put(key, (Short) value);
        } else if (value instanceof Integer) {
            ((ObjectNode) jsonNode).put(key, (Integer) value);
        } else if (value instanceof Long) {
            ((ObjectNode) jsonNode).put(key, (Long) value);
        } else if (value instanceof Float) {
            ((ObjectNode) jsonNode).put(key, (Float) value);
        } else if (value instanceof Double) {
            ((ObjectNode) jsonNode).put(key, (Double) value);
        } else if (value instanceof BigDecimal) {
            ((ObjectNode) jsonNode).put(key, (BigDecimal) value);
        } else if (value instanceof BigInteger) {
            ((ObjectNode) jsonNode).put(key, (BigInteger) value);
        } else if (value instanceof Boolean) {
            ((ObjectNode) jsonNode).put(key, (Boolean) value);
        } else if (value instanceof byte[]) {
            ((ObjectNode) jsonNode).put(key, (byte[]) value);
        } else {
            ((ObjectNode) jsonNode).put(key, toJson(value));
        }
    }

    /**
     * Unmarshalling json to provided generic type
     *
     * @param json input json
     * @param type generic Type
     * @return <tt>generic object</tt>
     */
    public static <T> T fromJson(String json, Class<T> type) {
        T resultObject;

        try {
            resultObject = objectMapper.readValue(json, type);
        } catch (Exception e) {
            throw new JsonException("Error while converting json to object - " + e);
        }

        return resultObject;
    }

    public static <T> List<T> readList(String str, Class<T> type) {
        return readList(str, ArrayList.class, type);
    }

    public static <T> List<T> readList(String str, Class<? extends Collection> type, Class<T> elementType) {
        try {
            return objectMapper.readValue(str, objectMapper.getTypeFactory().constructCollectionType(type, elementType));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Convert to Json from any object
     *
     * @param data Any object
     * @return <tt>Json string</tt>
     */
    public static String toJson(Object data) {

        Objects.requireNonNull(data);

        String outputJson;
        try {
            outputJson = objectMapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            throw new JsonException("Error while unmarshalling json to object - " + e);
        }

        return outputJson;
    }

    /**
     * Filter the corresponding json key with provided key path
     *
     * @param jsonData input json
     * @param keyPath  key to filter or find the corresponding node in input json
     * @return JsonNode {@link JsonNode}
     */
    public static JsonNode filterJsonNodeByKeys(String jsonData, String... keyPath) {
        JsonNode jsonNode = fromJson(jsonData, JsonNode.class);

        for (String key : keyPath) {
            if (jsonNode.has(key)) {
                jsonNode = jsonNode.path(key);
            } else {
                return null;
            }
        }

        return jsonNode;
    }

    public static JsonNode getKeysInJsonNode(String jsonData, String... keyPath) {
        JsonNode jsonNode;
        try {
            jsonNode = objectMapper.readTree(jsonData);
        } catch (IOException e) {
            return null;
        }
        for (String key : keyPath) {
            if (jsonNode.has(key)) {
                jsonNode = jsonNode.path(key);
            } else {
                return null;
            }
        }
        return jsonNode;
    }

    /**
     * Filter the corresponding json node with provided path
     *
     * @param rootNode {@link JsonNode}
     * @param path     filter key
     * @return JsonNode {@link JsonNode}
     */
    public static JsonNode filterJsonNodeByPath(JsonNode rootNode, String path) {
        final String[] keys = path.split("/");

        for (String key : keys) {
            if (rootNode.has(key)) {
                rootNode = rootNode.get(key);
            } else {
                return null;
            }
        }

        return rootNode;
    }

    /**
     * Find the json key if exist and return the value as string
     *
     * @param json input json
     * @param key  element to return as string
     * @return element value at the specified key
     */
    public static String getAsString(String json, String key) {
        String result = "";

        if (StringUtils.isNotEmpty(json)) {
            JsonNode jsonNode = filterJsonNodeByKeys(json, key);
            if (jsonNode != null) {
                result = getAsString(jsonNode);
            }
        }

        return result;
    }

    /**
     * Find the json key if exist and return the value as int
     *
     * @param json input json
     * @param key  element to find
     * @return element value at the specified key
     */
    public static int getAsInt(String json, String key) {
        int result = 0;

        if (StringUtils.isNotEmpty(json)) {
            JsonNode jsonNode = filterJsonNodeByKeys(json, key);
            if (jsonNode != null) {
                result = jsonNode.isInt() ? jsonNode.intValue() : Integer.parseInt(getAsString(jsonNode));
            }
        }

        return result;
    }

    /**
     * Find the json key if exist and return the value as int
     *
     * @param json input json
     * @param key  element to find
     * @return element value at the specified key
     */
    public static long getAsLong(String json, String key) {
        long result = 0L;

        if (StringUtils.isNotEmpty(json)) {
            JsonNode jsonNode = filterJsonNodeByKeys(json, key);
            if (jsonNode != null) {
                result = jsonNode.isLong() ? jsonNode.longValue() : Long.parseLong(getAsString(jsonNode));
            }
        }

        return result;
    }

    /**
     * Fetch the json element value as double
     *
     * @param json input json
     * @param key  element to find
     * @return element value at the specified key
     */
    public static double getAsDouble(String json, String key) {
        double result = 0.0;

        if (StringUtils.isNotEmpty(json)) {
            JsonNode jsonNode = filterJsonNodeByKeys(json, key);
            if (jsonNode != null) {
                result = jsonNode.isDouble() ? jsonNode.doubleValue() : Double.parseDouble(getAsString(jsonNode));
            }
        }

        return result;
    }

    /**
     * Find the json key if exist and return the value as {@link BigInteger}
     *
     * @param json input json
     * @param key  element to find
     * @return element value at the specified key
     */
    public static BigInteger getAsBigInteger(String json, String key) {
        BigInteger result = new BigInteger(String.valueOf(0));

        if (StringUtils.isNotEmpty(json)) {
            JsonNode jsonNode = filterJsonNodeByKeys(json, key);
            if (jsonNode != null) {
                result = jsonNode.isBigInteger() ? jsonNode.bigIntegerValue() : new BigInteger(getAsString(jsonNode));
            }
        }

        return result;
    }

    /**
     * Find the json key if exist and return the value as {@link BigDecimal}
     *
     * @param json input json
     * @param key  element to find
     * @return element value at the specified key
     */
    public static BigDecimal getAsBigDecimal(String json, String key) {
        BigDecimal result = new BigDecimal(String.valueOf(0.00));

        if (StringUtils.isNotEmpty(json)) {
            JsonNode jsonNode = filterJsonNodeByKeys(json, key);
            if (jsonNode != null) {
                result = jsonNode.isBigDecimal() ? jsonNode.decimalValue() : new BigDecimal(getAsString(jsonNode));
            }
        }

        return result;
    }

    /**
     * Find the json key if exist and return the value as boolean
     *
     * @param json input json
     * @param key  element to find
     * @return element value at the specified key
     */
    public static boolean getAsBoolean(String json, String key) {

        if (StringUtils.isEmpty(json)) {
            return false;
        }

        JsonNode jsonNode = filterJsonNodeByKeys(json, key);
        if (null == jsonNode) {
            return false;
        }

        boolean result = false;
        if (jsonNode.isBoolean()) {
            result = jsonNode.booleanValue();
        } else {
            if (jsonNode.isTextual()) {
                String textValue = jsonNode.textValue();
                if ("1".equals(textValue)) {
                    result = true;
                } else {
                    result = BooleanUtils.toBoolean(textValue);
                }
            } else {
                result = BooleanUtils.toBoolean(jsonNode.intValue());
            }
        }

        return result;
    }

    /**
     * Find the json key if exist and return the value as byte array
     *
     * @param json input json
     * @param key  element to find
     * @return element value at the specified key
     */
    public static byte[] getAsBytes(String json, String key) {
        byte[] result = null;

        try {

            if (StringUtils.isNotEmpty(json)) {
                JsonNode jsonNode = filterJsonNodeByKeys(json, key);
                if (jsonNode != null) {
                    result = jsonNode.isBinary() ? jsonNode.binaryValue() : getAsString(jsonNode).getBytes();
                }
            }
        } catch (Exception e) {
            throw new JsonException("Problem while reading as bytes - " + e);
        }
        return result;
    }

    /**
     * Append the given key and value in the input json
     *
     * @param json  input json string
     * @param key   new key to add in the input json
     * @param value value to be added for the json key
     * @return modified json with added key and value
     */
    public static <V> String add(String json, String key, V value) {
        try {
            JsonNode node = objectMapper.readTree(json);
            add(node, key, value);
            return node.toString();
        } catch (JsonProcessingException e) {
            throw new JsonException("Problem while adding json key - " + e.getMessage());
        }
    }

    /**
     * Remove the given key from the json input if exist
     *
     * @param json input json
     * @param key  element to remove
     * @return modified json with removed key
     */
    public static String remove(String json, String key) {
        try {
            JsonNode node = objectMapper.readTree(json);
            ((ObjectNode) node).remove(key);
            return node.toString();
        } catch (JsonProcessingException e) {
            throw new JsonException("Problem while removing json key - " + e.getMessage());
        }
    }

    /**
     * Update the provided element value in the input json
     *
     * @param json  input json
     * @param key   element to update
     * @param value new value to be set in the element
     * @return json with updated values
     */
    public static <V> String update(String json, String key, V value) {
        try {
            JsonNode node = objectMapper.readTree(json);
            ((ObjectNode) node).remove(key);
            add(node, key, value);
            return node.toString();
        } catch (JsonProcessingException e) {
            throw new JsonException("Problem while updating json key - " + e.getMessage());
        }
    }

    /**
     * Format the input json with pretty print
     *
     * @param json input json
     * @return formatted json
     */
    public static String format(String json) {
        try {
            JsonNode node = objectMapper.readTree(json);
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(node);
        } catch (JsonProcessingException e) {
            throw new JsonException("Problem while formatting json - " + e);
        }
    }

}
