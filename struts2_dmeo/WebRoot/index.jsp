<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<head id="Head1" runat="server">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title></title>
    <link href="EasyUi/themes/default/easyui.css" rel="stylesheet" />
    <link href="EasyUi/themes/icon.css" rel="stylesheet" />
    <link href="css/admin.css" rel="stylesheet" />
    <script src="EasyUi/jquery.min.js"></script>
    <script src="EasyUi/jquery.easyui.min.js"></script>
</head>
<body class="easyui-layout">
    <div data-options="region:'north',border:false" style="height: 74px; background: #2596ea; padding: 10px">
    	<span>
    		<h1 style="color: white;">学生信息管理系统</h1>
    	</span>
    </div>
    <div data-options="region:'south',border:false" style="height: 23px;">
        <div class="footer">EasyUIDemo</div>
    </div>
    <div data-options="region:'west',split:true" title="功能栏" style="width: 170px;">
        <div class="easyui-accordion" data-options="fit:true,border:false">
            <div title="查询信息" data-options="selected:true">
                <div style="margin: 5px">
                    <ul class="tree easyui-tree" data-options="animate:true,lines:true">
                        <li data-options="iconCls:'icon-group'">
                            <span>基本查询</span>
                            <ul>
                                <li data-options="iconCls:'icon-group_add'">
                                    <a href="student/Student_queryAll?pc=1" target="contents">查询所有信息</a>
                                </li>
                                <li data-options="iconCls:'icon-group_delete'">
                                    <a href="QueryByCon.jsp" target="contents">按条件查询</a>
                                </li>
                                <li data-options="iconCls:'icon-group_edit'">
                                    <a href="AddStudent.jsp" target="contents">添加用户</a>
                                </li>
                            </ul>
                        </li>
                        <li data-options="state:'closed',iconCls:'icon-joystick'">
                            <span>系统设置</span>
                            <ul>
                                <li data-options="iconCls:'icon-joystick_add'">
                                    <span>test4</span>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
            <div title="导入信息" style="padding: 10px;">
            	<div style="margin: 5px">
            		<ul class="tree easyui-tree" data-options="animate:true,lines:true">
            			<li data-options="iconCls:'icon-group'">
            				<span><a href="Import.jsp" target="contents">导入信息</a><br/><br/></span>
            			</li>
            			<li data-options="iconCls:'icon-group'">
            				<span><a href="<%=path%>/student/Student_TempletDownload">模板下载</a></span>
            			</li>
            		</ul>
            	</div>
            </div>
            <div title="系统设置3" style="padding: 10px">
                content3
            </div>
        </div>
    </div>
    <div data-options="region:'center',title:'欢迎用户：${user }!!',iconCls:'icon-ok'">
        <iframe name="contents" style="width: 100%;height: 100%;"></iframe>
    </div>
</body>
</html>
