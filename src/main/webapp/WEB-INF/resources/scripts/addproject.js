jQuery(document).ready(function() {

        $("input[id^='add-project']").on('click',function() {


        var me = $(this);
        var project_name = $(".project-name").val();
        var repository_path = $(".repository-path").val();
        var git_branch = $(".git-branch").val();

             $.ajax({

                url: "/user/add-project/persist",
                data:{projectname: project_name, repositorypath: repository_path, gitbranch: git_branch},


                    }).done(function(data) {

                    top.location.href = "/projects/";

                                                          });

            });
});