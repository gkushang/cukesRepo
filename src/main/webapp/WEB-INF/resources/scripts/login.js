jQuery(document).ready(function() {

        $("input[id^='login']").on('click',function() {


        var me = $(this);
        var username = $(".username").val();
        var password = $(".password").val();

             alert('here');
             $.ajax({

                url: "/login/verify/"+ username +"/"+ password,
                    }).done(function(data) {

                    top.location.href = "/login/";

                                                          });

            });
});