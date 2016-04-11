package com.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

	@RequestMapping("/")
	public String toIndex(){
		return "index";
	}
	
	@RequestMapping("/{item}")
	public String toPage(@PathVariable String item){
		return item;
	}
}
