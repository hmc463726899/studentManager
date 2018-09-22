/**
 * Created by chenkexuan on 2017/4/27.
 */

/**
 * edited and modified by dengxionghui on 2018/6/21
 */

$(document).ready(function(){
    initClick();
});


var initClick = function(){

        $("#update").click(function(){
        var adminName = $("#adminName").val();
        var adminUserName = $("#adminUserName").val();
        var adminId= $("#adminId").val();
        var adminOldPassword = $("#currentPassword").val();
        var adminNewPassword = $("#newPassword").val();
        var adminConfirmPassword = $("#confirmPassword").val();
        var role = 'manager';

        if(newPassword=='' || currentPassword=='' || confirmPassword=='')
        {
            $.messager.alert({
                title:"提示",
                icon:"error",
                msg:"请填写各个密码!"
                });
            return
        }



        if(checkOldPassword(adminUserName,adminOldPassword,role)==false)
        {
            $.messager.alert({
                title:"提示",
                icon:"error",
                msg:"旧密码错误！"
            });
            return
        }

        $.ajax({
            async:false,
            url:"/stu/user/updateAdminPassword.do",
            type:"POST",
            data:{
                adminId:adminId,
                newPassword:adminNewPassword,
                role:role
            },
            success:function(result){
                if(result.code){
                    $.messager.confirm("提示",result.msg,function(r){
                        if(r){
                            window.location.href='/login.jsp';
                        }else{
                            window.location.href='/login.jsp';
                        }
                    });
                }else{
                    $.messager.confirm("提示",result.msg,function(r){
                        if(r){
                            window.location.href='/login.jsp';
                        }
                    });
                }
            }
        });
    });
};

var checkOldPassword=function (adminName,adminPassword,role) {

    var check=undefined;

    $.ajax({
        async:false,    //登陆时关闭异步
        url:"/stu/user/userLogin.do",
        type:"POST",
        data:{
            username:adminName,
            password:adminPassword,
            role:role
        },
        success:function(result){
            if(result.code){
                check=true;
            }else{
                check=false;
            }
        }
    });

    return check;
}
