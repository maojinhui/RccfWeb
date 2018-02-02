$("[data-nav-number]").click(function () {

  if($(this).hasClass("panel-on")){
    return;
  }

  var navNumber = this.dataset.navNumber;

  var str = "[data-nav="+navNumber+"]";
  var navNode = $(str);

  $("[data-nav-number]").removeClass("panel-on");
  $(this).addClass("panel-on");
  
  $("[data-nav]").each(function () {
    if(!$(this).hasClass('hide')){
      $(this).addClass("hide");
    }
  });
  navNode.removeClass("hide");

});
$('.popup').click(function () {
  $(this).addClass('hide');
});
function bigger(obj) {
    var src = obj.src;

    $('.popup img').attr('src',src);
    $('.popup').removeClass('hide');
}

