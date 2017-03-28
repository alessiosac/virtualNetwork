How to run:


This project requires `JRE 1.8` or later.


**Windows**
- install `maven` [here](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- set ambient variable `JAVA_HOME` to where you installed the jdk (e.g. `C:\Program Files\Java\jdk1.8.X_YY`)
- install Apache Tomcat 8 [here](https://tomcat.apache.org/download-80.cgi)
- set ambient variable `CATALINA_HOME` to the directory where you installed Apache (e.g. `C:\Program Files\Java\apache-tomcat-8.0.30`)
- create `shared` folder under `%CATALINA_HOME%`
- add previously created folder to the Windows `Path` system variable (i.e. append the following string at the end: `;%CATALINA_HOME%\shared`)
- download `mcnet.jar`, `com.microsoft.z3.jar` and `qjutils.jar` from [here](https://github.com/netgroup-polito/verigraph/tree/master/service/build) and copy them to `%CATALINA_HOME%\shared` 
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
- launch Tomcat 8 with the startup script `%CATALINA_HOME%\bin\startup.bat`
- (optional) if you previously configured Tomcat Manager you can open a browser and navigate to [this link](http://localhost:8080/manager) and login using `tomcat/tomcat` as username/password
- (optional) you can deploy/undeploy/redeploy the downloaded WARs through the web interface

**Eclipse**
- open the project
- run the program
Please notice that input files are placed in main folder and output files will be created in the same folder.

**How to load graph on `mininet` on Windows**

1. download the VM from [the site](http://mininet.org/) and import on VirtualBox

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

putty.exe -X mininet@192.168.56.101

6. download (winSCP)[] and follow this (guide)[http://sandeshshrestha.blogspot.it/2015/01/transfer-files-between-host-os-and.html] to transfer file from host OS to mininet.

7. under `/verify/src/main/webapp/json/` create a file `<type>.json`. This file represents a JSON schema \(see [here](http://json-schema.org/) the official documentation\). For compatibility with the other functions it is mandatory to support an array as the root of the configuration, but feel free to specify all the other constraints as needed. A sample of `<type>.json` to describe an empty configuration could be the following:

  ```
	cd mininet/custom
	sudo python test.py
  ```
