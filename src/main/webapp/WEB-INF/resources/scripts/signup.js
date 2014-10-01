jQuery(document).ready(function() {

        $("input[id^='sign-up']").on('click',function() {


        var me = $(this);
        var name = $(".name").val();
        var password = $(".password").val();
        var email = $(".email").val();
        var role = $(".role").val();

             $.ajax({

                url: "/login/sign-up/persist",
                data:{name: name, password: password, email: email, role:role},


                    }).done(function(data) {

                    top.location.href = "/login/";

                                                          });

            });
});