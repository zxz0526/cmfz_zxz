<%@page pageEncoding="UTF-8" isELIgnored="false" %>

<script type="text/javascript">

    $(function () {
        $.get("${pageContext.request.contextPath}/guru/queryAll",function (result) {
            $.each(result,function (idx,gurus) {
                var option=$("<option></option>").attr("value",gurus.id).text(gurus.name);
                $("#article1").append(option);
            })
        },"JSON");
    })
    $(function () {
        $("#updateArticleInput").form('load','${pageContext.request.contextPath}/article/queryOne?id=${param.id}')
    })

</script>

<div style="text-align: center">
    <form id="updateArticleInput" class="easyui-form" method="post" enctype="multipart/form-data">
        <input name="id" type="hidden" value="{param.id}">
        <div style="margin-top: 20px;">
            名称: <input type="text" name="title" class="easyui-textbox" >
        </div>
        <div style="margin-top: 20px;">
            图片：<input name="file"  class="easyui-filebox" data-options="width:180,height:25,prompt:'请选择文件',buttonText:'上传文件'">
        </div>
        <div style="margin-top: 20px;">
            简介：<input type="text" name="content" class="easyui-textbox" >
        </div>

        <div style="margin-top: 20px;" >
            作者：<select   style="width:160px;height: 23px" id="article1" name="a_id">
                        <option>请选择</option>
                  </select>
        </div>
    </form>
</div>