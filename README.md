# VirtualNetwork

This project is written in Java and requires `JRE 1.8`. 


**How to run:**
- Open eclipse and import the project.
- Run the program passing 2 arguments : `<fileInput>` `<fileOutput>`. The output file will be created in the same directory.

Please notice that input and outout files are placed in main folder.

- create custom file setenv.bat under `%CATALINA_HOME%\bin` with the following content:
```bat
set CLASSPATH=%CLASSPATH%;%CATALINA_HOME%\shared\qjutils.jar;%CATALINA_HOME%\shared\mcnet.jar;%CATALINA_HOME%\shared\com.microsoft.z3.jar;.;%CATALINA_HOME%\webapps\verify\WEB-INF\classes\tests
```

  ```xml
  <role rolename="tomcat"/>
  <role rolename="role1"/>
  ```


**How to load graph on `mininet` on Windows**

1. download the VM from [the site](http://mininet.org/) and import on VirtualBox

2. download [PuTTY and Xming](https://github.com/mininet/openflow-tutorial/wiki/Installing-Required-Software)

3. modify settings on VirtualBox of the VM, create an adapter on eth1 and connect via PuTTY:

	```
	Login: mininet
	Password: mininet
	```
4. run ifconfig at $ prompt to get IP Address. You can note that eth1 is not set up, so do this:

	```
	sudo dhclient eth1
	```
5. using PuTTY connect the local machine to mininet, using the new IP(e.g., `192.168.56.101`), the password is `mininet`.

6. Double click on Xming 

7. download [winSCP](https://winscp.net/eng/download.php) and follow this [guide](http://sandeshshrestha.blogspot.it/2015/01/transfer-files-between-host-os-and.html) to transfer file from host OS to mininet, put them into `mininet/custom` folder.

8. in order to run the script do the following actions:

  ```
	cd mininet/custom
	sudo python test.py
  ```
