<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basepath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basepath %>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>这里是login.jsp页面</h3>
<h3>请登录：</h3>
<form action="uc/islogin" method="post" >
用户名：<input type="text" value="zhangsan" name="loginname" /><br/>
密码： <input type="password" value="123" name="loginpwd" /><br/>
<input type="submit" value="登录" />
</form>
</body>
</html>