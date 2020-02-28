package cn.sz.gl.service;

import java.util.List;

import cn.sz.gl.pojo.Account;

public interface IAccountService {

	public List<Integer> findAccidByUserid(Integer userid);
	
	public Double findBalanceByAccid(Integer accid);
	
	public List<Account> findAllAccountByUserid(Integer userid);
}
