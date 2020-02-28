package cn.sz.gl.pojo;

import java.io.Serializable;

public class StoreHouse implements Serializable {

	private Integer bookid;
	private Integer count;
	public Integer getBookid() {
		return bookid;
	}
	public void setBookid(Integer bookid) {
		this.bookid = bookid;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
}
