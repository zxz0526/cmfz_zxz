<%@page pageEncoding="UTF-8" isELIgnored="false" %>


<script>
    $(function () {
        $("#courseDg").datagrid({
            url:"${pageContext.request.contextPath}/course/queryAll",
            toolbar:'#courseTB',
            fitColumns:true,
            columns:[[
                {title:"Id",field:"id",align:"center",width:120,},
                {title:"课程",field:"title",align:"center",width:120,},
                {title:"状态",field:"marking",align:"center",width:120,
                    formatter: function(value,row,index){
                        if(row.marking == 'y')
                            return "<font color=\"#1e90ff\">必修</font>";
                        else
                            return "选修";
                    }},
                {title:"上传时间",field:"recordDate",align:"center",width:120,},
                {title:"操作",field:"options",width:120,formatter:function (value,row,index) {
                        return "<a href='javascript:;' class='options' onclick=\"delCourseRow('"+row.id+"')\" data-options=\"iconCls:'icon-remove',plain:true\">删除</a>&nbsp;&nbsp;"

                }}
            ]],
            onLoadSuccess:function () {
                $(".options").linkbutton();
            },
        })
    })

    function delCourseRow(id){
        $.messager.confirm("确认对话框","确定删除么？？",function(b){
            if(b){
                $.ajax({
                    url:"${pageContext.request.contextPath}/course/delete",
                    type:"POST",
                    traditional:true,
                    data:{id:id},
                    success:function(){
                        $.messager.show({title:'提示',msg:"删除成功!!!"});
                        $("#courseDg").datagrid('reload');
                    },
                    error:function(){
                        //消息提示
                        $.messager.show({title:'提示',msg:"删除失败!!!"});
                        //刷新datagrid
                        $("#courseDg").datagrid('reload');
                    }
                })

            }
        })
    }


    //增加
     function addCourse() {
         $("#addCourseDialog").dialog({
             buttons:[{
                 iconCls:'icon-save',
                 text:"增加",
                 handler:function(){
                    $("#addCourseInput").form('submit',{
                        url:"${pageContext.request.contextPath}/course/add",
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
                            $("#addCourseDialog").dialog('close');
                            //刷新datagrid
                            $("#courseDg").datagrid('reload');
                        }
                    })
                 }
             }, {
                 iconCls: 'icon-cancel',
                 text: '关闭',
                 handler: function () {
                     $("#addCourseDialog").dialog('close');
                 }
             }]
         })
     }




</script>

<table id="courseDg" class="easyui-datagrid" data-options="fit:true">

</table>

<div id="courseTB">
    <a href="#" class="easyui-linkbutton" onclick="addCourse()" data-options="iconCls:'icon-add',plain:true">添加功课</a>
</div>

<div id="addCourseDialog" data-options="href:'${pageContext.request.contextPath}/back/course/add.jsp',draggable:false,iconCls:'icon-save',width:600,height:400,title:'增加功课'">

</div>


