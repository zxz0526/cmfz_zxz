<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<div style="text-align: center">
    <form id="saveAlbumInputForm" class="easyui-form" method="post" enctype="multipart/form-data">
        <div style="margin-top: 20px;">
            名称：<input type="text" name="title" class="easyui-textbox" data-options="prompt:'请输入专辑名称',">
        </div>
        <div style="margin-top: 20px;">
            集数：<input type="text" name="count" class="easyui-textbox" data-options="prompt:'请输入专辑数量',validType:'number'">
        </div>
        <div style="margin-top: 20px;">
            图片：<input name="file"  class="easyui-filebox" data-options="width:160,height:25,prompt:'请选择文件',buttonText:'上传文件'">
        </div>
        <div style="margin-top: 20px;">
            分数：<input type="text" name="star" class="easyui-textbox" >
        </div>
        <div style="margin-top: 20px;">
            作者：<input type="text" name="author" class="easyui-textbox" >
        </div>
        <div style="margin-top: 20px;">
            播音：<input type="text" name="broadCast" class="easyui-textbox" >
        </div>
        <div style="margin-top: 20px;">
            简介：<input type="text" name="brief" class="easyui-textbox" >
        </div>
    </form>
</div>