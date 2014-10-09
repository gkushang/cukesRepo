jQuery(document).ready(function() {

        $("input[id^='email-request']").on('click',function() {

        var me = $(this);
        var id = me.attr('id');

        var feature_id=me.attr('content');
        var project_id=$('#project-id').val();

        me.val("sending email...");

            $.ajax({

                url: "/"+feature_id+"/"+project_id+"/email/review-request",
                    }).done(function(data) {
                    me.val("resend email");
                    $("#status-"+feature_id).html("Under Review");
                        });

            });
});