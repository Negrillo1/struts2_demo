<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'QueryByCon_success.jsp' starting page</title>
    <link href="EasyUi/themes/default/easyui.css" rel="stylesheet" />
    <link href="EasyUi/themes/icon.css" rel="stylesheet" />
    <link href="css/admin.css" rel="stylesheet" />
    <script src="EasyUi/jquery.min.js"></script>
    <script src="EasyUi/jquery.easyui.min.js"></script>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
		<table class="easyui-datagrid"
            data-options="url:'datagrid_data1.json',method:'get',border:false,singleSelect:true,fit:true,fitColumns:true">
            <thead>
                <tr>
                    <th data-options="field:'sid'" width="18%">学号</th>
                    <th data-options="field:'sname'" width="18%">姓名</th>
                    <th data-options="field:'ssex'" width="18%">性别</th>
                    <th data-options="field:'sage'" width="18%">年龄</th>
                    <th data-options="field:'sclass'" width="18%">班级</th>
                    <th data-options="field:'operate'" width="18%">操作</th>
                </tr>
            </thead>
			<s:iterator value="#session.pb.beanList" var="stu">
				<tr>
            		<td align="center"><s:property value="#stu.sid"/></td>
            		<td align="center"><s:property value="#stu.sname"/></td>
            		<td align="center"><s:property value="#stu.ssex"/></td>
            		<td align="center"><s:property value="#stu.sage"/></td>
            		<td align="center"><s:property value="#stu.classes.c_name"/></td>
            		<td align="center">
            		<a href="<%=path%>/student/Student_delete.action?sid=<s:property value="#stu.sid"/>" onclick="javascript:return confirm('确定删除？');">删除</a>
            		<a href="<%=path%>/student/Student_update1.action?sid=<s:property value="#stu.sid"/>">修改</a>
            		</td>
            		
            	</tr>
			</s:iterator> 
		
       	第${pb.pc }页/共${pb.tp }页
       		
        	<a href="${pb.url }&pc=1">首页</a>&nbsp;
        	<c:if test="${pb.pc > 1 }">
        	<a href="${pb.url }&pc=${pb.pc-1 }">上一页</a>&nbsp;
        	</c:if>
        	
        	<c:choose>
        		<c:when test="${pb.tp < 10 }">
        			<c:set var="begin" value="1"></c:set>
        			<c:set var="end" value="${pb.tp }"></c:set>
        		</c:when>
        		<c:otherwise>
        			<c:set var="begin" value="${pb.pc-5 }"></c:set>
        			<c:set var="end" value="${pb.pc+4 }"></c:set>
        			<%-- 头溢出 --%>
        			<c:if test="${begin < 1 }">
        				<s:set var="begin" value="1" />
        				<s:set var="end" value="10" />
        			</c:if>
        			<c:if test="${end > pb.tp }">
        				<c:set var="begin" value="${pb.tp - 9 }"></c:set>
        				<c:set var="end" value="${pb.tp }"></c:set>
        			</c:if>
        		</c:otherwise>
        	</c:choose>
        	<%-- 循环遍历页码列表 --%>
        	<c:forEach var="i" begin="${begin }" end="${end }">
        		<a href="${pb.url }&pc=${i }">${i }</a>&nbsp;&nbsp;
        	</c:forEach>
        	<c:if test="${pb.pc < pb.tp }">
        	<a href="${pb.url }&pc=${pb.pc+1 }">下一页</a>&nbsp;
        	</c:if>
        	<a href="${pb.url }&pc=${pb.tp}">尾页</a>

        </table>

  </body>
</html>
