<%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/8/18
  Time: 下午2:24
  To change this template use File | Settings | File Templates.
--%>
</div>
</div>

</div>


<script src="http://libs.baidu.com/jquery/2.1.1/jquery.min.js"></script>
<script src="http://apps.bdimg.com/libs/jquery.cookie/1.4.1/jquery.cookie.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/amazeui/2.7.2/js/amazeui.min.js"></script>
<script src="/js/amaze/app.js"></script>
<script>

    var type = $.cookie("type");
    if (type == "index") {
        $("#a_index").addClass("active");
    }
    else if (type == "material_dyp") {
        $("#a_dyp").addClass("active");
    }
    else if (type == "material_dyc") {
        $("#a_dyc").addClass("active");
    }
    else if (type == "material_xyd") {
        $("#a_xyd").addClass("active");
    }
    else if (type == "dyMatchPage") {
        $("#a_match").addClass("active");
    } else if (type == "xyd_rate") {
        $("#a_xyd_rate").addClass("active");
    } else if (type == "zyd_match") {
        $("#a_zy_match").addClass("active");
    }

    $('input[name="radio-btn"]').wrap('<div class="radio-btn"><i></i></div>');
    $(".radio-btn").on('click', function () {
        var _this = $(this),
            block = _this.parent().parent();
        block.find('input:radio').attr('checked', false);
        block.find(".radio-btn").removeClass('checkedRadio');
        _this.addClass('checkedRadio');
        _this.find('input:radio').attr('checked', true);
    });
    $('input[name="check-box"]').wrap('<div class="check-box"><i></i></div>');
    $.fn.toggleCheckbox = function () {
        this.attr('checked', !this.attr('checked'));
    }
    $('.check-box').on('click', function () {
        $(this).find(':checkbox').toggleCheckbox();
        $(this).toggleClass('checkedBox');
    });
</script>
</body>
</html>
