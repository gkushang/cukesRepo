jQuery(document).ready(function() {

        $("input[id^='update-project']").on('click',function() {


        var me = $(this);
        var project_id = $("#project-id").val();
        var project_name = $(".project-name").val();
        var repository_url = $(".repository-path").val();
        var path_to_features = $(".git-branch").val();
        var email_of_po = $(".project-owners").val();

             $.ajax({

                url: "/projects/" + project_id + "/update",
                data:{projectname: project_name, repositorypath: repository_url, featurespath: path_to_features, emailofpo: email_of_po},


                    }).done(function(data) {

                    top.location.href = "/projects/";

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