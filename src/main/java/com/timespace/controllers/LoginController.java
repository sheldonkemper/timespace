package com.timespace.controllers;

import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.unbescape.html.HtmlEscape;
import com.timespace.models.Employee;
import com.timespace.services.EmployeeService;

@SessionAttributes("user")
@Controller
public class LoginController 
{
	EmployeeService employeeService;
	
	public LoginController(EmployeeService employeeService)
	{
		this.employeeService = employeeService;
	}
	
	  @ModelAttribute("user")
	    public Employee setUpUserForm() {
	        return new Employee();
	    }
	  /** Login form. */
    @RequestMapping({"","/","login"})
    public String login(@ModelAttribute("user") Employee user, @ModelAttribute("employee") Employee employee, Model model, Principal principal ) {
    	 //@TODO Check if null
    	if(principal!=null)
    	{
    		  String lastName = principal.getName(); //get logged in username
    	      employee = this.employeeService.findByLastName(lastName);//Find user in storage
    	      model.addAttribute("user", employee.getId());//add user id to session

    	}
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