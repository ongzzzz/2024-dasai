package com.example.xiyouji.quiz.vo;



import com.example.xiyouji.quiz.dto.QuizDto;
import com.example.xiyouji.type.Characters;
import com.example.xiyouji.type.Language;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@Inheritance(strategy = InheritanceType.JOINED) // 상속 전략 설정
@DiscriminatorColumn(name = "type") // 상속받는 클래스를 구분하는 컬럼
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated
    private List<Characters> characterType;

    private String quizContent;

    private String answerDescription;

    private Language language;


    public Quiz(Long id, List<Characters> characterType, String quizContent, String answerDescription, Language language) {
        this.id = id;
        this.characterType = characterType;
        this.quizContent = quizContent;
        this.answerDescription = answerDescription;
        this.language = language;
    }

    public abstract QuizDto.QuizResponseDto toQuizResponse();
}
