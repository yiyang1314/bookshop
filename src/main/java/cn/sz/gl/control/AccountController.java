package cn.sz.gl.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.sz.gl.pojo.Account;
import cn.sz.gl.pojo.Users;
import cn.sz.gl.service.IAccountService;

@Controller
@RequestMapping("/ac")
public class AccountController {

	@Autowired
	private IAccountService accService;
	
	/*@RequestMapping(value="cb",method=RequestMethod.POST)
	public void checkBalance(Integer accid,HttpServletResponse response) throws IOException {
		Double balance = accService.findBalanceByAccid(accid);
		PrintWriter out = response.getWriter();
		out.print(balance);
		out.flush();
	}*/
	
	@RequestMapping(value="cb",method=RequestMethod.POST)
	@ResponseBody
	public String checkBalance(Integer accid) throws IOException {
		Double balance = accService.findBalanceByAccid(accid);
		return balance+"";
	}
	
	@RequestMapping(value="findacc",method=RequestMethod.POST)
	@ResponseBody
	public List<Account> findAllAccountByUserid(HttpServletRequest request){
		Users u = (Users) request.getSession().getAttribute("myuser");
		List<Account> acclist = accService.findAllAccountByUserid(u.getUserid());
		return acclist;
	}
	
	
	
}
