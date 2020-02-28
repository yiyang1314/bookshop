package cn.sz.gl.control;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.sz.gl.pojo.Book;
import cn.sz.gl.service.IBookService;

@Controller
@RequestMapping("/")
public class FirstPageController {

	private IBookService bookServiceImpl;
	
	@RequestMapping("/")
	public String firstpage() {
		return "forward:fp";
	}
	
	/**
	 * 多条件查询+分页
	 * 
	 * springmvc中，日期的接收：默认是不能接收日期类型的；
	 * 要想接收日期：
	 * 1.在接收参数的时候，加上@DateTimeFormat注解，并指定日期格式;
	 * 2.在通过实体类对象接收参数前提下，如果某个属性的类型是日期类型，那么可以在该属性上加上@DateTimeFormat注解;
	 * 3.自定义类型转换器：
	 * 	a.创建一个class,并实现Converter接口，然后重写convert方法;
	 *  b.根据上一步写的类，创建对象；
	 *  c.app.xml中，创建转换对象，并注入第二步中的对象;
	 *  d.在MVC注解驱动时，通过conversion-service来指定自己的转换器
	 * 
	 * @param cp
	 *            : 当前要查询第几页
	 * @param ps
	 *            : 每页最多显示多少行数据
	 * @param kw
	 *            : 根据书名来模糊查询的关键字
	 * @param publicDept
	 *            : 根据出版社来查询书籍
	 * @param loprice
	 *            : 价格的最低值
	 * @param hiprice
	 *            : 价格的最高值
	 * @param starttime
	 *            : 起始日期
	 * @param endtime
	 *            : 结束日期
	 * @param auth
	 *            : 作者
	 * @return
	 */
	@RequestMapping("fp")
	public String enterFirstPage(@RequestParam(defaultValue = "1") Integer cp,
			@RequestParam(defaultValue = "3") Integer ps, String kw, String publicDept, Double loprice, Double hiprice,
			@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date starttime, @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")Date endtime, String auth,Model model) {
	/*public String enterFirstPage(@RequestParam(defaultValue = "1") Integer cp,
			@RequestParam(defaultValue = "3") Integer ps, String kw, String publicDept, Double loprice, Double hiprice,
			Date starttime, Date endtime, String auth,Model model) {*/
		//System.out.println("kw:"+kw);
		//System.out.println("starttime:"+starttime);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("start", (cp-1)*ps);
		map.put("end", cp*ps);
		if(kw!=null&&!kw.equals("")) {
			map.put("kw", kw);
		}
		
		if(publicDept!=null&&!publicDept.equals("")) {
			map.put("publicDept", publicDept);
		}
		
		if(loprice!=null&&hiprice!=null) {
			map.put("loprice", loprice);
			map.put("hiprice", hiprice);
		}
		
		if(starttime!=null&&endtime!=null) {
			map.put("starttime", starttime);
			map.put("endtime", endtime);
		}
		
		if(auth!=null&&!auth.equals("")) {
			map.put("auth", auth);
		}
		
		
		List<Book> booklist = bookServiceImpl.findBookSplit(map);
		int count = bookServiceImpl.getCount(map);
		int allpage = (count-1)/ps+1;
		
		
		map.put("cp", cp);
		map.put("ps", ps);
		model.addAttribute("booklist", booklist);
		model.addAttribute("count", count);
		model.addAttribute("allpage", allpage);
		model.addAttribute("map", map);
		
		return "main";
	}

	public IBookService getBookServiceImpl() {
		return bookServiceImpl;
	}

	public void setBookServiceImpl(IBookService bookServiceImpl) {
		this.bookServiceImpl = bookServiceImpl;
	}
}
