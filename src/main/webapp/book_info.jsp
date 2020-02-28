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
<script type="text/javascript">
function buybook(bid){
	location = "bc/prebuy?bookid="+bid;
}

</script>
</head>
<body>
<h3>这里是book_info.jsp页面</h3>
编号：${book.bookid }<br/>
书名：${book.bookName }<br/>
出版社：${book.publicDept }<br/>
价格：${book.price }<br/>
出版日期：${book.publicDate }<br/>
作者：${book.auth }<br/>
封面：<img src="${book.picpath }" width="200" /><a href="bc/download?picpath=${book.picpath }" >下载</a><br/>
简介：${book.summary }<br/>
<input type="button" value="购买" onclick="buybook('${book.bookid }');" />
</body>
</html>