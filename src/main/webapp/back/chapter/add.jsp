<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<script type="text/javascript">
    
    $(function () {
        $.get("${pageContext.request.contextPath}/album/showAll",function (result) {
            $.each(result,function (idx,albums) {
                var option=$("<option></option>").attr("value",albums.id).text(albums.title);
                $("#album").append(option);
            })
        },"JSON");
    })
    
</script>
<div style="text-align: center">
    <form id="saveChapterInputForm" class="easyui-form" method="post" enctype="multipart/form-data">
        <div style="margin-top: 20px;">
            文章名称： <input type="text" name="title" class="easyui-textbox" data-options="prompt:'请输入文章名称',">
        </div>
        <div style="margin-top: 20px;">
            文件大小： <input type="text" name="fileSize" class="easyui-textbox" >
        </div>
        <div style="margin-top: 20px;">
            文件时长： <input type="text" name="duration" class="easyui-textbox" >
        </div>
        <div style="margin-top: 20px;">
            文件音频： <input name="file"  class="easyui-filebox" data-options="width:160,height:25,prompt:'请选择文件',buttonText:'上传文件'">
        </div>


        <div style="margin-top: 20px;" >
           所属专辑： <select   style="width:160px;height: 23px;" id="album" name="c_id">
                            <option>请选择</option>
                      </select>
        </div>
    </form>
</div>