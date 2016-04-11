package com.blog.service;

import java.util.List;

import com.blog.common.pojo.EUTreeNode;

public interface CategoryService {
	
	public List<EUTreeNode> getCatList(Integer parentId);
}
