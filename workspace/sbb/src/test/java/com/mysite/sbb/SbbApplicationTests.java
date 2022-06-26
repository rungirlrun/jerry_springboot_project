package com.mysite.sbb;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.answer.AnswerRepository;
import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionRepository;

@SpringBootTest
class SbbApplicationTests {

	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private AnswerRepository answerRepository;
	
	@Transactional									// 이 애너테이션을 사용하면 메서드가 종료될때까지 DB 세션이 유지 됨.
	@Test
	void testJpa() {
		/* List<Question> all = this.questionRepository.findAll();
		assertEquals(2, all.size());			// assertEquals는 assertEquals(기대값, 실제값)와 같이 사용하고 기대값과 실제값이 동일한지를 조사
	
		Question q = all.get(0);				// all 객체에서 첫번째 요소를 가져옴. 
		assertEquals("sbb가 무엇인가요?", q.getSubject()); */
		
		/*
		Optional<Question> oq = this.questionRepository.findById(1);		// question_id가 1번인 데이터 조회
		if(oq.isPresent()) {
			Question q = oq.get();											// oq에서 실제 객체 조회
			assertEquals("sbb가 무엇인가요?", q.getSubject());		}			// 객체에서 subject 값 가져온 후 비교값과 매칭.
		*/
		
		/*Question q = this.questionRepository.findBySubject("sbb가 무엇인가요?");		// questionRepository에서 만든 커스텀 메서드
		assertEquals(1, q.getId());*/
		
		/*Question q = this.questionRepository.findBySubjectAndContent("sbb가 무엇인가요?", "sbb에 대해서 알고 싶습니다.");		// questionRepository에서 만든 커스텀 메서드
		assertEquals(1, q.getId());*/
		
		/* List<Question> qList = this.questionRepository.findBySubjectLike("sbb%");
		Question q = qList.get(0);
		assertEquals("sbb가 무엇인가요?", q.getSubject()); */
		
		/*
		Optional<Question> oq = this.questionRepository.findById(1);
		assertTrue(oq.isPresent());				// 값이 True인지 테스트
		Question q = oq.get();
		q.setSubject("수정된 제목");
		this.questionRepository.save(q);*/
		
		/*
		assertEquals(2, this.questionRepository.count());
		Optional<Question> oq = this.questionRepository.findById(1);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		this.questionRepository.delete(q);
		assertEquals(1, this.questionRepository.count());
		*/
		
		/*
		Optional<Question> oq = this.questionRepository.findById(2);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		
		Answer a = new Answer();
		a.setContent("네 자동으로 생성됩니다.");
		a.setQuestion(q);
		a.setCreateDate(LocalDateTime.now());
		this.answerRepository.save(a); */
		
		/*
		Optional<Answer> oa = this.answerRepository.findById(1);
		assertTrue(oa.isPresent());
		Answer a = oa.get();
		assertEquals(2, a.getQuestion().getId());*/
		
		Optional<Question> oq = this.questionRepository.findById(2);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		
		List<Answer> answerList = q.getAnswerList();
		
		assertEquals(1, answerList.size());
		assertEquals("네 자동으로 생성됩니다.", answerList.get(0).getContent());
		}
		
	}
