package com.peeyush.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.apache.commons.lang3.StringUtils;
import org.msgpack.jackson.dataformat.MessagePackFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Atul Kumar Gupta.
 */
public class JsonUtils {

	private static ObjectMapper	objectMapper = new ObjectMapper();
	private static ObjectMapper	objectMapperMessagePack	= new ObjectMapper(new MessagePackFactory());
	static {
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		objectMapperMessagePack.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapperMessagePack.setSerializationInclusion(Include.NON_NULL);
	}

	private JsonUtils() {
	}

	public static String getJsonStringFromObject(Object obj) throws JsonGenerationException,
					JsonMappingException, IOException {
		StringWriter writer = new StringWriter();
		objectMapper.writeValue(writer, obj);
		return writer.toString();
	}

	public static <T> T getObjectFromJsonString(Class<T> clazz, String requestJson,
					String callingMethod) throws IOException {
		T t = null;
		InputStream is = new ByteArrayInputStream(requestJson.getBytes("UTF-8"));
		t = (T) objectMapper.readValue(is, clazz);
		return t;
	}
  
// Passing Different Class as Common Parameter to a method in a different class

	public static <T> T getObjectFromJsonString(String json, Class<T> className)
					throws JsonParseException, JsonMappingException, IOException {
		return getObjectFromJsonString(className, json, "null");
	}

	public static <T> Object getObjectFromJsonString(String json, TypeReference<T> typeRef)
					throws IOException {
		InputStream is = new ByteArrayInputStream(json.getBytes("UTF-8"));
		return objectMapper.readValue(is, typeRef);
	}

	public static JsonNode convertObjectToJsonNode(Object object) {
		JsonNode jsonAdObject = objectMapper.valueToTree(object);
		return jsonAdObject;
	}

	public static <T> Object getObjectFromFile(String fileName, Class<T> className)
					throws JsonParseException, JsonMappingException, IOException {
		T obj = null;
		File file = new File(fileName);
		obj = objectMapper.readValue(file, className);
		return obj;
	}

	public static JsonNode getJsonNodeFromQueryParamsMap(Map<String, String[]> queryParams) {
		if (queryParams == null) {
			return null;
		}
		Map<String, String> queryParamsMap = new HashMap<String, String>();

		for (Map.Entry<String, String[]> entry : queryParams.entrySet()) {

			queryParamsMap.put(entry.getKey(), entry.getValue()[0]);

		}
		JsonNode jsonObject = objectMapper.valueToTree(queryParamsMap);

		return jsonObject;

	}

	public static JsonNode getFirstParamNodeFromQueryParams(Map<String, String[]> queryParams) {
		if (queryParams == null) {
			return null;
		}
		ObjectNode node = objectMapper.createObjectNode();
		for (String key : queryParams.keySet()) {
			node.put(key, queryParams.get(key)[0]);
		}
		return node;
	}

	public static <T> T getObjectFromJsonNode(JsonNode node, Class<T> className)
					throws JsonProcessingException {
		return objectMapper.treeToValue(node, className);
	}

	public static JsonNode getJsonNodeFromObject(Object object) {
		return objectMapper.valueToTree(object);
	}

	public static JsonNode getJsonNodeFromJsonString(String jsonString) throws JsonParseException,
					JsonMappingException, IOException {
		JsonNode retval = null;
		if (StringUtils.isNotEmpty(jsonString)) {
			retval = objectMapper.readValue(jsonString, JsonNode.class);
		}
		return retval;
	}

	public static ArrayNode createArrayNode() {
		return objectMapper.createArrayNode();
	}

	public static ObjectNode createObjectNode() {
		return objectMapper.createObjectNode();
	}

	public static byte[] messagePackSerialize(Object obj) throws JsonProcessingException {
		return objectMapperMessagePack.writeValueAsBytes(obj);
	}

	public static <T> Object messagePackDeserialize(byte[] b, Class<T> className)
					throws JsonParseException, JsonMappingException, IOException {
		return objectMapperMessagePack.readValue(b, className);
	}

	public static List<String> getSortedArrayNode(ArrayNode arrayNode) throws IOException {
		List<String> list = new ArrayList<String>();
		Iterator<JsonNode> it = arrayNode.iterator();
		while (it.hasNext()) {
			list.add(it.next().asText());
		}
		Collections.sort(list);
		return list;
	}

}
