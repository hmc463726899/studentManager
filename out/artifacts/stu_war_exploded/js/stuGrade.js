/**
 * Created by chenkexuan on 2017/4/27.
 */

/**
 * edited by dengxionghui on 2018/6/21
 */
$(document).ready(function(){
    initDataGrid();
    initJD();
});
var initDataGrid = function(){
    var stuId = $("#userId").val();
    $("#studentGradeBox").datagrid({
        url:"/stu/grade/getGrade.do",
        width:'auto',   //表格宽度
        height:'500px',
        columns:[[
            {field:'id',title:'编号',width:'auto',hidden:'true'},
            {field:'name',title:'课程名称',width:'200',align : 'center'},
            {field:'courseType',title:'课程类型',width:'200',align : 'center'},
            {field:'courseScore',title:'学分',width:'100',align : 'center'},
            {field:'score',title:'得分',width:'100',align : 'center',
                formatter:function(value, row, index) {
                    if (row.score == undefined || row.score == "") {
                        var str = '暂无';
                        return str;
                    }else{
                        var str = row.score;
                        return str;
                    }
                }
            }
        ]],
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
            stuId:stuId
        }
    });
};
var initJD = function(){
    var stuId = $("#userId").val();
    $.ajax({
        url:"/stu/grade/getJD.do",
        type:"POST",
        data:{
            stuId:stuId
        },
        success:function(result){
            if(result.code){
                if(result.score!=undefined) {
                    $("#MsgBtn").html("绩点：" + result.score);
                }
                else
                {
                    $("#MsgBtn").html("绩点：还没有成绩!");
                }
            }else{
                $("#MsgBtn").html("绩点："+result.msg);
            }
        }
    });
};