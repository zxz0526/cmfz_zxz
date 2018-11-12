<%@page pageEncoding="UTF-8" isELIgnored="false" %>


<script>
    $(function () {
        $("#guru").datagrid({
            url:"${pageContext.request.contextPath}/guru/showAll",
            loadMsg:"加载中！",
            pagination:true,
            toolbar:'#addguru',
            fitColumns:true,
            pageSize:5,
            pageList:[5,10,20,30,50],
            columns:[[
                {title:"Id",field:"id",align:"center",width:120,},
                {title:"上师姓名",field:"name",align:"center",width:120,},
                {title:"图片",field:"headPic",align:"center",width:120,},
                {title:"性别",field:"sex",align:"center",width:120,},
                {title:"操作",field:"options",width:120,formatter:function (value,row,index) {
                        return "<a href='javascript:;' class='options' onclick=\"delGuruRow('"+row.id+"')\" data-options=\"iconCls:'icon-remove',plain:true\">删除</a>&nbsp;&nbsp;"

                }}
            ]],
            onLoadSuccess:function () {
                $(".options").linkbutton();
            },
            view: detailview,
            detailFormatter: function(rowIndex, rowData){
                return '<table><tr>' +
                    '<td rowspan=2 style="border:0"><img src="${pageContext.request.contextPath}' + rowData.headPic + '" style="height:50px;"></td>' +
                    '<td style="border:0">' +
                    '<p>Attribute: ' + rowData.name + '</p>' +
                    '<p>Status: ' + rowData.sex + '</p>' +
                    '</td>' +
                    '</tr></table>';
            }
        })
    })

    //删除
    function delGuruRow(id){
        $.messager.confirm("确认对话框","确定删除么？？",function(b){
            if(b){
                $.ajax({
                    url:"${pageContext.request.contextPath}/guru/delete",
                    type:"POST",
                    traditional:true,
                    data:{id:id},
                    success:function(){
                        $.messager.show({title:'提示',msg:"删除成功!!!"});
                        $("#guru").datagrid('reload');
                    },
                    error:function(){
                        //消息提示
                        $.messager.show({title:'提示',msg:"删除失败!!!"});
                        //刷新datagrid
                        $("#guru").datagrid('reload');
                    }
                })
            }
        })
    }



    //增加
     function addGuru() {
         $("#addGuruDialog").dialog({
             buttons:[{
                 iconCls:'icon-save',
                 text:"增加",
                 handler:function(){
                    $("#addGuruInput").form('submit',{
                        url:"${pageContext.request.contextPath}/guru/add",
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
                            $("#addGuruDialog").dialog('close');
                            //刷新datagrid
                            $("#guru").datagrid('reload');
                        }
                    })
                 }
             }, {
                 iconCls: 'icon-cancel',
                 text: '关闭',
                 handler: function () {
                     $("#addGuruDialog").dialog('close');
                 }
             }]
         })
     }




</script>

<table id="guru" class="easyui-datagrid" data-options="fit:true">

</table>

<div id="addguru">
    <a href="#" class="easyui-linkbutton" onclick="addGuru()" data-options="iconCls:'icon-add',plain:true">添加上师</a>
</div>

<div id="addGuruDialog" data-options="href:'${pageContext.request.contextPath}/back/guru/add.jsp',draggable:false,iconCls:'icon-save',width:600,height:400,title:'保存图片信息'">

</div>


