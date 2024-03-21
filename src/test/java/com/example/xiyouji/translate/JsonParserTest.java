package com.example.xiyouji.translate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JsonParserTest {

    @Autowired
    private JsonParser jsonParser;

    @Test
    void parse_ValidJson_ReturnsExpectedText() {
        // 준비
        String jsonInput = "{\"trans_result\":[{\"dst\":\"Translated text\"}]}";

        // 실행
        String result = jsonParser.parse(jsonInput);

        // 검증
        Assertions.assertEquals("Translated text", result);
    }

    @Test
    void parse_InvalidJson_ThrowsRuntimeException() {
        // 준비
        String invalidJsonInput = "{invalid json}";

        // 실행 & 검증
        Assertions.assertThrows(RuntimeException.class, () -> jsonParser.parse(invalidJsonInput));
    }
}