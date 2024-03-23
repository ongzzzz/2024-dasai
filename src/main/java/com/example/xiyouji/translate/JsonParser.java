package com.example.xiyouji.translate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JsonParser {

    public String parse(String jsonStr)  {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = null;
        try {
            rootNode = objectMapper.readTree(jsonStr);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Json parse exception");
        }

        return rootNode.path("trans_result").get(0).path("dst").asText();
    }

}
