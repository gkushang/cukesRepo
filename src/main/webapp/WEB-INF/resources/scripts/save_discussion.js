jQuery(document).ready(function() {

        $("input[id^='save-discussion']").on('click',function() {


                var me = $(this);
                var project_id = $(".projectId").val();
                var feature_id = $(".featureId").val();
                var discussions = $("#discussions").val();


             $.ajax({

                type: "POST",
                url: "/" + project_id + "/" + feature_id + "/save-discussion/persist",

                data:{projectId: project_id, featureId: feature_id, discussions: discussions},

                    }).done(function(data) {

                          me.val("...");
                          me.prop("disabled",true);

                          setTimeout(
                            function()
                            {
                             me.val("Save");
                             me.prop("disabled",false);

                          me.attr("background-image",
                          "~/images/settings-icon.png");
                            }, 1000);
                         });
            });
});