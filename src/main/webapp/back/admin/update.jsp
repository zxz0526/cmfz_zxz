<%@page pageEncoding="UTF-8" isELIgnored="false" %>

<div style="text-align: center">
    <form id="updateDeptsInput" class="easyui-form" method="post">
        <input type="hidden" name="id" value="${sessionScope.admin.id}">
        <div style="margin-top: 70px";>
            原密码：<input type="text" name="oldpassword" class="easyui-passwordbox" data-options="required:true,validType:'pwd'">
        </div>
        <div style="margin-top: 70px";>
            新密码：<input type="text" name="password" class="easyui-passwordbox" data-options="required:true,validType:'pwd'">
        </div>
      <%--<div style="margin-top: 20px;">
            确认密码: <input type="text" name="password" class="easyui-textbox" data-options="validType:'cofirmPwd'">
      </div>--%>
    </form>
</div>