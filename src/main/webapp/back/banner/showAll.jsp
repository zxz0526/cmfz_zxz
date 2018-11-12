<%@page pageEncoding="UTF-8" isELIgnored="false" %>


<script>
    $(function () {
        $("#dg").datagrid({
            url:"${pageContext.request.contextPath}/banner/queryAll",
            pagination:true,
            toolbar:'#tb',
            fitColumns:true,
            pageSize:5,
            pageList:[5,10,20,30,50],
            columns:[[
                {title:"Id",field:"id",align:"center",width:120,},
                {title:"图片名称",field:"title",align:"center",width:120,},
                {title:"图片",field:"imgPath",align:"center",width:120,},
                {title:"描述",field:"d_desc",align:"center",width:120,},
                {title:"状态",field:"status",align:"center",width:120,
                    formatter: function(value,row,index){
                        if(row.status == 'y')
                            return "<font color=\"#1e90ff\">展示中</font>";
                        else
                            return "未展示";
                    }},
                {title:"上传时间",field:"createdate",align:"center",width:120,},
                {title:"操作",field:"options",width:120,formatter:function (value,row,index) {
                        return "<a href='javascript:;' class='options' onclick=\"delRow('"+row.id+"')\" data-options=\"iconCls:'icon-remove',plain:true\">删除</a>&nbsp;&nbsp;" +
                            "<a href='javascript:;' class='options' onclick=\"updRow('"+row.id+"')\" data-options=\" iconCls:'icon-edit',plain:true\">修改</ahref>"
                }}
            ]],
            onLoadSuccess:function () {
                $(".options").linkbutton();
            },
            view: detailview,
            detailFormatter: function(rowIndex, rowData){
                return '<table><tr>' +
                    '<td rowspan=2 style="border:0"><img src="${pageContext.request.contextPath}' + rowData.imgPath + '" style="height:50px;"></td>' +
                    '<td style="border:0">' +
                    '<p>Attribute: ' + rowData.title + '</p>' +
                    '<p>Status: ' + rowData.status + '</p>' +
                    '</td>' +
                    '</tr></table>';
            }
        })
    })

 function delRow(id){
        $.messager.confirm("确认对话框","确定删除么？？",function(b){
            if(b){
                $.ajax({
                    url:"${pageContext.request.contextPath}/banner/delete",
                    type:"POST",
                    traditional:true,
                    data:{id:id},
                    success:function(){
                        $.messager.show({title:'提示',msg:"删除成功!!!"});
                        $("#dg").datagrid('reload');
                    },
                    error:function(){
                        //消息提示
                        $.messager.show({title:'提示',msg:"删除失败!!!"});
                        //刷新datagrid
                        $("#dg").datagrid('reload');
                    }
                })

            }
        })
    }
    //增加
     function addBanner() {
         $("#addBannerDialog").dialog({
             buttons:[{
                 iconCls:'icon-save',
                 text:"增加",
                 handler:function(){
                    $("#addBannersInput").form('submit',{
                        url:"${pageContext.request.contextPath}/banner/add",
                        success:function(result){
                            var resultObj = $.parseJSON(result);
                            if(resultObj.success){
                                //提示信息
                                $.messager.show({title:'提示',msg:"添加成功!!!"});
                            }else{
                                //提示信息
                                $.messager.show({title:'提示',msg:resultObj.message});
                            }
                            //关闭对话框
                            $("#addBannerDialog").dialog('close');
                            //刷新datagrid
                            $("#dg").datagrid('reload');
                        }
                    })
                 }
             }, {
                 iconCls: 'icon-cancel',
                 text: '关闭',
                 handler: function () {
                     $("#addBannerDialog").dialog('close');
                 }
             }]
         })
     }

    //修改
    function updRow(id) {
        $("#updateBannersDialog").dialog({
            href:'${pageContext.request.contextPath}/back/banner/update.jsp?id='+id,
            buttons:[
                {
                    iconCls:'icon-edit',
                    text:"修改",
                    handler:function(){
                        $("#updateBannersInput").form('submit',{
                            url:"${pageContext.request.contextPath}/banner/update",
                            success:function (result) {
                                var resultObj = $.parseJSON(result);
                                if(resultObj.success){
                                    $.messager.show({title:'提示',msg:"修改成功"})
                                }else{
                                    $.messager.show({title:'提示',msg:resultObj.message})
                                }
                                $("#updateBannersDialog").dialog('close');
                                $("#dg").datagrid('reload');
                            }
                        })
                    }
                },
                {
                    iconCls:'icon-cancel',
                    text:"取消",
                    handler:function(){
                        $("#updateBannersDialog").dialog('close');
                    }
                }
            ]
        })
    }


</script>

<table id="dg" class="easyui-datagrid" data-options="fit:true">

</table>

<div id="tb">
    <a href="#" class="easyui-linkbutton" onclick="addBanner()" data-options="iconCls:'icon-add',plain:true">添加图片</a>
</div>

<div id="addBannerDialog" data-options="href:'${pageContext.request.contextPath}/back/banner/add.jsp',draggable:false,iconCls:'icon-save',width:600,height:400,title:'保存图片信息'">

</div>

<div id="updateBannersDialog" data-options="draggable:false,iconCls:'icon-edit',width:600,height:400,title:'修改图片信息'">

</div>
