# VirtualNetwork

This Java program accepts an input configuration file and returns a text file with the virtual network graph generated.

The input file contains the following lines:

	nodes: size of the virtual network graph
	topology: linear, full, star, or random (i.e., randomly connected graph)
	\&#945;: the bias of the random coin (ignored if the topology is not random)
	min virtual node CPU: the min virtual CPU capacity allocated to a virtual node (VM)
	max virtual node CPU: max virtual CPU capacity allocated to a virtual node (VM)
	min virtual node queue: min buffer capacity available at virtual node (VM)
	max virtual node queue: max buffer capacity available at virtual node (VM)
	min virtual link bandwidth: min bandwidth reserved by a virtual link
	max virtual link bandwidth: max bandwidth reserved by a virtual link
	min virtual link delay : min bandwidth reserved by a virtual link
	max virtual link delay: max bandwidth reserved by a virtual link
	
If the parameter random is chosen, the program needs to generate the graph as follows:
For each possible pair of nodes: flip a biased coin with bias alpha. If the output of the coin
flip is 1, then add the edge, otherwise do not add the edge and move on to the next edge
candidate. Make sure that if &#945; = 1 the resulting virtual network (graph) is fully connected
(full), and if &#945; = 0 the graph should have no links. If &#945; = 0:5 then you have a perfectly
unbiased coin.
 
The output file contains the following space-separated values:


	Source-Node-ID Destination Node-ID Link0-bandwidth Link0-delay
	Source-Node-ID Destination Node-ID Link1-bandwidth Link1-delay
	...
	Node0-CPU Node1-CPU ... NodeN-CPU
	Node0-queue Node1-queue ... NodeN-queue


where  the last two lines are not link definitions, but a space-separated list of node CPU and queues, respectively.

This project is written in Java and requires `JRE 1.8`. 

The `tester` folder includes a script that generates with mininet a virtual network according to the output file, that
is, the network should have as many virtual hosts as you specified in your configuration file, with the same network connectivity
and the same bandwidth, delay and queue.

**How to run:**
- The project was developed in `Eclipse`, if you use the same IDE, open `Eclipse` and import the project. However it is possible to run this project from command line, as usual Java project.

- Run the program passing 2 arguments : `<fileInput>` `<fileOutput>` (e.g., `star` and `starVirtual`).

The project already cointains some files used as input file, `<file>.txt`, and the results of the execution on theme,  `<file>Virtual.txt`.

The `tester` folder cointains the files that are needed for the second phase.


**How to load graph on `mininet` on Windows**

1. download the VM from [the Mininet site](http://mininet.org/) and import on VirtualBox

2. download [PuTTY and Xming](https://github.com/mininet/openflow-tutorial/wiki/Installing-Required-Software)

3. modify settings on VirtualBox of the VM, create an adapter on `eth1` and  set `attached to : Host-only Adapter` in network settings,then start `mininet`:

	```
	Login: mininet
	Password: mininet
	```

4. run ifconfig at $ prompt to get IP Address. You can note that eth1 is not set up, so do this :

	```
	sudo dhclient eth1
	```

5. using PuTTY connect the local machine to `mininet`, using the new IP(e.g., `192.168.56.101`), the password is `mininet`

6. double click on Xming 

7. download [winSCP](https://winscp.net/eng/download.php) and follow this [guide](http://sandeshshrestha.blogspot.it/2015/01/transfer-files-between-host-os-and.html) to transfer file from host OS to mininet, put them into `mininet/custom` folder

8. in order to run the script do the following actions:

  	```
	cd mininet/custom
	sudo python test.py
	  ```
	  
The script reads the file `topology` and creates the corresponding network, for any modification please modify this file.
