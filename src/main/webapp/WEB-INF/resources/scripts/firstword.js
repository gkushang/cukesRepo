jQuery(document).ready(function() {
	$('.step').each(function(){
	    var me = $(this);
	       me.html( me.text().replace(/(^\w+)/,'<strong style="color: #EF08F8;">$1</strong>') );
	  });
  });

