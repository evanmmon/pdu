<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<link rel="shortcut icon" href="img/icon/LOGO01-1.png">
<!-- saved from url=(0138)https://www.wconcept.cn/customer/address/new/is_checkout/1/isRedirect/1/is_gomonpage/1/sendtype/1,1/item-id/3057636,3057720/send-type/1,1/ -->
<html lang="zh">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <style>


        #vcode{
            height:47px;
            width:38%;




        }
        #code{
            color:#ffffff;/*字体颜色白色*/
            background-color:#000000;
            font-size:18pt;
            font-family:"STXinwei";
            padding:10px 40px 6px 35px;
            cursor:pointer;
            height:49px;
            border-radius:3px 3px 3px 3px
        }


    </style>


    <title>登录界面</title>

    <link href="./login_files/login.css" rel="stylesheet" type="text/css">
</head>

<body leftmargin="0" onload="changeImg()">
<div class="login_box">
    <div class="login_l_img"><img src="./login_files/login-img.png"></div>
    <div class="login" style="height: 420px;">
        <div class="login_logo"><img src="./login_files/login_logo.png"></div>
        <div class="login_name">
            <p>后台管理系统</p>
        </div>
        <form action="doLogin" method="post" onsubmit="return check()">
            <h3  style="color:red ;font-size:13px ">${msg}</h3>
            <input name="username"  id="box_name" type="text" placeholder="用户名">

            <input name="password"  id="box_pass" type="password" placeholder="密码">
            <div style="width:300px; height:60px;">
                <input name="vcode" id="vcode" type="text"  placeholder="验证码" onfocus="this.value=''" onblur="if(this.value=='')this.value='请输入验证码'"/  style="width:55%" >
                <span id="code" style="display:block; width:130px; height:45.5px;float:right;margin-top:0px;" title="看不清，换一张" ></span>
            </div>
            <input value="登录" style="width:100% ,margin-left:10; " type="submit">
        </form>
    </div>
    <div class="copyright">湖北窗口科技有限公司 版权所有©2018 技术支持电话：027-87411898</div>
</div>
<script type="text/javascript">
    var code;//声明一个变量用于存储生成的验证码
    document.getElementById("code").onclick=changeImg;
    function changeImg(){
        //alert("换图片");
        var arrays=new Array(
            '2','3','4','5','6','7','8','9',
            'a','b','c','d','e','f','g','h','j',
            'k','m','n','p','q','r','s','t',
            'u','v','w','x','y','z',
            'A','B','C','D','E','F','G','H','J',
            'K','M','N','P','Q','R','S','T',
            'U','V','W','X','Y','Z'
        );
        code='';//重新初始化验证码
        //alert(arrays.length);
        //随机从数组中获取四个元素组成验证码
        for(var i=0;i<4;i++){
            //随机获取一个数组的下标
            var r=parseInt(Math.random()*arrays.length);
            code+=arrays[r];
            //alert(arrays[r]);
        }
        //alert(code);
        document.getElementById('code').innerHTML=code;//将验证码写入指定区域
    }

    //效验验证码(表单被提交时触发)
    function check(){
        //获取用户输入的验证码
        var input_code=document.getElementById('vcode').value;
        //alert(input_code+"----"+code);
        if(input_code.toLowerCase()==code.toLowerCase())
        {
            //验证码正确(表单提交)
            return true;
        }
        alert("请输入正确的验证码!");
        //验证码不正确,表单不允许提交
        return false;
    }
</script>

