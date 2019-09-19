<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>小码哥客户关系管理系统</title>
    <link rel="stylesheet" href="css/style.css">
    <script type="text/javascript" src="/plugins/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/plugins/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript">
        function submitForm() {
            $.post("/login", $("form").serialize(), function (data) {
                //返回json：
                //成功时：{"success":true,"msg":"登陆成功"}
                //失败时：{"success":false,"msg":"账号密码不匹配"}
                if (data.success) {
                    window.location.href = "/index";
                } else {
                    alert("账号密码不匹配");
                }
            }, "json");
        }

        function resetForm() {
            //使用js原生的dom对象来调用reset方法
            $("form")[0].reset();
        }

        //输入完账号密码后按回车登录
        $(document).keyup(function(event){
            // console.log(event);//打印xx事件
            //获取输入的username和password
            var username = $("#username").val();
            var password = $("#password").val();
            if(username !=null && password && event.keyCode==13){
                submitForm();
            }
        });

    </script>
</head>
<body>
<section class="container">
    <div class="login">
        <h1>用户登录</h1>
        <form method="post">
            <p><input id="username" type="text" name="username" value="" placeholder="账号"></p>
            <p><input id="password" type="password" name="password" value="" placeholder="密码"></p>
            <p class="submit">
                <input type="button" value="登录" onclick="submitForm()">
                <input type="button" value="重置" onclick="resetForm()">
            </p>
        </form>
    </div>
</section>
<div style="text-align:center;" class="login-help">
    <p>Copyright ©2015 广州小码哥教育科技有限公司</p>
</div>
</body>
</html>