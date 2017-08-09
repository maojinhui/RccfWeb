<%@page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="applicable-device" content="mobile">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <meta charset="UTF-8">
    <title>RCCF-融成金服</title>
    <link rel="stylesheet" href="//cdn.bootcss.com/weui/1.1.1/style/weui.min.css">
    <style type="text/css">
        .weui-cell__hd img {
            width: 88px;
            height: 88px;
        }

        .weui-cell__bd {
            margin-left: 20px;
        }

        .weui-cell__bd div {
            font-size: 20px;
        }

        .weui-cell__ft {

            text-align: left;
            display: block;
            width: 14em;
            word-break: keep-all;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
            color: black;
        }
    </style>
</head>
<body>
<div id="list" class="weui-cells">

</div>
<script src="//cdn.bootcss.com/jquery/1.11.0/jquery.min.js"></script>
<script src="//cdn.bootcss.com/jquery-weui/1.0.1/js/jquery-weui.min.js"></script>
<script type="text/javascript">
    function getData() {
        $.ajax({
            url: "/back/markets",
            type: "POST",
            dataType: "json",
            data: {},
            success: function (result) {
                if (result.code) {
                    data = JSON.parse(result.data);
                    if (data.length < 10) {
                        $(document.body).destroyInfinite();
                        $(".weui-loadmore").hide();
                    }
                    for (var i in data) {
                        $("#list").append("<a href='" + data[i].marketUrl + "'>" +
                            "        <div class=\"weui-cell\">\n" +
                            "            <div class=\"weui-cell__hd\"><img src=" + data[i].marketImg + "></div>\n" +
                            "            <div class=\"weui-cell__bd\">\n" +
                            "                <div class=\"weui-cell__ft\">" + data[i].marketTitle + "</div>\n" +
                            "                <div class=\"weui-cell__ft\">" + data[i].marketDes + "</div>\n" +
                            "            </div>\n" +
                            "        </div>\n" +
                            "    </a>");
                    }

                    loading = false;


                } else {
                    alert(result.errormsg);
                }
            },
            error: function () {

            }

        });
    }
    getData();

    $(document.body).infinite(20); //100（px） 设置距离底部多少像素开始加载
    var loading = false;            //状态标记
    $(document.body).infinite().on("infinite", function () {
        if (loading) return;
        loading = true;
        getData();

    });
</script>
</body>
</html>