<%@page pageEncoding="UTF-8" isELIgnored="false" %>

<script type="text/javascript">

    $(function () {
        $.get("${pageContext.request.contextPath}/guru/queryAll",function (result) {
            $.each(result,function (idx,gurus) {
                var option=$("<option></option>").attr("value",gurus.id).text(gurus.name);
                $("#article").append(option);
            })
        },"JSON");
    })

</script>
<div style="text-align: center">
    <form id="addArticleInput" class="easyui-form" method="post" enctype="multipart/form-data">
        <div style="margin-top: 20px;">
            名称：<input type="text" name="title" class="easyui-textbox" >
        </div>
        <div style="margin-top: 20px;">
            图片：<input name="file"  class="easyui-filebox" data-options="width:160,height:25,prompt:'请选择图片',buttonText:'上传封面'">
        </div>
        <div style="margin-top: 20px;">
            简介：<input type="text" name="content" class="easyui-textbox" >
        </div>

        <div style="margin-top: 20px;" >
            作者：<select   style="width:160px;height: 23px" id="article" name="a_id">
                    <option>请选择</option>
                  </select>
        </div>
    </form>
</div>