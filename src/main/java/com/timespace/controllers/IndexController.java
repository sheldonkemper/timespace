package com.timespace.controllers;

import org.springframework.web.bind.annotation.RequestMapping;

public class IndexController {
	
	   @RequestMapping({"index"})
	    public String login() {
	        return "index";
	    }

}
