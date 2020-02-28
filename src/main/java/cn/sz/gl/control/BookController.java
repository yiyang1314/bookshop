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
		System.out.println("ִ��editBook����");
		Book book = bookServiceImpl.findById(bookid);
		model.addAttribute("book", book);
		return "book_info";
	}
	
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String addBook() {
		return "book_add";
	}
	
	/**
	 * �ļ��ϴ���
	 * 1.����ļ��ϴ�����������ʹ��commons���
	 * 		commons-fileupload
	 * 		commons-io
	 * 2.��app.xml�ļ��У�׼���ļ��ϴ���������CommonsMultipartResolver
	 * 		id������multipartResolver
	 * 		���ﻹ�����趨���뷽ʽ���ļ���С���Ƶ�
	 * 3.ҳ���ϣ�
	 * 		�ļ��ϴ�����Ҫʹ��file�������Ӧ��input,�������name����
	 * 		Ҫ������ԣ�������ʵ�����ж�Ӧ����������ͬ;
	 * 		form�����������һ�����ԣ� enctype="multipart/form-data"
	 * 4.�ڿ������У�ͨ��@RequestParam MultipartFile pic�������ļ�
	 * 		�ڿ����������У��ѽ��յ����ļ���ת�浽������Ӳ����
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
		System.out.println("���յ�book��Ϣ��"+book.getBookName()+",�����磺"+book.getPublicDept());
		System.out.println("�û��ϴ����ļ���ԭ��������:"+pic.getOriginalFilename());
		
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
			
			//������ֻ��Ҫ��book������ӵ����ݿ���
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
		System.out.println("׼������...");
		//1.����bookid����ѯbook��Ϣ
		Book book = bookServiceImpl.findById(bookid);
		//2.��ѯ�������
		int count = storeHouseServiceImpl.findCountByBookid(bookid);
		//3.��ȡ��ǰ��¼�û�����ѯ��ǰ�û�����Щ�˻�;
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
