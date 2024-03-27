package com.example.xiyouji.type;

import com.example.xiyouji.exception.RestApiException;
import com.example.xiyouji.exception.impl.EnumErrorCode;

public enum Characters {
    손오공("sunwukong", "孙悟空"),
    삼장법사("samjang", "三藏法師"),
    사오정("shawujing", "沙悟净"),
    저팔계("zhubajie", "猪八戒"),
    NONE("NONE", "无"),
    ;

    private final String value;
    private final String value2;

    Characters(String value, String value2) {
        this.value = value;
        this.value2 = value2;
    }

    public String getValue_kr() {
        return value;
    }

    public String getValue_cn() {
        return value2;
    }



    public static Characters fromString(String value) {
        for (Characters character : Characters.values()) {
            if (character.getValue_kr().equalsIgnoreCase(value)) {
                return character;
            }
        }
        for (Characters character : Characters.values()) {
            if (character.getValue_cn().equalsIgnoreCase(value)) {
                return character;
            }
        }
        throw new RestApiException(EnumErrorCode.ENUM_NOT_FOUNDED);
        // 또는 null 반환을 원하면 return null; 사용
    }


}
