package com.example.xiyouji.quiz.vo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QChoiceQuiz is a Querydsl query type for ChoiceQuiz
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QChoiceQuiz extends EntityPathBase<ChoiceQuiz> {

    private static final long serialVersionUID = -87023868L;

    public static final QChoiceQuiz choiceQuiz = new QChoiceQuiz("choiceQuiz");

    public final QQuiz _super = new QQuiz(this);

    //inherited
    public final StringPath answerDescription = _super.answerDescription;

    public final NumberPath<Integer> answerNum = createNumber("answerNum", Integer.class);

    //inherited
    public final EnumPath<com.example.xiyouji.type.Characters> characterType = _super.characterType;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final EnumPath<com.example.xiyouji.type.Language> language = _super.language;

    public final ListPath<String, StringPath> options = this.<String, StringPath>createList("options", String.class, StringPath.class, PathInits.DIRECT2);

    //inherited
    public final StringPath quizContent = _super.quizContent;

    public QChoiceQuiz(String variable) {
        super(ChoiceQuiz.class, forVariable(variable));
    }

    public QChoiceQuiz(Path<? extends ChoiceQuiz> path) {
        super(path.getType(), path.getMetadata());
    }

    public QChoiceQuiz(PathMetadata metadata) {
        super(ChoiceQuiz.class, metadata);
    }

}

