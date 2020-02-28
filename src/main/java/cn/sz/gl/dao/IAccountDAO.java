package cn.sz.gl.dao;

import java.util.List;
import java.util.Map;

import cn.sz.gl.pojo.Account;

public interface IAccountDAO {

	public List<Integer> findAccidByUserid(Integer userid);
	
	/**
	 * 根据账户编号，查询账户余额
	 * @param accid
	 * @return
	 */
	public Double findBalanceByAccid(Integer accid);
	
	/**
	 * 根据用户编号查询该用户下的所有账户信息
	 * @param userid
	 * @return
	 */
	public List<Account> findAllAccountByUserid(Integer userid);
	
	/**
	 * 账户余额减少
	 * @param map
	 */
	public void subBalance(Map<String,Object> map);
}
