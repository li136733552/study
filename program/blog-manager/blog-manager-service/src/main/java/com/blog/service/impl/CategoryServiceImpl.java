package com.blog.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.blog.common.pojo.EUTreeNode;
import com.blog.mapper.CategoryMapper;
import com.blog.pojo.Category;
import com.blog.pojo.CategoryExample;
import com.blog.pojo.CategoryExample.Criteria;
import com.blog.pojo.CategoryExample.Criterion;
import com.blog.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Resource
	private CategoryMapper categoryMapper;
	
	@Override
	public List<EUTreeNode> getCatList(Integer parentId) {
		CategoryExample catExample = new CategoryExample();
		Criteria criteria = catExample.createCriteria();
		criteria.andParentidEqualTo(parentId);
		List<Category> catList = categoryMapper.selectByExample(catExample);
		List<EUTreeNode> EUList = new ArrayList<EUTreeNode>();
		for(Category cat : catList){
			EUTreeNode node = new EUTreeNode();
			node.setId(cat.getId());
			node.setText(cat.getName());
			node.setState(cat.getParentid()>0?"open":"close");
			EUList.add(node);
		}
		return EUList;
	}

}
