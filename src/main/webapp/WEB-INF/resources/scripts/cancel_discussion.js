jQuery(document).ready(function() {

        $("input[id^='cancel-discussion']").on('click',function() {


                var me = $(this);
                var project_id = $(".projectId").val();
                var feature_id = $(".featureId").val();
                var discussions = $("#discussions").val();

             $.ajax({

                data:{projectId: project_id, featureId: feature_id, discussions: discussions},

                    }).done(function(data) {
                        top.location.href = "/projects/" + project_id + "/" + feature_id + "/";
                         });
            });
});