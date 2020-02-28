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
	 * 在控制器方法调用之前，适配器适配成功之后调用;
	 * 方法返回一个boolean值；
	 * 如果返回的是true;那么请求继续；
	 * 如果返回的是false,那么请求到此为止;
	 * 
	 * 一般在这里写登录验证，权限验证等操作
	 * 
	 * 
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("拦截器的前半部分，执行preHandle方法");
		
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("myuser");
		
		//获取当前请求
		String uri = request.getRequestURI();
		String contextpath = request.getContextPath();
		String reqname = uri.substring(contextpath.length()+1);
		
		if(obj==null) {
			if(this.checkUrl(reqname)) {
				return true;
			}else {
				//要跳转
				request.getRequestDispatcher("/login.jsp").forward(request, response);
				return false;
			}
		}else {
			return true;
		}
		
		
	}

	/**
	 * 在控制器方法执行之后调用;
	 */
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("拦截器后半部分，执行postHandle方法");

	}

	/**
	 * 请求处理完毕的时候调用；
	 * 一般用来释放资源
	 * 
	 * 
	 */
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("请求处理完毕的时候,调用afterCompletion方法");

	}

}
