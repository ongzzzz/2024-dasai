package com.example.xiyouji.story.service;

import com.example.xiyouji.exception.RestApiException;
import com.example.xiyouji.exception.impl.StoryErrorCode;
import com.example.xiyouji.store.FileConvert;
import com.example.xiyouji.store.FileHandler;
import com.example.xiyouji.store.InMemoryMultipartFile;
import com.example.xiyouji.store.UploadFile;
import com.example.xiyouji.story.dto.StoryDto;
import com.example.xiyouji.story.repository.StoryRepository;
import com.example.xiyouji.story.vo.Story;
import com.example.xiyouji.story.vo.StoryImage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoryService {

    private final StoryRepository storyRepository;

    private final FileHandler fileHandler;

    public StoryDto.StoryResponseDto getStory(StoryDto.StoryRequestDto storyRequestDto) {
        Story story = storyRepository.getStoryByIdAndLanguage(storyRequestDto.getStoryId(), storyRequestDto.getLanguage())
                .orElseThrow(() -> new RestApiException(StoryErrorCode.STORY_NOT_EXIST));

        List<String> fileNames = story.getStoryImages().stream()
                .map(StoryImage::getFilename)
                .toList();

        return story.toStoryResponseDto(fileHandler.getFullPaths(fileNames));
    }

    public StoryDto.StoryResponseDto getCharacterStory(StoryDto.StoryRequestDto storyRequestDto) {
        Story story = storyRepository.getStoryByCharactersAndLanguage(storyRequestDto.getCharacter(), storyRequestDto.getLanguage())
                .orElseThrow(() -> new RestApiException(StoryErrorCode.STORY_NOT_EXIST));

        return story.toStoryResponseDto();
    }

}
