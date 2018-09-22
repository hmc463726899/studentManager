<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
    if(request.getSession().getAttribute("adminName")==null)
    {
        out.print("<script type=\"text/javascript\"> alert(\"请先登陆!\");window.location=\"/login.jsp\" </script>");
    }
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
      <link href="css/dcalendar.picker.css" rel="stylesheet">
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
			    <li role="presentation" class="active"><a href="index.jsp">学生信息管理</a></li>
			    <li role="presentation"><a href="adminCourseManager.jsp">课程信息管理</a></li>
                <li role="presentation"><a href="adminAFLManager.jsp">请假信息管理</a></li>
                <li role="presentation"><a href="adminDormiManager.jsp">住宿信息管理</a></li>
			</ul>
	   </div>
	   <div class="col-md-10">
	   		<div>
				<ol class="breadcrumb">
				  <li><a href="javascript:void(0)">首页</a></li>
				  <li class="active">学生信息管理</li>
				</ol>
   			</div>
   			<div id="content">
   			</div>
		   <div id="toolBar" class="form-horizontal" style="width:100%; margin-bottom:0;vertical-align: middle;">
			   <div id="toolBar_btn" class="form-group col-lg-8 col-md-8"  style="margin-bottom:0;">
				   <button class="btn btn-default btn-sm" id="del-btn">多选删除</button>
				   <button class="btn btn-default btn-sm" id="showAll-btn">查询所有</button>
				   <button class="btn btn-default btn-sm" id="add-btn">添加学生</button>
                   <div class="switch" style="display: inline-block">
                       <input type="checkbox" name="my-checkbox" id="studentSwitch">
                   </div>
                   <button class="btn btn-default btn-sm" id="checkAll-btn">全部选择</button>
                   <button class="btn btn-default btn-sm" id="unCheckAll-btn">取消选择</button>
			   </div>
			   <div id="searchBar" class="form-group" style="margin-bottom:0;">
				   <div class="form-group col-lg-4 col-md-4" style="margin-bottom:0;">
					   <input type="text" class="form-control form-inline input-sm" placeholder="姓名、身份证、性别、电话、QQ、邮箱、地址" id="searchBarText">
				   </div>
				   <button id="searchBarBtn" class="btn btn-default btn-sm">搜索</button>
			   </div>
		   </div>
           <div id="dd" style="padding: 20px;display: none">
               <div class="form-horizontal" id="addForm">
                    <div class="form-group">
                        <label for="add-name" class="col-md-3 control-label">姓名:</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" id="add-name" name="addName">
                        </div>
                    </div>
                   <div class="form-group">
                        <label for="add-idcard" class="col-md-3 control-label">身份证:</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" id="add-idcard" name="addIdcard">
                        </div>
                   </div>
                   <div class="form-group">
                        <label class="col-md-3 control-label">性别:</label>
                        <div class="col-md-8">
                            <label class="radio-inline">
                                <input type="radio" id="add-radio1" name="addSex" value="男" checked> 男
                            </label>
                            <label class="radio-inline">
                                <input type="radio" id="add-radio2" name="addSex" value="女"> 女
                            </label>
                        </div>
                   </div>
                   <div class="form-group">
                       <label class="col-md-3 control-label"></label>
                       <div class="col-md-8">
                           <div class="alert alert-info" role="alert">
                               您可以选择只填写学生姓名，并打开允许学生修改个人信息的开关，让学生自行修改个人信息
                               添加的学生默认密码为123456, 需要修改后使用其他功能
                           </div>
                       </div>
                   </div>
                   <div class="form-group">
                       <div class="col-md-offset-5">
                           <button id="subAddBtn" class="btn btn-primary">提交</button>
                           <button type="reset" class="btn btn-default">重置</button>
                       </div>
                   </div>
               </div>
           </div>
           <div id="rr" style="padding: 20px;display: none">
               <div class="form-horizontal">
                    <div class="form-group">
                        <label for="add-name" class="col-md-3 control-label">姓名:</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" id="studentNameCombox" placeholder="请输入学生姓名">
                        </div>
                    </div>
                   <div class="form-group">
                        <label for="add-idcard" class="col-md-3 control-label">奖励信息:</label>
                        <div class="col-md-8">
                            <ul class="list-group" id="rewardMsg">
                                <li class="list-group-item">暂无奖励信息</li>
                            </ul>
                        </div>
                   </div>
                   <div class="form-group">
                       <div class="col-md-offset-5">
                           <button id="addRewardBtn" class="btn btn-primary">新增</button>
                           <button id="delRewardBtn" class="btn btn-default">注销</button>
                       </div>
                   </div>
                   <div class="form-group">
                        <label class="col-md-3 control-label">惩罚信息:</label>
                        <div class="col-md-8">
                            <ul class="list-group" id="punishMsg">
                                <li class="list-group-item">暂无惩罚信息</li>
                            </ul>
                        </div>
                   </div>
                   <div class="form-group">
                       <div class="col-md-offset-5">
                           <button id="addPunishBtn" class="btn btn-primary">新增</button>
                           <button id="delPunishBtn" class="btn btn-default">注销</button>
                       </div>
                   </div>
               </div>
           </div>
           <div id="updateBox" style="padding: 20px;display: none">
               <div class="form-horizontal" id="updateForm">
                    <div class="form-group">
                        <label for="update-name" class="col-md-3 control-label">姓名:</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" id="update-name" name="updateName">
                            <input type="hidden" class="form-control" id="update-hiddenID" name="id">
                        </div>
                    </div>
                   <div class="form-group">
                        <label for="update-idcard" class="col-md-3 control-label">身份证:</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" id="update-idcard" name="updateIdcard">
                        </div>
                   </div>
                   <div class="form-group">
                        <label class="col-md-3 control-label">性别:</label>
                        <div class="col-md-8">
                            <label class="radio-inline">
                                <input type="radio" id="update-radio1" name="updateSex" value="男"> 男
                            </label>
                            <label class="radio-inline">
                                <input type="radio" id="update-radio2" name="updateSex" value="女"> 女
                            </label>
                        </div>
                   </div>
                   <div class="form-group">
                       <label for="update-phone" class="col-md-3 control-label">电话:</label>
                       <div class="col-md-8">
                           <input type="text" class="form-control" id="update-phone" name="updatePhone">
                       </div>
                   </div>
                   <div class="form-group">
                       <label for="update-qq" class="col-md-3 control-label">QQ:</label>
                       <div class="col-md-8">
                           <input type="text" class="form-control" id="update-qq" name="updateQQ">
                       </div>
                   </div>
                   <div class="form-group">
                       <label for="update-email" class="col-md-3 control-label">邮箱:</label>
                       <div class="col-md-8">
                           <input type="text" class="form-control" id="update-email" name="updateEmail">
                       </div>
                   </div>
                   <div class="form-group">
                       <label for="update-address" class="col-md-3 control-label">地址:</label>
                       <div class="col-md-8">
                           <input type="text" class="form-control" id="update-address" name="updateAddress">
                       </div>
                   </div>
                   <div class="form-group">
                       <div class="col-md-offset-5">
                           <button id="subUpdateBtn" class="btn btn-primary">提交</button>
                           <button type="reset" class="btn btn-default">重置</button>
                       </div>
                   </div>
               </div>
           </div>

           <div id="updateDormBox" style="padding: 20px;display: none">
               <div class="form-horizontal" id="updateDormForm">
                   <div class="form-group">
                       <label for="add-name" class="col-md-3 control-label">园区:</label>
                       <div class="col-md-8">
                           <input type="text" class="form-control" id="dormZoneCombox" placeholder="请输入园区名">
                       </div>
                   </div>
                   <div class="form-group">
                       <label for="update-idcard" class="col-md-3 control-label">楼栋:</label>
                       <div class="col-md-8">
                           <input type="text" class="form-control" id="update-building" name="updateBuilding">
                       </div>
                   </div>
                   <div class="form-group">
                       <label for="update-phone" class="col-md-3 control-label">房间:</label>
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

           <div id="ww" style="padding: 20px;display: none">
               <div class="form-horizontal">
                   <div class="form-group">
                       <label for="rewardText" class="col-md-3 control-label">奖励内容：</label>
                       <div class="col-md-8">
                           <input type="text" class="form-control" id="rewardText" placeholder="请输入奖励内容">
                       </div>
                       <label for="rewardText" class="col-md-3 control-label">奖励时间：</label>
                       <div class="col-md-8">
                           <input type="text" class="form-control" id="rewardTime" placeholder="请选择奖励时间">
                       </div>
                   </div>
                   <div class="form-group" style="padding-top: 150px">
                       <div class="col-md-offset-8">
                           <button id="subReward" class="btn btn-success">提交</button>
                           <button id="cancelSubReward" class="btn btn-danger">提交</button>
                       </div>
                   </div>
               </div>
           </div>

           <div id="xx" style="padding: 20px;display: none">
               <div class="form-horizontal">
                   <div class="form-group">
                       <label for="rewardCombobox" class="col-md-3 control-label">奖励内容：</label>
                       <div class="col-md-8">
                           <input type="text" class="form-control" id="rewardCombobox" placeholder="请选择奖励">
                       </div>
                   </div>
                   <div class="form-group">
                       <div class="col-md-offset-6">
                           <button id="removeReward" class="btn btn-primary">注销</button>
                       </div>
                   </div>
               </div>
           </div>
           <div id="ee" style="padding: 20px;display: none">
               <div class="form-horizontal">
                   <div class="form-group">
                       <label for="punishText" class="col-md-3 control-label">惩罚内容：</label>
                       <div class="col-md-8">
                           <input type="text" class="form-control" id="punishText" placeholder="请输入惩罚内容">
                       </div>
                       <label for="punishText" class="col-md-3 control-label">惩罚时间：</label>
                       <div class="col-md-8">
                           <input type="text" class="form-control" id="punishTime" placeholder="请选择惩罚时间">
                       </div>
                   </div>
                   <div class="form-group">
                       <div class="col-md-offset-6" style="padding-top: 150px">
                           <button id="subPubish" class="btn btn-success">提交</button>
                           <button id="cancelSubPubish" class="btn btn-danger">提交</button>
                       </div>
                   </div>
               </div>
           </div>

           <div id="ff" style="padding: 20px;display: none">
               <div class="form-horizontal">
                   <div class="form-group">
                       <label for="punishCombobox" class="col-md-3 control-label">惩罚内容：</label>
                       <div class="col-md-8">
                           <input type="text" class="form-control" id="punishCombobox" placeholder="请选择惩罚">
                       </div>
                   </div>
                   <div class="form-group">
                       <div class="col-md-offset-6">
                           <button id="removePunish" class="btn btn-primary">注销</button>
                       </div>
                   </div>
               </div>
           </div>

           <div id="aa" style="display: none">
               <div id="qualityDatagrid"></div>
           </div>
           <div id="qualityToolBar" class="form-horizontal" style="width:100%; margin-bottom:0;vertical-align: middle;display: none">
               <div class="form-group col-lg-8 col-md-8"  style="margin-bottom:0;">
                   <button class="btn btn-default btn-sm" id="countQuality" disabled></button>
                   <button class="btn btn-default btn-sm" id="addQualityBtn">添加活动</button>
                   <button class="btn btn-default btn-sm" id="showAllQualityBtn">显示全部</button>
               </div>
               <div class="form-group" style="margin-bottom:0;">
                   <div class="form-group col-lg-4 col-md-4" style="margin-bottom:0;">
                       <input type="text" class="form-control form-inline input-sm" placeholder="活动名称" id="searchQualityText">
                   </div>
                   <button id="searchQualityBtn" class="btn btn-default btn-sm">搜索</button>
               </div>
           </div>
           <div id="gg" style="padding: 20px;display: none">
               <div class="form-horizontal">
                   <div class="form-group">
                       <label for="qualityText" class="col-md-3 control-label">活动名称:</label>
                       <div class="col-md-8">
                           <input type="text" class="form-control" id="qualityText" placeholder="请输入活动名称">
                           <input type="hidden" name="stuHiddenId" id="stuHiddenId">
                       </div>
                   </div>

                   <div class="form-group">
                       <label for="qualityScore" class="col-md-3 control-label">分数:</label>
                       <div class="col-md-4">
                           <input type="text" class="form-control" id="qualityScore">
                       </div>
                   </div>

                   <div class="form-group">
                       <label for="qualityTime" class="col-md-3 control-label">活动时间:</label>
                       <div class="col-md-8">
                           <input type="text" class="form-control" id="qualityTime">
                       </div>
                   </div>

                   <div class="form-group">
                       <div class="col-md-offset-6">
                           <button id="subAddQuality" class="btn btn-primary">新增</button>
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
    <script src="js/index.js"></script>
    <script src="js/dcalendar.picker.js"></script>

    <script type="text/javascript">
        $('#rewardTime').dcalendarpicker({
            format:'yyyy-mm-dd'
        });

        $('#punishTime').dcalendarpicker({
            format:'yyyy-mm-dd'
        });

        $('#qualityTime').dcalendarpicker({
            format: 'yyyy-mm-dd'
        });
    </script>

  </body>
</html>