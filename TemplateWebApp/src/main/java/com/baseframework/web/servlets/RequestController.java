package com.baseframework.web.servlets;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RequestController {

	@RequestMapping(value = "/media/js/jquery.js", method = RequestMethod.GET)
	public ModelAndView jqueryForward(ModelMap model) {
		return new ModelAndView("forward:/ext/jquery/js/jquery.js", model);
	}

}