package com.mysite.sbb.question;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.mysite.sbb.answer.Answer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity							
// @Entity 애너테이션을 적용해야 JPA가 엔티티로 인식
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)			// @GeneratedValue : sequence처럼 1씩 자동으로 증가. / GenerationType.IDENTITY는 해당 컬럼만의 독립적인 시퀀스를 생성하여 번호를 증가시킬 때 사용
	private Integer id;
	
	@Column(length = 200)
	private String subject;
	
	@Column(columnDefinition = "TEXT")							// columnDefinition = "TEXT"은 "내용"처럼 글자 수를 제한할 수 없는 경우에 사용
	private String content;
	
	private LocalDateTime createDate;
	
	@OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
	private List<Answer> answerList;
}
