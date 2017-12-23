<%@ page import="java.util.List" %>
<%@ page import="com.rccf.model.poster.BPoster" %><%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/12/15
  Time: 下午3:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>展业海报</title>
    <meta name="viewport" content="width=device-width,initial-scale=1, user-scalable=no">
    <link rel="stylesheet" href="/css/app/normalize.css">
    <%--<link href="https://cdn.bootcss.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" rel="stylesheet">--%>
    <link rel="stylesheet" href="/css/bootstrap/bootstrap.css">
    <link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/bootstrap/extension.css">
    <style>
        .col-xs-4 {
            padding-left: 8px;
        }
    </style>
</head>
<body>
<%--<div class="head">--%>
<%--&lt;%&ndash;<i class="fa fa-search"></i>&ndash;%&gt;--%>
<%--&lt;%&ndash;<input type="text" placeholder="搜索之前浏览的，标题中内容">&ndash;%&gt;--%>
<%--&lt;%&ndash;<a>搜索</a>&ndash;%&gt;--%>
<%--<h1>展业海报</h1>--%>
<%--</div>--%>
<div class="head-img">
    <img src="/image/poster/extension_head.png">
</div>
<div class="container nav-ga">
    <div class="row">
        <div class="col-xs-4">
            <img src="/image/poster/nav1.jpg">
            <p>私人订制</p>
        </div>
        <div class="col-xs-4">
            <img src="/image/poster/nav2.jpg">
            <p>名片</p>
        </div>
        <div class="col-xs-4">
            <img src="/image/poster/nav3.png">
            <p>海报</p>
        </div>
    </div>
</div>
<%--<div class="img-show am-hide">--%>
<%--<div class="img-title">--%>
<%--今日推荐--%>
<%--</div>--%>
<%--<div class="container img-container">--%>
<%--<div class="row">--%>


<%--<div class="col-xs-4" >--%>
<%--<img data-extension-img  data-poster-id="1" src="/image/poster/model/poster1.jpeg">--%>
<%--<div class="title-img">--%>
<%--<label>请找我</label>--%>
<%--</div>--%>
<%--</div>--%>


<%--<div class="col-xs-4">--%>
<%--<img data-extension-img  data-poster-id="2" src="/image/poster/model/poster2.jpeg">--%>
<%--<div class="title-img">--%>
<%--<label>圣诞活动</label>--%>
<%--</div>--%>
<%--</div>--%>

<%--</div>--%>
<%--</div>--%>
<%--</div>--%>

<div class="img-show">
    <div class="img-title">
        热门海报
    </div>
    <div class="container img-container">
        <div class="row">
            <%
                List<BPoster> posters = (List<BPoster>) request.getAttribute("posters");
                for (int i = 0; i < posters.size(); i++) {
                    BPoster poster = posters.get(i);
            %>
            <div class="col-xs-4" data-poster_id="<%=poster.getId()%>">
                <img data-extension-img data-poster-id="<%=poster.getId()%>" src="<%=poster.getThumb()%>">
                <div class="title-img">
                    <label><%=poster.getTitle()%></label>
                </div>
            </div>
            <%
                }
            %>

            <%--<div class="col-xs-4">--%>
            <%--<img data-extension-img  data-poster-id="2" src="/image/poster/model/poster2.jpeg">--%>
            <%--<div class="title-img">--%>
            <%--<label>圣诞活动</label>--%>
            <%--</div>--%>
            <%--</div>--%>

        </div>
    </div>
</div>


<script src="/js/app/self_adaption.js"></script>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script>
    $('[data-extension-img]').click(function () {
        var poster_id = $(this).data('posterId');
        var showSrc = this.src;
//        console.log(showSrc);
//        console.log(typeof showSrc);
//        var extensionSrc = showSrc.replace('show', 'extension');
//        console.log(extensionSrc);
        sessionStorage.setItem('posterId', poster_id);
        sessionStorage.setItem('src',showSrc);
        window.location.href = '/poster/show_v1?poster_id=' + poster_id;
    })
</script>
</body>
</html>
