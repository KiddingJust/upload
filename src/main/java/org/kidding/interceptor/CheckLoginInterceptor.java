package org.kidding.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.log4j.Log4j;

@Log4j
public class CheckLoginInterceptor extends HandlerInterceptorAdapter {

	
	//사전 처리. 로그인 안 되면 들어갈 수 없게 하는 부분
	//로그인 안 하면 로그인 페이지로 튕겨낼 것 
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		Cookie[] cks = request.getCookies();
		
		boolean check = false;
		
		//쿠키가 없다면 cks.length 가 0이 나올 것. cks==null은 WAS 문제로 해주는 것. 관용적으로 둘다 체크함
		if (cks == null || cks.length ==0) {
			response.sendRedirect("/");
			return false;
		}
		
		for (Cookie cookie : cks) {
			if(cookie.getName().equals("lcookie")) {
				check = true;
				break;
			}
		}
		log.info("LOGIN CHECK RESULT: " + check);
		
		if (check == false) {
			response.sendRedirect("/");
			return false;
		}
		
		return super.preHandle(request, response, handler);
	}

	
}
