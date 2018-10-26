package org.kidding.interceptor;

import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kidding.domain.MemberVO;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.log4j.Log4j;

@Log4j
public class AfterLoginInterceptor extends HandlerInterceptorAdapter {

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		//MemberController 갔다 와서 로그인 끝난 상태
		//로그인 할 때는 아이디, 비번 받으므로 내부적으로는 Map
		Object result = modelAndView.getModel().get("member");
	
		log.info("RESULT: " + result);
		if(result == null) {
			return;
		}
		
		//result가 null이 아니면 casting
		MemberVO memberVO = (MemberVO)result;
		
		log.info("MEMBER: " + memberVO);
		//로그인 성공했으니 쿠키에 담아줘야 함
		Cookie loginCookie = new Cookie("lcookie", URLEncoder.encode(memberVO.getName(), "UTF-8"));
		//쿠키 지속 시간 설정
		loginCookie.setMaxAge(60*5);
		response.addCookie(loginCookie);
	}
	
	
	
}
