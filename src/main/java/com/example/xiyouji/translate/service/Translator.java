package com.example.xiyouji.translate.service;

import com.example.xiyouji.type.Language;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Component;


public interface Translator {
    String translate(String query, Language from, Language to) throws JsonProcessingException;
}
