package com.user_creation.application.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Controller("error")
public class ErrorController {
	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(HttpServletRequest req, Exception ex) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("message", ex.getMessage());
		modelAndView.setViewName("error");
		return modelAndView;
	}
}