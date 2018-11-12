<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>持名法州主页</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back/easyui/themes/IconExtension.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back/easyui/themes/black/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back/easyui/themes/icon.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/back/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/back/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/back/easyui/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/back/easyui/form.validator.rules.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/back/easyui/datagrid-detailview.js"></script>
<script type="text/javascript">
    if("${sessionScope.admin}"==""){
        location.href="${pageContext.request.contextPath}/back/admin/login.jsp";
    }
	<!--菜单处理-->
    $(function () {
        //页面加载完成之后显示菜单数据
        $.post("${pageContext.request.contextPath}/menu/queryAll",function (menu) {
            //遍历一级菜单
            $.each(menu,function (index,m) {
                //遍历二级菜单
                var content = "<div style='text-align: center;'>";
                $.each(m.menus,function(idx,child){
                    content += "<a onclick=\"addTabs('"+child.name+"','"+child.iconCls+"','"+child.href+"')\" " +
                        "style='width:95%;margin:10px; border: 2px #95B8E7 solid;' class='easyui-linkbutton' " +
                        "data-options=\"plain:true,iconCls:'"+child.iconCls+"'\">"+child.name+"</a><br>";
                });
                content += "</div>"
                //添加菜单
                $("#menu").accordion('add',{
                    title:m.name,
                    iconCls:m.iconCls,
                    content:content,
                })
            });
        });
    });
    function addTabs(title,iconCls,href){
        if(!$("#tabs").tabs('exists',title)){
            $("#tabs").tabs('add',{
                title:title,
                iconCls:iconCls,
                closable:true,
                fit:true,
                href:"${pageContext.request.contextPath}/"+href,
            });
        }else{
            $("#tabs").tabs('select',title);
        }
    }

    //修改密码
    function updPWDRow(id) {
        $("#updateUserDialog").dialog({
            href:'${pageContext.request.contextPath}/back/admin/update.jsp',
            buttons:[
                {
                    iconCls:'icon-edit',
                    text:"修改",
                    handler:function(){
                        $("#updateDeptsInput").form('submit',{
                            url:"${pageContext.request.contextPath}/admin/update",
                            success:function (result) {
                                var resultObj = $.parseJSON(result);
                                if(resultObj.success){
                                    $.messager.show({title:'提示',msg:"密码修改成功"})
                                }else{
                                    $.messager.show({title:'提示',msg:resultObj.message})
                                }
                                $("#updateUserDialog").dialog('close');
                            }
                        })
                    }
                },
                {
                    iconCls:'icon-cancel',
                    text:"取消",
                    handler:function(){
                        $("#updateUserDialog").dialog('close');
                    }
                }
            ]
        })
    }
    setInterval("document.getElementById('time1').innerHTML=new Date().toLocaleString()+' 星期'+'日一二三四五六'.charAt(new Date().getDay());", 1000);
</script>

</head>
<body class="easyui-layout">

    <div data-options="region:'north',split:false" style="height:60px;background-color:  #5C160C">

        <%--动态时间--%>
        <div id="time1" style="font-size: 16px;color: #FAF7F7;font-family: 楷体;width: 300px;float:left;padding-top:15px;padding-left: 20px"></div>

    	<div style="font-size: 24px;color: #FAF7F7;font-family: 楷体;font-weight: 900;width: 500px;float:left;padding-left: 290px;padding-top: 10px;" >持名法州后台管理系统</div>

        <div style="font-size: 16px;color: #FAF7F7;font-family: 楷体;width: 300px;float:right;padding-top:15px">欢迎您:<span style="color: #00ee00">${sessionScope.admin.name}</span> &nbsp;<a href="#" onclick="updPWDRow()" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改密码</a>&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/admin/out" class="easyui-linkbutton" data-options="iconCls:'icon-01'">退出系统</a></div>
    </div>

    <div data-options="region:'south',split:false" style="height: 40px;background: #5C160C">
    	<div style="text-align: center;font-size:15px; color: #FAF7F7;font-family: 楷体" >&copy;百知教育 htf@zparkhr.com.cn</div>
    </div>

    <div data-options="region:'west',title:'导航菜单',split:false" style="width:220px;">
    	<div id="menu" class="easyui-accordion" data-options="fit:true">
		</div>
    </div>

    <div data-options="region:'center'">
    	<div id="tabs" class="easyui-tabs" data-options="fit:true,narrow:true,pill:true">
		    <div title="主页" data-options="iconCls:'icon-neighbourhood',"  style="background-image:url(image/shouye.jpg);background-repeat: no-repeat;background-size:100% 100%;"></div>
		</div>
    </div>

    <div id="updateUserDialog" data-options="draggable:false,iconCls:'icon-edit',width:600,height:400,title:'修改管理员密码'">

    </div>

</body>

</html>