package com.blog.common.pojo;

import java.util.List;

public class EUDataGridResult {
	
	private long total; //总页数
	private List<?> rows; //返回的结果集
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	
	
	
}
