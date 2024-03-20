package com.example.xiyouji.story.service;

import com.example.xiyouji.exception.StoryNotExistException;
import com.example.xiyouji.story.dto.StoryDto;
import com.example.xiyouji.story.repository.StoryRepository;
import com.example.xiyouji.story.vo.Story;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoryService {

    private final StoryRepository storyRepository;

    public StoryDto.StoryResponseDto getStory(StoryDto.StoryRequestDto storyRequestDto) {
        Story story = storyRepository.getStoryByStoryTitleAndLanguage(storyRequestDto.getStoryTitle(), storyRequestDto.getLanguage())
                .orElseThrow(StoryNotExistException::new);

        return story.toStoryResponseDto();
    }

    public StoryDto.StoryResponseDto getCharacterStory(StoryDto.StoryRequestDto storyRequestDto) {
        Story story = storyRepository.getStoryByCharactersAndLanguage(storyRequestDto.getCharacter(), storyRequestDto.getLanguage())
                .orElseThrow(StoryNotExistException::new);

        return story.toStoryResponseDto();
    }
}
