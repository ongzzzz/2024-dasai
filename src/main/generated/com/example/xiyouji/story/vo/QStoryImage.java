package com.example.xiyouji.story.vo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStoryImage is a Querydsl query type for StoryImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStoryImage extends EntityPathBase<StoryImage> {

    private static final long serialVersionUID = -1560761328L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStoryImage storyImage = new QStoryImage("storyImage");

    public final StringPath filename = createString("filename");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QStory story;

    public QStoryImage(String variable) {
        this(StoryImage.class, forVariable(variable), INITS);
    }

    public QStoryImage(Path<? extends StoryImage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStoryImage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStoryImage(PathMetadata metadata, PathInits inits) {
        this(StoryImage.class, metadata, inits);
    }

    public QStoryImage(Class<? extends StoryImage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.story = inits.isInitialized("story") ? new QStory(forProperty("story")) : null;
    }

}

