<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<script>
    $(function () {
        $("#updateBannersInput").form('load','${pageContext.request.contextPath}/banner/queryOne?id=${param.id}')
    })
</script>
<div style="text-align: center">
    <form id="updateBannersInput" class="easyui-form" method="post">
        <input type="hidden" name="id" value="${param.id}">
        <div style="margin-top: 20px;">
            图片名称：<input type="text" name="title" class="easyui-textbox" data-options="required:true,iconCls:'icon-man'">
        </div>

        <div style="margin-top: 20px;">
            图片描述：<input type="text" name="d_desc" class="easyui-textbox" data-options="iconCls:'icon-man'">
        </div>
        <div style="margin-top: 20px;">
        <select class="easyui-combobox" name="status"
                style="width:220px" >
            <option value="y">展示</option>
            <option selected="selected" value="n">不展示</option>
        </select>
        </div>
        <div style="margin-top: 20px;">
            上传时间：<input type="text" name="createdate" class="easyui-datebox" >
        </div>
    </form>
</div>