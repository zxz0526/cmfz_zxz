<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<div style="text-align: center">
    <form id="addBannersInput" class="easyui-form" method="post" enctype="multipart/form-data">
        <div style="margin-top: 20px;">
            图片名称: <input type="text" name="title" class="easyui-textbox" data-options="prompt:'请输入轮播图名称',">
        </div>
        <div style="margin-top: 20px;">
            图片：<input name="file"  class="easyui-filebox" data-options="width:180,height:25,prompt:'请选择文件',buttonText:'上传文件'">
        </div>
        <div style="margin-top: 20px;">
            图片描述：<input type="text" name="d_desc" class="easyui-textbox" data-options="prompt:'请输入轮播图描述信息',">
        </div>

        <div style="margin-top:20px">
        <select class="easyui-combobox" name="status"
                style="width:220px" >
            <option selected="selected" value="y">展示</option>
            <option  value="n">不展示</option>
        </select>
        </div>
    </form>
</div>