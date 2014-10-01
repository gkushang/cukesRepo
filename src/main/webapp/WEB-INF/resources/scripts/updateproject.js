jQuery(document).ready(function() {

        $("input[id^='update-project']").on('click',function() {


        var me = $(this);
        var project_id = $("#project-id").val();
        var project_name = $(".project-name").val();
        var repository_path = $(".repository-path").val();
        var git_branch = $(".git-branch").val();

             $.ajax({

                url: "/projects/" + project_id + "/update",
                data:{projectname: project_name, repositorypath: repository_path, gitbranch: git_branch},


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