package com.example.xiyouji.translate;


import com.example.xiyouji.type.Language;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class BaiduTranslateService implements Translator {
    @Value("${baidu.translate.appid}")
    private String appId;

    @Value("${baidu.translate.secret}")
    private String secretKey;

    private static final String TRANSLATE_URL = "https://api.fanyi.baidu.com/api/trans/vip/translate";

    public String translate(String query, Language from, Language to) {
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

        return restTemplate.getForObject(TRANSLATE_URL + "?q={q}&from={from}&to={to}&appid={appid}&salt={salt}&sign={sign}",
                String.class, params);
    }

    private String generateSign(String query, String salt) {
        String src = appId + query + salt + secretKey;
        return DigestUtils.md5DigestAsHex(src.getBytes(StandardCharsets.UTF_8)).toLowerCase();
    }
}