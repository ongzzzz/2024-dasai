package com.example.xiyouji.translate.service.impl;

import com.example.xiyouji.type.Language;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class BaiduTranslatorTest {

    @Autowired
    private BaiduTranslator baiduTranslator;

    private RestTemplate restTemplate;

    @BeforeEach
    public void before() {
        restTemplate = new RestTemplate();
    }


    @Test
    void translateCN_ValidResponse_ReturnsKRTranslation() throws JsonProcessingException {
        // 준비
        String beforeTranslate = "你好";

        // 실행
        String translation = baiduTranslator.translate(beforeTranslate, Language.CN, Language.KR);

        // 검증
        assertEquals("안녕하세요.", translation);
    }

    @Test
    void translateKR_ValidResponse_ReturnsCNTranslation() throws JsonProcessingException {
        // 준비
        String beforeTranslate = "안녕하세요.";

        // 실행
        String translation = baiduTranslator.translate(beforeTranslate, Language.KR, Language.CN);

        // 검증
        assertEquals("您好。", translation);
    }

    @Test
    void translateMany_ValidResponse_ReturnsKRTranslation() throws JsonProcessingException {
        // 준비
        String beforeTranslate = "你好";

        // 실행
        List<String> list = IntStream.range(0, 2)
                .mapToObj(i -> baiduTranslator.translate(beforeTranslate, Language.CN, Language.KR))
                .toList();

        // 검증
        assertEquals("안녕하세요.", list.get(0));
        assertEquals("안녕하세요.", list.get(1));
    }


    @Test
    void translate_InvalidLanguage_ReturnsIllegalArgumentException() {
        // 준비
        String beforeTranslate = "안녕하세요.";

        // 실행 & 검증
        assertThrows(IllegalArgumentException.class, () -> baiduTranslator.translate(beforeTranslate, Language.valueOf("wrong value"), Language.CN));
    }

  /*  @Test
    void translate_ApiThrowsException_ThrowsJsonProcessingException() {
        // 준비
        when(restTemplate.getForObject(any(String.class), any(Class.class), any(Map.class)))
                .thenThrow(new RuntimeException("API 호출 실패"));

        // 실행 & 검증
        assertThrows(JsonProcessingException.class, () -> baiduTranslator.translate("Hello", Language.ENGLISH, Language.CHINESE));
    }*/

}