<%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/8/7
  Time: 下午4:10
  To change this template use File | Settings | File Templates.
--%>

</div>
</div>



<script>
    function setChooseType(ctype){
        $.cookie("ctype",ctype,{path:"/"});
    }
    function getChooseType(){
        return $.cookie("ctype");
    }
    $(function () {
        var ctype = getChooseType();
        if(typeof (ctype)!= undefined ){
            if(ctype == "product"){
                $("#product_i").addClass("tpl-left-nav-more-ico-rotate");
                $("#product_ul").css("display","block");
            }
        }
    });


</script>


<script src="http://apps.bdimg.com/libs/jquery.cookie/1.4.1/jquery.cookie.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/amazeui/2.7.2/js/amazeui.min.js"></script>
<script src="/js/amaze/app.js"></script>
<script src="/js/comm.js"></script>
<script>

    $("#quit").bind("click",function () {
//        $.cookie('userid', '', { expires: -1 });
        $.cookie("userid",null,{path:"/"});
        location.href="/back/login";

    });


    $('input[name="radio-btn"]').wrap('<div class="radio-btn"><i></i></div>');
    $(".radio-btn").on('click', function () {
        var _this = $(this),
            block = _this.parent();
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
