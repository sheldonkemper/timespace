package com.timespace.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.unbescape.html.HtmlEscape;

@Controller
public class LoginController 
{

	  /** Login form. */
    @RequestMapping({"","/","login"})
    public String login() 
    {
        return "index";
    }

	/** Login form with error. */
	@RequestMapping("/login-error.html")
	public String loginError(Model model) 
	{
	    model.addAttribute("loginError", true);
	    return "login";
	}

	/** Error page. */
	@RequestMapping("/403")
	public String forbidden() 
	{
	    return "403";
	}

	/** Error page. */
	@RequestMapping("/error.html")
	public String error(HttpServletRequest request, Model model) 
	{
	    model.addAttribute("errorCode", "Error " + request.getAttribute("javax.servlet.error.status_code"));
	    Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
	    StringBuilder errorMessage = new StringBuilder();
	    errorMessage.append("<ul>");
	    while (throwable != null) {
	        errorMessage.append("<li>").append(HtmlEscape.escapeHtml5(throwable.getMessage())).append("</li>");
	        throwable = throwable.getCause();
	    }
	    errorMessage.append("</ul>");
	    model.addAttribute("errorMessage", errorMessage.toString());
	    return "error";
	}
}