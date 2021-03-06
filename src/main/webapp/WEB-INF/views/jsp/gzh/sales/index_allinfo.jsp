<%@ page import="com.rccf.model.customer.CustomerSubmit" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2018/1/15
  Time: 下午3:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>全部事项</title>
    <meta name="viewport" content="width=device-width,user-scalable=no,initial-scale=1">
    <link rel="stylesheet" href="/work/css/normalize.css">
    <link rel="stylesheet" href="/work/css/basic.css">
    <link rel="stylesheet" href="/work/css/more.css">
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
        <ul data-nav="1" class=" nav-block ">

            <%
                List<CustomerSubmit> programs = (List<CustomerSubmit>) request.getAttribute("programs");
                if(programs!=null){
                    int count = programs.size();
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

        </ul>
        <ul data-nav="2" class="nav-block hide">
            <%
                List<CustomerSubmit> logs = (List<CustomerSubmit>) request.getAttribute("programs");
                if(logs!=null){
                    int count = logs.size();
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
        </ul>

    </div>

</div>


<script src="/work/js/self_adaption.js"></script>
<script src="/work/js/jquery.js"></script>
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
    });

    function clickNotice(obj) {
        var programId =  $(obj).data("programId");
        var url = '/gzh/sales/customer/program?program_id='+programId;
        window.location.href=url;
    }


</script>
</body>
</html>
