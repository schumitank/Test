<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<script language="javascript">
			function check()
			{
				var userName = document.frm.userName.value;
				
 				if (userName == null || userName == "")
 				{
 					alert("用户名不能为空");
 					return false;
 				}else
 				{
 					return true;
 				}
			}
		</script>
</head>
<body>
<body bgcolor="grey">
		<center>
			<form name="frm" action="LoginServlet" method="post">
				用户名 :  <input name="userName" size="8"><br>
				<br>
				<input type="submit" value="提交" onclick="return check()">
				<!-- <input type="button" value="Test" onclick="return check()">
				<input type="button" value="Test" onclick="return check()" disabled>
				 --><a href="register.jsp">注册新用户</a>
			</form>
			<% 
				String s = request.getParameter("error"); 
				if (s != null)
				{
				%>
					<h1>错误的用户名，请重新登陆</h1>
				<%
				}
			%>
		</center>
	</body>
</html>