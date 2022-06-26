package com.mysite.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 * @Controller : 해당 클래스가 컨트롤러의 기능을 수행한다는 의미. 이게 있어야 SB 프레임워크가 컨트롤러로 인식.
 * @RequestMapping("/어쩌구") : URL요청이 발생하면 hello 메서드가 실행됨. URL과 hello 메서드를 매핑하는 역할
 * @ResponseBody : hello 메서드의 응답 결과가 문자열 그 자체임을 나타냄. 
 * 
 * Spring Boot Devtools를 설치하면 서버 재시작 없이도 클래스 변경시 서버가 자동으로 재기동
 */

@Controller
public class HelloController {				
	@RequestMapping("/hello")
	@ResponseBody
	public String hello() {
		return "Hello Spring Boot Board";
	}
}
