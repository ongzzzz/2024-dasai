package com.example.xiyouji.story.controller;

import com.example.xiyouji.story.dto.StoryDto;
import com.example.xiyouji.story.service.StoryService;
import com.example.xiyouji.type.Characters;
import com.example.xiyouji.type.Language;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StoryController {

    private final StoryService storyService;

    @GetMapping("/map/{storyId}/NONE/{language}")
    public ResponseEntity<StoryDto.StoryResponseDto> getMapStoryContent(@PathVariable String language, @PathVariable Long storyId) {
        StoryDto.StoryRequestDto storyRequestDto = StoryDto.StoryRequestDto.builder()
                .storyId(storyId)
                .language(Language.fromString(language))
                .build();

        return ResponseEntity.ok(storyService.getStory(storyRequestDto));
    }

    @GetMapping("/timeline/{storyTitle}/NONE/{language}")
    public ResponseEntity<StoryDto.StoryResponseDto> getTimelineStoryContent(@PathVariable String language, @PathVariable String storyTitle) {
        StoryDto.StoryRequestDto storyRequestDto = StoryDto.StoryRequestDto.builder()
                .storyTitle(storyTitle)
                .language(Language.fromString(language))
                .build();

        return ResponseEntity.ok(storyService.getStory(storyRequestDto));
    }

    @GetMapping("/character/{characterType}/{language}")
    public StoryDto.StoryResponseDto getCharacterStoryContent(@PathVariable String language, @PathVariable String characterType) {
        StoryDto.StoryRequestDto storyRequestDto = StoryDto.StoryRequestDto.builder()
                .character(Characters.fromString(characterType))
                .language(Language.fromString(language))
                .build();







        return storyService.getStory(storyRequestDto);
    }
}
