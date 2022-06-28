package com.mysite.sbb.question;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController {
	
	private final QuestionService questionService;
	
	@RequestMapping("/list")
	public String list(Model model) {
		List<Question> questionList = this.questionService.getList();		// this가 필요한 이유 :questionService라는 객체는 이 QuestionController 라는 클래스 안에 구현되어 있기 때문. 
		model.addAttribute("questionList", questionList);
		return "question_list";
	}
	
	@RequestMapping("/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {		// question의 id처럼 변하는 id값을 얻기 위해 @PathVariable 애너테이션 사용이 필요. 
		Question question = this.questionService.getQuestion(id);			// 이때 url 파라미터에서 사용한 매개변수명과 PathVariable의 매개변수 이름이 동일해야 함. 
		model.addAttribute("question", question);
		return "question_detail";											
	}
}
