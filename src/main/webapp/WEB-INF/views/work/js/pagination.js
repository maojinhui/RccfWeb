

$(function () {
  var pagination = $(document).find('.a-pagination');
  console.log(pagination);
  var pages =$(pagination).children('span');
  console.log(pages);

  var page = pages[0];
  console.log(page);

});