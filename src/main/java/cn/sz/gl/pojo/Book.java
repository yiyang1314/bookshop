package cn.sz.gl.pojo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Book implements Serializable {

	private Integer bookid;
	private String bookName;
	private String publicDept;//出版社
	private Double price;//价格
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date publicDate;//出版日期
	private String auth;//作者
	private String picpath;//书籍图片
	private String summary;//简介
	public Integer getBookid() {
		return bookid;
	}
	public void setBookid(Integer bookid) {
		this.bookid = bookid;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getPublicDept() {
		return publicDept;
	}
	public void setPublicDept(String publicDept) {
		this.publicDept = publicDept;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Date getPublicDate() {
		return publicDate;
	}
	public void setPublicDate(Date publicDate) {
		this.publicDate = publicDate;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	public String getPicpath() {
		return picpath;
	}
	public void setPicpath(String picpath) {
		this.picpath = picpath;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
}
