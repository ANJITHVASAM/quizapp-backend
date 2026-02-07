package com.project1.quizapp.Service;

import com.project1.quizapp.Entity.Question;
import com.project1.quizapp.Repository.SqlData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UrlService {

    @Autowired
    private SqlData db;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try{
            List<Question> questionList=db.findAll();
            if(questionList.isEmpty()) return new ResponseEntity<>(questionList,HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(questionList,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Question> getQuestionById(int id) {
        try{
            return new ResponseEntity<>(db.findById(id).get(),HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(db.findById(id).get(),HttpStatus.OK);
        }
    }

    public ResponseEntity<List<Question>> getAllQuestionsByLevel(String difLevel) {
            try{
                List<Question> questionList=db.findByDifficultyLevel(difLevel);
                if(questionList.isEmpty()) return new ResponseEntity<>(questionList,HttpStatus.NO_CONTENT);
                return new ResponseEntity<>(questionList,HttpStatus.OK);
            } catch (Exception e){
                return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
            }
    }

    public ResponseEntity<Question> addQuestion(Question question) {
        try{
            db.save(question);
            return new ResponseEntity<>(question,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(question,HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Question> updateQ(int id, Question question) {
        try{
            question.setId(id);
            db.save(question);
            return new ResponseEntity<>(question,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(question,HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> deleteId(int id) {
        try{
            Question question=db.findById(id).get();
            db.deleteById(id);
            return new ResponseEntity<>("Deleted",HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>("Item Not Found To Delete",HttpStatus.NOT_FOUND);
        }
    }

    public List<Question> getQuiz(String language,String level,int numQ) {
        return db.findRandomQuestionsByCategory(language,level, numQ);
    }
}
