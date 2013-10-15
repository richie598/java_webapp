java_webapp
===========

Contents
--------

This repo contains the following java web applications:

- A vanilla "hello world" Servlet 
- Simple Servlet that uses the pre-configured EYMySQL datasource
- Simple Spring MVC demo that also uses the pre-configured EYMySQL datasource

- a build.xml ant configuration file that uses ivy for dependency management

Prerequisites
-------------
- You need a JDK (Java 7) and apache ant to build these samples
- You need an account on Engine Yard to deploy these samples

Getting Started
---------------
1. git clone the repository locally
2. run ant
3. Target war files are built in the top level directory

Deploying the Applications
--------------------------
The war files built locally by ant can be deployed with Engine Yard using the UI or CLI.

Objectives
----------
These are simple demos for illustrating basic Java deployment on Engine Yard. Feature development will be required for generic JDBC access, more complex Spring configurations, and possibly JEE as well 

