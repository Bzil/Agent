# Dependency Injection Java Agent

This java agent helps you define logger in application.


## How to build the agent

    mvn package

It creates log-agent.jar

## How to use it

Add this argument to the VM used to run your application:

    -javaagent:/path/to/log-agent.jar
