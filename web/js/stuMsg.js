/**
 * Created by chenkexuan on 2017/4/27.
 */

/**
 * edited by dengxionghui on 2018/6/21
 */
$(document).ready(function(){
    initMsg();
    initClick();
});
var initMsg = function(){
    var stuId = $("#userId").val();
    $.ajax({
        url:"/pim/student/getStuMsg.do",
        type:"POST",
        data:{
            stuId:stuId
        },
        success:function(result){
            $("#idcard").val(result.data.idcard);
            $("input[name='sex']").each(function(){
                if($(this).val()==result.data.sex){
                    $(this).attr("checked","checked");
                }
            });
            $("#phone").val(result.data.phone);
            $("#qq").val(result.data.qq);
            $("#email").val(result.data.email);
            $("#address").val(result.data.address);
            if(result.data.reset==0){
                $("#resetPassword").css("display","block");
            }else{
                $("#resetPassword").css("display","none");
            }
        }
    });
};

var initClick = function(){
    $("#subUpdateBtn").click(function(){
        var stuId = $("#userId").val();
        var idcard = $("#idcard").val();
        var sex = $("input[name='sex']:checked").val();
        var phone = $("#phone").val();
        var qq =  $("#qq").val();
        var email = $("#email").val();
        var address = $("#address").val();
        $.ajax({
            url:"/pim/student/updateMsg.do",
            type:"POST",
            data:{
                stuId:stuId,
                idcard:idcard,
                sex:sex,
                phone:phone,
                qq:qq,
                email:email,
                address:address
            },
            success:function(result){
                $.messager.confirm("提示信息",result.msg,function(r){
                    if(r){
                       window.location.reload();
                    }else{
                        window.location.reload();
                    }
                });
            }
        });
    });
    $("#resetPasswordBtn").click(function(){
        var stuId = $("#userId").val();
        var username = $("#username").val();
        var password = $("#password").val();
        var repassword = $("#repassword").val();
        $.ajax({
            url:"/pim/student/resetAccount.do",
            type:"POST",
            data:{
                stuId:stuId,
                username:username,
                password:password,
                repassword:repassword,
            },
            success:function(result){
                if(result.code){
                    $.messager.confirm("提示消息",result.msg,function(r){
                        if(r){
                            window.location.reload();
                        }else{
                            window.location.reload();
                        }
                    });
                }else{
                    $.messager.alert("提示消息",result.msg);
                }
            }
        });
    });
};
