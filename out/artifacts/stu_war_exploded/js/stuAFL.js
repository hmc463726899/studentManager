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

var initClick = function () {
    $("#AFLBtn").click(function(){
        $("#dd").dialog({
            title:"请假信息填写",
            width:400,
            height:'auto'
        });
    });

    $("#subAddAFLBtn").click(function(){

        var stuId = $("#userId").val();
        var reason = $("#afl-reason").val();
        var start_time = $("#afl-start-time").val();
        var end_time = $("#afl-end-time").val();

        if(reason==undefined)
        {
            alert("请填写理由");
            return;
        }

        $.ajax({
            url:"/stu/askforleave/addStuAFL.do",
            type:"POST",
            data:{
                stuId:stuId,
                reason:reason,
                start_time:start_time,
                end_time:end_time
            },
            success:function(){
                $.messager.confirm("提示","添加请假信息成功!",function(r){
                    if(r){
                        $("#dd").dialog('close');
                        $("#studentAFLContent").datagrid("load");
                    }else{
                        $("#dd").dialog('close');
                        $("#studentAFLContent").datagrid("load");
                    }
                });
            },
            error:function(){
                $.messager.alert({
                    title:"提示消息",
                    icon:"error",
                    msg:"添加请假信息失败！"
                });
            }
        });
    });
}

var initDataGrid = function(){
    var stuId = $("#userId").val();
    $("#studentAFLContent").datagrid({
        url:"/stu/askforleave/getStuAFL.do",
        width:'auto',   //表格宽度
        height:'500px',
        columns:[[
            {field:'id',title:'编号',width:'auto',hidden:'true'},
            {field:'reason',title:'请假原因',width:'400',align : 'center'},
            {field:'start_time',title:'开始时间',width:'100',align : 'center'},
            {field:'end_time',title:'结束时间',width:'100',align : 'center'},
            {field:'approve',title:'批准状态',width:'100',align : 'center',
                formatter:function(value, row, index) {
                    if (row.approve == undefined || row.approve == '0') {
                        var str = '<font style="color:red;">未批准</font>';
                        return str;
                    }else if(row.approve='1'){
                        var str ='<font style="color:green;">已批准</font>';
                        return str;
                    }
                    else{
                        var str ='<font style="color:red;">错误</font>';
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