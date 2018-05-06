<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'AddStudent.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <link href="EasyUi/themes/default/easyui.css" rel="stylesheet" />
    <link href="EasyUi/themes/icon.css" rel="stylesheet" />
    <link href="css/admin.css" rel="stylesheet" />
    <script src="EasyUi/jquery.min.js"></script>
    <script src="EasyUi/jquery.easyui.min.js"></script>
    <style type="text/css">
        html, body {
        height: 100%;
        overflow: auto;
        
        }
    	.main_form {
    	width: 100%;
    	height: 100%;
    	}
    	.radioSpan {
      	position: relative;
      	border: 1px solid #95B8E7;
      	background-color: #fff;
      	vertical-align: middle;
      	display: inline-block;
      	overflow: hidden;
      	white-space: nowrap;
      	margin: 0;
      	padding: 0;
      	-moz-border-radius: 5px 5px 5px 5px;
      	-webkit-border-radius: 5px 5px 5px 5px;
      	border-radius: 5px 5px 5px 5px;
      	display:block;
    	}
    	.input_container{
    	margin-bottom:32px;
    	}
    	#con{
    	width: 65%;
    	background: red;
    	margin: auto;
    	margin-top: 5%;
    	}
    </style>
  </head>
  
  <body>
  <div id="con">
    <form class="mian_form" action="student/Student_add.action" method="post">
    	<div class="easyui-panel" title="学生信息表单" style="width: 100%;">
    		<div class="input_container">
    			<input class="easyui-textbox" name="sid" label="学号：" required="true" labelPosition="top" data-options="prompt:'学号'" style="width:100%;height:52px" missingMessage="必须填写学号"/>
    		</div>
    		 <div class="input_container">
                <input class="easyui-textbox" name="sname" label="姓名：" required="true" labelPosition="top" data-options="prompt:'请输入姓名'" style="width:100%;height:52px" missingMessage="必须填写姓名"/>
            </div>
            <div class="input_container">
            	<span class="radioSpan" style="width:100%;" >
            		<input type="radio" name="ssex" value="男" checked="checked">男</input>
                	<input type="radio" name="ssex" value="女">女</input>
            	</span>
            </div>
            <div class="input_container">
                <input class="easyui-numberbox" min="16" max="26" name="sage" required="true" label="年龄：" labelPosition="top" data-options="prompt:'年龄'" missingMessage="必须填写16~26的整数" style="width:100%;height:52px">
            </div>
			<div class="input_container">
                <input class="easyui-textbox" name="sclass" label="班级：" required="true" labelPosition="top" data-options="prompt:'班级'" style="width:100%;height:52px" missingMessage="必须填写班级">
            </div>
            <div>
                <input type="submit" value="提交"/>
            </div> 
    	</div>
    </form>
  </div>
  </body>
</html>
