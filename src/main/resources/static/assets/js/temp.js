//页面向下滚动时导航悬浮在顶部
(function($){
    $.fn.capacityFixed = function(options) {
        var opts = $.extend({},$.fn.capacityFixed.deflunt,options);
        var FixedFun = function(element) {
            var top = opts.top;
            element.css({
                "top":top
            });
            $(window).scroll(function() {
                var scrolls = $(this).scrollTop();
                if (scrolls > top) {

                    if (window.XMLHttpRequest) {
                        element.css({
                            position: "fixed",
                            top: 0							
                        });
                    } else {
                        element.css({
                            top: scrolls
                        });
                    }
                }else {
                    element.css({
                        position: "absolute",
                        top: top
                    });
                }
            });
            element.find(".close-ico").click(function(event){
                element.remove();
                event.preventDefault();
            })
        };
        return $(this).each(function() {
            FixedFun($(this));
        });
    };
    $.fn.capacityFixed.deflunt={
		right : 0,//相对于页面宽度的右边定位
        top:75
	};
})(jQuery);

$(document).ready(function(){
	
	$(".navWrap").capacityFixed();
	
});


//二级导航
$(document).ready(function(){
		  $('.mainNav').hover(function(){
			  $(this).children('.subNav').slideToggle();
				 });
});

//博客图标
$(function(){
	$('.blog ul li').hover(function(){
		$('span',this).stop().animate({'top':'-60px'});
	},function(){
		$('span',this).stop().animate({'top':'0px'});
	});
});


//回到顶部
$(document).ready(function(){
	$("#top").click(function() {
		$('body,html').animate({
			scrollTop: 0
		},
		1000);
		return false;
	});					  
});