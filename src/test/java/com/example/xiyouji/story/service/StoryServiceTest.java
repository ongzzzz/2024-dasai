package com.example.xiyouji.story.service;

import com.example.xiyouji.exception.RestApiException;
import com.example.xiyouji.story.dto.StoryDto;
import com.example.xiyouji.story.repository.StoryContentRepository;
import com.example.xiyouji.story.repository.StoryImageRepository;
import com.example.xiyouji.story.repository.StoryRepository;
import com.example.xiyouji.story.vo.Story;

import com.example.xiyouji.story.vo.StoryContent;
import com.example.xiyouji.story.vo.StoryImage;
import com.example.xiyouji.type.Characters;
import com.example.xiyouji.type.Language;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static com.example.xiyouji.instance.Instance.fileDir;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class StoryServiceTest {

    @Autowired
    private StoryService storyService;

    @MockBean
    private StoryRepository storyRepository;

    @MockBean
    private StoryContentRepository storyContentRepository;

    @MockBean
    private StoryImageRepository storyImageRepository;


    @Test
    public void whenKRStoryExists_thenStoryShouldBeReturnedStoryContent() {
        // 준비
        StoryDto.StoryRequestDto requestDto = StoryDto.StoryRequestDto.builder()
                .language(Language.KR)
                .storyTitle("제목입니다.")
                .storyId(1L)
                .build();

        Story mockStory = Story.builder()
                .characters(Characters.NONE)
                .language(Language.KR)
                .storyContent(List.of(StoryContent.builder()
                        .content("한국어 내용입니다.")
                        .build()))
                .storyTitle("제목입니다.")
                .build();

        when(storyRepository.getStoryByIdAndLanguage(1L, Language.KR))
                .thenReturn(Optional.of(mockStory));

        // 실행
        StoryDto.StoryResponseDto responseDto = storyService.getStory(requestDto);

        // 검증
        assertEquals("한국어 내용입니다.", responseDto.getStoryContents().get(0));
    }

    @Test
    public void whenKRStoryExists_thenStoryShouldBeReturnedStoryContentAndImage() {
        // 준비
        StoryDto.StoryRequestDto requestDto = StoryDto.StoryRequestDto.builder()
                .language(Language.KR)
                .storyId(1L)
                .build();

        Story mockStory = Story.builder()
                .characters(Characters.NONE)
                .language(Language.KR)
                .storyContent(List.of(StoryContent.builder()
                        .content("한국어 내용입니다.")
                        .build()))
                .storyImages(List.of(StoryImage.builder()
                        .filename("손오공.png")
                        .build()))
                .storyTitle("제목입니다.")
                .build();

        when(storyRepository.getStoryByIdAndLanguage(1L, Language.KR))
                .thenReturn(Optional.of(mockStory));

        // 실행
        StoryDto.StoryResponseDto responseDto = storyService.getStory(requestDto);

        // 검증
        assertEquals("한국어 내용입니다.", responseDto.getStoryContents().get(0));
        assertEquals(fileDir + "손오공.png", responseDto.getStoryImagesUrl().get(0));
    }

    @Test
    public void whenStoryCNExists_thenStoryShouldBeReturnedStoryContent() {
        // 준비
        StoryDto.StoryRequestDto requestDto = StoryDto.StoryRequestDto.builder()
                .language(Language.CN)
                .storyTitle("中文题目")
                .storyId(1L)
                .build();

        Story mockStory = Story.builder()
                .characters(Characters.NONE)
                .language(Language.CN)
                .id(1L)
                .storyContent(List.of(StoryContent.builder()
                        .content("中文内容")
                        .build()))
                .storyTitle("中文题目")
                .build();

        when(storyRepository.getStoryByIdAndLanguage(1L, Language.CN))
                .thenReturn(Optional.of(mockStory));

        // 실행
        StoryDto.StoryResponseDto responseDto = storyService.getStory(requestDto);

        // 검증
        assertEquals("中文内容", responseDto.getStoryContents().get(0));
    }

    @Test
    public void whenStoryCNExists_thenStoryShouldBeReturnedStoryContentAndImage() {
        // 준비
        StoryDto.StoryRequestDto requestDto = StoryDto.StoryRequestDto.builder()
                .language(Language.CN)
                .storyTitle("中文题目")
                .storyId(1L)
                .build();

        Story mockStory = Story.builder()
                .characters(Characters.NONE)
                .language(Language.CN)
                .id(1L)
                .storyContent(List.of(StoryContent.builder()
                        .content("中文内容")
                        .build()))
                .storyImages(List.of(StoryImage.builder()
                        .filename("손오공.png")
                        .build()))
                .storyTitle("中文题目")
                .build();

        when(storyRepository.getStoryByIdAndLanguage(1L, Language.CN))
                .thenReturn(Optional.of(mockStory));

        // 실행
        StoryDto.StoryResponseDto responseDto = storyService.getStory(requestDto);

        // 검증
        assertEquals("中文内容", responseDto.getStoryContents().get(0));
        assertEquals(fileDir + "손오공.png", responseDto.getStoryImagesUrl().get(0));
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
        assertThrows(RestApiException.class, () -> {
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
                .storyContent(List.of(StoryContent.builder()
                        .content("孙悟空内容")
                        .build()))
                .build();

        Story mockStory_kr = Story.builder()
                .characters(Characters.손오공)
                .language(Language.KR)
                .storyContent(List.of(StoryContent.builder()
                        .content("손오공 내용")
                        .build()))
                .build();
        when(storyRepository.getStoryByCharactersAndLanguage(Characters.손오공, Language.CN))
                .thenReturn(Optional.of(mockStory));

        // 실행
        StoryDto.StoryResponseDto responseDto = storyService.getCharacterStory(requestDto);


        // 검증
        assertEquals("孙悟空内容", responseDto.getStoryContents().get(0));
    }

    @Test
    public void whenCharacterStoryCNExists_thenStoryNotExistExceptionShouldBeThrown() {
        // 준비
        StoryDto.StoryRequestDto requestDto = StoryDto.StoryRequestDto.builder()
                .language(Language.CN)
                .character(Characters.저팔계)
                .build();

        when(storyRepository.getStoryByCharactersAndLanguage(Characters.저팔계, Language.CN))
                .thenReturn(Optional.empty());

        assertThrows(RestApiException.class, () -> {
            storyService.getStory(requestDto);
        });
    }
}