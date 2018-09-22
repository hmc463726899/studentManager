/**
 * Created by chenkexuan on 2017/4/27.
 */

/**
 * edited by dengxionghui on 2018/6/21
 */

$(document).ready(function(){
    initDataGrid();
    initClick();
});
//初始化datagrid
var initDataGrid = function(){
    $("#content").datagrid({
        url:"/stu/courseManager/getAllcourse.do",
        width:'auto',   //表格宽度
        height:'500px',
        columns:[[
            {field:'id',title:'编号',width:'auto',hidden:'true'},
            {field:'name',title:'课程名',width:'200',align : 'center'},
            {field:'typeName',title:'课程类型',width:'200',align : 'center'},
            {field:'score',title:'学分',width:'150',align : 'center'},
            {field:'count',title:'选课人数',width:'150',align : 'center'},
            {field:'opt',title:'操作',width:'100',align:'center',
                formatter:function(value, row, index){
                    var str = '<a href="javascript:void(0)" onclick="addStudent(\''+row.id+'\')" name="addStudent" class="easyui-linkbutton" ></a>';
                    str += '<a href="javascript:void(0)" onclick="showStudent(\''+row.id+'\',\''+row.name+'\')" name="show" class="easyui-linkbutton" ></a>';
                    str += '<a href="javascript:void(0)" onclick="inputGrade(\''+row.id+'\')" name="inputGrade" class="easyui-linkbutton" ></a>';
                    str += '<a href="javascript:void(0)" onclick="delCourse(\''+row.id+'\')" name="del" class="easyui-linkbutton" ></a>';
                    return str; }
            }
        ]],
        onLoadSuccess:function(data){
            $("a[name='addStudent']").linkbutton({text:'选课',plain:true,iconCls:'icon-man'});
            $("a[name='show']").linkbutton({text:'详细',plain:true,iconCls:'icon-tip'});
            $("a[name='inputGrade']").linkbutton({text:'成绩',plain:true,iconCls:'icon-save'});
            $("a[name='del']").linkbutton({text:'删除',plain:true,iconCls:'icon-cut'});
        },
        border:false,
        pagination:true,
        pageList:[18,25,30,40,50],
        pageSize:18,
        striped:true,
        nowrap:true,
        autoRowHeight:true,
        fitColumns:true,
        loadMsg:'怕是等一下哦',
        toolbar:"#toolBar",
        queryParams:{
            keywords:""
        }
    });
};
var initClick = function(){
    //选课开关
    $("#courseSwitch").bootstrapSwitch({
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
                    name:"courseSwitch"
                },
                success:function (result) {
                    if(result.data==1){
                        $("#courseSwitch").bootstrapSwitch('toggleState');
                    }
                }
            });
        },
        onSwitchChange:function(event,state){
            changeSwitchState(state);
        }
    });
    //设置switch状态
    var changeSwitchState = function(state){
        $.ajax({
            url:"/stu/stuManager/setSwitch.do",
            type:"POST",
            data:{
                name:"courseSwitch",
                state:state
            },
            success:function (result) {
                //DONOTING
            }
        });
    };
    //显示全部点击事件
    $("#showAll-btn").click(function(){
        $("#content").datagrid("load",{keyword:""});
    });
    //添加课程按钮点击事件
    $("#add-btn").click(function(){
        //弹出对话框
        $("#dd").dialog({
            title:"添加课程",
            width:400,
            height:300
        });
        //课程分类下拉列表
        $("#courseType").combobox({
            url:"/stu/courseManager/getCombox.do",
            valueField:"id",
            textField:"name",
            value:'1',//默认值
            width:220
        });
    });
    //添加课程提交按钮
    $("#subAddBtn").click(function(){
        var name = $("#courseName").val();
        var type = $("#courseType").val();
        var score = $("#courseScore").val();
        $.ajax({
            url:"/stu/courseManager/addCourse.do",
            type:"POST",
            data:{
                name:name,
                type:type,
                score:score
            },
            success:function(){
                $.messager.confirm("提示","添加成功!",function(r){
                    if(r){
                        $("#dd").dialog('close');
                        $("#content").datagrid("load");
                    }else{
                        $("#dd").dialog('close');
                        $("#content").datagrid("load");
                    }
                });
            },
            error:function(){
                $.messager.alert({
                    title:"提示消息",
                    icon:"error",
                    msg:"添加失败！"
                });
            }
        });
    });
    //搜索按钮点击事件
    $("#searchBarBtn").click(function(){
        var keywords = $("#searchBarText").val();
        $("#content").datagrid("load",{keywords:keywords});
    });
    //添加类型点击事件
    $("#addTypebtn").click(function(){
        $("#ss").dialog({
            title:"添加课程类型",
            width:400,
            height:200
        });
    });
    //新增类型提交按钮
    $("#subAddTyptBtn").click(function () {
        var typeName = $("#courseTypeName").val();
        $.ajax({
            url:"/stu/courseManager/addCourseType.do",
            type:"POST",
            data:{
                typeName:typeName
            },
            success:function(){
                $.messager.confirm("提示","添加成功!",function(r){
                    if(r){
                        $("#ss").dialog('close');
                        $("#content").datagrid("load");
                    }else{
                        $("#ss").dialog('close');
                        $("#content").datagrid("load");
                    }
                });
            },
            error:function(){
                $.messager.alert({
                    title:"提示消息",
                    icon:"error",
                    msg:"添加失败！"
                });
            }
        });
    });
    //学生选课提交按钮
    $("#subAddStuToCourse").click(function(){
        var courseId = $("#courseIdHidden").val();
        var stuId = $("#studentNameCombobox").combobox("getValue");
        $.ajax({
            url:"/stu/courseManager/addStuToCourse.do",
            type:"POST",
            data:{
                courseId:courseId,
                stuId:stuId
            },
            success:function(){
                $.messager.alert("提示","选课成功!");
                $("#hh").dialog("close");
                $("#content").datagrid("reload");
            }
        });
    });
    //学生成绩弹窗显示全部
    $("#stuGradeshowAllBtn").click(function(){
        var courseId = $("#courseId").val();
        $("#studentGradeBox").datagrid("reload",{keywords:"",courseId:courseId});
    });
    //学生成绩弹窗搜索按钮
    $("#stuGradesearchBarBtn").click(function(){
        var courseId = $("#courseId").val();
        var keywords = $("#stuGradesearchBarText").val();
        $("#studentGradeBox").datagrid("load",{keywords:keywords,courseId:courseId});
    });

    //学生成绩弹窗添加按钮
    $("#subGrade").click(function(){
        var courseId = $("#courseId").val();
        var stuId = $("#gradeStuIdHidden").val();
        var score = $("#grade").val();

        if(score<0 ||score >100)
        {
            $.messager.alert({
                title:"提示消息",
                icon:"error",
                msg:"输入的分数不合法！"
            });
            return;
        }

        $.ajax({
            url:"/stu/courseManager/addStuGrade.do",
            type:"POST",
            data:{
                courseId:courseId,
                stuId:stuId,
                score:score
            },
            success:function(result){
                if(result.code){
                    $.messager.alert("提示消息",result.msg);
                    $("#jj").dialog("close");
                    $("#studentGradeBox").datagrid("reload");
                }else{
                    $.messager.alert("提示消息",result.msg);
                }
            },
            error:function(){
                $.messager.alert("系统出错了");
            }
        });
    });
};
//显示选课学生
var showStudent = function(id,courseName){
    //显示对话框
    $("#aa").dialog({
        title:"查看选课学生------"+courseName,
        width:800,
        height:530
    });
    //学生datagrid
    $("#studentBox").datagrid({
        url:"/stu/courseManager/getStuByCourse.do",
        width:'auto',   //表格宽度
        height:'500',
        columns:[[
            {field:'id',title:'编号',width:'auto',hidden:'true'},
            {field:'name',title:'姓名',width:'100',align : 'center'},
            {field:'idcard',title:'身份证',width:'200',align : 'center'},
            {field:'sex',title:'性别',width:'50',align : 'center'},
            {field:'phone',title:'联系电话',width:'100',align : 'center'},
            {field:'qq',title:'QQ',width:'100',align : 'center'},
            {field:'email',title:'邮箱',width:'150',align : 'center'},
            {field:'address',title:'联系地址',width:'200',align : 'center'}
            ]],
        border:false,
        pagination:true,
        pageList:[10,20,30,40,50],
        pageSize:10,
        striped:true,
        nowrap:false,
        autoRowHeight:true,
        fitColumns:true,
        loadMsg:'怕是等一下哦',
        queryParams:{
            id:id
        }
    });
};
//删除课程
var delCourse = function(id){
    $.messager.confirm("提示信息","您确定要删除这个课程吗？",function(r){
        if(r){
            $.ajax({
                url:"/stu/courseManager/delCourse.do",
                type:"POST",
                data:{
                    id:id
                },
                success:function(){
                    $.messager.confirm("提示","删除成功!",function(r){
                        if(r){
                            $("#content").datagrid("load");
                        }else{
                            $("#content").datagrid("load");
                        }
                    });
                },
                error:function(){
                    $.messager.alert({
                        title:"提示消息",
                        icon:"error",
                        msg:"删除失败！"
                    });
                }
            });
        }
    });
};
//学生选课
var addStudent = function(courseId){
    //课程id赋值给隐藏input
    $("#courseIdHidden").val(courseId);
    //添加学生显示对话框
    $("#hh").dialog({
        title:"学生选课",
        width:400,
        height:180
    });
    //学生下拉列表
    $("#studentNameCombobox").combobox({
        loader:function(param,success,error){
            $.ajax({
                url:"/stu/courseManager/getStudentCombox.do",
                type:"POST",
                data:{
                    courseId:courseId
                },
                success:success,
                error:error
            });
        },
        valueField:"id",
        textField:"name",
        value:"请选择学生",//默认值
        width:220
    });
};
//课程所有学生成绩录入
var inputGrade = function(courseId){
    $("#courseId").val(courseId);
    $("#zz").dialog({
        title:"成绩录入",
        width:400,
        height:400
    });
    $("#studentGradeBox").datagrid({
        url:"/stu/courseManager/getStuGradeGrid.do",
        width:'auto',   //表格宽度
        height:'400px',
        columns:[[
            {field:'id',title:'编号',width:'auto',hidden:'true'},
            {field:'stuId',title:'学生',width:'auto',hidden:'true'},
            {field:'name',title:'姓名',width:'200',align : 'center'},
            {field:'score',title:'分数',width:'200',align : 'center',
                formatter:function(value, row, index){
                    var str='';
                    if(value==undefined||value==""){
                        str += '<a href="javascript:void(0)" onclick="inputStuGrade(\''+row.stuId+'\',\''+row.name+'\',\''+courseId+'\')" name="inputStuGrade" class="easyui-linkbutton" ></a>';
                    }else{
                        str=value;
                    }
                    return str;
                }
            }
        ]],
        onLoadSuccess:function(data){
            $("a[name='inputStuGrade']").linkbutton({text:'录入',plain:true,iconCls:'icon-add'});
        },
        border:false,
        pagination:true,
        pageList:[8,16,30],
        pageSize:8,
        striped:true,
        nowrap:true,
        autoRowHeight:true,
        fitColumns:true,
        loadMsg:'怕是等一下哦',
        toolbar:"#stuGradetoolBar",
        queryParams:{
            keywords:"",
            courseId:courseId
        }
    });
};
//录入学生成绩
var inputStuGrade = function(stuId,stuName,courseId){
    $("#jj").dialog({
        title:"录入成绩-----"+stuName,
        width:400,
        height:200
    });
    $("#gradeStuIdHidden").val(stuId);
};