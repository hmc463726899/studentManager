$(document).ready(function(){
    //checkAdminLogin();
	initSwitch();
	initDataGrid();
	initClick();
});

/*var checkAdminLogin = function(){
    $.ajax({
        url:"/stu/stuManager/getAdminLoginStatus.do",
        type:"POST",
        data:{

        },
        success:function(result){
            if(result.code){
                //已经登陆
                //什么也不做
            }else{
                alert("请先登录!");
                //跳转
                window.location.href='/login.jsp';
            }
        }
    });
};*/

var selectedUser;

//初始化按钮开关
var initSwitch = function(){
    $("#studentSwitch").bootstrapSwitch({
        onText:"开放",
        offText:"关闭",
        onColor:"primary",
        offColor:"default",
        size:"small",
        onInit:function(){
            $.ajax({
                url:"/stu/stuManager/getSwitch.do",
                type:"POST",
                data:{
                    name:"studentSwitch"
                },
                success:function (result) {
                    if(result.data==1){
                        $("#studentSwitch").bootstrapSwitch('toggleState');
                    }
                }
            });
        },
        onSwitchChange:function(event,state){
            changeSwitchState(state);
        }
    });
};
//设置switch状态
var changeSwitchState = function(state){
    $.ajax({
        url:"/stu/stuManager/setSwitch.do",
        type:"POST",
        data:{
            name:"studentSwitch",
            state:state
        },
        success:function (result) {
            //DONOTING
        }
    });
};
//初始化datagrid
var initDataGrid = function(){
	$("#content").datagrid({
		url:"/stu/stuManager/getAllStu.do",
		width:'auto',   //表格宽度
		height:'500px',
		columns:[[
			{field:'id',title:'编号',width:'auto',hidden:'true'},
			{field:'name',title:'姓名',width:'50',align : 'center'},
			{field:'idcard',title:'身份证',width:'140',align : 'center'},
			{field:'sex',title:'性别',width:'50',align : 'center'},
			{field:'phone',title:'联系电话',width:'100',align : 'center'},
			{field:'qq',title:'QQ',width:'100',align : 'center'},
			{field:'email',title:'邮箱',width:'120',align : 'center'},
			{field:'address',title:'联系地址',width:'200',align : 'center'},
			{field:'opt',title:'操作',width:'200',align:'center',
				formatter:function(value, row, index){
                var str = '<a href="javascript:;" onclick="showUpdateBox(\''+row.id+'\')" name="update" class="easyui-linkbutton" ></a>';
                str += '<a href="javascript:;" onclick="reward(\''+row.id+'\')" name="reward" class="easyui-linkbutton" ></a>';
                str += '<a href="javascript:;" onclick="quality(\''+row.id+'\')" name="quality" class="easyui-linkbutton" ></a>';
                str += '<a href="javascript:;" onclick="del(\''+row.id+'\')" name="del" class="easyui-linkbutton" ></a>';
                str += '<a href="javascript:;" onclick="dorm(\''+row.id+'\')" name="dorm" class="easyui-linkbutton" ></a>';
                return str; }
			}
		]],
        onLoadSuccess:function(data){
            $("a[name='update']").linkbutton({text:'修改',plain:true,iconCls:'icon-edit'});
            $("a[name='reward']").linkbutton({text:'奖惩',plain:true,iconCls:'icon-man'});
            $("a[name='quality']").linkbutton({text:'素质',plain:true,iconCls:'icon-man'});
            $("a[name='del']").linkbutton({text:'删除',plain:true,iconCls:'icon-remove'});
            $("a[name='dorm']").linkbutton({text:'住宿',plain:true,iconCls:'icon-man'});
        },
        border:false,
		pagination:true,
		pageList:[18,25,30,40,50],
		pageSize:18,
		striped:true,
		nowrap:true,
		autoRowHeight:true,
		checkOnSelect:true,
		fitColumns:true,
		loadMsg:'怕是等一下哦',
		toolbar:"#toolBar",
        queryParams:{
            keywords:""
		}
	});
};
//绑定点击事件
var initClick = function(){
	//全选按钮
	$("#checkAll-btn").click(function(){
        $("#content").datagrid("selectAll");
	});
	//取消选择按钮
    $("#unCheckAll-btn").click(function(){
        $("#content").datagrid("unselectAll");
    });
	//搜索按钮
	$("#searchBarBtn").click(function(){
		var keywords = $("#searchBarText").val();
        $("#content").datagrid("load",{keywords:keywords});
	});
	//显示全部按钮
	$("#showAll-btn").click(function(){
        $("#content").datagrid("load",{keywords:""});
	});
	//添加按钮
	$("#add-btn").click(function(){
        $("#dd").dialog({
            title:"添加学生",
			width:400,
			height:'auto'
        });
	});

	//添加学生
	$("#subAddBtn").click(function(){
	    var name = $("#add-name").val();
	    var idcard = $("#add-idcard").val();
	    var sex = $("input[name='addSex']:checked").val();

	    var duplicateCheck=undefined;

        if(name=='' || name.length<2 || name.length>10)
        {
            $.messager.alert({
                title:"提示",
                icon:"error",
                msg:"请填写正确格式的名字！长度>=2 <=10"
            });
            return
        }

	    $.ajax({
            url:"/stu/stuManager/checkStuDuplicate.do",
            async:false,
            type:"POST",
            data:{
                stuName:name,
                stuIdCard:idcard,
            },

            success:function(data){
                if(data.code){
                    duplicateCheck=true;
                }else{
                    duplicateCheck=false;
                }
            },
            error:function(e){
                $.messager.alert({
                    title:"提示",
                    icon:"error",
                    msg:"请求重复检查失败！"
                });
                $("#dd").dialog("close");
                return;
            }
        })

        if(duplicateCheck==false)
        {
            $.messager.alert({
                title:"提示",
                icon:"error",
                msg:"该学生已经存在!"
            });

            $("#dd").dialog("close");
            return;
        }

        $.ajax({
            url:"/stu/stuManager/addStudent.do",
            type:"POST",
            data:{
                name:name,
                idcard:idcard,
                sex:sex
            },

            success:function(data){
            	if(data.code){
					$.messager.confirm("提示",data.msg,function(r){
						if(r){
                            $("#dd").dialog('close');
                            var name = $("#add-name").val("");
                            var idcard = $("#add-idcard").val("");
                            $("#add-radio1").attr("checked","checked");
                            $("#content").datagrid("load");
						}else{
                            $("#dd").dialog('close');
                            $("#content").datagrid("load");
						}
					});
                }else{
                    $.messager.confirm("提示",data.msg,function(r){
                        if(r){
                        	//DO NOTING
                        }
                    });
				}
            },
            error:function(e){
                $.messager.alert({
                    title:"提示",
                    icon:"error",
                    msg:"添加失败！"
                });
            }
        });
    });
	//多选删除
	$("#del-btn").click(function(){
        var selectedRow = $("#content").datagrid("getSelections");
        var ids = [];
        for(var i=0;i<selectedRow.length;i++){
            ids.push(selectedRow[i].id);
        }
        $.messager.confirm("提示","您确定要删除这条学生信息吗？",function(r){
			if(r){
                $.ajax({
                    url:"/stu/stuManager/delMoreStudent.do",
                    type:"POST",
                    data:{
                        ids:ids
                    },
                    cache:false,
                    dataType:"json",
                    traditional: true,
                    success:function(data){
                        if(data.code){
                            $.messager.confirm("提示",data.msg,function(r){
                                if(r){
                                    $("#content").datagrid("load");
                                }else{
                                    $("#content").datagrid("load");
                                }
                            });
                        }else{
                            $.messager.confirm("提示",data.msg,function(r){
                                if(r){
                                    //DO NOTING
                                }
                            });
                        }
                    },
                    error:function(){
                        $.messager.alert({
                            title:"提示",
                            icon:"error",
                            msg:"删除失败！"
                        });
                    }
                });
			}
		});
	});
    //修改学生信息
    $("#subUpdateBtn").click(function(){
        var id = $("#update-hiddenID").val();
        var name = $("#update-name").val();
        var idcard = $("#update-idcard").val();
        var sex = $("input[name='updateSex']:checked").val();
        var phone = $("#update-phone").val();
        var qq = $("#update-qq").val();
        var email = $("#update-email").val();
        var address = $("#update-address").val();

        if(name=='' || name.length<2 || name.length>10)
        {
            $.messager.alert({
                title:"提示",
                icon:"error",
                msg:"请填写正确格式的名字！"
            });
            return
        }

        if(phone.length!=11)
        {
            $.messager.alert({
                title:"提示",
                icon:"error",
                msg:"电话号码长度错误！"
            });
            return
        }

        var p = /[a-z A-Z]/i;

        if(p.test(phone)==true)
        {
            $.messager.alert({
                title:"提示",
                icon:"error",
                msg:"电话号码包含非法字符！"
            });
            return
        }

        $.ajax({
            url:"/stu/stuManager/updateStudent.do",
            type:"POST",
            data:{
                id:id,
                name:name,
                idcard:idcard,
                sex:sex,
                phone:phone,
                qq:qq,
                email:email,
                address:address
            },
            success:function(data){
                if(data.code){
                    $.messager.confirm("提示",data.msg,function(r){
                        if(r){
                            $("#updateBox").dialog("close");
                            $("#content").datagrid("load");
                        }else{
                            $("#updateBox").dialog("close");
                            $("#content").datagrid("load");
                        }
                    });
                }else{
                    $.messager.confirm("提示",data.msg,function(r){
                        if(r){
                            //DO NOTING
                        }
                    });
                }
            },
            error:function(e){
                $.messager.alert({
                    title:"提示",
                    icon:"error",
                    msg:"修改失败！"
                });
            }
        });

    });

    //修改住宿信息
    $("#subDormUpdateBtn").click(function(){
        var id = $("#update-hiddenID").val();
        var zone_id = $("#dormZoneCombox").combobox("getValue");
        var building = $("#update-building").val();
        var room = $("#update-room").val();

        if(zone_id=="请选择宿舍园区")  //如果没有选择园区
        {
            alert("必须选择园区!");
            return;
        }

        $.ajax({
            url:"/stu/dormitory/updateStuDormitoryInfo.do",
            type:"POST",
            data:{
                stuId:id,
                zone_id:zone_id,
                building:building,
                room:room
            },
            success:function(data){
                if(data.code){
                    $.messager.confirm("提示",data.msg,function(r){
                        if(r){
                            $("#updateDormBox").dialog("close");
                        }else{
                            $("#updateDormBox").dialog("close");
                        }
                    });
                }else{
                    $.messager.confirm("提示",data.msg,function(r){
                        if(r){
                            //DO NOTING
                        }
                    });
                }
            },
            error:function(e){
                $.messager.alert({
                    title:"提示",
                    icon:"error",
                    msg:"修改失败！"
                });
            }
        });

    });

    //删除住宿信息
    $("#subDormDelBtn").click(function(){

        var id = $("#update-hiddenID").val();
        $.ajax({
            url:"/stu/dormitory/delStuDormitoryInfo.do",
            type:"POST",
            data:{
                stuId:id,
            },
            success:function(data){
                if(data.code){
                    $.messager.confirm("提示",data.msg,function(r){
                        if(r){
                            $("#updateDormBox").dialog("close");
                        }else{
                            $("#updateDormBox").dialog("close");
                        }
                    });
                }else{
                    $.messager.confirm("提示",data.msg,function(r){
                        if(r){
                            //DO NOTING
                        }
                    });
                }
            },
            error:function(e){
                $.messager.alert({
                    title:"提示",
                    icon:"error",
                    msg:"删除失败！"
                });
            }
        });

    });

    //添加住宿信息
    $("#subDormAddBtn").click(function(){
        var id = $("#update-hiddenID").val();
        var zone_id = $("#dormZoneCombox").combobox("getValue");
        var building = $("#update-building").val();
        var room = $("#update-room").val();

        if(zone_id=="请选择宿舍园区")  //如果没有选择园区
        {
            alert("必须选择园区!");
            return;
        }

        $.ajax({
            url:"/stu/dormitory/addStuDormitoryInfo.do",
            type:"POST",
            data:{
                stuId:id,
                zone_id:zone_id,
                building:building,
                room:room
            },
            success:function(data){
                if(data.code){
                    $.messager.confirm("提示",data.msg,function(r){
                        if(r){
                            $("#updateDormBox").dialog("close");
                        }else{
                            $("#updateDormBox").dialog("close");
                        }
                    });
                }else{
                    $.messager.confirm("提示",data.msg,function(r){
                        if(r){
                            //DO NOTING
                        }
                    });
                }
            },
            error:function(e){
                $.messager.alert({
                    title:"提示",
                    icon:"error",
                    msg:"添加失败！"
                });
            }
        });

    });


    //增加奖励
    $("#addRewardBtn").click(function(){
        $("#ww").dialog({
            title:"添加奖励",
            width:400,
            height:500
        });
    });

    //提交增加奖励
    $("#subReward").click(function(){
        var id = $("#studentNameCombox").combobox("getValue");
        var content = $("#rewardText").val();
        var time = $("#rewardTime").val();
        $.ajax({
            url:"/stu/stuManager/addreward.do",
            type:"POST",
            data:{
                id:id,
                content:content,
                time:time
            },
            success:function(result){
                if(result.code){
                    $.messager.alert("提示信息",result.msg);
                    var content = $("#rewardText").val("");
                    $("#ww").dialog("close");
                    loadRewardMsg(id);
                }else{
                    $.messager.alert("提示信息",result.msg);
                }
            }
        });
    });
    //注销奖励
    $("#delRewardBtn").click(function(){
        var id = $("#studentNameCombox").combobox("getValue");
        $("#xx").dialog({
            title:"注销奖励",
            width:400,
            height:600
        });
        //奖励下拉列表
        $("#rewardCombobox").combobox({
           loader:function(param,success,error){
               $.ajax({
                   url:"/stu/stuManager/getRewardCombobox.do",
                   type:"POST",
                   data:{
                       id:id
                   },
                   success:success,
                   error:error
               })
           },
            valueField:"id",
            textField:"content",
            value:"请选择奖励",//默认值
            width:220
        });
    });
    //提交删除奖励
    $("#removeReward").click(function(){
        var id = $("#rewardCombobox").combobox("getValue");
        var stuid = $("#studentNameCombox").combobox("getValue");
        $.ajax({
            url:"/stu/stuManager/delReword.do",
            type:"POST",
            data:{
                id:id
            },
            success:function(result){
                if(result.code){
                    $.messager.alert("提示信息",result.msg);
                    $("#xx").dialog("close");
                    loadRewardMsg(stuid);
                }else{
                    $.messager.alert("提示信息",result.msg);
                }
            }
        })
    });
    //新增惩罚按钮
    $("#addPunishBtn").click(function(){
        $("#ee").dialog({
            title:"新增惩罚",
            width:400,
            height:500
        });
    });
    //提交惩罚按钮
    $("#subPubish").click(function(){
        var stuId = $("#studentNameCombox").combobox("getValue");
        var content = $("#punishText").val();
        var time=$("#punishTime").val();
        $.ajax({
            url:"/stu/stuManager/addPunish.do",
            type:"POST",
            data:{
                id:stuId,
                content:content,
                time:time
            },
            success:function(result){
                if(result.code){
                    $.messager.alert("提示消息",result.msg);
                    //清空输入框内容
                    $("#punishText").val("");
                    //关闭对话框
                    $("#ee").dialog('close');
                    //刷新页面奖惩信息
                    loadRewardMsg(stuId);
                }else{
                    $.messager.alert("提示消息",result.msg);
                }
            }
        })
    });
    //显示注销惩罚对话框
    $("#delPunishBtn").click(function(){
        var id = $("#studentNameCombox").combobox("getValue");
        $("#ff").dialog({
            title:'注销惩罚',
            width:400,
            height:200
        });
        //惩罚下拉菜单
        $("#punishCombobox").combobox({
            loader:function(param,success,error){
                $.ajax({
                    url:"/stu/stuManager/getPunishCombobox.do",
                    type:"POST",
                    data:{
                        id:id
                    },
                    success:success,
                    error:error
                })
            },
            valueField:"id",
            textField:"content",
            value:"请选择惩罚",//默认值
            width:220
        });
    });
    //提交删除惩罚
    $("#removePunish").click(function(){
        var id = $("#punishCombobox").combobox("getValue");
        var stuid = $("#studentNameCombox").combobox("getValue");
        $.ajax({
            url:"/stu/stuManager/delPunish.do",
            type:"POST",
            data:{
                id:id
            },
            success:function(result){
                if(result.code){
                    $.messager.alert("提示信息",result.msg);
                    $("#ff").dialog("close");
                    loadRewardMsg(stuid);
                }else{
                    $.messager.alert("提示信息",result.msg);
                }
            }
        })
    });
    //新增素质拓展活动按钮
    $("#addQualityBtn").click(function(){
        $("#gg").dialog({
            title:"新增素质拓展活动",
            width:400,
            height:400
        });
    });
    //提交新增素质拓展按钮
    $("#subAddQuality").click(function(){
        var name = $("#qualityText").val();
        var score = $("#qualityScore").val();
        var time = $("#qualityTime").val();
        var id = $("#stuHiddenId").val();
        $.ajax({
            url:"/stu/stuManager/addQulity.do",
            type:"POST",
            data:{
                id:id,
                name:name,
                score:score,
                time:time
            },
            success:function(result){
                if(result.code){
                    $.messager.alert("提示信息",result.msg);
                    $("#gg").dialog("close");
                    $("#qualityDatagrid").datagrid("reload");
                    //清空表单数据
                    $("#qualityText").val("");
                    $("#qualityScore").val("");
                    $("#stuHiddenId").val("");
                }
            }
        });
    });
    //素质拓展datagrid搜索按钮
    $("#searchQualityBtn").click(function(){
        var stuId = $("#stuHiddenId").val();
        var keywords = $("#searchQualityText").val();
        $("#qualityDatagrid").datagrid("load",{id:stuId,keywords:keywords})
    });
    //显示全部按钮
    $("#showAllQualityBtn").click(function(){
        var stuId = $("#stuHiddenId").val();
        $("#qualityDatagrid").datagrid("reload",{id:stuId,keywords:""});
    });
};
//显示修改学生信息对话框
var showUpdateBox = function(id){
	$.ajax({
		url:"/stu/stuManager/getStudent.do",
        type:"POST",
        data:{
            id:id
        },
		success:function(data){
			if(data.code){
				$("#update-hiddenID").val(data.data.id);
                $("#update-name").val(data.data.name);
                $("#update-idcard").val(data.data.idcard);
				$(":radio[name='updateSex'][value='" + data.data.sex + "']").prop("checked", "checked");
                $("#update-phone").val(data.data.phone);
                $("#update-qq").val(data.data.qq);
                $("#update-email").val(data.data.email);
                $("#update-address").val(data.data.address);
                $("#updateBox").dialog({
                    title:"修改信息",
                    width:400,
                    height:460
                });
			}else{
                $.messager.alert("提示","信息加载失败");
			}
		},
		error:function(){
			$.messager.alert("提示","信息加载失败");
		}
	});

};

//删除学生信息
var del = function(id){
	$.messager.confirm("确认信息","您确定要删除这条学生信息吗？",function(r){
		if(r){
			$.ajax({
				url:"/stu/stuManager/delStudent.do",
				type:"POST",
				data:{
					id:id
				},
				success:function(data){
					if(data.code){
                        $.messager.confirm("提示",data.msg,function(r){
                            if(r){
                                $("#content").datagrid("load");
                            }else{
                                $("#content").datagrid("load");
                            }
                        });
					}else{
                        $.messager.confirm("提示",data.msg,function(r){
                            if(r){
                                //DO NOTING
                            }
                        });
					}
				},
				error:function(){
                    $.messager.alert({
                        title:"提示",
                        icon:"error",
                        msg:"删除失败！"
                    });
				}
			});
		}
	});
};

//奖惩信息弹出框
var reward = function(studentId){
    //奖惩信息弹出框
    $("#rr").dialog({
        title:"奖惩信息",
        width:600,
        height:400
    })
    //学生姓名下拉列表
    $("#studentNameCombox").combobox({
        url:"/stu/stuManager/getStudentCombox.do",
        valueField:"id",
        textField:"name",
        value:studentId,//默认值
        width:220,
        onLoadSuccess:function(){
            loadRewardMsg(studentId);
        },
        onChange:function(){
            var id = $("#studentNameCombox").combobox("getValue");
            loadRewardMsg(id);
        }
    });
};

//住宿信息弹出框
var dorm = function(studentId){
    //住宿信息弹出框
    $("#update-hiddenID").val(studentId);

    $("#updateDormBox").dialog({
        title:"住宿信息修改",
        width:400,
        height:400
    })
    var selectedStuZoneId=loadStuDormMsg(studentId);
    $("#dormZoneCombox").combobox({
        loader:function(param,success,error){
            $.ajax({
                url:"/stu/dormitory/getdormZoneCombox.do",
                type:"POST",
                data:{
                },
                success:success,
                error:error
            })
        },
        valueField:"id",
        textField:"zone_name",
        value:selectedStuZoneId,//默认值
        width:220,
        onLoadSuccess:function(){

        },
        onBeforeLoad:function() {

        }
    });
};

//综合素质弹出框
var quality = function(studentId){
    $("#aa").dialog({
        title:"综合素质",
        width:600,
        height:400
    });
    $("#qualityDatagrid").datagrid({
        url:"/stu/stuManager/getAllQuality.do",
        width:'auto',   //表格宽度
        height:'365px',
        columns:[[
            {field:'id',title:'编号',width:'auto',hidden:'true'},
            {field:'qualityName',title:'活动名称',width:'200',align : 'center'},
            {field:'score',title:'活动分数',width:'60',align : 'center'},
            {field:'qualityTime',title:'活动时间',width:'60',align : 'center',
                formatter:function(value, row, index) {
                    if (row.qualityTime == undefined || row.time == "") {
                        var str = '暂无时间记录';
                        return str;
                    }else{
                        var str = row.qualityTime;
                        return str;
                    }
                }
            },
            {field:'opt',title:'操作',width:'200',align:'center',
                formatter:function(value, row, index){
                    var str = '<a href="javascript:;" onclick="delQuality(\''+row.id+'\')" name="delQuality" class="easyui-linkbutton" ></a>';
                    return str; }
            }
        ]],
        onLoadSuccess:function(data){
            $("a[name='delQuality']").linkbutton({text:'删除',plain:true,iconCls:'icon-remove'});
            getCountQuality(studentId);
            $("#stuHiddenId").val(studentId);
        },
        border:false,
        pagination:true,
        pageList:[8,16,30],
        pageSize:8,
        striped:true,
        nowrap:true,
        autoRowHeight:true,
        checkOnSelect:true,
        fitColumns:true,
        loadMsg:'怕是等一下哦',
        toolbar:"#qualityToolBar",
        queryParams:{
            id:studentId,
            keywords:""
        }
    });
};
//通过学生id查找学生奖惩信息
var loadRewardMsg = function (studentId) {
    $.ajax({
        url:"/stu/stuManager/getStudentReward.do",
        type:"POST",
        data:{id:studentId},
        success:function(result){
            if(result.reward.length>0){
                var html = "" ;
                for(var i=0;i<result.reward.length;i++){
                    if(result.reward[i].time!=undefined) {
                        html += '<li class="list-group-item">' + result.reward[i].content + ' @ ' + result.reward[i].time + ' </li>';
                    }
                    else
                    {
                        html += '<li class="list-group-item">' + result.reward[i].content + ' @ ' +'时间未知' + ' </li>';
                    }
                }
                $("#rewardMsg").html(html);
            }else{
                var html = '<li class="list-group-item">暂无奖励信息</li>';
                $("#rewardMsg").html(html);
            }

            if(result.punish.length>0){
                var html = "" ;
                for(var i=0;i<result.punish.length;i++){
                    html+='<li class="list-group-item">'+result.punish[i].content+' @ '+result.punish[i].time+'</li>';
                }
                $("#punishMsg").html(html);
            }else{
                var html = '<li class="list-group-item">暂无惩罚信息</li>';
                $("#punishMsg").html(html);
            }
        }
    });
};

//通过id查找学生的住宿信息
var loadStuDormMsg = function (studentId) {

    $('#subDormAddBtn').attr('disabled',"true");
    $('#subDormUpdateBtn').attr('disabled',"true");
    $('#subDormDelBtn').attr('disabled',"true");
    var zone_id=undefined;
    $.ajax({
        async: false,
        url:"/stu/dormitory/getStuDormitoryInfo.do",
        type:"POST",
        data:{
            stuId:studentId},
        success:function(result){
            if(result.data!=undefined) {
                $("#update-building").val(result.data.building);
                $("#update-room").val(result.data.room);
                $('#subDormUpdateBtn').removeAttr("disabled");
                $('#subDormDelBtn').removeAttr("disabled");
                zone_id=result.data.zone_id;
            }
            else
            {
                $("#update-building").val("没有入住");
                $("#update-room").val("没有入住");
                $('#subDormAddBtn').removeAttr("disabled");
                zone_id=undefined;
            }
        }
    });
    return zone_id;
};

//通过学生ID计算素质拓展分总分
var getCountQuality = function(id){
    $.ajax({
        url:"/stu/stuManager/getCountQuality.do",
        type:"POST",
        data:{
            id:id,
        },
        success:function(result){
            $("#countQuality").html("总分:"+result.count);
        }
    })
};
//删除素质拓展活动
var delQuality = function(id){
    $.messager.confirm("提示信息","您确定要删除这个活动吗？",function (r) {
        if(r){
            $.ajax({
                url:"/stu/stuManager/delQuality.do",
                type:"POST",
                data:{
                    id:id
                },
                success:function(result){
                    if(result.code){
                        $.messager.alert("提示信息",result.msg);
                        $("#qualityDatagrid").datagrid("reload");
                    }
                }
            })
        }
    });
};