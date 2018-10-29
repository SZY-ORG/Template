package com.shizy.template.common.bean;

import java.util.List;

/**
 * 用于接收分页数据
 *
 * @param <T>
 */
public class Pagination<T> extends BaseBean {

	private int page;// 当前页码
	private int count;// 数据个数
	private int total;// 总数
	private String timestamp;// 首页数据时间戳
	private List<T> list;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
}
