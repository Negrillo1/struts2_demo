<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录成功</title>
<link rel="stylesheet" href="css/login_success.css" type="text/css">
<script type="text/javascript" src="js/login_success.js"></script>
</head>
<body>
<div id="head">
<h2>&nbsp;&nbsp;学生信息管理系统</h2>
<div id="welcome">欢迎:${sessionScope.loginUserName}</div>
<div id="logout"><a href="<%=path%>/User_logout.action">安全退出</a></div>
</div>
<div id="content">
	<div id="nav">
		<div class="navitem" onclick="show1();">主菜单</div>
		<div class="navitem" style="display:none" id="1">基础信息</div>
		<div class="navitem" style="display:none" id="2">班级信息</div>
		<div class="navitem" style="display:none" id="3"><a href="student/Student_query.action" target="contents">学生信息</a></div>
		<div class="navitem" style="display:none" id="4">个人信息</div>
	</div>
	<div id="right">
		<div id="search">
			<div id="addlink"><a href="Student_add.jsp" target="contents">添加</a></div>
			<div>
				<form action="<%=path%>/student/Student_querybyid.action" method="post" target="contents">
					<table>
						<tr>
							<td><input type="text" name="Sno" placeholder="张三"></td>
							<td><input type="submit" value="查询"></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div id="info">
			<iframe name="contents" src="">				
			</iframe>
		</div>
	</div>
</div>
<div id="foot">
	<span>作者：林敬凯</span>
</div>
</body>
</html>