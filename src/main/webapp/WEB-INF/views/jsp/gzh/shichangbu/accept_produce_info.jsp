<%@ page import="com.rccf.model.gzh.accpet.AcceptedTemp" %><%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2018/2/2
  Time: 下午3:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    AcceptedTemp acceptedTemp = (AcceptedTemp) request.getAttribute("accept");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>客户信息填写</title>
    <meta name="viewport" content="width=device-width,user-scalable=no,initial-scale=1">
    <link rel="stylesheet" href="/work/css/normalize.css">
    <link rel="stylesheet" href="/work/css/basic.css">
    <link rel="stylesheet" href="/work/css/mar_apply.css">
    <link rel="stylesheet" href="/work/css/font-awesome.css">
</head>
<body>

<div class="container">
    <div class="product">
        <div class="apply-list">
            <table class="b-table">
                <caption>费用信息</caption>
                <tr>
                    <td>渠道费用</td>
                    <td>
                        <input id="qudaoFee" value="<%=acceptedTemp.getChannelFee()%>">
                    </td>
                </tr>
                <tr>
                    <td>三方费用</td>
                    <td>
                        <input id="sanfangFee" value="<%=acceptedTemp.getSanfangFee()%>">
                    </td>
                </tr>
                <tr>
                    <td>材料费用</td>
                    <td>
                        <input id="cailiaoFee" value="<%=acceptedTemp.getMaterialFee()%>">
                    </td>
                </tr>
            </table>
        </div>

        <div class="add-btn">
            <span>填写受理方案</span>
        </div>
        <div class="add-form">
            <input type="text" id="name" placeholder="产品名称">

            <div class="txt-hint hide">
                <%--<span data-product-id="1" onclick="selectBank(this)">中信银行-宅e贷</span>--%>
            </div>
        </div>

        <div class="add-pro">
            <%--<span data-product-id="1" onclick="deleteBank(this)">中信银行-宅e贷<i class="fa fa-times"></i></span>--%>
        </div>


        <div class="a-btns">
            <button id="lack" class="a-btn a-btn-wrong col-5">缺少资料</button>
            <button id="submit" class="a-btn a-btn-success col-5">确认提交</button>
        </div>
    </div>


    <div class="pro-data hide">
        <textarea id="lackData" autofocus="autofocus" placeholder="填写缺少的资料……" cols="35" rows="5"></textarea>
        <button id="lackSubmit" class="a-btn b-btn">退回受理单</button>
    </div>


</div>


<script src="/work/js/self_adaption.js"></script>
<script src="/work/js/jquery.js"></script>
<script src="/js/comm.js"></script>
<script>

    var produces = <%=request.getAttribute("produces")%>;

    function getProduce(keyWord){
        //正则表达式
        var len = produces.length;
        var arr = [];
        var reg = new RegExp(keyWord);
        for(var i=0;i<len;i++){
            //如果字符串中不包含目标字符会返回-1
            if(produces[i].agency_name.match(reg) || produces[i].name.match(reg) ||  produces[i].code.match(reg) ){
                arr.push(produces[i]);
            }
        }
        return arr;
    }

    function getProduceName(agency_name , name){
        if(isNull(agency_name)){
            return '';
        }
        if(!isNull(name)){
            return agency_name+'-'+name;
        }else{
            return agency_name;
        }
    }

    // 提交受理单
    $('#submit').click(function () {
        var jsonObj = {};
        var productArr = [];
        $('.add-pro span').each(function () {
            var productId = this.dataset.productId;
            var productType = this.dataset.productType;
            var obj = {};
            obj.productId= productId;
            obj.productType = productType;
            productArr.push(obj);
        });
        console.log(productArr);
        jsonObj.accept_id = '<%=acceptedTemp.getId()%>';
        jsonObj.qudaoFee = $('#qudaoFee').val();
        jsonObj.sanfangFee = $('#sanfangFee').val();
        jsonObj.cailiaoFee = $('#cailiaoFee').val();
        jsonObj.products = JSON.stringify(productArr);

        $.ajax({
            url: '/gzh/accept/submit/state3',
            data: jsonObj,
            dataType: 'json',
            success: function (result) {
                if(result.code){
                    alert(1);
                }else{
                    alert(result.errormsg);
                }
            }
        })
    });



    $('#lackSubmit').click(function () {
        var text = $('#lackData').val();
        console.log(text);
        var obj = {};
        obj.content = text;
        obj.accept_id = '<%=acceptedTemp.getId()%>';

        $.ajax({
            url: '/gzh/accept/submit/state2',
            data: obj,
            dataType: 'json',
            type: 'POST',
            success: function (result) {
                if(result.code){
                    alert('提交成功');
                    window.history.back(-2);
                }else{
                    alert(result.errormsg);
                }
            },
            error: function () {

            }

        });


    });

    // 去填写缺少的资料
    $('#lack').click(function () {
        $('.product').addClass('hide');
        $('.pro-data').removeClass('hide');
    });

    // 输入提示功能
    $('#name').on('keyup', function () {

        var val = this.value;
        var txtHint = $('.txt-hint');

        console.log(val);
        if (val === " " || val === "") {
            txtHint.addClass('hide');
        }
        else {
            txtHint.empty();
            var dataArray = getProduce(val);
            for(var i=0;i<dataArray.length;i++){
                var data = dataArray[i];
               var str = '<span data-product-id="'+data.id+'"  data-product-type="'+data.type+'"     onclick="selectBank(this)">' + getProduceName(data.agency_name ,data.name) + '</span>';
                txtHint.append(str);
            }

            txtHint.removeClass('hide');
        }

    });

    // 选择产品功能
    function selectBank(obj) {
        var name = obj.innerHTML;
        var id = obj.dataset.productId;
        var type = obj.dataset.productType
        var str = '<span data-product-id="' + id + '"  data-product-type="' + type + '"     onclick="deleteBank(this)">' + name + '<i class="fa fa-times"></i></span>';
        $('.txt-hint').addClass('hide');
        $('#name').val('');
        $('.add-pro').append(str);
    }

    // 删除产品功能
    function deleteBank(obj) {
        var dNode = obj.parentNode;

        dNode.removeChild(obj);
    }


</script>
<script>



</script>

</body>
</html>
