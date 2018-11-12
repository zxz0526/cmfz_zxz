<%@page pageEncoding="UTF-8" isELIgnored="false" %>


<script>

    $(function () {
        $("#articleDg").datagrid({
            url:"${pageContext.request.contextPath}/article/showAll",
            pagination:true,
            toolbar:'#articleTB',
            fitColumns:true,
            columns:[[
                {title:"Id",field:"id",align:"center",width:120,},
                {title:"文章名称",field:"title",align:"center",width:120,},
                {title:"图片",field:"imgPath",align:"center",width:120,},
                {title:"描述",field:"content",align:"center",width:120,},
                {title:"上传时间",field:"publishDate",align:"center",width:120,},
                {title:"作者",field:"name",align:"center",width:120,
                    formatter:function (value,row,index) {
                        if(row.guru){
                            return row.guru.name;
                        }else{
                            return value;
                        }
                    }
                },
                {title:"操作",field:"options",width:120,formatter:function (value,row,index) {
                        return "<a href='javascript:;' class='options' onclick=\"deleteRow('"+row.id+"')\" data-options=\"iconCls:'icon-remove',plain:true\">删除</a>&nbsp;&nbsp;" +
                            "<a href='javascript:;' class='options' onclick=\"updateRow('"+row.id+"')\" data-options=\" iconCls:'icon-edit',plain:true\">修改</ahref>"
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
                    '<p>Status: ' + rowData.content + '</p>' +
                    '</td>' +
                    '</tr></table>';
            }
        })
    })
    function deleteRow(id){
        $.messager.confirm("确认对话框","确定删除么？？",function(b){
            if(b){
                $.ajax({
                    url:"${pageContext.request.contextPath}/article/delete",
                    type:"POST",
                    traditional:true,
                    data:{id:id},
                    success:function(){
                        $.messager.show({title:'提示',msg:"删除成功!!!"});
                        $("#articleDg").datagrid('reload');
                    },
                    error:function(){
                        //消息提示
                        $.messager.show({title:'提示',msg:"删除失败!!!"});
                        //刷新datagrid
                        $("#articleDg").datagrid('reload');
                    }
                })

            }
        })
    }


    //增加
     function addArticle() {
         $("#addArticleDialog").dialog({
             buttons:[{
                 iconCls:'icon-save',
                 text:"增加",
                 handler:function(){
                    $("#addArticleInput").form('submit',{
                        url:"${pageContext.request.contextPath}/article/add",
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
                            $("#addArticleDialog").dialog('close');
                            //刷新datagrid
                            $("#articleDg").datagrid('reload');
                        }
                    })
                 }
             }, {
                 iconCls: 'icon-cancel',
                 text: '关闭',
                 handler: function () {
                     $("#addArticleDialog").dialog('close');
                 }
             }]
         })
     }

    //修改
    function updateRow(id) {
        $("#updateArticleDialog").dialog({
            href:'${pageContext.request.contextPath}/back/article/update.jsp?id='+id,
            buttons:[
                {
                    iconCls:'icon-edit',
                    text:"修改",
                    handler:function(){
                        $("#updateArticleInput").form('submit',{
                            url:"${pageContext.request.contextPath}/article/update",
                            success:function (result) {
                                var resultObj = $.parseJSON(result);
                                if(resultObj.success){
                                    $.messager.show({title:'提示',msg:"修改成功"})
                                }else{
                                    $.messager.show({title:'提示',msg:resultObj.message})
                                }
                                $("#updateArticleDialog").dialog('close');
                                $("#articleDg").datagrid('reload');
                            }
                        })
                    }
                },
                {
                    iconCls:'icon-cancel',
                    text:"取消",
                    handler:function(){
                        $("#updateArticleDialog").dialog('close');
                    }
                }
            ]
        })
    }


</script>

<table id="articleDg" class="easyui-datagrid" data-options="fit:true">

</table>

<div id="articleTB">
    <a href="#" class="easyui-linkbutton" onclick="addArticle()" data-options="iconCls:'icon-add',plain:true">添加文章</a>
</div>

<div id="addArticleDialog" data-options="href:'${pageContext.request.contextPath}/back/article/add.jsp',draggable:false,iconCls:'icon-save',width:600,height:400,title:'保存图片信息'">

</div>

<div id="updateArticleDialog" data-options="draggable:false,iconCls:'icon-edit',width:600,height:400,title:'修改图片信息'">

</div>
