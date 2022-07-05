package com.mysite.sbb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mysite.sbb.answer.AnswerService;
import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionService;
import com.mysite.sbb.user.SiteUser;
import com.mysite.sbb.user.UserService;

@SpringBootTest
class SbbApplicationTests {

	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private AnswerService answerService;

	@Autowired
	private UserService userService;
	
	@Test
	void contextLoads() {
		for (int i = 1; i <= 300; i++) {
			Question question = this.questionService.getQuestion(307);
			SiteUser siteUser = this.userService.getUser("admin");
			String content = "내용없음";
			this.answerService.create(question, content, siteUser);
		}
	}

}