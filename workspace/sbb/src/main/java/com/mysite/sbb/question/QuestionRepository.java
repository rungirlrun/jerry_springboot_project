package com.mysite.sbb.question;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/*
 * DB 테이블에 접근하는 메서드들을 사용하기 위한 인터페이스. CRUD를 어떻게 처리할지를 정의하는 계층.
 * JpaRepository 인터페이스 상속해야 함. 상속시에는 제너릭 타입으로 <리포지토리의 대상이 되는 엔티티 타입과, 해당 엔티티의 PK 타입을 지정해야 함.
 */
public interface QuestionRepository extends JpaRepository<Question, Integer> {

	Question findBySubject(String subject);
	Question findBySubjectAndContent(String subject, String content);
	List<Question> findBySubjectLike(String subject);
}
