package com.example.xiyouji.story.vo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStory is a Querydsl query type for Story
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStory extends EntityPathBase<Story> {

    private static final long serialVersionUID = -967975925L;

    public static final QStory story = new QStory("story");

    public final EnumPath<com.example.xiyouji.type.Characters> characters = createEnum("characters", com.example.xiyouji.type.Characters.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<com.example.xiyouji.type.Language> language = createEnum("language", com.example.xiyouji.type.Language.class);

    public final ListPath<StoryContent, QStoryContent> storyContent = this.<StoryContent, QStoryContent>createList("storyContent", StoryContent.class, QStoryContent.class, PathInits.DIRECT2);

    public final ListPath<StoryImage, QStoryImage> storyImages = this.<StoryImage, QStoryImage>createList("storyImages", StoryImage.class, QStoryImage.class, PathInits.DIRECT2);

    public final StringPath storyTitle = createString("storyTitle");

    public QStory(String variable) {
        super(Story.class, forVariable(variable));
    }

    public QStory(Path<? extends Story> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStory(PathMetadata metadata) {
        super(Story.class, metadata);
    }

}

