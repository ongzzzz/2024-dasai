package com.example.xiyouji.story.service;

import com.example.xiyouji.exception.StoryNotExistException;
import com.example.xiyouji.story.dto.StoryDto;
import com.example.xiyouji.story.repository.StoryRepository;
import com.example.xiyouji.story.vo.Story;

import com.example.xiyouji.type.Characters;
import com.example.xiyouji.type.Language;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class StoryServiceTest {

    @MockBean
    private StoryRepository storyRepository;

    @Autowired
    private StoryService storyService;



    @Test
    public void whenKRStoryExists_thenStoryShouldBeReturned() {
        // 준비
        StoryDto.StoryRequestDto requestDto = StoryDto.StoryRequestDto.builder()
                .language(Language.KR)
                .storyTitle("제목입니다.")
                .build();

        Story mockStory = Story.builder()
                .characters(Characters.NONE)
                .language(Language.KR)
                .storyContent("한국어 내용입니다.")
                .storyTitle("제목입니다.")
                .build();
        when(storyRepository.getStoryByStoryTitleAndLanguage("제목입니다.", Language.KR))
                .thenReturn(Optional.of(mockStory));

        // 실행
        StoryDto.StoryResponseDto responseDto = storyService.getStory(requestDto);

        // 검증
        assertEquals("한국어 내용입니다.", responseDto.getStoryContent());
    }

    @Test
    public void whenStoryCNExists_thenStoryShouldBeReturned() {
        // 준비
        StoryDto.StoryRequestDto requestDto = StoryDto.StoryRequestDto.builder()
                .language(Language.CN)
                .storyTitle("中文题目")
                .build();

        Story mockStory = Story.builder()
                .characters(Characters.NONE)
                .language(Language.CN)
                .storyContent("中文内容")
                .storyTitle("中文题目")
                .build();
        when(storyRepository.getStoryByStoryTitleAndLanguage("中文题目", Language.CN))
                .thenReturn(Optional.of(mockStory));

        // 실행
        StoryDto.StoryResponseDto responseDto = storyService.getStory(requestDto);

        // 검증
        assertEquals("中文内容", responseDto.getStoryContent());
    }

    @Test
    public void whenStoryKRRequestedAndCNExists_thenStoryNotExistExceptionShouldBeThrown() {
        // 준비
        StoryDto.StoryRequestDto requestDto = StoryDto.StoryRequestDto.builder()
                .language(Language.KR)
                .storyTitle("中文题目")
                .build();

        when(storyRepository.getStoryByStoryTitleAndLanguage("中文题目", Language.KR))
                .thenReturn(Optional.empty());

        // 실행 & 검증
        assertThrows(StoryNotExistException.class, () -> {
            storyService.getStory(requestDto);
        });
    }

    @Test
    public void whenCharacterStoryCNExists_thenStoryShouldBeReturned() {
        // 준비
        StoryDto.StoryRequestDto requestDto = StoryDto.StoryRequestDto.builder()
                .language(Language.CN)
                .character(Characters.손오공)
                .build();

        Story mockStory = Story.builder()
                .characters(Characters.손오공)
                .language(Language.CN)
                .storyContent("孙悟空内容")
                .build();
        when(storyRepository.getStoryByCharacters(Characters.손오공))
                .thenReturn(Optional.of(mockStory));

        // 실행
        StoryDto.StoryResponseDto responseDto = storyService.getCharacterStory(requestDto);

        // 검증
        assertEquals("孙悟空内容", responseDto.getStoryContent());
    }

    @Test
    public void whenCharacterStoryCNExists_thenStoryNotExistExceptionShouldBeThrown() {
        // 준비
        StoryDto.StoryRequestDto requestDto = StoryDto.StoryRequestDto.builder()
                .language(Language.CN)
                .character(Characters.저팔계)
                .build();

        when(storyRepository.getStoryByCharacters(Characters.저팔계))
                .thenReturn(Optional.empty());

        assertThrows(StoryNotExistException.class, () -> {
            storyService.getStory(requestDto);
        });
    }
}