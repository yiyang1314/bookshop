package cn.sz.gl.dao;

import java.util.List;
import java.util.Map;

import cn.sz.gl.pojo.Book;

public interface IBookDAO {

	/**
	 * ������+��ҳ��ѯ�鼮��Ϣ
	 * @param map
	 * @return
	 */
	public List<Book> findBookSplit(Map<String,Object> map);
	
	/**
	 * ͳ���������������ݵ�������
	 * @param map
	 * @return
	 */
	public Integer getCount(Map<String,Object> map);
	
	public Book findBookById(Integer bookid);
	
	public void insertBook(Book book);
	
	public Integer findMaxBookid();
	
	/**
	 * �����鼮��ţ���ѯ�鼮�۸�
	 * @param bookid
	 * @return
	 */
	public Double findPriceById(Integer bookid);
}
