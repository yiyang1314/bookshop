package cn.sz.gl.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.sz.gl.dao.IAccountDAO;
import cn.sz.gl.dao.IBookDAO;
import cn.sz.gl.dao.IStoreHouseDAO;
import cn.sz.gl.pojo.Book;
import cn.sz.gl.service.IBookService;
import cn.sz.gl.util.BalanceLessException;
import cn.sz.gl.util.StoreHouseLessException;
@Service
public class BookServiceImpl implements IBookService {

	@Autowired
	private IBookDAO bookDAOImpl;
	@Autowired
	private IStoreHouseDAO storehosuedao;
	@Autowired
	private IAccountDAO accdao;
	
	public List<Book> findBookSplit(Map<String, Object> map) {
		return bookDAOImpl.findBookSplit(map);
	}

	public int getCount(Map<String, Object> map) {
		return bookDAOImpl.getCount(map);
	}

	public Book findById(Integer bookid) {
		if(bookid==null) {
			return null;
		}
		return bookDAOImpl.findBookById(bookid);
	}

	public boolean insertBook(Book book) {
		
		try {
			int max = bookDAOImpl.findMaxBookid();
			book.setBookid(max+1);
			bookDAOImpl.insertBook(book);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/*@Transactional(isolation=Isolation.READ_COMMITTED,propagation=Propagation.REQUIRED,rollbackForClassName= {"cn.sz.gl.util.StoreHouseLessException","cn.sz.gl.util.BalanceLessException"})*/
	@Transactional(isolation=Isolation.READ_COMMITTED,propagation=Propagation.REQUIRED,rollbackFor= {BalanceLessException.class,StoreHouseLessException.class})
	public boolean buybook(Integer bookid, Integer accid) {
		/*try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		//1.库存要减少
		//减库存之前，先判断库存是否>0
		//得到库存数量
		int count = storehosuedao.findCountByBookid(bookid);
		if(count<=0) {
			throw new StoreHouseLessException("库存不足");
		}
		storehosuedao.subStore(bookid);
		//2.账户余额要减少
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("accid", accid);
		//这里，需要先查询出账户余额是多少；然后查出书籍的价格是多少
		//账户余额
		double balance_old = accdao.findBalanceByAccid(accid);
		//价格
		double price = bookDAOImpl.findPriceById(bookid);
		map.put("balance", balance_old-price);
		if(balance_old<price) {
			throw new BalanceLessException("账户余额不足");
		}
		accdao.subBalance(map);
		return true;
	}

}
