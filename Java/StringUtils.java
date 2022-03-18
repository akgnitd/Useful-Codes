package com.akg.utility;

import org.apache.commons.text.similarity.LevenshteinDistance;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class StringUtils {

    private static final String salutationRegex = "^(MRS|MR|MS|MISS|SHRI)\\.?\\s+";

    private static final LevenshteinDistance levenshteinDistance = new LevenshteinDistance();

    public static Integer getDistance(String left, String right) {
        return levenshteinDistance.apply(left, right);
    }

    public static String getTrimmedAndSanitisedString(String receivedStr) {
        String trimmedStr = receivedStr.trim().toUpperCase();
        String sanitisedStr = removeSalutations(trimmedStr);
        sanitisedStr = removeSpecialCharacters(sanitisedStr);
        return sanitisedStr;
    }

    public static List<String> getStringParts(String sanitisedStr) {
        List<String> splitStr = splitStringByDelimiter(sanitisedStr, "\\s|\\.");
        List<String> parts = getValidParts(splitStr);

        return parts;
    }

    public static String removeSalutations(String str) {
        return str.replaceFirst(salutationRegex, "");
    }

    private static String removeSpecialCharacters(String str) {
        return str.replaceAll("[^\\dA-Za-z\\.\\s]", "");
    }


    private static List<String> splitStringByDelimiter(String name, String delimiter) {
        return Arrays.asList(name.split(delimiter));
    }

    private static List<String> getValidParts(List<String> nameParts) {
        return nameParts.stream().filter(part -> !isBlank(part)).collect(Collectors.toList());
    }

    public static String replaceAll(StringBuilder sb, String find, String replace) {
        return Pattern.compile(find).matcher(sb).replaceAll(replace);
    }
    
}
