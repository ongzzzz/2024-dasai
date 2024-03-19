package com.example.xiyouji.quiz.vo;



import com.example.xiyouji.type.Characters;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private Characters characterType;

    private String content_kr;

    private String content_cn;


    public Quiz(Long id, Characters characterType, String content_kr, String content_cn) {
        this.id = id;
        this.characterType = characterType;
        this.content_kr = content_kr;
        this.content_cn = content_cn;
    }
}
