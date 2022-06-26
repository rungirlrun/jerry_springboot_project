package com.mysite.sbb.question;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor			// 생성자 자동으로 생성. final 키워드를 사용해야 함. 
@Controller
public class QuestionController {
	
	private final QuestionRepository questionRepository;
	
	@RequestMapping("/question/list")
	public String list(Model model) {
		List<Question> questionList = this.questionRepository.findAll();
		
		model.addAttribute("questionList", questionList);					// Model 객체는 자바 클래스와 템플릿 간의 연결고리 역할을 함. Model 객체에 값을 담아 두면 템플릿에서 그 값을 사용할 수 있음.
		return "question_list";				// @ResponseBody를 빼고 html 명 리턴
	}
}
