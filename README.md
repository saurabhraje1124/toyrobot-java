# Toy Robot

## Author Saurabh Raje

# Overview
This toy robot application has been built as an interview submission. The application has been built using Java 1.8.x.
The application accepts input in the form of a text file the path of which can be specified as a command line argument.

# Creating the command file
The command file follows a specific pattern for the commands as mentioned in the question statement. The commands file will have multiple commands 
separated by newlines. The file should be a utf-8 encoded text file. If you're using a linux machine, the file extension is can be left blank, but if you're using Windows, please make sure that the file has an extension of .txt and encoded using utf-8 encoding system.

## Sample command file content
```
PLACE 2,3,NORTH
MOVE
RIGHT
MOVE
LEFT
MOVE
REPORT
```
A sample commands.txt file has been added to the repo for reference.

# Testing

We've used JUnit 4.x along with Maven for testing. The following command can be used to test the application.

```
mvn clean test
```

# Compilation and execution

## Compile into an executable jar using Maven
```
mvn clean package -Dmaven.test.skip=true
```

## Running the app
```
java -jar target/toyrobot.jar -f  PATH_TO_COMMANDS_TXT_FILE
```
