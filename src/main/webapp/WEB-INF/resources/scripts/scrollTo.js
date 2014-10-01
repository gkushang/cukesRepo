jQuery(document).ready(function() {

	$(".top").on('click', function(event) {
	    event.preventDefault();
		var me = $(this);
	    $('html, body').animate({
	        scrollTop: 0
	    }, 2000);
	});
	 	$("a[class^='scenario']").on('click', function(event) {
	 	    event.preventDefault();
	 		var me = $(this);
	 	    var target = $('#'+me.attr('class'));
	 	    $('html, body').animate({
	 	        scrollTop: $(target).offset().top
	 	    }, 2000);
			});
 });

		
