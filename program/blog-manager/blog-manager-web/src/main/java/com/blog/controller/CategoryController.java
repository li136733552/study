package com.blog.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blog.common.pojo.EUTreeNode;
import com.blog.service.CategoryService;

@Controller
@RequestMapping("/cat/")
public class CategoryController {

	@Resource
	private CategoryService categoryService;
	
	@RequestMapping("cat/list")
	@ResponseBody
	public List<EUTreeNode> getCatList(@RequestParam(value="id",defaultValue="0") Integer parentId){
		return categoryService.getCatList(parentId);
	}
}
