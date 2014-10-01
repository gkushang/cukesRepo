jQuery(document).ready(function() {

        $("input[id^='approve']").on('click',function() {

        var me = $(this);


        var scenario_number=me.attr('content');

        var project_id=$('#project-id').val();

        var feature_id=$('#feature-id').val();

             $.ajax({

                url: "/" + project_id + "/"+ feature_id + "/"+ scenario_number + "/approve",

                    }).done(function(data) {
                       me.val("Approved");
                       me.attr("disabled", "disabled");
                       me.attr("class", "cukes-button-approved");
                       $('#comments' + scenario_number).hide();
                       $('#add-comment' + scenario_number).hide();
                       $('.comments-box').next('br').remove ();
                       $('.comments-box').prev('br').remove ();
                       $('.comment-box-div').next('br').remove ();
                       $('.comment-box-div').remove ();
                    });

            });
});