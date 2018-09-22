/**
 * Created by chenkexuan on 2017/4/27.
 */

/**
 * edited by dengxionghui on 2018/6/21
 */
$(document).ready(function(){
    initDataGrid();
    initMsg();
    initClick();
});
//初始化datagrid
var initDataGrid = function(){
    var stuId = $("#userId").val();
    $("#studentCourseBox").datagrid({
        url:"/stu/stuCourse/getAllCourse.do",
        width:'auto',   //表格宽度
        height:'500px',
        columns:[[
            {field:'id',title:'编号',width:'auto',hidden:'true'},
            {field:'flag',title:'标记',width:'auto',hidden:'true'},
            {field:'name',title:'课程名称',width:'200',align : 'center'},
            {field:'courseType',title:'课程类型',width:'200',align : 'center'},
            {field:'score',title:'学分',width:'100',align : 'center'},
            {field:'opt',title:'操作',width:'200',align:'center',
                formatter:function(value, row, index) {
                    if (row.flag == undefined || row.flag == "") {
                        var str = '<a href="javascript:;" onclick="getCourse(\'' + row.id + '\')" name="getCourse" class="easyui-linkbutton" ></a>';
                        return str;
                    }else{
                        var str = '已选';
                        return str;
                    }
                }
            }
        ]],
        onLoadSuccess:function(data){
            $("a[name='getCourse']").linkbutton({text:'选课',plain:true,iconCls:'icon-edit'});
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
            keywords:"",
            stuId:stuId
        }
    });
};
//初始化信息
var initMsg = function(){
    $.ajax({
        url:"/stu/stuCourse/getMsg.do",
        type:"POST",
        success:function(result){
            $("#MsgBtn").html(result.msg);
        }
    });
};
//初始化点击事件
var initClick = function(){
    var stuId = $("#userId").val();
    $("#removeKeywords").click(function(){
        $("#studentCourseBox").datagrid("load",{keywords:"",stuId:stuId});
    });
    $("#searchBarBtn").click(function(){
        var keywords = $("#searchBarText").val();
        $("#studentCourseBox").datagrid("load",{keywords:keywords,stuId:stuId});
    });
};
//学生选课
var getCourse =function(courseId){
    var stuId = $("#userId").val();
    $.ajax({
        url:"/stu/stuCourse/getCourse.do",
        type:"POST",
        data:{
            stuId:stuId,
            courseId:courseId
        },
        success:function(result){
            $.messager.alert("提示消息",result.msg);
            $("#studentCourseBox").datagrid("load",{keywords:"",stuId:stuId});
        },
        error:function(){
            $.messager.alert("提示消息","服务器错误!");
        }
    });
};