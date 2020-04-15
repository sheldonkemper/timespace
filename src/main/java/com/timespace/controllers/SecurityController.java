package com.timespace.controllers;

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
	
	@PreAuthorize("hasRole('EMPLOYEE')")
	@GetMapping("/index")
	public String managersStatusCheck(Principal principal) {
	    return "Working for managers. Principal name = " + principal.getName();
	}
}


