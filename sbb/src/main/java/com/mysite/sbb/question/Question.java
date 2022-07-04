package com.mysite.sbb.question;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.user.SiteUser;

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
	
	private LocalDateTime modifyDate;							// 수정일시 컬럼 추가
	
	@OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
	private List<Answer> answerList;
	
	@ManyToOne													// 한 명의 사용자가 여러개의 질문글을 작성할 수 있음
	private SiteUser author;									// 이 컬럼에는 site_user의 id값이 자동 입력됨
	
	@ManyToMany													// 질문 하나에 여러 사람이 추천할 수 있고, 한 사람은 여러개의 질문을 추천할 수 있음. 이럴땐 대등한 관계 ManyToMany를 써야함. 
	Set<SiteUser> voter;										// List가 아닌 Set인 이유 : 한 사람 당 한 번만 추천할 수 있으므로
}
