<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>学生管理信息系统--管理员</title>
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <link href="css/mycss.css" rel="stylesheet">
    <link href="plugin/easyui/themes/icon.css" rel="stylesheet">
    <link href="plugin/easyui/themes/bootstrap/easyui.css" rel="stylesheet">
    <link href="css/bootstrap-switch.min.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <a class="navbar-brand" href="#">学生信息管理系统--管理员</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">欢迎您：管理员<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="index.jsp">学生信息管理</a></li>
                        <li><a href="adminCourseManager.jsp">课程信息管理</a></li>
                        <li><a href="adminAFLManager.jsp">请假信息管理</a></li>
                        <li><a href="adminDormiManager.jsp">住宿信息管理</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="/stu/user/adminLogout.do">退出系统</a></li>
                        <li><a href="adminChangePassword.jsp">修改密码</a></li>
                    </ul>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>

<div class="container-fluid">
    <div class="col-md-2">
        <ul class="nav nav-pills nav-stacked">
            <li role="presentation"><a href="index.jsp">学生信息管理</a></li>
            <li role="presentation"><a href="adminCourseManager.jsp">课程信息管理</a></li>
            <li role="presentation"><a href="adminAFLManager.jsp">请假信息管理</a></li>
            <li role="presentation"  class="active"><a href="adminDormiManager.jsp">住宿信息管理</a></li>
        </ul>
    </div>

    <div class="col-md-10">
        <div>
            <ol class="breadcrumb">
                <li><a href="javascript:void(0)">首页</a></li>
                <li class="active">住宿信息管理</li>
            </ol>
        </div>

        <div id="content">
        </div>

        <div id="toolBar" class="form-horizontal" style="width:100%; margin-bottom:0;vertical-align: middle;">
            <div id="toolBar_btn" class="form-group col-lg-8 col-md-8"  style="margin-bottom:0;">
                <button class="btn btn-default btn-sm" id="addZoneBtn">添加园区</button>
                <button class="btn btn-default btn-sm" id="delZoneBtn">删除园区</button>
            </div>

            <div id="searchBar" class="form-group" style="margin-bottom:0;">
                <div class="form-group col-lg-4 col-md-4" style="margin-bottom:0;">
                    <input type="text" class="form-control form-inline input-sm" placeholder="姓名、区域名称" id="searchBarText">
                </div>
                <button id="searchBarBtn" class="btn btn-default btn-sm">搜索</button>
            </div>
        </div>

        <div id="updateDormBox" style="padding: 20px;display: none">
            <div class="form-horizontal" id="updateDormForm">
                <div class="form-group">
                    <label for="dormZoneCombox" class="col-md-3 control-label">园区:</label>
                    <div class="col-md-8">
                        <input type="hidden" class="form-control" id="update-hiddenID" name="id">
                        <input type="text" class="form-control" id="dormZoneCombox" placeholder="请选择园区名">
                    </div>
                </div>
                <div class="form-group">
                    <label for="update-building" class="col-md-3 control-label">楼栋:</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" id="update-building" name="updateBuilding">
                    </div>
                </div>
                <div class="form-group">
                    <label for="update-room" class="col-md-3 control-label">房间:</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" id="update-room" name="updateRoom">
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-md-offset-4">
                        <button id="subDormAddBtn" class="btn btn-success">添加</button>
                        <button id="subDormUpdateBtn" class="btn btn-warning">更新</button>
                        <button id="subDormDelBtn" class="btn btn-danger">删除</button>
                        <button type="reset" class="btn btn-default">重置</button>
                    </div>
                </div>
            </div>
        </div>

        <div id="addZoneBox" style="padding: 20px;display: none">
            <div class="form-horizontal" id="addZoneForm">

                <div class="form-group">
                    <label for="update-building" class="col-md-3 control-label">园区名:</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" id="addZoneName" name="ZoneName" placeholder="请输入园区名">
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-md-offset-3">
                        <button id="subAddZoneBtn" class="btn btn-success">添加</button>
                        <button type="reset" class="btn btn-default">重置</button>
                    </div>
                </div>
            </div>
        </div>

        <div id="delZoneBox" style="padding: 20px;display: none">
            <div class="form-horizontal" id="delZoneForm">

                <div class="form-group">
                    <label for="delZoneCombox" class="col-md-3 control-label">园区:</label>
                    <div class="col-md-8">
                        <input type="hidden" class="form-control" id="delete-hiddenID" name="id">
                        <input type="text" class="form-control" id="delZoneCombox" placeholder="请选择园区名">
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-md-offset-3">
                        <button id="subDelZoneBtn" class="btn btn-danger">删除</button>
                        <button type="reset" class="btn btn-default">重置</button>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery-2.1.1.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.js"></script>
<script src="js/bootstrap-switch.min.js"></script>
<script src="plugin/easyui/jquery.easyui.min.js"></script>
<script src="plugin/easyui/locale/easyui-lang-zh_CN.js"></script>
<script src="js/adminDormiManager.js"></script>

</body>
</html>