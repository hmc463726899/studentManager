<%@ page language="java" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>注册</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="plugin/easyui/themes/bootstrap/easyui.css" rel="stylesheet">
    <link href="css/mycss.css" rel="stylesheet">
</head>
<body>
<div class="signup-box">
    <fieldset>
        <legend><h2>学生用户注册</h2></legend>
        <fieldset>
            <div class="form-horizontal mybox">
                <div class="form-group">
                    <lebel for="username" class="col-md-3 control-label">用户名：</lebel>
                    <div class="col-md-8">
                        <input type="text" class="form-control" id="username" placeholder="请输入用户名">
                    </div>
                </div>
                <div class="form-group">
                    <lebel for="password" class="col-md-3 control-label">密码：</lebel>
                    <div class="col-md-8">
                        <input type="password" class="form-control" id="password" placeholder="请输入密码">
                    </div>
                </div>
                <div class="form-group">
                    <lebel for="password" class="col-md-3 control-label">身份证号码：</lebel>
                    <div class="col-md-8">
                        <input type="password" class="form-control" id="idn" placeholder="请输入身份证号码">
                    </div>
                </div>
                <div class="form-group">
                    <lebel for="password" class="col-md-3 control-label">电话号码：</lebel>
                    <div class="col-md-8">
                        <input type="password" class="form-control" id="phone" placeholder="请输入电话号码">
                    </div>
                </div>
                <div class="form-group">
                    <lebel for="password" class="col-md-3 control-label">性别：</lebel>
                    <div class="col-md-8">
                        <label class="radio-inline">
                            <input type="radio" name="gender" value="m" checked> 男
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="gender" value="f"> 女
                        </label>
                    </div>
                </div>
                <div class="form-group" style="margin-top: 30px;">
                    <div class="col-md-offset-5">
                        <button class="btn btn-primary" id="signup">注册</button>
                        <button class="btn btn-default" id="reset">重置</button>
                    </div>
                </div>
            </div>
        </fieldset>
    </fieldset>
</div>
<script src="js/jquery-2.1.1.min.js"></script>
<script src="plugin/easyui/jquery.easyui.min.js"></script>
<script src="js/bootstrap.js"></script>
<script src="js/login.js"></script>
</body>
</html>