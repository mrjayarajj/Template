package com.baseframework.web.security.authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.baseframework.domain.security.core.userdetails.MyAccInfoTokenHandler;

@Controller
public class LoginController {

	private static final String SSO_PAGE = "redirect:" + "http://template.com/sso/login.html";

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String redirectSSOPage(HttpServletRequest request, HttpServletResponse response) {
		return SSO_PAGE;
	}

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String dsReturn(HttpServletRequest request, HttpServletResponse response) {

		String pageToBeshown = SSO_PAGE;

		if (request.getCookies() != null) {

			String token = "";// getCookies(request.getCookies(), "");

			MyAccInfoTokenHandler myAcInfoToken = new MyAccInfoTokenHandler(token, "template.cookie.name");

			if (!(SecurityUtils.getSubject().isAuthenticated())) {
				// log
			}

			SecurityUtils.getSubject().login(myAcInfoToken);
			SecurityUtils.getSubject().isPermitted("tab:welcomePage:view");

			pageToBeshown = "/jsp/base/welcome.jsp";

		}

		return pageToBeshown;
	}

}
