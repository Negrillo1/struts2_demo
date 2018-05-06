<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录</title>
<style type="text/css">
		#head{
			height: 100px;
			border: 1px black solid;
			background:url(./Pic/bg.png);
		}
		#content{
			height: 700px;
			border: 1px black solid;
		}
		#right,#left{
			border:1px gray solid;
			float: left;
			width: 25%;
			height: 65%; 
			text-align: center;
			margin-top: 5%;
		}
		#left {	 
			margin-left: 23%;
		}
		#login_icon{
			width: 200px;
			height:200px;
			background:url(./Pic/login.png);
			position:relative;
			left:137px;
			top:100px;
		}
		#left span{
			position: relative;
			top:80px;
		}
		#foot{
			height: 100px;
			border: 1px black solid;
			text-align: right;
			background:black;
			
		}
		#foot span{
			position: relative;
			right: 20px;
			top:70px;
			color:#ffffff;
		}
		table{
			position: relative;
			top: 100px;
		}
		#btn{
			position:relative;
			width: 100px;
			top:10px; 
		}
		#tip{
			position:relative;
			top:130px;
			left:80px;
			width:250px;
			height:100px;
		}
</style>

</head>
<body>
<script type="text/javascript">
function changeVerifyCode() {
	document.getElementById("img").src="User_createCode?t=" + Math.random();
}
</script>
<div id="head">
	<h2>&nbsp;&nbsp;学生信息系统</h2>
</div>
<div id="content">
	<div id="left">
		<span>用户登录<br>LOGIN</span>
		<div id=login_icon></div>
	</div>
	
	<div id="right">
		<form name="loginForm" action="<%=path%>/User_login.action" method="post">
			<table align="center" >
				<tr>
				<h2>${msg }</h2>
				</tr>
				<tr align="center">
					<td align="right">用户名：</td>
					<td><input type="text" name="username" class="form-input"/></td>
				</tr>
				<tr align="center">
					<td align="right">密　码：</td>
					<td><input type="password" name="password" class="form-input"/></td>
				</tr>
				<tr align="center">
					<td align="right">验证码：</td>
					<td><input type="text" name="verifycode"/></td>				
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="登录" id="btn"></td>
				</tr>
				<tr>
					<td colspan="2"><input type="reset" value="重置" id="btn"/></td>
				</tr>
			</table>
		</form>
		<div id="tip">
			<img id="img" src="<%=path%>/User_createCode.action" style="width: 85px; height: 25px;">
			<a href="#" onclick="changeVerifyCode()">看不清？换一张</a>
			<s:fielderror/>
		</div>
	</div>
</div>
<div id="foot">
	<span>作者：林敬凯</span>
</div>
</body>
</html>