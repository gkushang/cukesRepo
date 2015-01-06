
$("input:radio[name=group1]").click(function() {
    var value = $(this).val();
    alert(value);

});

jQuery(document).ready(function() {

        $("input[id^='send-feedback']").on('click',function() {

        var me = $(this);

        var feedback_comment=$('.feedback_comment-text-area').val().trim();

       $("#feedback-error").html('');

        var radioValue = $("input:radio[name=group1]:checked").val();

        if(feedback_comment.length > 0)  {
        me.val('...');
        }


            $.ajax({

                    type: "POST",
                    url: "/send-feedback",
                    data:{comments: feedback_comment,
                    radioVal: radioValue},
                    success: function(data) {

                                                    if(feedback_comment.length == 0)  {
                                                     $("#feedback-error").html("feedback can't be blank");

                               }

                                if(feedback_comment.length > 0)  {
                               $('#send-feedback').hide();
                               $('#send-feedback').replaceWith( "<span class=\"email-sent\"><img class=\"check-mark\"/>Feedback Sent</span>" );
                               }

                          },

                    error: function(data) {

                    }

                    })

            });



});







