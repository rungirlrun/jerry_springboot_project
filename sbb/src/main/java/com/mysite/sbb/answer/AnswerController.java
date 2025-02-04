package com.mysite.sbb.answer;

import java.security.Principal;			// 현재 로그인한 사용자에 대한 정보를 알기 위해 Spring Security의 Principal 객체 필요

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionService;
import com.mysite.sbb.user.SiteUser;
import com.mysite.sbb.user.UserService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {

	private final QuestionService questionService;
	private final AnswerService answerService;
	private final UserService userService;
	
	// 로그인이 필요한 메서드. 로그인 안되어 있으면 로그인 페이지로 이동시킴
	@PostMapping("/create/{id}")
	public String createAnswer(Model model, @PathVariable("id") Integer id, @Valid AnswerForm answerForm, BindingResult bindingResult, Principal principal)
	{
		Question question = this.questionService.getQuestion(id);
		SiteUser siteUser = this.userService.getUser(principal.getName());
		if (bindingResult.hasErrors()) {
			model.addAttribute("question", question);
			return "question_detail";
		}
		Answer answer = this.answerService.create(question, answerForm.getContent(), siteUser);
		return String.format("redirect:/question/detail/%s#answer_%s", answer.getQuestion().getId(), answer.getId());
	}
	
	// 답변 수정
	@PreAuthorize("isAuthenticated()")				// 로그인이 필요한 메서드. 로그인 안되어 있으면 로그인 페이지로 이동시킴
	@GetMapping("/modify/{id}")
	public String modifyAnswer(AnswerForm answerForm, @PathVariable("id") Integer id, Principal principal) {
		Answer answer = this.answerService.getAnswer(id);
		if (!answer.getAuthor().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
		}
		answerForm.setContent(answerForm.getContent());
		return "answer_form";
	}
	
	// 답변 수정 Post
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/modify/{id}")
	public String answerModify(@Valid AnswerForm answerForm, BindingResult bindingResult, @PathVariable("id") Integer id, Principal principal) {
		if (bindingResult.hasErrors()) {
			return "answer_form";
		}
		Answer answer = this.answerService.getAnswer(id);
		if (!answer.getAuthor().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
		}
		this.answerService.modify(answer, answerForm.getContent());
		return String.format("redirect:/question/detail/%s#answer_%s", answer.getQuestion().getId(), answer.getId());
	}
	
	// 답변 삭제
	@PreAuthorize("isAuthenticated()")				// 로그인이 필요한 메서드. 로그인 안되어 있으면 로그인 페이지로 이동시킴
	@GetMapping("/delete/{id}")
	public String answerDelete(Principal principal, @PathVariable("id") Integer id) {
		Answer answer = this.answerService.getAnswer(id);
		if (!answer.getAuthor().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
		}
		this.answerService.delete(answer);
		return String.format("redirect:/question/detail/%s", answer.getQuestion().getId());
	}

	// 답변 추천
	@PreAuthorize("isAuthenticated()")				// 로그인이 필요한 메서드. 로그인 안되어 있으면 로그인 페이지로 이동시킴
	@GetMapping("/vote/{id}")
	public String answerVote(Principal principal, @PathVariable("id") Integer id) {
		Answer answer = this.answerService.getAnswer(id);
		SiteUser siteUser = this.userService.getUser(principal.getName());
		this.answerService.vote(answer, siteUser);
		return String.format("redirect:/question/detail/%s#answer_%s",answer.getQuestion().getId(), answer.getId());
	}
}
