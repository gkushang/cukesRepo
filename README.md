cukesRepo
=========

<br/>


### Snapshots

## Projects

![Alt text](/src/main/resources/snapshots/projects.png)

## Features

![Alt text](/src/main/resources/snapshots/features.png)

## Comment On Scenarios

![Alt text](/src/main/resources/snapshots/commentScenario.png)

## Approved Scenarios

![Alt text](/src/main/resources/snapshots/approvedScenario.png)

## Project Setting

![Alt text](/src/main/resources/snapshots/projectSettings.png)


### Important

The prefered IDE is IntelliJ IDEA 13. Dependency and project setup sections are written for IDEA.

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

<br/>

<br/>
