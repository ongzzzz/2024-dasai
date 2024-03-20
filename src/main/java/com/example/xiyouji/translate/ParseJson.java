package com.example.xiyouji.translate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ParseJson {

    private String jsonStr;

    public ParseJson(String jsonStr) {
        this.jsonStr = jsonStr;
    }

    public String parse() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(jsonStr);

        // trans_result 배열에서 첫 번째 요소의 dst 값을 추출
        return rootNode.path("trans_result").get(0).path("dst").asText();
    }

}
