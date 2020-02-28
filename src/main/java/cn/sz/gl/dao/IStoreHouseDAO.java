package cn.sz.gl.dao;

public interface IStoreHouseDAO {

	public Integer findCountByBookid(Integer bookid);
	
	/**
	 * ¿â´æÊıÁ¿¼õÒ»
	 * @param bookid
	 */
	public void subStore(Integer bookid);
}
