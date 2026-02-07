package com.project1.quizapp.Controller;

import com.project1.quizapp.Entity.Question;
import com.project1.quizapp.Service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
@CrossOrigin(origins = "http://localhost:5173")
public class UrlController {
    @Autowired
    private UrlService urlService;

    @GetMapping("/allquestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return urlService.getAllQuestions();
    }

    @GetMapping("/question/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable int id){
        return urlService.getQuestionById(id);
    }

    @GetMapping("/level/{difLevel}")
    public ResponseEntity<List<Question>> getAllQuestionsByLevel(@PathVariable String difLevel){
        return urlService.getAllQuestionsByLevel(difLevel);
    }

    @PostMapping("/addquestion")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question){
        return urlService.addQuestion(question);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Question> updateQ(@PathVariable int id, @RequestBody Question question){
        return urlService.updateQ(id,question);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteId(@PathVariable int id){
        return urlService.deleteId(id);
    }

    @GetMapping("/quiz")
    public List<Question> getQuiz(@RequestParam String language,@RequestParam String level,@RequestParam int numQ){
        return urlService.getQuiz(language,level,numQ);
    }
}
