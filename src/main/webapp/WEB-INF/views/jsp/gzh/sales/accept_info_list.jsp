<%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2018/1/31
  Time: 下午6:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>受理信息</title>
    <meta name="viewport" content="width=device-width,user-scalable=no,initial-scale=1">
    <link rel="stylesheet" href="/work/css/normalize.css">
    <link rel="stylesheet" href="/work/css/basic.css">
    <link rel="stylesheet" href="/work/css/emp_apply.css">
    <style type="text/css">
        a:link, a:visited {
            text-decoration: none; /*超链接无下划线*/
        }

        a:hover {
            text-decoration: none; /*鼠标放上去有下划线*/
        }

        .top-info {
            display: block;
            position: absolute;
            top: -0.15rem;
            right: 0.6rem;

            width: 0.5rem;
            height: 0.5rem;

            padding-top: 0.1rem;
            background-color: #a4241f;
            color: #fff;
            border-radius: 50%;

            box-sizing: border-box;
        }
    </style>
</head>
<body>
<div class="container" id="list">

    <div class="head">
        <a></a>
    </div>

</div>

<div class="tab-bar">
    <div class="row ">
        <a class="col-25 tab-bar-on" href="/gzh/sales/index" style="border: none;">
            <img src="/work/img/index.png">
            <p style="color:#999;border: none;">个人</p>
        </a>
        <a class="col-25 tab-bar-on" style="border: none;">
            <img src="/work/img/apply_on.png">
            <p style="color:#4d5398;border: none;">受理信息</p>
            <%
                Integer count = (Integer) request.getAttribute("acceptNotificationCount");

                if (count != null && count > 0) {
            %>
            <span class="top-info"><%=count%></span>
            <%
                }
            %>
        </a>
        <a class="col-25" href="/gzh/rank/index" style="border: none;">
            <img src="/work/img/rank.png">
            <p style="color:#999;border: none;">排行榜</p>
        </a>
        <a class="col-25" href="/gzh/sales/page/data" style="border: none;">
            <img src="/work/img/data.png">
            <p style="color:#999;border: none;">数据统计</p>
        </a>
    </div>
</div>
<script src="/work/js/self_adaption.js"></script>
<script src="/work/js/jquery.js"></script>
<script src="/js/comm.js"></script>
<script src="/js/accept.js"></script>
<script>
    $.ajax({
        url: '/gzh/accept/info/list/sale',
        type: 'POST',
        dataType: 'json',
        data: {},
        success: function (result) {
            if (result.code) {
                var data = result.data;
                for (var i=0; i<data.length;i++) {
                    var accept = data[i];
                    var messageClass = "apply-message ";
                    if(accept.state===2){
                        messageClass = "apply-message danger";
                    }

                    var content = isNull(accept.content)?'':"原因："+accept.content;
                var str = '<div data-accept-id = "'+accept.id+'" onclick="acceptInfo(this)"  class="apply-pro">\n' +
                    '        <div class="apply-title">\n' +
                    '            <span>'+new Date(accept.createTime).format('yyyy-MM-dd')+'</span>\n' +
                    '            <span>'+new Date(accept.createTime).format('hh:mm:ss')+'</span>\n' +
                    '        </div>\n' +
                    '        <div class="'+messageClass+'">\n' +
                    '            <p>\n' +
                    '                '+getAcceptStateInfo(accept.state,accept.customerName,accept.employeeName,accept.houqiName)+'<br>\n' +
                    '                '+content+'\n' +
                    '            </p>\n' +
                    '        </div>\n' +
                    '    </div>';

                    $('#list').append(str);
                }

            } else {
                alert(result.errormsg);
            }
        },
        error: function () {

        }

    });


</script>

</body>
</html>
