package dev.leopassos.archetype.helpers;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class Helpers {
    
    public static String encodeFormData(Map<String, String> data) {
        StringBuilder encodedData = new StringBuilder();
        for (Map.Entry<String, String> entry : data.entrySet()) {
            if (!encodedData.isEmpty()) {
                encodedData.append("&");
            }
            encodedData.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8));
            encodedData.append("=");
            encodedData.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));
        }
        return encodedData.toString();
    }
}
