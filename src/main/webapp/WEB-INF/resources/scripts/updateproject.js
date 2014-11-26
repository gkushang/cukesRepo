jQuery(document).ready(function() {

        $("input[id^='update-project']").on('click',function() {


        var me = $(this);
        var update_error = $("#update-error");
        var project_id = $("#project-id").val();
        var project_name = $(".display-name").val();
        var repository_url = $(".repository-path").val();
        var email_of_po = $(".project-owners").val();
        var display_project_name = $(".display-name").val();
        var collaborators_email = $(".collaborators").val();

        var p1_test_job = $(".p1-test-job").val();
        var acceptance_test_job = $(".acceptance-test-job").val();
        var e2e_test_job = $(".e2e-test-job").val();

             $.ajax({

                url: "/projects/" + project_id + "/update",
                data:{
                    projectname: project_name,
                    displayprojectname: display_project_name,
                    repositorypath: repository_url,
                    emailofpo: email_of_po,
                    collaborators: collaborators_email,
                    p1: p1_test_job,
                    acceptance:acceptance_test_job,
                    e2e:e2e_test_job},

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