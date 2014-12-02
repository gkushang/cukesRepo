
jQuery(document).ready(function() {

        $("input[id^='send-feedback']").on('click',function() {

        var me = $(this);

        var feedback_comment=$('.feedback_comment-text-area').val();


            $.ajax({

                    type: "POST",
                    url: "/send-feedback",
                    data:{comments: feedback_comment},
                    success: function(data) {

                          top.location.href = "/projects/";

                          },

                    error: function(data) {
                          top.location.href = "/projects/";
                    }

                    })

            });



});







