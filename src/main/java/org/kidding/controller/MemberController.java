package org.kidding.controller;

import org.kidding.domain.MemberVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class MemberController {

	//로그인은 원래 포스트 방식인데, 간단하게 겟 방식으로 만들어 볼거야
	@GetMapping("/login")
	public void login(String id, String pw, Model model) {
		
		log.info("id: " + id + ", pw: " + pw);
		
		//아이디 aaa, 패스워드 111 가정
		if(id.equals("aaa") && pw.equals("111")) {
			MemberVO vo = new MemberVO();
			vo.setId(id);
			vo.setPw(pw);
			vo.setName("김가익");
			model.addAttribute("member", vo);

		}
	}
	
		
	
}
