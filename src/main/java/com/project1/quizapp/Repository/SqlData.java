package com.project1.quizapp.Repository;

import com.project1.quizapp.Entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SqlData extends JpaRepository<Question,Integer> {
    List<Question> findByDifficultyLevel(String difLevel);
    @Query(value = "SELECT * FROM question q WHERE LOWER(q.language)=LOWER(:language) AND LOWER(q.difficulty_level)=LOWER(:level) ORDER BY RAND() LIMIT :numQ", nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(String language,String level,int numQ);
}

