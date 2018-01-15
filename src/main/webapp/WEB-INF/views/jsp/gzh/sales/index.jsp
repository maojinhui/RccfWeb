<%@ page import="com.rccf.constants.build.Application" %>
<%@ page import="java.util.List" %>
<%@ page import="com.rccf.model.customer.CustomerSubmit" %><%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/12/25
  Time: 下午6:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
//    if (request.getProtocol().compareTo("HTTP/1.0") == 0){
//        response.setHeader("Pragma","no-cache");
//    }else if (request.getProtocol().compareTo("HTTP/1.1") == 0){
//        response.setHeader("Cache-Control","no-cache");
//    }
    response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
    response.setHeader("Expires", "0");
    response.setHeader("Pragma","no-cache");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <%=Application.web_head_title%>
    <%=Application.web_head_img%>
    <meta name="viewport" content="width=device-width,user-scalable=no,initial-scale=1">
    <META HTTP-EQUIV="pragma" CONTENT="no-cache">
    <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
    <META HTTP-EQUIV="expires" CONTENT="0">
    <link rel="stylesheet" href="/work/css/normalize.css">
    <link rel="stylesheet" href="/work/css/basic.css">
    <link rel="stylesheet" href="/work/css/index.css">
    <style type="text/css">
        a:link,a:visited{
            text-decoration:none;  /*超链接无下划线*/
        }
        a:hover{
            text-decoration:none;  /*鼠标放上去有下划线*/
        }
    </style>
</head>
<body>
<div class="content">

    <div class="container">
        <div class="row">
            <div data-nav-bar="1" class="col-5 nav-bar active">
                通知
                <% Integer notificationCount = (Integer) request.getAttribute("notificationCount");
                    if (notificationCount != null && notificationCount > 0) { %>
                <sup><%=notificationCount%>
                </sup>
                <% }%>
            </div>
            <div data-nav-bar="2" class="col-5 nav-bar">已办事项</div>
        </div>
        <ul  data-nav="1"  class=" nav-block ">
            <%
                List<CustomerSubmit> programs = (List<CustomerSubmit>) request.getAttribute("programs");
                if(programs!=null){
                    int count = programs.size()>5?5:programs.size();
                    for (int i = 0 ; i< count ;i++){
                        CustomerSubmit program = programs.get(i);
            %>
            <li class="notice  <%=program.getState()==1?"click-no":"clicked"%> " data-program-id="<%=program.getId()%>" onclick="clickNotice(this)" >
                <span>客户<%=program.getCustomer_name()%>的贷款方案已由市场部<%=program.getHouqi_name()%>给出</span>
                <label><%=program.getMonth_day()%></label>
                <label><%=program.getHourminute()%></label>
            </li>
            <%
                    }
                }
            %>

            <% if(programs !=null &&  programs.size()>5){ %>
            <li class="notice-all">查看全部 >></li>
            <% } %>

        </ul>
        <ul data-nav="2" class="nav-block hide">

            <%
                List<CustomerSubmit> logs = (List<CustomerSubmit>) request.getAttribute("programs");
                if(logs!=null){
                    int count = logs.size()>5?5:logs.size();
                    for (int i = 0 ; i< count ;i++){
                        CustomerSubmit log = logs.get(i);
            %>
            <li class="notice ">
                <span>客户<%=log.getCustomer_name()%>已提交至<%=log.getHouqi_name()%>进行产品匹配</span>
                <label><%=log.getMonth_day()%></label>
                <label><%=log.getHourminute()%></label>
            </li>
            <%
                    }
                }
            %>

            <% if(logs !=null && logs.size()>5){ %>
            <li class="notice-all">查看全部 >></li>
            <% } %>

        </ul>
    </div>

    <div class="personal-customer">
        个人客户管理
    </div>

</div>

<div class="tab-bar">
    <div class="row ">
        <a class="col-5 tab-bar-on" style="border: none;">
            <img src="/work/img/index_on.png">
            <p style="color:#4d5398;border: none;">个人</p>
        </a>
        <a class="col-5" href="/gzh/sales/page/data" style="border: none;">
            <img src="/work/img/data.png">
            <p style="color:#999;border: none;">数据统计</p>
        </a>
    </div>
</div>


<script src="/work/js/self_adaption.js"></script>
<script src="/work/js/jquery.js"></script>
<script>
    window.addEventListener("popstate", function (e) {
        self.location.reload();
    }, false);
    var state = {
        title: "",
        url: "#"
    };
</script>
<script>
    $('[data-nav-bar]').click(function () {
        var barNum = this.dataset.navBar;
        console.log(barNum);
        var nav_1 = $('[data-nav-bar="1"]');
        var nav_2 = $('[data-nav-bar="2"]');
        var block_1 = $('[data-nav="1"]');
        var block_2 = $('[data-nav="2"]');
        if (barNum === "1") {
            nav_2.removeClass('active');
            nav_1.addClass('active');
            block_2.addClass('hide');
            block_1.removeClass('hide');
        } else {
            nav_1.removeClass('active');
            nav_2.addClass('active');
            block_1.addClass('hide');
            block_2.removeClass('hide');
        }
    })

    $('.personal-customer').click(function () {
        window.location.href = '/gzh/sales/customer/list'
    })
    
    function clickNotice(obj) {
        var programId =  $(obj).data("programId");
        var url = '/gzh/sales/customer/program?program_id='+programId;
        window.location.href=url;
    }

//    window.history.pushState(state, "title", "#");

</script>

<script>


    window.history.replaceState(state, "", "#");
    var rnumber = Math.floor(Math.random()*1000)
    history.replaceState({mod: rnumber}, 'Title', '?mod='+rnumber);


//    if ((/iphone|ipod|ipad.*os 5/gi).test(navigator.appVersion)) {
//        window.onpageshow = function(event) {
//            if (event.persisted) {
//                window.location.reload()
//            }
//        };
//    }else{
//        onload=function(){
//            var refreshedId=document.getElementById("refreshed");
//            if(refreshedId.value=="no"){
//                refreshedId.value="yes";
//            } else{
//                refreshedId.value="no";
//                location.reload();
//            }
//        }
//    }
</script>


</body>
</html>
