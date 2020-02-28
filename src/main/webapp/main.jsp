<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js" ></script>
<script type="text/javascript">
function gopage(nowpage){
	var allpage = parseInt($("#allpage").val());
	if(parseInt(nowpage)<1){
		alert("已经是首页了");
	}else if(parseInt(nowpage)>allpage){
		alert("已经是最后一页了");
	}else{
		splitform.cp.value=nowpage;
		//splitform.action = "/";
		splitform.submit();
	}
}

function addbook(){
	location = "bc/add";
}


</script>
</head>
<body>
<h3>这里是main.jsp页面</h3>
<h4>欢迎您：${sessionScope.myuser.realname }</h4>
<form id="splitform" name="splitform" action="<%=basepath %>" method="post">
<input type="hidden" value="${map.cp }" name="cp" id="cp" />
<input type="hidden" value="${count }" name="count" id="count" />
<input type="hidden" value="${allpage }" name="allpage" id="allpage" />


书名：<input type="text" value="${map.kw }" name="kw" /><br/>
出版社:
<select name="publicDept" >
	<option value="">===请选择出版社===</option>
	<option value="清华大学出版社" ${map.publicDept=='清华大学出版社'?"selected":"" }>清华大学出版社</option>
	<option value="人民出版社" ${map.publicDept=='人民出版社'?"selected":"" }>人民出版社</option>
	<option value="三联出版社" ${map.publicDept=='三联出版社'?"selected":"" }>三联出版社</option>
	<option value="机械工业出版社" ${map.publicDept=='机械工业出版社'?"selected":"" }>机械工业出版社</option>
</select>
<br/>
价格：<input type="text" value="${map.loprice }" name="loprice" />-<input type="text" value="${map.hiprice }" name="hiprice" />
<br/>
日期：<input type="text" onfocus="WdatePicker({minDate:'1900-01-07 00:00:00',maxDate:'2020-01-11 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss'});" name="starttime" />
 - <input type="text" onfocus="WdatePicker({minDate:'2020-01-07 00:00:00',maxDate:'2020-01-11 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss'});" name="endtime" /><br/>
作者：<input type="text" value="${map.auth }" name="auth" /><br/>
<input type="button" value="搜索" onclick="gopage('1');" />
<input type="button" value="增加" onclick="addbook();" />
<table border="1" cellspacing="0" cellpadding="0" width="1000" >
	<tr>
		<th>编号</th>
		<th>书名</th>
		<th>出版社</th>
		<th>作者</th>
	</tr>
	<c:forEach items="${booklist }" var="bl">
	<tr>
		<td>${bl.bookid }</td>
		<td>
			<a href="bc/edit?bookid=${bl.bookid }">${bl.bookName }</a>
		</td>
		<td>${bl.publicDept }</td>
		<td>${bl.auth }</td>
	</tr>
	</c:forEach>
</table>
<input type="button" value="首页" ${map.cp==1?"disabled":"" }  onclick="gopage('1');" />
<input type="button" value="上一页" ${map.cp==1?"disabled":"" } onclick="gopage('${map.cp-1}');" />

<a href="javascript:void(0);" >${map.cp }</a>

<input type="button" value="下一页" ${map.cp==allpage?"disabled":"" } onclick="gopage('${map.cp+1}');" />
<input type="button" value="尾页" ${map.cp==allpage?"disabled":"" } onclick="gopage('${allpage}');" />

</form>
</body>
</html>