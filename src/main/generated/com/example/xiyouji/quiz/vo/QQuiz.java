package com.example.xiyouji.quiz.vo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QQuiz is a Querydsl query type for Quiz
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QQuiz extends EntityPathBase<Quiz> {

    private static final long serialVersionUID = -395464637L;

    public static final QQuiz quiz = new QQuiz("quiz");

    public final StringPath answerDescription = createString("answerDescription");

    public final EnumPath<com.example.xiyouji.type.Characters> characterType = createEnum("characterType", com.example.xiyouji.type.Characters.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<com.example.xiyouji.type.Language> language = createEnum("language", com.example.xiyouji.type.Language.class);

    public final StringPath quizContent = createString("quizContent");

    public QQuiz(String variable) {
        super(Quiz.class, forVariable(variable));
    }

    public QQuiz(Path<? extends Quiz> path) {
        super(path.getType(), path.getMetadata());
    }

    public QQuiz(PathMetadata metadata) {
        super(Quiz.class, metadata);
    }

}

