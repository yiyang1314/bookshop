package cn.sz.gl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.sz.gl.dao.IAccountDAO;
import cn.sz.gl.pojo.Account;
import cn.sz.gl.service.IAccountService;
@Service
public class AccountServiceImpl implements IAccountService {

	@Autowired
	private IAccountDAO accdao;
	
	public List<Integer> findAccidByUserid(Integer userid) {
		return accdao.findAccidByUserid(userid);
	}

	public Double findBalanceByAccid(Integer accid) {
		if(accid==null) {
			return null;
		}
		return accdao.findBalanceByAccid(accid);
	}

	public List<Account> findAllAccountByUserid(Integer userid) {
		return accdao.findAllAccountByUserid(userid);
	}

}
