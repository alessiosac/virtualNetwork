 
#!/usr/bin/python                                                                            
                                                                                             
from mininet.topo import Topo
from mininet.net import Mininet
from mininet.node import CPULimitedHost
from mininet.link import TCLink
from mininet.util import dumpNodeConnections
from mininet.log import setLogLevel, info

class TestTopo(Topo):
    def build(self, links, nodes):
    	hosts = [ self.addHost(str(node.Id), cpu = int(node.Cpu), queue = int(node.Queue) ) for node in nodes ]
    	for link in links:
           self.addLink( hosts[int(link.Source)], hosts[int(link.Destination)], bw=float(link.Bandwidth), delay= str(link.Delay)+'ms')
    
class Link:
    def __init__(self, source, destination, bw, delay): 
        self.Source = source 
        self.Destination = destination 
        self.Bandwidth = bw
        self.Delay = delay
    
class Node:
    def __init__(self, id, cpu, queue): 
        self.Id = id 
        self.Cpu = cpu 
        self.Queue = queue
        
def readFile(filename, linkList, nodeList):
    queue = []
    cpu = []
    with open(filename) as f:
        content = f.readlines()
    content = [x.strip() for x in content] 
    
    queueLine = content.pop()
    info = queueLine.split()
    for q in info:
        queue.append(q)
    
    cpuLine = content.pop()
    info = cpuLine.split()
    for q in info:
        cpu.append(q)
    
    #Create a list of Nodes
    i = 0
    while i < cpu.__len__():
        x = Node(i, cpu[i], queue[i])
        nodeList.append(x)
        i+=1
    
    for line in content:
        info = line.split()
        y = Link(info[0], info[1], info[2], info[3])
        #Creates a list of Links
        linkList.append(y)                                                                                                            
    
def simpleTest():
    "Create and test a simple network"
    links = []
    nodes = []
    readFile('topology', links, nodes)
    topo = TestTopo(links, nodes)
    net = Mininet( topo=topo,host=CPULimitedHost, link=TCLink )
    net.start()
    print ("Dumping host connections")
    dumpNodeConnections(net.hosts)
    print ("Testing network connectivity")
    net.stop()

if __name__ == '__main__':
    # Tell mininet to print useful information
    setLogLevel('info')
    simpleTest()
