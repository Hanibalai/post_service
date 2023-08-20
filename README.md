UPD: 
To quickly start the database and application, Docker-compose.yaml file was created.
To run the container, you need to have docker running, clone the repository, go to the root folder in the console and run command: docker-compose up -d --build 

A project-tracker of postal items project has been implemented.
Two controllers have been implemented: a postal item controller and a post office controller.
Information on working with API is documented using Swagger.
The pom file is configured to build the project in a war archive.
A JaCoCo limit has been set that requires 70 percent of the code to be covered by tests for a successful build.
Information for connecting to the database is specified in the properties file, postgres is used as a database.
The test coverage report was obtained using JaCoCo, the coverage screen is placed in the project root in the reports directory.
