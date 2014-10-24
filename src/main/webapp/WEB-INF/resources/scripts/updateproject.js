jQuery(document).ready(function() {

        $("input[id^='update-project']").on('click',function() {


        var me = $(this);
        var update_error = $("#update-error");
        var project_id = $("#project-id").val();
        var project_name = $(".display-name").val();
        var repository_url = $(".repository-path").val();
        var path_to_features = $(".git-branch").val();
        var email_of_po = $(".project-owners").val();
        var display_project_name = $(".display-name").val();

             $.ajax({

                url: "/projects/" + project_id + "/update",
                data:{projectname: project_name, displayprojectname: display_project_name,
                repositorypath: repository_url, featurespath: path_to_features, emailofpo: email_of_po},

                   error: function(jqXHR, tx, er){

                      $("#update-error").html(jqXHR.responseText);

                   }
                    }).done(function(data) {


                       if(data === '') {
                        top.location.href = "/projects/";
                        }
                        else{
                        $("#update-error").html(data);
                        }

                   }).error(function(err){

                   });

            });
});


jQuery(document).ready(function() {

        $("input[id^='delete-project']").on('click',function() {


        var me = $(this);
        var project_id = $("#project-id").val();
        var project_name = $(".project-name").val();
        var repository_path = $(".repository-path").val();
        var git_branch = $(".git-branch").val();

             $.ajax({

                url: "/projects/" + project_id + "/delete",


                    }).done(function(data) {

                    top.location.href = "/projects/";

                                                          });

            });
});