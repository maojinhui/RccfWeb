<%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 17/8/3
  Time: 下午5:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>贷款进度</title>
    <link rel="shortcut icon" type="image/x-icon" href="/image/rccf.ico">
    <link rel="stylesheet" href="//cdn.bootcss.com/weui/1.1.1/style/weui.min.css">
    <link rel="stylesheet" href="//cdn.bootcss.com/jquery-weui/1.0.1/css/jquery-weui.min.css">
    <style type="text/css">
        .weui-cell{
            font-size: 16px;
            padding: 10px;
        }
    </style>
</head>
<body>

<div style="height: 100px;background-color: #f9bd5c;color: whitesmoke;font-size: 20px;">
    <span style="display:block;padding-top: 30px;padding-left: 20px">待处理……</span>
</div>
<div class="weui-cells weui-cells_form">
    <h4 style="padding:10px 0 10px 20px;">申请信息</h4>
    <div class="weui-cell weui-cell_vcode">
        <div class="weui-cell__hd">
            <label class="weui-label">姓名</label>
        </div>
        <div class="weui-cell__bd">
            <input class="weui-input" type="text" placeholder="张三" readonly>
        </div>
    </div>
    <div class="weui-cell weui-cell_vcode">
        <div class="weui-cell__hd">
            <label class="weui-label">联系方式</label>
        </div>
        <div class="weui-cell__bd">
            <input class="weui-input" type="tel" placeholder="12489123321" readonly>
        </div>
    </div>
    <div class="weui-cell weui-cell_vcode">
        <div class="weui-cell__hd">
            <label class="weui-label">申请额度</label>
        </div>
        <div class="weui-cell__bd">
            <input class="weui-input" type="number" placeholder="30W" readonly>
        </div>
    </div>
    <div class="weui-cell weui-cell_vcode">
        <div class="weui-cell__hd">
            <label class="weui-label">申请周期</label>
        </div>
        <div class="weui-cell__bd">
            <input class="weui-input" type="text" placeholder="3个月" readonly>
        </div>
    </div>
</div>
<div class="weui-cells__tips">
    提交时间:<input class="weui-input" type="datetime-local" value="2017年/8/8 15:32" readonly/>
</div>


<!--底部导航栏-->
<div class="weui-tabbar">
    <a href="/app/homepage" class="weui-tabbar__item ">
        <span style="display: inline-block;position: relative;">
            <img src="/image/weixin/home.png" alt="" class="weui-tabbar__icon">
            <!--<span class="weui-badge" style="position: absolute;top: -2px;right: -13px;">8</span>-->
        </span>
        <p class="weui-tabbar__label">首页</p>
    </a>
    <a href="/app/producepage" class="weui-tabbar__item">
        <img src="/image/weixin/produce.png" alt="" class="weui-tabbar__icon">
        <p class="weui-tabbar__label">产品</p>
    </a>
    <a href="javascript:;" class="weui-tabbar__item weui-bar__item_on">
        <span style="display: inline-block;position: relative;">
            <img src="/image/weixin/progress_on.png" alt="" class="weui-tabbar__icon">
            <!--<span class="weui-badge weui-badge_dot" style="position: absolute;top: 0;right: -6px;"></span>-->
        </span>
        <p class="weui-tabbar__label">进度</p>
    </a>
    <a href="/app/mypage" class="weui-tabbar__item " >
        <img src="/image/weixin/me.png" alt="" class="weui-tabbar__icon">
        <p class="weui-tabbar__label">我的</p>
    </a>
</div>
<script src="//cdn.bootcss.com/jquery/1.11.0/jquery.min.js"></script>
<script src="//cdn.bootcss.com/jquery-weui/1.0.1/js/jquery-weui.min.js"></script>

</body>
</html>
