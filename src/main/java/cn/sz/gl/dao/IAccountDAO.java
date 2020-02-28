package cn.sz.gl.dao;

import java.util.List;
import java.util.Map;

import cn.sz.gl.pojo.Account;

public interface IAccountDAO {

	public List<Integer> findAccidByUserid(Integer userid);
	
	/**
	 * �����˻���ţ���ѯ�˻����
	 * @param accid
	 * @return
	 */
	public Double findBalanceByAccid(Integer accid);
	
	/**
	 * �����û���Ų�ѯ���û��µ������˻���Ϣ
	 * @param userid
	 * @return
	 */
	public List<Account> findAllAccountByUserid(Integer userid);
	
	/**
	 * �˻�������
	 * @param map
	 */
	public void subBalance(Map<String,Object> map);
}
