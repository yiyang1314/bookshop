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
	 * ��������ѯ+��ҳ
	 * 
	 * springmvc�У����ڵĽ��գ�Ĭ���ǲ��ܽ����������͵ģ�
	 * Ҫ��������ڣ�
	 * 1.�ڽ��ղ�����ʱ�򣬼���@DateTimeFormatע�⣬��ָ�����ڸ�ʽ;
	 * 2.��ͨ��ʵ���������ղ���ǰ���£����ĳ�����Ե��������������ͣ���ô�����ڸ������ϼ���@DateTimeFormatע��;
	 * 3.�Զ�������ת������
	 * 	a.����һ��class,��ʵ��Converter�ӿڣ�Ȼ����дconvert����;
	 *  b.������һ��д���࣬��������
	 *  c.app.xml�У�����ת�����󣬲�ע��ڶ����еĶ���;
	 *  d.��MVCע������ʱ��ͨ��conversion-service��ָ���Լ���ת����
	 * 
	 * @param cp
	 *            : ��ǰҪ��ѯ�ڼ�ҳ
	 * @param ps
	 *            : ÿҳ�����ʾ����������
	 * @param kw
	 *            : ����������ģ����ѯ�Ĺؼ���
	 * @param publicDept
	 *            : ���ݳ���������ѯ�鼮
	 * @param loprice
	 *            : �۸�����ֵ
	 * @param hiprice
	 *            : �۸�����ֵ
	 * @param starttime
	 *            : ��ʼ����
	 * @param endtime
	 *            : ��������
	 * @param auth
	 *            : ����
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
