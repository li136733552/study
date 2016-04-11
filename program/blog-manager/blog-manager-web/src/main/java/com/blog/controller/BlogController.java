package com.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blog.common.pojo.EUDataGridResult;
import com.blog.pojo.Blog;
import com.blog.service.BlogService;

@Controller
@RequestMapping("/blog/")
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	@RequestMapping("/blog/{id}")
	@ResponseBody
	public Blog selectByPrimaryKey(@PathVariable Integer id){
		return blogService.selectByPrimaryKey(id);
	}
	
	@RequestMapping("list")
	@ResponseBody
	public EUDataGridResult getBlogList(Integer page,Integer rows){
		return blogService.getBlogList(page, rows);
	}
	
}
