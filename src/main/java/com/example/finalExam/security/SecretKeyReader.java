package com.example.finalExam.security;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SecretKeyReader {
    private static final String SECRET_KEY_PROPERTY = "secret_key";

    public static String getSecretKeyProperty() {
        Properties properties = new Properties();
        try (InputStream inputStream = new ClassPathResource("jwt_secret_key.properties").getInputStream()) {
            properties.load(inputStream);
            return properties.getProperty(SECRET_KEY_PROPERTY);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
