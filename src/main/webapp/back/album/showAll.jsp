<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>

<script type="text/javascript">
    $(function () {
        $('#albumTB').treegrid({
            url: '${pageContext.request.contextPath}/album/queryByPage',
            striped:true, <%-- 斑马线 --%>
            idField: 'id',	<%--定义关键字段来标识树节点。（必须的）--%>
            treeField: 'title',<%-- 定义树节点字段。（必须的） --%>
            animate:true,  <%-- 定义在节点展开或折叠的时候是否显示动画效果。 --%>
            loadMsg:'正在加载中,请等待。。。', //远程加载时显示信息
            pagination:true, //显示分页工具栏
            onlyLeafCheck:true,//定义是否仅在叶子节点前显示复选框
            toggle:true,
            fitColumns:true,
            onDblClickRow:function (row){
                if (row == null) {
                    alert("请先选中行！！！")
                } else {
                    if (row.downPath != null) {
                        $("#album_paly").dialog("open");
                        $("#audio").prop("src", "${pageContext.request.contextPath}"+row.downPath)
                    } else {
                        alert("请先选妙音！！！")
                    }
                }
            },
            columns: [[
                {title: 'cks', field: 'cks', checkbox: true},
                {field: 'id', title: 'id', width: 80},
                {field: 'title', title: '标题', width: 160},
                {field: 'duration', title: '章节时长', width: 80},
                {field: 'fileSize', title: '章节大小', width: 80},
                {field: 'uploadTime', title: '修改时间', width: 160},
            ]],
            toolbar: [
                {
                    text: '添加文章',
                    iconCls: 'icon-add',
                    handler: openChapterDialog,
                },
                {
                    text: '添加专辑',
                    iconCls: 'icon-add',
                    handler: openAlbumDialog,
                },
                {
                    iconCls: 'icon-tip',
                    text: "专辑详情",
                    handler: function () {
                        var row = $("#albumTB").treegrid("getSelected");
                        if(row == null ){
                            alert("请选中行！！！！");
                        }else{
                            if(row.fileSize == null){  //选中的是专辑
                                $("#album_dialog").dialog("open");
                                /*填充内容*/
                                $("#album_ff").form("load",row);
                                $("#coverImg").prop("src",'${pageContext.request.contextPath}'+row.coverImg);

                            }else {
                                alert("请先选择专辑！！！");
                            }
                        }
                    }
                },{
                    text: "下载音频",
                    iconCls: 'icon-undo',
                    handler: function () {
                        var row = $("#albumTB").treegrid("getSelected");
                        if(row==null){
                            $.messager.show({title:'提示',msg:'至少选中一个音频'});
                        }else{
                            if(row.downPath!=null){
                                location.href = "${pageContext.request.contextPath}/chapter/download?fileName=" + row.downPath + "&openStyle=attachment";
                            }else{
                                $.messager.show({title:'提示',msg:'请选择您要下载的音频'});
                            }
                        }
                    }
                }
            ],
            onLoadSuccess: function(row){
                $(this).treegrid('enableDnd', row?row.id:null);
            }
        });
    });

    //添加
    function openAlbumDialog() {
        $("#saveAlbumDialog").dialog({
            href: '${pageContext.request.contextPath}/back/album/add.jsp',
            buttons: [
                {
                    text: '保存',
                    iconCls: "icon-save",
                    handler: function () {
                        $("#saveAlbumInputForm").form('submit', {
                            url: '${pageContext.request.contextPath}/album/add',
                            success: function (result) {//响应的一定是json格式字符串 使用应该先转为js对象
                                //提示信息
                                $.messager.show({title: '提示', msg: "专辑添加成功！！！"});
                                //关闭对话框
                                $("#saveAlbumDialog").dialog('close');
                                //刷新datagrid
                                $("#albumTB").treegrid('reload');
                            }
                        });
                    }
                },
                {
                    text: '关闭',
                    iconCls: "icon-cancel",
                    handler: function () {
                        $("#saveAlbumDialog").dialog('close');
                    }
                },
            ]
        });
    }

    //添加文章
    function openChapterDialog() {
        $("#saveChapterDialog").dialog({
            href: '${pageContext.request.contextPath}/back/chapter/add.jsp',
            buttons: [
                {
                    text: '保存',
                    iconCls: "icon-save",
                    handler: function () {
                        $("#saveChapterInputForm").form('submit', {
                            url: '${pageContext.request.contextPath}/chapter/add',
                            success: function (result) {//响应的一定是json格式字符串 使用应该先转为js对象
                                //提示信息
                                $.messager.show({title: '提示', msg: "专辑添加成功！！！"});
                                //关闭对话框
                                $("#saveChapterDialog").dialog('close');
                                //刷新datagrid
                                $("#albumTB").treegrid('reload');
                            }
                        });
                    }
                },
                {
                    text: '关闭',
                    iconCls: "icon-cancel",
                    handler: function () {
                        $("#saveChapterDialog").dialog('close');
                    }
                },
            ]
        });
    }

</script>

<%-- 章节树形结构图 --%>
<table id="albumTB" title="测试专辑中" class="easyui-treegrid" data-options="fit:true"></table>

<div id="saveAlbumDialog" data-options="width:600,height:400,iconCls:'icon-add',title:'添加专辑'"></div>

<div id="saveChapterDialog" data-options="width:600,height:400,iconCls:'icon-add',title:'添加文章'"></div>

<%--专辑详情--%>
<div id="album_dialog" class="easyui-dialog" title="专辑详情" style="width:400px;height:550px;"
     data-options="iconCls:'icon-tip',resizable:true,modal:true,closed:true">

    <form id="album_ff" method="post">
        <div style="height: 40px;margin-top: 30px;padding-left: 20%;padding-right: 20%">
            <label for="title">专辑名称:</label>
            <input id="title" name="title" type="text"  class="easyui-validatebox"  style="width:100%;height: 100%">
        </div>

        <div style="height: 40px;margin-top: 30px;padding-left: 20%;padding-right: 20%">
            <label for="count">专辑集数:</label>
            <input id="count" name="count" type="text"  class="easyui-validatebox"  style="width:100%;height: 100%">
        </div>

        <div style="height: 40px;margin-top: 30px;padding-left: 20%;padding-right: 20%">
            <label for="star">专辑星级:</label>
            <input id="star" name="star" type="text"  class="easyui-validatebox"  style="width:100%;height: 100%">
        </div>

        <div style="height: 40px;margin-top: 30px;padding-left: 20%;padding-right: 20%">
            <label for="author">专辑作者:</label>
            <input id="author" name="author" type="text"  class="easyui-validatebox"  style="width:100%;height: 100%">
        </div>

        <div style="height: 40px;margin-top: 30px;padding-left: 20%;padding-right: 20%">
            <label for="broadCast">播音员:</label>
            <input id="broadCast" name="broadCast" type="text"  class="easyui-validatebox"  style="width:100%;height: 100%">
        </div>

        <div style="height: 40px;margin-top: 30px;padding-left: 20%;padding-right: 20%">
            <label for="brief">简介:</label>
            <input id="brief" name="brief" type="text"  class="easyui-validatebox"  style="width:100%;height: 100%">
        </div>

        <div style="height: 40px;margin-top: 30px;padding-left: 20%;padding-right: 20%">
            <label for="publishDate">发布日期:</label>
            <input id="publishDate" name="publishDate" type="text"  class="easyui-validatebox"  style="width:100%;height: 100%">
        </div>

        <div style="margin-top: 30px;padding-left: 20%;padding-right: 20%">
            <label for="coverImg">封面:</label>
            <img id="coverImg" src="${pageContext.request.contextPath}+coverImg" style="width:50%;height: 50%">
        </div>
    </form>

    <%--双击播放--%>
    <div id="album_paly" class="easyui-dialog" title="播放音频" style="width:400px;height:200px;"
         data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">
        <audio id="audio" src="" controls="controls" autoplay="autoplay"></audio>
    </div>

</div>
