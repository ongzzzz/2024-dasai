package com.example.xiyouji.translate.service.impl;


import com.example.xiyouji.translate.JsonParser;
import com.example.xiyouji.translate.service.Translator;
import com.example.xiyouji.type.Language;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class BaiduTranslator implements Translator {

    private static final String TRANSLATE_URL = "https://api.fanyi.baidu.com/api/trans/vip/translate";
    @Value(value = "${baidu.translate.appid}")
    private String appId;

    @Value("${baidu.translate.secret}")
    private String secretKey;


    private final JsonParser jsonParser;

    public String translate(String query, Language from, Language to)  {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = new HashMap<>();
        String salt = String.valueOf(new Random().nextInt());
        String sign = generateSign(query, salt);

        params.put("q", query);
        params.put("from", from.getValue_baidu());
        params.put("to", to.getValue_baidu());
        params.put("appid", appId);
        params.put("salt", salt);
        params.put("sign", sign);


        sleepThread();//Baidu API 의 API 응답 시간 제한이 있는 것 같음, 0초로 설정 시 최초 응답이후 NULL 반환 됨

        String baiduResponse = restTemplate.getForObject(TRANSLATE_URL + "?q={q}&from={from}&to={to}&appid={appid}&salt={salt}&sign={sign}",
                String.class, params);

        return jsonParser.parse(baiduResponse);
    }

    private String generateSign(String query, String salt) {
        String src = appId + query + salt + secretKey;
        return DigestUtils.md5DigestAsHex(src.getBytes(StandardCharsets.UTF_8)).toLowerCase();
    }

    private void sleepThread() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}