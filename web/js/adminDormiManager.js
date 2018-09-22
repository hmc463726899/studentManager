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
        url:"/stu/dormitory/getAllStuDormitory.do",
        width:'auto',   //表格宽度
        height:'500px',
        columns:[[
            {field:'id',title:'编号',width:'auto',hidden:'true'},
            {field:'student_id',title:'学生编号',width:'auto',hidden:'true'},
            {field:'student_name',title:'学生名',width:'200',align : 'center'},
            {field:'zone_name',title:'所住片区',width:'200',align : 'center'},
            {field:'building',title:'楼栋',width:'150',align : 'center'},
            {field:'room',title:'房间',width:'150',align : 'center'},
            {field:'opt',title:'操作',width:'100',align:'center',
                formatter:function(value, row, index){
                    var str = '<a href="javascript:void(0)" onclick="dorm(\''+row.student_id+'\')" name="drom" class="easyui-linkbutton" ></a>';
                    return str; },
            }
        ]],
        onLoadSuccess:function(data){
            $("a[name='drom']").linkbutton({text:'住宿操作',plain:true,iconCls:'icon-ok'});
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

var initClick = function(){
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
                            $("#content").datagrid("load");
                            $("#updateDormBox").dialog("close");
                        }else{
                            $("#content").datagrid("load");
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
                            $("#content").datagrid("load")
                            $("#updateDormBox").dialog("close");
                        }else{
                            $("#content").datagrid("load")
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
                            $("#content").datagrid("load")
                            $("#updateDormBox").dialog("close");
                        }else{
                            $("#content").datagrid("load")
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
    
    $("#addZoneBtn").click(function () {

        //弹出添加园区的信息框
        $("#addZoneBox").dialog({
            title:"添加园区",
            width:400,
            height:200
        })
    })

    $("#subAddZoneBtn").click(function () {

        var zoneName=$("#addZoneName").val();

        if(zoneName.length<2)
        {
            $.messager.alert({
                title:"提示",
                icon:"error",
                msg:"园区名长度不能小于2！"
            });
            return;
        }

        $.ajax({
            url:"/stu/dormitory/addDormiZone.do",
            type:"POST",
            data:{
                zoneName:zoneName
            },
            success:function(data){
                if(data.code){
                    $.messager.confirm("提示",data.msg,function(r){
                        if(r){
                            $("#content").datagrid("load")
                            $("#addZoneBox").dialog("close");
                        }else{
                            $("#content").datagrid("load")
                            $("#addZoneBox").dialog("close");
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
    })

    $("#delZoneBtn").click(function () {
        //弹出删除园区的信息框
        $("#delZoneBox").dialog({
            title:"删除园区",
            width:400,
            height:200
        })


        $("#delZoneCombox").combobox({
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
            value:"请选择要删除的园区",//默认值
            width:220,
            onLoadSuccess:function(){

            },
            onBeforeLoad:function() {

            }
        });
    })



    $("#subDelZoneBtn").click(function(){
        var zone_id = $("#delZoneCombox").combobox("getValue");

        if(zone_id=="请选择园区名")  //如果没有选择园区
        {
            alert("必须选择园区!");
            return;
        }

        $.ajax({
            url:"/stu/dormitory/delDormiZone.do",
            type:"POST",
            data:{
                zoneId:zone_id
            },
            success:function(data){
                if(data.code){
                    $.messager.confirm("提示",data.msg,function(r){
                        if(r){
                            $("#content").datagrid("load");
                            $("#delZoneBox").dialog("close");
                        }else{
                            $("#content").datagrid("load");
                            $("#delZoneBox").dialog("close");
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
};