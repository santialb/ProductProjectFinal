package com.example.finalExam.configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class API_KeyReader {

    private static final String API_KEY_PROPERTY = "PROFANITY_FILTER_API_KEY";

    public static String getApiKey() {
        Properties properties = new Properties();
        try (InputStream inputStream = new ClassPathResource("api_key.properties").getInputStream()) {
            properties.load(inputStream);
            return properties.getProperty(API_KEY_PROPERTY);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
