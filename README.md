cukesRepo
=========

<br/>


### Important

The prefered IDE is IntelliJ IDEA 12. Dependency and project setup sections are written for IDEA.

<br/>


### Running the project

1. Click Run and then Edit Configurations…

2. Set the configuration name (e.g. CukesRepo) and tick the *Single instance only* checkbox

3. Add a *Make* configuration to the *Before launch* section

4. Under the Parameters tab

    4.1. Set the Working directory path to your project's root directory

    4.2. Populate the *Command line* text box with **jetty:run**

5. Under the Runner tab
    
    5.1. Set the following VM Options:
    
        -Djetty.port=[port]
        -Denv.props.path=[tokens file path]
        
    5.2. Set the JRE to v1.7

6. Save and run the configuration

<br/>

### Running the project in EclipseIDE

1. Click Help and then Install New Software...

2. In the Work With field, enter http://run-jetty-run.googlecode.com/svn/trunk/updatesite

3. Select 'RunJettyRun' from the dialog box, and continue through installation steps

4. With the project loaded in Eclipse, right click on the project and select Run As/ Run Configuration...

5. Click the Add config button and select Jetty WebApp

6. In the Web Application section, input the port you wish to run on and populate the context field with '/'

7. In the JVM options, enter '-Denv.props.path=[path/to/your/]/local.properties

8. Click Run

9. In a browser, navigate to http://localhost:[port_from_step_6]/projects/ and you should see the projects page if everything is working properly

</br>

### External dependencies

1. Prerequisites

    1.1. Install Homebrew from http://mxcl.github.com/homebrew
    
    1.2. Make sure that you have the latest brews:

        brew update


2. Mongo

    2.1. Installation
    
        brew install mongo

    2.2. Running the daemon
    
        mongod

3. RabbitMQ

    3.1. Installation
    
        brew install rabbitmq
        
    3.2. Running the server
    
        rabbitmq-server

4. Memcache

    4.1. Installation

        brew install memcached

    4.2. Running the daemon
        
        memcached


<br/>


###Mocked services

A Spring profile is used to inject mocks instead of the actual services for development. Beans defined in **/WEB-INF/config/mocks.xml** file
have the 'primary' attribute set to 'true'. This will make Spring choose the beans here over any other beans of the same interface. Spring profile used for mocks is **ora.profiles.mock**.
Refer to section 5.1. on how to enable the behavior. Additionally, you can add **src/mocks/java** path to your Source Folders under Module Settings.


<br/>
