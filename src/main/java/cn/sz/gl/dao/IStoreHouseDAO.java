package cn.sz.gl.dao;

public interface IStoreHouseDAO {

	public Integer findCountByBookid(Integer bookid);
	
	/**
	 * ���������һ
	 * @param bookid
	 */
	public void subStore(Integer bookid);
}
