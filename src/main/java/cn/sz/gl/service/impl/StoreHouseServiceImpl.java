package cn.sz.gl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.sz.gl.dao.IStoreHouseDAO;
import cn.sz.gl.service.IStoreHouseService;
@Service
public class StoreHouseServiceImpl implements IStoreHouseService {

	@Autowired
	private IStoreHouseDAO storehousedao;
	
	public Integer findCountByBookid(Integer bookid) {
		if(bookid==null) {
			return null;
		}
		return storehousedao.findCountByBookid(bookid);
	}

}
