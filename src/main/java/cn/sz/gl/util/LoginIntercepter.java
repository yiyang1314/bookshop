package cn.sz.gl.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginIntercepter implements HandlerInterceptor {

	private String [] arr = {"","fp","bc/edit","bc/add","uc/islogin"};
	
	public boolean checkUrl(String reqname) {
		
		if(reqname==null||reqname.equals("")) {
			return true;
		}
		
		if(reqname.endsWith(".js")||reqname.endsWith(".css")||reqname.endsWith(".jpg")||reqname.endsWith("png")) {
			return true;
		}
		
		for(int i=0;i<arr.length;i++) {
			if(arr[i].equals(reqname)) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * �ڿ�������������֮ǰ������������ɹ�֮�����;
	 * ��������һ��booleanֵ��
	 * ������ص���true;��ô���������
	 * ������ص���false,��ô���󵽴�Ϊֹ;
	 * 
	 * һ��������д��¼��֤��Ȩ����֤�Ȳ���
	 * 
	 * 
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("��������ǰ�벿�֣�ִ��preHandle����");
		
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("myuser");
		
		//��ȡ��ǰ����
		String uri = request.getRequestURI();
		String contextpath = request.getContextPath();
		String reqname = uri.substring(contextpath.length()+1);
		
		if(obj==null) {
			if(this.checkUrl(reqname)) {
				return true;
			}else {
				//Ҫ��ת
				request.getRequestDispatcher("/login.jsp").forward(request, response);
				return false;
			}
		}else {
			return true;
		}
		
		
	}

	/**
	 * �ڿ���������ִ��֮�����;
	 */
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("��������벿�֣�ִ��postHandle����");

	}

	/**
	 * ��������ϵ�ʱ����ã�
	 * һ�������ͷ���Դ
	 * 
	 * 
	 */
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("��������ϵ�ʱ��,����afterCompletion����");

	}

}
