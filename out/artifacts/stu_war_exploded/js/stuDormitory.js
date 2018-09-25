/**
 * Created by chenkexuan on 2017/4/27.
 */

/**
 * edited by dengxionghui on 2018/6/21
 */
$(document).ready(function(){
    initMsg();
});
var initMsg = function(){
    var stuId = $("#userId").val();
    $.ajax({
        url:"/pim/dormitory/getStuDormitoryInfo.do",
        type:"POST",
        data:{
            stuId:stuId
        },
        success:function(result){
            if(result.data!=undefined) {
                $("#zone").val(result.data.zone_name);
                $("#building").val(result.data.building);
                $("#room").val(result.data.room);
            }
            else
            {
                alert("该学生目前没有住宿信息！");
                $("#zone").val("没有住宿信息!");
                $("#building").val("没有住宿信息!");
                $("#room").val("没有住宿信息!");
            }
        }
    });
};

