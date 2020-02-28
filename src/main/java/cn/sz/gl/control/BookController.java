package cn.sz.gl.control;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cn.sz.gl.pojo.Book;
import cn.sz.gl.pojo.Users;
import cn.sz.gl.service.IAccountService;
import cn.sz.gl.service.IBookService;
import cn.sz.gl.service.IStoreHouseService;

@Controller
@RequestMapping("/bc")
public class BookController {

	@Autowired
	private IBookService bookServiceImpl;
	@Autowired
	private IStoreHouseService storeHouseServiceImpl;
	@Autowired
	private IAccountService accService;
	
	@RequestMapping(value="edit",method=RequestMethod.GET)
	public String editBook(Integer bookid,Model model) {
		System.out.println("执行editBook方法");
		Book book = bookServiceImpl.findById(bookid);
		model.addAttribute("book", book);
		return "book_info";
	}
	
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String addBook() {
		return "book_add";
	}
	
	/**
	 * 文件上传：
	 * 1.添加文件上传依赖，这里使用commons组件
	 * 		commons-fileupload
	 * 		commons-io
	 * 2.在app.xml文件中，准备文件上传操作对象CommonsMultipartResolver
	 * 		id必须是multipartResolver
	 * 		这里还可以设定编码方式，文件大小限制等
	 * 3.页面上：
	 * 		文件上传，需要使用file组件，对应的input,必须给定name属性
	 * 		要求该属性，不能与实体类中对应的属性名相同;
	 * 		form表单，必须添加一个属性： enctype="multipart/form-data"
	 * 4.在控制器中，通过@RequestParam MultipartFile pic来接收文件
	 * 		在控制器方法中，把接收到的文件，转存到服务器硬盘上
	 * 
	 * 
	 * 
	 * 
	 * @param book
	 * @param pic
	 * @param request
	 * @return
	 */
	@RequestMapping(value="add",method=RequestMethod.POST)
	public String addBook(Book book,@RequestParam MultipartFile pic,HttpServletRequest request) {
		System.out.println("接收到book信息："+book.getBookName()+",出版社："+book.getPublicDept());
		System.out.println("用户上传的文件的原来的名字:"+pic.getOriginalFilename());
		
		/*MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
		MultipartFile mf = req.getFileMap().get("pic");*/
		
		String realpath = request.getSession().getServletContext().getRealPath("/upload");
		String uuid = UUID.randomUUID().toString();
		String endname = pic.getOriginalFilename().substring(pic.getOriginalFilename().lastIndexOf("."));
		File file = new File(realpath+"/"+uuid+endname);
		
		try {
			OutputStream os = new FileOutputStream(file);
			FileCopyUtils.copy(pic.getInputStream(), os);
			
			book.setPicpath("upload/"+uuid+endname);
			
			//接下来只需要把book对象添加到数据库中
			boolean flag = bookServiceImpl.insertBook(book);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return "redirect:/";
	}
	
	@RequestMapping(value="download",method=RequestMethod.GET)
	public ResponseEntity<byte[]> downloadfile(String picpath,HttpServletRequest request) throws IOException {
		
		String realpath = request.getSession().getServletContext().getRealPath(picpath);
		//System.out.println("realpath:"+realpath);
		File file = new File(realpath);
		//FileCopyUtils.copyToByteArray(in)
		
		HttpHeaders header = new HttpHeaders();
		header.setContentDispositionFormData("attachment", picpath);
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), header, HttpStatus.OK);
	}
	
	@RequestMapping(value="prebuy",method=RequestMethod.GET)
	public String prebuy(Integer bookid,HttpServletRequest request,Model model) {
		System.out.println("准备购买...");
		//1.根据bookid，查询book信息
		Book book = bookServiceImpl.findById(bookid);
		//2.查询库存数量
		int count = storeHouseServiceImpl.findCountByBookid(bookid);
		//3.获取当前登录用户，查询当前用户有哪些账户;
		Users u = (Users) request.getSession().getAttribute("myuser");
		List<Integer> acclist = accService.findAccidByUserid(u.getUserid());
		
		model.addAttribute("book", book);
		model.addAttribute("count", count);
		model.addAttribute("acclist", acclist);
		return "book_buy";
	}
	
	@RequestMapping(value="buybook",method=RequestMethod.POST)
	public String buybook(Integer bookid,Integer accid,HttpServletRequest request) {
		boolean flag = bookServiceImpl.buybook(bookid, accid);
		return "redirect:/";
	}
	
	
	
}
