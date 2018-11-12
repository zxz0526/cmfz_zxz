<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<div style="text-align: center">
    <form id="addCourseInput" class="easyui-form" method="post" enctype="multipart/form-data">
        <div style="margin-top: 20px;">
            课程: <input type="text" name="title" class="easyui-textbox" data-options="prompt:'请输入功课名称',">
        </div>
        <div style="margin-top:20px">
            <select class="easyui-combobox" name="marking"
                    style="width:200px" >
                <option selected="selected" value="y">必修</option>
                <option  value="n">选修</option>
            </select>
        </div>
    </form>
</div>