package com.example.xiyouji.translate;

import com.example.xiyouji.type.Language;
import org.springframework.stereotype.Component;


public interface Translator {
    String translate(String query, Language from, Language to);
}
