<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
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
<h3>这里是book_add.jsp页面</h3>
<form action="bc/add" method="post" enctype="multipart/form-data" >
	书名：<input type="text" value="" name="bookName" /><br/>
	出版社：<input type="text" value="" name="publicDept" /><br/>
	价格：<input type="text" value="" name="price" /><br/>
	出版日期：<input type="text" value="" name="publicDate" /><br/>
	作者：<input type="text" value="" name="auth" /><br/>
	封面：<input type="file" name="pic" /><br/>
	简介：<textarea rows="5" cols="50" name="summary"></textarea> <br/>
	<input type="submit" value="保存" />
</form>
</body>
</html>