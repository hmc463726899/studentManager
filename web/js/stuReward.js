/**
 * Created by chenkexuan on 2017/4/27.
 */

/**
 * edited by dengxionghui on 2018/6/21
 */

$(document).ready(function(){
    initDataGrid();
});
var initDataGrid = function(){
    var stuId = $("#userId").val();
    $("#studentRewardGrid").datagrid({
        url:"/pim/rewardpunish/getReward.do",
        width:'auto',   //表格宽度
        height:'500px',
        columns:[[
            {field:'id',title:'编号',width:'auto',hidden:'true'},
            {field:'content',title:'奖励原因',width:'400',align : 'center'},
            {field:'time',title:'时间',width:'200',align : 'center',
                formatter:function(value, row, index) {
                    if (row.time == undefined || row.time == "") {
                        var str = '暂无时间记录';
                        return str;
                    }else{
                        var str = row.time;
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