package com.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.common.pojo.EUDataGridResult;
import com.blog.mapper.BlogMapper;
import com.blog.pojo.Blog;
import com.blog.pojo.BlogExample;
import com.blog.pojo.BlogExample.Criteria;
import com.blog.service.BlogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class BlogServiceImpl implements BlogService{

	@Autowired
	private BlogMapper lxBlogMapper;
	
	@Override
	public Blog selectByPrimaryKey(Integer id) {

		BlogExample example = new BlogExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(id);
		List<Blog> blogList = lxBlogMapper.selectByExample(example);
		if(blogList != null && blogList.size() > 0){
			return blogList.get(0);
		}
		return null;
	}
	/**
	 * 分页查询
	 */
	@Override
	public EUDataGridResult getBlogList(Integer page, Integer rows) {
		//设置分页
		PageHelper.startPage(page, rows);
		//条件查询
		BlogExample example = new BlogExample();
		List<Blog> blogs = lxBlogMapper.selectByExample(example);
		//查询总记录信息
		PageInfo pInfo = new PageInfo<>(blogs);
		//设置返回信息
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(blogs);
		result.setTotal(pInfo.getTotal());
		return result;
	}

}
