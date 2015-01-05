jQuery(document).ready(function() {

        $("input[id^='add-project']").on('click',function() {


                var me = $(this);
                var project_name = $(".project-name").val();
                var repository_url = $(".repository-path").val();
                var path_to_features = $(".git-branch").val();
                var email_of_po = $(".project-owners").val();
                var display_project_name = $(".display-name").val();
                var collaborators_email = $(".collaborators").val();

             $.ajax({

                        url: "/user/add-project/persist",

                        data:{
                                 projectname: project_name,
                                 displayprojectname: display_project_name,
                                 repositorypath: repository_url,
                                 featurespath: path_to_features,
                                 emailofpo: email_of_po,
                                 collaborators: collaborators_email
                             },

                     }).done(function(data) {

                    top.location.href = "/projects/";
                                     });
            });
});


jQuery(document).ready(function() {

        $("input[id^='request-project']").on('click',function() {

  var me = $(this);
  me.val('...');
        var update_error = $("#update-error");
        var project_id = $(".project-name").val();
        var repository_url = $(".repository-path").val();
        var email_of_po = $(".project-owners").val();
        var collaborators_email = $(".collaborators").val();

             $.ajax({

               url: "/user/request-project/email",
                data:{
                    projectname: project_id,
                    repositorypath: repository_url,
                    emailofpo: email_of_po,
                    collaborators: collaborators_email},

                   error: function(jqXHR, tx, er){

                      $("#update-error").html(jqXHR.responseText);

                   }
                    }).done(function(data) {


                       if(data === '') {
                           $('#request-project').hide();
                           $(".check-mark").show();

                           $('#request-project').replaceWith( "<span class=\"confirmation\"><img class=\"check-mark\"/>Thank you</span>" );
                           $('#confirmation').html("N.B: Your request is under progress. You will be notified by email when your project is added." );

                           $('#cancel-update').val("Go Back");

                        }
                        else{
                        $("#update-error").html(data);
                        }

                   }).error(function(err){

                   });

            });
});

