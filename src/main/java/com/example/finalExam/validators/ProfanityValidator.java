package com.example.finalExam.validators;

import com.example.finalExam.configuration.API_KeyReader;
import com.example.finalExam.exceptions.ProfanityFilterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class ProfanityValidator {

    private static final Logger logger = LoggerFactory.getLogger(ProfanityValidator.class);

    private static final String API_KEY = API_KeyReader.getApiKey();


    public static boolean hasProfanity(String name, String description) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Api-Key", API_KEY);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<ProfanityFilterAPIResponse> responseEntity = restTemplate
                    .exchange("https://api.api-ninjas.com/v1/profanityfilter?text=" + name + description,
                            HttpMethod.GET,
                            entity,
                            ProfanityFilterAPIResponse.class);

            logger.info("Profanity Validator - {}", responseEntity.getBody());
            return responseEntity.getBody().isHas_profanity();
        } catch (Exception e) {
            throw new ProfanityFilterException();
        }

    }
}
