<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register new user</title>
<script language="javascript">
			function check()
			{
				var userName = document.frm.userName.value;
				var userColor = document.frm.userColor.value;
 				if (userName == null || userName == "" || userColor == null || userColor == "")
 				{
 					alert("用户名或颜色不能为空");
 					return false;
 				}else
 				{
 					return true;
 				}
			}
		</script>
</head>

<body bgcolor="grey">
		<center>
			<form name="frm" action="RegisterServlet" method="post">
				用户名 :  <input name="userName" size="8"><br>
				<br>
				事件颜色 :  <input name="userColor" size="8"><br>
				<br>
				<input type="submit" value="提交" onclick="return check()">
				<a href="login.jsp">返回登录</a>
				<br>
				<br>
				<br>
				 大红 #ff0000 ；  粉红 #ff66cc ； 淡红 #ff66ff ；
				<br>
				绿色 #ccff00 ； 紫蓝 #ff33ff ；  黄色 #ffff33 ;
				<br> 
				天蓝 #66ffff ； 草绿 #66cc00 ；  深蓝 #3333ff ；
				<br> 
				深绿 #339900 ； 白色 #ffffff ；  黑色 #000000 ；
				<br>
			</form>
			<% 
				String s = request.getParameter("error"); 
				if (s != null)
				{
				%>
					<h1>注册失败，请稍后重试！</h1>
				<%
				}
			%>
		</center>

</body>
</html>