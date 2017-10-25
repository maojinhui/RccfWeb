<%@ page import="com.rccf.model.User" %>
<%@ page import="com.rccf.util.Strings" %><%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 17/8/3
  Time: 下午5:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String headimg = "//image/app/header_default.png";
    User user = (User) request.getAttribute("user");
    if (null != user.getHeadimg()) {
        headimg = user.getHeadimg();
    }
    String phone = user.getPhone();
    String bindText = "未绑定";
    if (null != phone){
        bindText= Strings.phoneNumberFormat(phone);
    }


%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>我的</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scale=0"/>
    <link rel="stylesheet" href="/css/app/weui.min.css"/>
    <link rel="stylesheet" href="/css/app/jquery-weui.min.css"/>
    <link rel="stylesheet" href="/css/app/font-awesome.css"/>
    <link rel="stylesheet" href="/css/app/rccf_base.css">
    <link rel="stylesheet" href="/css/app/rccf_index.css">
</head>
<body>
<div class="rccf_body">
    <div class="rccf_grey_body">
        <header class="rccf_padding rccf_text_center fz40">
            <div class="rccf_text_center rccf_margin_bottom_xs">
                <img style="width: 1.6rem;height: 1.6rem;" src="<%=headimg%>">
            </div>
            <p><%=user.getUserName()%></p>
        </header>

        <div class="rccf_list rccf_margin_bottom ">
            <div class="weui-cells fz32">
                <a class="weui-cell weui-cell_access" href="rccf_mine_invitation.html">
                    <div class="weui-cell__hd"><img src="/image/app/user/mine.png"></div>
                    <div class="weui-cell__bd">
                        <p>我的邀请</p>
                    </div>
                    <div class="weui-cell__ft">
                    </div>
                </a>
            </div>
        </div>
        <div class="rccf_list rccf_margin_bottom ">
            <div class="weui-cells rccf_margin_zero fz32">
                <a class="weui-cell weui-cell_access" href="/app/datapage">
                    <div class="weui-cell__hd"><img src="/image/app/user/info.png"></div>
                    <div class="weui-cell__bd">
                        <p>资料</p>
                    </div>
                    <div class="weui-cell__ft ">
                    </div>
                </a>
            </div>
            <div class="weui-cells rccf_margin_zero fz32">
                <a class="weui-cell weui-cell_access" href="javascript:bindPhone();">
                    <div class="weui-cell__hd"><img src="/image/app/user/tie.png"></div>
                    <div class="weui-cell__bd">
                        <p>绑定手机号</p>
                    </div>
                    <div class="weui-cell__ft" id="bindtext">
                        <%=bindText%>
                    </div>
                </a>
            </div>
        </div>
        <div class="rccf_list rccf_margin_bottom">
            <div class="weui-cells rccf_margin_zero fz32">
                <a class="weui-cell weui-cell_access" href="javascript:;">
                    <div class="weui-cell__hd"><img src="/image/app/user/setting.png"></div>
                    <div class="weui-cell__bd">
                        <p>选项</p>
                    </div>
                    <div class="weui-cell__ft">
                    </div>
                </a>
            </div>
            <div class="weui-cells rccf_margin_zero fz32">
                <a class="weui-cell weui-cell_access" href="/appother/versionPage">
                    <div class="weui-cell__hd"><img src="/image/app/user/version.png"></div>
                    <div class="weui-cell__bd">
                        <p>版本信息</p>
                    </div>
                    <div class="weui-cell__ft">
                    </div>
                </a>
            </div>
        </div>
    </div>
</div>
<div class="weui-tabbar rccf_tabbar">
    <a href="/app/index" class="weui-tabbar__item">
        <img src="/image/app/tabbar/home.png" alt="" class="rccf_tabbar_icon">
        <p class="weui-tabbar__label fz30 ">首页</p>
    </a>
    <a href="/app/progresspage" class="weui-tabbar__item">
        <img src="/image/app/tabbar/loan.png" alt="" class="rccf_tabbar_icon">
        <p class="weui-tabbar__label fz30 ">我的贷款</p>
    </a>
    <a href="javascript:;" class="weui-tabbar__item">
        <img src="/image/app/tabbar/me_on.png" alt="" class="rccf_tabbar_icon">
        <p class="weui-tabbar__label fz30 rccf_tabbar_text">我的</p>
    </a>
</div>
<script src="/js/app/self_adaption.js"></script>
<script src="//cdn.bootcss.com/jquery/1.11.0/jquery.min.js"></script>
<script src="//cdn.bootcss.com/jquery-weui/1.0.1/js/jquery-weui.min.js"></script>
<script>
    function bindPhone() {
        var bindt = $('#bindtext').text();
        if(bindt.indexOf("未绑定")!=-1){
            window.location.href="/app/bindphone";
        }else{
            $.confirm({
                title:"更换手机号？",
                text:"您是否确定更换已绑定的手机号？",
                onOK:function () {
                    window.location.href="/app/bindphone";
                },
                onCancel:function () {
                }
            });
        }

    }

</script>
</body>


</html>
