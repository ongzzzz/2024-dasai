package com.example.xiyouji.type;

import com.example.xiyouji.exception.RestApiException;
import com.example.xiyouji.exception.impl.EnumErrorCode;

public enum Language {
    KR("kr", "KR", "kor"),
    CN("cn","CN", "zh"),
    ;

    private final String value;
    private final String value2;

    private final String value_baidu;

    Language(String value, String value2, String value_baidu) {
        this.value = value;
        this.value2 = value2;
        this.value_baidu = value_baidu;
    }

    public String getValue_upper() {
        return value2;
    }

    public String getValue_low() {
        return value;
    }

    public String getValue_baidu() {
        return value_baidu;
    }

    public static Language fromString(String value) {
        for (Language language : Language.values()) {
            if (language.getValue_low().equalsIgnoreCase(value)) {
                return language;
            }
        }
        for (Language language : Language.values()) {
            if (language.getValue_upper().equalsIgnoreCase(value)) {
                return language;
            }
        }
        throw new RestApiException(EnumErrorCode.ENUM_NOT_FOUNDED);
        // 또는 null 반환을 원하면 return null; 사용
    }
}
