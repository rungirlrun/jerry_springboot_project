package com.mysite.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
<<<<<<< HEAD

	@RequestMapping("/sbb")
	@ResponseBody
	public String index() {
		return "안녕하세요 sbb에 오신것을 환영합니다.";
	}
=======
	
	@RequestMapping("/sbb")
	@ResponseBody
	public String index() {
		return "안녕하세요 sbb에 오신 것을 환영합니다!";
		}
>>>>>>> 37dd8869ddfce3063550505e618af6f663213bee
	
	@RequestMapping("/")
	public String root() {
		return "redirect:/question/list";
<<<<<<< HEAD
	}
=======
		}
>>>>>>> 37dd8869ddfce3063550505e618af6f663213bee
}
