# Event Platform Api.

## Description

The Java Template API is a template service that serves as a basis for creating other templates. It is built with Java and Spring Boot, using Gradle as a build automation tool.  This service is designed to be a basic framework, providing a solid starting point for the development of new projects. It includes standard configurations and common code structures that are often used in Java projects.

## Technologies Used

- Java: Programming language used to develop the service.
- Spring Boot: Framework used to simplify the application development.
- Gradle: Build automation tool used to manage dependencies and build the project.

## How to Run the Project

1. Ensure Docker is installed on your machine.
2. Clone the repository.
3. Navigate to the project directory.
4. Run `docker-compose up` to start up the Amazon SQS and PostgreSQL services.
5. After the Docker services are up and running, execute `./gradlew bootRun` (for Unix-based systems) or `gradlew bootRun` (for Windows) to start the application.

Please note that the application won't run correctly if the Docker services are not up and running.

## How to Use the Template

To use the github actions based on this template, you need to create a new repository by this [link](https://github.com/new?template_name=java-template-spring&template_owner=kaiqkt).
After this you will need to setup teh following secrets in your repository:
 - `DOCKERHUB_TOKEN` the token to access your dockerhub account.
 - `DOCKERHUB_USERNAME` your dockerhub username.
 - `DH_REPO_NAME` the name of the repository in dockerhub.
After this you will be able to run the github actions and starts following the git flow and continuous integration.

## To see the full documentation:

You will need the npm and docsify installed in your machine.
To install docsify run `npm i docsify-cli -g`
after installing dosify run `docsify serve ./docs/guide` and click [here](http://localhost:3000)
