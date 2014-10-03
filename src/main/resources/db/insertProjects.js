var project_one = "oraTest";
var project_two = "campaignManagerTest";
var workspace =  "/Users/kugajjar/Documents/workspace/";

var projects = [   	{
                    		"_class" : "com.cukesrepo.domain.Project",
                    		"name" : project_one,
                    		"repositorypath" : workspace + project_one,
                    		"featurespath" : workspace + project_two
                    	},
                    	{
                    		"_class" : "com.cukesrepo.domain.Project",
                    		"name" : project_two,
                    		"repositorypath" : workspace + project_two,
                    		"featurespath" : workspace + project_two
                    	}
                ]

db.project.remove({"name" : project_one});
db.project.remove({"name" : project_two});
db.project.insert(projects);
