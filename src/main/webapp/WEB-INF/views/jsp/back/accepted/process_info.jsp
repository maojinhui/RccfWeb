<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="http://cdn.amazeui.org/amazeui/2.7.2/css/amazeui.min.css"/>
    <style>
        html, body {
            overflow: hidden;
        }

        .am-list input {
            display: inline-block;
            width: 50%;
            vertical-align: middle;
            word-wrap: normal; /* for IE */
            text-overflow: ellipsis;
            white-space: nowrap;
            overflow: hidden;

        }

        @media screen and (max-width: 768px) {
            .am-list span {
                display: block;
            }

            .am-btn {
                display: block;
                float: none;
            }
        }

    </style>
</head>
<body>
<div class="am-cf am-padding am-padding-bottom-0">
    <div class="am-fl am-cf">
        <strong id="back" class="am-text-primary am-text-lg">受理单进度</strong>
    </div>
</div>

<ul id="list" class="am-list am-list-static">
    <li>
        <label>添加记录:&emsp;</label>
        <input id="new_item" value="" style="width:80%;">
        <div class="am-btn-group">
            <button id="new_add" class="am-btn am-btn-primary am-align-right">添加</button>
        </div>
    </li>
</ul>

</body>
<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="/js/common.js"></script>
<script>
    var accept_id =<%=request.getAttribute("aid")%>;

    function editButton(pid) {
        var state = $('#input-' + pid).attr("readonly");
        if (state === 'readonly') {
//            alert('不可编辑');
            $('#input-' + pid).removeAttr('readonly');
            $('#ebtn-' + pid).html('确定');
            $('#dbtn-' + pid).html('取消');
        } else {
            //提交内容
            var content = $('#input-' + pid).val();
            $.ajax({
                type: 'POST',
                url: '/accept/addprocess',
                dataType: 'json',
                data: {'pid': pid, 'content': content},
                success: function (result) {
                    if (result.code) {
                        window.location.reload();
                    } else {
                        alert(result.errormsg);
                    }
                },
                error: function () {

                }
            });

        }
    }

    function deleteButton(pid) {
        var state = $('#input-' + pid).attr("readonly");
        if (state) {
            //删除
        } else {
            $('#input-' + pid).attr("readonly", 'readonly');
            $('#ebtn-' + pid).html('编辑');
            $('#dbtn-' + pid).html('删除');
        }
    }


    $.ajax({
        url: '/accept/processdetail',
        dataType: 'json',
        data: {'aid': accept_id},
        success: function (result) {
            if (result.code) {
                var info = JSON.parse(result.data);
                for (var i = 0; i < info.length; i++) {
                    var pro = info[i];
                    var str = ' <li>\n' +
                        '        <label>' + getDate(pro.updateTime) + '</label>\n' +
                        '        <input id="input-' + pro.id + '" readonly="true"  value="' + pro.process + '">\n' +
                        '        <div class="am-btn-group">\n' +
                        '        <button id="ebtn-' + pro.id + '" onclick="editButton(' + pro.id + ')" class="am-btn am-btn-primary am-align-right">编辑</button>\n' +
                        '        <button id="dbtn-' + pro.id + '" onclick="deleteButton(' + pro.id + ')" class="am-btn am-btn-default am-align-right">删除</button>\n' +
                        '        </div>\n ' +
                        '    </li>';
                    $('#list').append(str);
                }
            } else {
                alert(result.errormsg);
            }
        },
        error: function () {

        }

    });

    $('#new_add').bind('click', function () {
        var content = $('#new_item').val();
        $.ajax({
            type: 'POST',
            url: '/accept/addprocess',
            dataType: 'json',
            data: {'aid': accept_id, 'content': content},
            success: function (result) {
                if (result.code) {
                    alert("添加成功");
                    window.location.reload();
                } else {
                    alert(result.errormsg);
                }
            },
            error: function () {

            }
        });


    });

</script>


</html>