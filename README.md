*VirtualNetwork*

This project is written in Java and requires `JRE 1.8` or later. 


**How to run:**
- Open eclipse and import the project.
- Run the program passing 2 arguments : `<fileInput>` `<fileOutput>`. The output file will be created in the same directory.

Please notice that input and outout files are placed in main folder.

- create custom file setenv.bat under `%CATALINA_HOME%\bin` with the following content:
```bat
set CLASSPATH=%CLASSPATH%;%CATALINA_HOME%\shared\qjutils.jar;%CATALINA_HOME%\shared\mcnet.jar;%CATALINA_HOME%\shared\com.microsoft.z3.jar;.;%CATALINA_HOME%\webapps\verify\WEB-INF\classes\tests
```
- download `neo4jmanager.war` and `verify.war` from [here](https://github.com/netgroup-polito/verigraph/tree/master/service/build/windows)
- copy downloaded WARs into `%CATALINA_HOME%\webapps`
- (optional) configure Tomcat Manager:
  - open the file `%CATALINA_HOME%\conf\tomcat-users.xml`
  - under the `tomcat-users` tag place the following content:
  ```xml
  <role rolename="tomcat"/>
  <role rolename="role1"/>
  <user username="tomcat" password="tomcat" roles="tomcat,manager-gui"/>
  <user username="both" password="tomcat" roles="tomcat,role1"/>
  <user username="role1" password="tomcat" roles="role1"/>
  ```


**How to load graph on `mininet` on Windows**

1. download the VM from (the site)[http://mininet.org/] and import on VirtualBox

2. download (PuTTY and Xming)[https://github.com/mininet/openflow-tutorial/wiki/Installing-Required-Software]

3. modify settings on VirtualBox of the VM, create an adapter on eth1 and connect via PuTTY:

	```
	Login: mininet
	PW: mininet
	```
4. run ifconfig at $ prompt to get IP Address. You can note that eth1 is not set up, so do this:
	```
	sudo dhclient eth1
	
	```

5.


6. download (winSCP)[] and follow this (guide)[http://sandeshshrestha.blogspot.it/2015/01/transfer-files-between-host-os-and.html] to transfer file from host OS to mininet.

7. under `/verify/src/main/webapp/json/` create a file `<type>.json`. This file represents a JSON schema \(see [here](http://json-schema.org/) the official documentation\). For compatibility with the other functions it is mandatory to support an array as the root of the configuration, but feel free to specify all the other constraints as needed. A sample of `<type>.json` to describe an empty configuration could be the following:

  ```
	cd mininet/custom
	sudo python test.py
  ```
