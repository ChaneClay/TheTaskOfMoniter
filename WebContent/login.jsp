<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>研究生管理系统</title>
    <link rel="stylesheet" type="text/css" href="https://www.layuicdn.com/layui/css/layui.css">
   <!--  <link rel="stylesheet" type="text/css" href="/static/layui/css/layui.css"> -->
    <style>
        .login{
            background-color: rgba(254, 255, 162, 0.23);
            margin: auto;
            margin-top: 8%;
            padding: 50px;
            width: 250px;
            border-radius: 15px;
            box-shadow:0 0 3px #000 inset;


        }
        .system{
            font-family: 宋体;
            letter-spacing: 13px;
        }
        body{

            background-image: url("/static/img/background.jpg");
        }
    </style>
</head>
<body>

    <%--<div style="color: red">${errMsg}</div>--%>
<div class="login">
    <div>
        <h2 class="system">研究生管理系统</h2>
    </div>

    <hr class="layui-bg-orange">

    <form class="layui-form" action="${ctx}/stu/login" method="post">     <!--表单-->
    <%--<form class="layui-form" action="/jsp/dev/login.jsp" method="post">     <!--表单-->--%>
        <br/>
        <div class="layui-form-item">
            <label class="layui-form-label">账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</label>
            <div class="layui-input-block">
                <input type="text" name="num" lay-verify="title" autocomplete="off" placeholder="请输入账号" class="layui-input">
            </div>
        </div>
        <br/>
        <div class="layui-form-item">
            <label class="layui-form-label">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：</label>
            <div class="layui-input-block">
                <input type="password" name="password" lay-verify="pass" placeholder="请输入密码" autocomplete="off" class="layui-input">
            </div>
        </div>

        <br/>

        <div class="layui-form-item" style="padding-left: 65px">
            <div class="layui-input-inline">
                <button type="submit" class="layui-btn layui-btn-warm" lay-submit="" lay-filter="demo1">登录</button>
                <button type="button" onclick="window.location.href='/jsp/register.jsp'" class="layui-btn layui-btn-warm">注册</button>
            </div>
        </div>


    </form>

</div>

<script src="/static/layui/layui.js"></script>
<script>
    layui.use('layer', function(){
        var layer = layui.layer;
        if("${requestScope.error}" != "" )
        {
            layer.msg("${requestScope.error}");         //重要
        }
        if("${requestScope.success}" != "" )
        {
            layer.msg("${requestScope.success}");         //重要
        }

    });
</script>

</body>
</html>
