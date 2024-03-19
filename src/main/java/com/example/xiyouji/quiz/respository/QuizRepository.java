package com.example.xiyouji.quiz.respository;

import com.example.xiyouji.quiz.vo.Quiz;
import com.example.xiyouji.type.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

    List<Quiz> findQuizzesByLanguage(Language language);
}
