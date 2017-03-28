# VirtualNetwork

This project is written in Java and requires `JRE 1.8`. 


**How to run:**
- Open eclipse and import the project.

- Run the program passing 2 arguments : `<fileInput>` `<fileOutput>` (e.g., `star` and `starVirtual`).

The project conatins some files used as input file and the results of the execution on theme named `<file>Virtual.txt`.


**How to load graph on `mininet` on Windows**

1. download the VM from [the site](http://mininet.org/) and import on VirtualBox

2. download [PuTTY and Xming](https://github.com/mininet/openflow-tutorial/wiki/Installing-Required-Software)

3. modify settings on VirtualBox of the VM, create an adapter on `eth1` and  set `attached to : Host-only Adapter` in network settings,then start `mininet`:

	```
	Login: mininet
	Password: mininet
	```

4. run ifconfig at $ prompt to get IP Address. You can note that eth1 is not set up, so do this:

	```
	sudo dhclient eth1
	```

5. using PuTTY connect the local machine to `mininet`, using the new IP(e.g., `192.168.56.101`), the password is `mininet`.

6. double click on Xming 

7. download [winSCP](https://winscp.net/eng/download.php) and follow this [guide](http://sandeshshrestha.blogspot.it/2015/01/transfer-files-between-host-os-and.html) to transfer file from host OS to mininet, put them into `mininet/custom` folder.

8. in order to run the script do the following actions:

  	```
	cd mininet/custom
	sudo python test.py
	  ```
	  
The script reads the file `topology` and creates the corresponding network, for any modification please modify this file.
