$(function() {
  var navSelector = '#toc';
  var $myNav = $(navSelector);
  Toc.init($myNav);
  $('body').scrollspy({
    target: navSelector
  });

  $("li a[href^='#']").on('click', function(event) {
    if (this.hash !== "") {
      event.preventDefault();

      var hash = this.hash;

      $('html, body').animate({
        scrollTop: $(hash).offset().top
      }, 650, function(){

        window.location.hash = hash;
      });

    }
  });
});
