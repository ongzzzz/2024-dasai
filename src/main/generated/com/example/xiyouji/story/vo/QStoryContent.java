package com.example.xiyouji.story.vo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStoryContent is a Querydsl query type for StoryContent
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStoryContent extends EntityPathBase<StoryContent> {

    private static final long serialVersionUID = -1908449810L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStoryContent storyContent = new QStoryContent("storyContent");

    public final StringPath content = createString("content");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QStory story;

    public QStoryContent(String variable) {
        this(StoryContent.class, forVariable(variable), INITS);
    }

    public QStoryContent(Path<? extends StoryContent> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStoryContent(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStoryContent(PathMetadata metadata, PathInits inits) {
        this(StoryContent.class, metadata, inits);
    }

    public QStoryContent(Class<? extends StoryContent> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.story = inits.isInitialized("story") ? new QStory(forProperty("story")) : null;
    }

}

