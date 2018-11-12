<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<div style="text-align: center">
    <form id="addGuruInput" class="easyui-form" method="post" enctype="multipart/form-data">
        <div style="margin-top: 20px;">
            姓名：<input type="text" name="name" class="easyui-textbox" style="width:180px;" data-options="required:true,iconCls:'icon-man',">
        </div>
        <div style="margin-top: 20px;">
            头像：<input name="file"  class="easyui-filebox" data-options="width:180,height:25,prompt:'请选择文件',buttonText:'上传文件'">
        </div>
        <div style="margin-top: 20px;">
            性别：<select id="cc" class="easyui-combobox" name="sex" style="width:180px;">
                    <option value="男">男</option>
                    <option value="女">女</option>
                 </select>
        </div>
    </form>
</div>