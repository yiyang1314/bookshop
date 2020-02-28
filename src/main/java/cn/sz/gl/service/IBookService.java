package cn.sz.gl.service;

import java.util.List;
import java.util.Map;

import cn.sz.gl.pojo.Book;

public interface IBookService {

	public List<Book> findBookSplit(Map<String,Object> map);
	
	public int getCount(Map<String,Object> map);
	
	public Book findById(Integer bookid);
	
	public boolean insertBook(Book book);
	
	public boolean buybook(Integer bookid,Integer accid);
}
