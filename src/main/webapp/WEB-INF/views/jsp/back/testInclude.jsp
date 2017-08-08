<%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/8/7
  Time: 下午2:09
  To change this template use File | Settings | File Templates.
--%>
<%@page language="java" pageEncoding="utf-8" %>
<%--<script src="/js/comm.js" type="text/javascript" ></script>--%>
<script>
    loadStyles("/css/instyle.css");
</script>
<div>
It Works!
</div>
<div class="am-g am-center maindiv">
    <div class="am-u-sm-4 am-u-sm-centered">
        <h1>融成财富后台管理系统</h1>
    </div>
    <div class="am-u-sm-4 am-u-sm-centered formdiv">
        <div class="formdiv">
            <input class="am-form-field am-radius" id="user-name" type="tel" placeholder="手机号" value="<%="123"%>"/>
        </div>
        <div class="formdiv">
            <input class="am-form-field am-radius" id="pwd" type="password" placeholder="密码" value="<%="123"%>"/>
        </div>
        <div>
            <div class="am-text-right am-text-sm am-margin-top-xs">
                <span style="display: none;" class="am-align-left"><input id="checkbox" type="checkbox" value="" checked>记住密码</span>
                <a href="/back/findpwdp">忘记密码？</a>
            </div>
        </div>
        <div class="formdiv">
            <button type="button" class="am-btn am-btn-primary am-btn-block" id="submit">登录</button>
        </div>
    </div>
</div>
