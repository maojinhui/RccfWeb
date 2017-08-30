<%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/8/25
  Time: 下午4:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>首页</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scale=0"/>
    <link rel="stylesheet" href="//cdn.bootcss.com/weui/1.1.1/style/weui.min.css">
    <link rel="stylesheet" href="//cdn.bootcss.com/jquery-weui/1.0.1/css/jquery-weui.min.css">
    <link rel="stylesheet" href="/css/app/style.css">
</head>
<body>
<div class="content_phone">
    <!-- 首页轮播图 -->
    <div class="swiper-container">
        <!-- Additional required wrapper -->
        <div class="swiper-wrapper">
            <!-- Slides -->

        </div>
    </div>
    <!--导航按钮-->
    <!--<div class="weui-flex" style="margin-top: 1em;">-->
    <!--<div class="weui-flex__item">-->
    <!--<div class="weui-grid__icon">-->
    <!--<img src="image/4.png" alt="">-->
    <!--</div>-->
    <!--<p class="weui-grid__label">-->
    <!--贷款-->
    <!--</p>-->
    <!--</div>-->
    <!--<div class="weui-flex__item">-->
    <!--<div class="weui-grid__icon">-->
    <!--<img src="image/1.png" alt="">-->
    <!--</div>-->
    <!--<p class="weui-grid__label">-->
    <!--信用卡-->
    <!--</p>-->
    <!--</div>-->
    <!--<div class="weui-flex__item">-->
    <!--<div class="weui-grid__icon">-->
    <!--<img src="image/2.png" alt="">-->
    <!--</div>-->
    <!--<p class="weui-grid__label">-->
    <!--附近银行-->
    <!--</p>-->
    <!--</div>-->
    <!--<div class="weui-flex__item">-->
    <!--<div class="weui-grid__icon">-->
    <!--<img src="image/3.png" alt="">-->
    <!--</div>-->
    <!--<p class="weui-grid__label">-->
    <!--咨询-->
    <!--</p>-->
    <!--</div>-->
    <!--</div>-->
    <!--热门产品介绍-->
    <div class="weui-cells" style="margin-top: 1em;">
        <a class="weui-cell weui-cell_access" href="javascript:;">
            <div class="weui-cell__hd">
                <p class="hot-pro">|</p>
            </div>
            <div class="weui-cell__bd hot">
                <p>热门产品</p>
            </div>
            <div class="weui-cell__ft">
            </div>
        </a>
        <div>
            <div class="swiper-container_1">
                <div class="swiper-wrapper">
                    <div class="swiper-slide">
                        <div class="hot_product-slide-top">
                            <div class="text">
                                信用贷--种类多
                            </div>
                            <div class="money">
                                <span>¥<strong>50</strong>万</span>
                            </div>
                            <div class="rate_month" style=" ">
                                <span>年利率:<strong>7-12%</strong>&emsp;期限:<strong>3年</strong></span>
                            </div>
                            <button class="button">立即申请</button>
                        </div>
                    </div>
                    <div class="swiper-slide">
                        <div class="hot_product-slide-top">
                            <div class="text">抵押贷--成数高
                            </div>
                            <div style="text-align: center;color:#ff6600;margin-top:0.5em; ">
                                <span>￥<strong style="font-size: 2.2em;">5000</strong>万</span>
                            </div>
                            <div style="color:#23527c;margin-top:0.5em;padding-left: 0.2em; ">
                            <span>成数:<strong style="color:#ff6600;">最高9成</strong>&emsp;期 限:<strong
                                    style="color:#ff6600;">1-25</strong>年</span>
                            </div>
                            <button class="button">立即申请</button>
                        </div>
                    </div>
                    <div class="swiper-slide">
                        <div class="hot_product-slide-top">
                            <div class="text">金融服务--放款快
                            </div>
                            <div style="text-align: center;color:#ff6600;margin-top:0.5em; ">
                            <span><strong style="font-size:2.2em;"> </strong><strong
                                    style="font-size: 1.4em;">质押、垫资、保证金</strong></span>
                            </div>
                            <div style="color:#23527c;margin-top:1.4em;padding-left: 0.2em; ">
                            <span>可<strong style="color:#ff6600;">当天</strong>放款&emsp;期 限:<strong
                                    style="color:#ff6600;">1-12</strong>个月</span>
                            </div>
                            <button class="button">立即申请</button>
                        </div>
                    </div>
                    <div class="swiper-slide">
                        <div class="hot_product-slide-top">
                            <div class="text">疑难房贷款--方案多
                            </div>
                            <div style="text-align: center;color:#ff6600;margin-top:0.5em; ">
                                <span>￥<strong style="font-size: 2.2em;">无上限</strong></span>
                            </div>
                            <div style="color:#23527c;margin-top:0.5em;padding-left: 0.2em; ">
                                <span>条件:<strong style="color:#ff6600;">房屋可抵押</strong></span>
                            </div>
                            <button class="button">立即申请</button>
                        </div>
                    </div>

                </div>
                <!--<div class="swiper-pagination"></div>-->
            </div>
        </div>
    </div>
    <!--优秀业务员-->
    <div class="weui-cells" style="margin-top: 1em;margin-bottom: 4em;">
        <a class="weui-cell weui-cell_access" href="javascript:;">
            <div class="weui-cell__hd">
                <p class="hot-pro">|</p>
            </div>
            <div class="weui-cell__bd hot">
                <p>优秀贷款顾问</p>
            </div>
        </a>
        <div id="ex_staff" class="weui-flex" style="margin-top: 1em;">
        </div>
    </div>
</div>
<!--底部导航栏-->
<!--底部导航栏-->
<div class="weui-tabbar fix-bottom">
    <a href="/app/index" class="weui-tabbar__item weui-bar__item_on ">
        <span style="display: inline-block;position: relative;">
            <img src="/image/weixin/home_on.png" alt="" class="weui-tabbar__icon">
            <!--<span class="weui-badge" style="position: absolute;top: -2px;right: -13px;">8</span>-->
        </span>
        <p class="weui-tabbar__label">首页</p>
    </a>
    <a href="/app/producepage" class="weui-tabbar__item ">
        <img src="/image/weixin/produce.png" alt="" class="weui-tabbar__icon">
        <p class="weui-tabbar__label">产品</p>
    </a>
    <a href="/app/progresspage" class="weui-tabbar__item">
        <span style="display: inline-block;position: relative;">
            <img src="/image/weixin/progress.png" alt="" class="weui-tabbar__icon">
            <!--<span class="weui-badge weui-badge_dot" style="position: absolute;top: 0;right: -6px;"></span>-->
        </span>
        <p class="weui-tabbar__label">进度</p>
    </a>
    <a href="/app/mypage" class="weui-tabbar__item">
        <img src="/image/weixin/me.png" alt="" class="weui-tabbar__icon">
        <p class="weui-tabbar__label">我的</p>
    </a>
</div>
<script src="//cdn.bootcss.com/jquery/1.11.0/jquery.min.js"></script>
<script src="//cdn.bootcss.com/jquery-weui/1.0.1/js/jquery-weui.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Swiper/3.0.1/js/swiper.min.js"></script>
<script>
    //   页首伦轮播图控制器
    var mySwiper = new Swiper('.swiper-container', {
        effect: "slide",
        speed: 1000,
        autoplay: 2000,
        autoplayDisableOnInteraction: false,
        loop: true,
        spaceBetween: 20
    });
    //  贷款信息轮播
    var swiper = new Swiper('.swiper-container_1', {
        pagination: '.swiper-pagination',
        effect: 'coverflow',
        grabCursor: true,
        coverflow: {
            rotate: -50,
            stretch: 0,
            depth: 100,
            modifier: 1,
            slideShadows: false
        }
    });
    //    后台获取页首图片并显示
    $.ajax({
        url: "/index/recommendList",
        dataType: "json",
        success: function (result) {
            if (result.code) {
                var info = JSON.parse(result.data);
                for (var i = 0; i < info.length; i++) {
                    var src = info[i].img;
                    var list = "<div  class=\"swiper-slide\"><img src=\"" + src + "\"/></div>";
                    mySwiper.appendSlide(list);
                }
            }
            else {
                $.alert(result.errormsg);
            }
        }
    });
    // 获取优秀员工信息
    $.ajax({
        url: '/index/employeeList',
        dataType: "json",
        success: function (result) {
            if (result.code) {
                var infom = JSON.parse(result.data);
                for (var i = 0; i < infom.length; i++) {
                    var headerImg = infom[i].headerImg;
                    var phone = infom[i].phone;
                    var list = ' <a href="tel:' + phone + '" class="weui-flex__item">\n' +
                        '            <div class="weui-grid__icon salesman">\n' +
                        '                <img src="' + headerImg + '" alt="">\n' +
                        '            </div>\n' +
                        '            <p class="weui-grid__label">\n' +
                        '                <button class="call_me">点击咨询</button>\n' +
                        '            </p>\n' +
                        '        </a>';
                    $("#ex_staff").append(list);
                }
            }
            else {
                $.alert(result.errormsg);
            }
        }
    });

    //    文字闪烁效果
    function changeColor() {
        var color = "red|green|blue";
        color = color.split("|");
        $(".call_me").css("color", color[parseInt(Math.random() * color.length)]);
    }

    setInterval("changeColor()", 200);

</script>
</body>
</html>