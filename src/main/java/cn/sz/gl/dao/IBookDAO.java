package cn.sz.gl.dao;

import java.util.List;
import java.util.Map;

import cn.sz.gl.pojo.Book;

public interface IBookDAO {

	/**
	 * 多条件+分页查询书籍信息
	 * @param map
	 * @return
	 */
	public List<Book> findBookSplit(Map<String,Object> map);
	
	/**
	 * 统计满足条件的数据的总行数
	 * @param map
	 * @return
	 */
	public Integer getCount(Map<String,Object> map);
	
	public Book findBookById(Integer bookid);
	
	public void insertBook(Book book);
	
	public Integer findMaxBookid();
	
	/**
	 * 根据书籍编号，查询书籍价格
	 * @param bookid
	 * @return
	 */
	public Double findPriceById(Integer bookid);
}
