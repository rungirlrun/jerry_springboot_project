package com.mysite.sbb.answer;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.mysite.sbb.question.Question;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Answer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(columnDefinition = "TEXT")
	private String content;
	
	private LocalDateTime createDate;
	
	@ManyToOne									// 답변은 질문과 다대일 관계이다. 즉 답변이 많은 것, 질문이 하나가 됨. Answer 엔티티의 question 속성과 Question 엔티티가 서로 연결됨. 
	private Question question;					// 답변 엔티티에서 질문 엔티티를 참조하기 위해 추가함. 
}
