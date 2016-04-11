package com.blog.service;

import com.blog.common.pojo.EUDataGridResult;
import com.blog.pojo.Blog;

public interface BlogService {
	
	public Blog selectByPrimaryKey(Integer id);
	public EUDataGridResult getBlogList(Integer page,Integer rows);
	
}
