<%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/11/14
  Time: 下午2:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title></title>
    <link href="/css/app/bootstrap.css" rel="stylesheet">
    <link href="/css/app/clock.css" rel="stylesheet">
    <link href="/css/app/clock-1.css" rel="stylesheet">

    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="/js/app/jquery.lwtCountdown-1.0.js"></script>

    <style type="text/css">
        .btn:hover,
        .btn:active,
        .btn:focus,
        .btn:visited {
            background-color: #337ab7;
            color: #fff3f3;
        }
    </style>

</head>
<body>
<div class="header">
    <h1>申请成功！</h1>
</div><!--/header-->
<div class="container">
    <div class="timer-box">
        <h5>您已申请成功，我们将在八小时内联系您</h5>
        <h5>周一至周五您也可以拨打我们官方客服电话。</h5>
        <div id="countdown">

            <div class="dash hours_dash">
                <span class="dash_title">小时</span>
                <div class="digit">
                    <div class="top" style="overflow: hidden; display: none;">0</div>
                    <div class="bottom" style="overflow: hidden; display: block;">2</div>
                </div>
                <div class="digit">
                    <div class="top" style="overflow: hidden; display: none;">0</div>
                    <div class="bottom" style="overflow: hidden; display: block;">3</div>
                </div>
            </div>

            <div class="dash minutes_dash">
                <span class="dash_title">分</span>
                <div class="digit">
                    <div class="top" style="overflow: hidden; display: none;">0</div>
                    <div class="bottom" style="overflow: hidden; display: block;">4</div>
                </div>
                <div class="digit">
                    <div class="top" style="overflow: hidden; display: none;">0</div>
                    <div class="bottom" style="overflow: hidden; display: block;">9</div>
                </div>
            </div>

            <div class="dash seconds_dash">
                <span class="dash_title">秒</span>
                <div class="digit">
                    <div class="top" style="overflow: hidden; display: none;">0</div>
                    <div class="bottom" style="overflow: hidden; display: block;">0</div>
                </div>
                <div class="digit">
                    <div class="top"
                         style="overflow: hidden; display: block; height: 51.8825px; padding-top: 0px; margin-top: 0px; padding-bottom: 0px; margin-bottom: 0px;">
                        0
                    </div>
                    <div class="bottom" style="overflow: hidden; display: block; height: 18.38px;">5</div>
                </div>
            </div>
        </div> <!-- end of countdown -->
    </div><!--/timer-box-->
</div><!--/container-->

<div class="col-sm-12" style=" text-align: center; margin-top: 70px">
    <a class="btn btn-primary" href="tel:4006-810-688"
       style="font-size: 50px;margin: 0 auto;width: 300px;border-radius: 10px;padding: 10px">联系客服</a>
</div>
<!-- start of the javascript code that handles the countdown -->
<script language="javascript" type="text/javascript">
    jQuery(document).ready(function () {
        var date = new Date();
        var day = date.getDate();
        var month = date.getMonth() + 1;
        var year = date.getYear() + 1900;
        var hour = date.getHours() + 8;
        var min = date.getMinutes();
        var sec = date.getSeconds();
        $('#countdown').countDown({
            targetDate: {
                'day': day,
                'month': month,
                'year': year,
                'hour': hour,
                'min': min,
                'sec': sec
            }
        });
    });
</script>
</body>
</html>