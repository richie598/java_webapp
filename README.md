java_webapp
===========

Contents
--------
This repo contains the following java web applications:

- A vanilla "hello world" Servlet 
- Simple Servlet that uses a container-configured JDBC datasource (with JNDI name EYMySQL)
- Simple Spring MVC demo that also uses the EYMySQL datasource
- a build.xml ant configuration file that uses ivy for dependency management

Prerequisites
-------------
- You need a JDK [Java 7][1] and Apache [ant][7] to build these samples
- You need an account on [Engine Yard][2] to deploy these samples. [Sign up][5] for one!

Getting Started
---------------
1. git clone the repository locally
2. run ant
3. Target war files are built in the top level directory

Configuring the Database
------------------------
The HelloJNDI and HelloSpringMVC Servlets connect to a database to retrieve a message displayed by the Servlet.
To configure the database for

1. SSH into your database server.
If you have not set up SSH, follow [these instructions][6].

2. Use mysql 
Enter start mysql -u <environment_name>_user -p
Enter the database password.
To retrieve your database password:
Log into your Engine Yard account.
Select the relevant organization.
Select the environment containing the relevant database server.
Click the Servers tab.
Find the database server and click the  link.
Your password should look similar to the following:
Enter the following into the mysql command line:
mysql > 
'''SQL
use <environment_name>;
DROP TABLE IF EXISTS `javademo`;
CREATE TABLE `javademo` (
`ID` varchar(4) NOT NULL,
`message` varchar(128) NOT NULL,
PRIMARY KEY  (`ID`),
UNIQUE KEY `ID_UNIQUE` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `javademo` VALUES ('jdbc','Hello from the JDBC servlet!'),('jndi','Hello from the JNDI servlet!'), ('spf','Hello from the Spring servlet!');
'''

Deploying the Applications
--------------------------
The war files built locally by ant can be deployed with Engine Yard using the [UI][3] or [CLI][4].

Objectives
----------
These are simple demos for illustrating basic Java deployment on Engine Yard. Future releases will enable generic JDBC access, more complex Spring configurations, and possibly support for JEE containers. 

[1]: http://www.oracle.com/technetwork/java/javase/downloads/index.html
[2]: http://ui.engineyard.com
[3]: https://support.cloud.engineyard.com/entries/26483236-User-Interface-for-Deploying-your-Java-Application-on-Engine-Yard
[4]: https://support.cloud.engineyard.com/entries/27042383-CLI-for-Deploying-your-Java-Application-on-Engine-Yard
[5]: https://support.cloud.engineyard.com/entries/27322283-Sign-up-for-an-Engine-Yard-Account
[6]: https://support.cloud.engineyard.com/entries/27519756-Set-up-SSH
[7]: http://ant.apache.org/