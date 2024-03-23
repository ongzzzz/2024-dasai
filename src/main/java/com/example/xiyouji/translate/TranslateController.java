package com.example.xiyouji.translate;

import com.example.xiyouji.translate.service.impl.BaiduTranslator;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TranslateController {

    private final BaiduTranslator baiduTranslator;


    /**
     * 테스트 컨트롤러, 테스트를 위해 생성.
     * @return
     * @throws JsonProcessingException
     */
/*    @GetMapping("/translate/en")
    public String translate_en() throws JsonProcessingException {
        String jsonStr = baiduTranslator.translate("你好", Language.CN, Language.KR);
        ParseJson parseJson = new ParseJson(jsonStr);


        System.out.println(parseJson.parse()); // 출력: 안녕하세요.
        return parseJson.parse();
    }*/

}
