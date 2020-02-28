<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<script type="text/javascript" src="js/jquery-1.8.3.js" ></script>
<script type="text/javascript">
function showBalance(accid){
	
	$.post("ac/cb",{"accid":accid},function(data){
		//console.log(data);
		$("#balanceInfo").html(data);
	},"json");
	
}

function checkAllAccount(){
	$.post("ac/findacc",{},function(data){
		//console.log(data);
		$.each(data,function(i,n){
			
			$("#accinfo").append("<li>"+n.accid+"---"+n.accName+"---"+n.balance+"</li>");
			
		});
		
		
	},"json");
}




</script>
</head>
<body>
<h3>这里是book_buy.jsp页面</h3>
当前用户：${sessionScope.myuser.realname}<br/>

<hr/>
编号：${book.bookid }<br/>
书名：${book.bookName }<br/>
出版社：${book.publicDept }<br/>
价格：${book.price }<br/>
出版日期：${book.publicDate }<br/>
作者：${book.auth }<br/>
<hr/>
库存数量(这里假定每次只能买一本书)：${count }<br/>
<hr/>
<form action="bc/buybook" method="post" >
<input type="hidden" value="${book.bookid }" name="bookid" />

选择账户：
<select name="accid" onchange="showBalance(this.value);">
	<option value="">===请选择付款账户===</option>
	<c:forEach items="${acclist }" var="al">
	<option value="${al }" >${al }</option>
	</c:forEach>
</select>
该账户余额为：<span id="balanceInfo"></span><br/>
<input type="submit" value="支付"  />
<br/>
</form>
<hr/>
<input type="button" value="查询当前用户所有的账户信息" onclick="checkAllAccount();" /><br/>
<ul id="accinfo" >
	
</ul>


</body>
</html> 