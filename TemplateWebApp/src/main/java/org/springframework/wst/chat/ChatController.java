package org.springframework.wst.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ChatController {

	@Autowired
	private SessionRegistry sessionRegistry;

	@RequestMapping(value = "/chat", method = RequestMethod.GET)
	public ModelAndView onLoad() {

		ModelAndView mv = new ModelAndView("/static/chat.jsp");
 		mv.addObject("principals", sessionRegistry.getAllPrincipals());

		return mv;
	}

}
