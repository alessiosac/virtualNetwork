#!/usr/bin/python                                                                            
                                                                                             
from mininet.topo import Topo
from mininet.net import Mininet
from mininet.util import dumpNodeConnections
from mininet.log import setLogLevel

class SingleSwitchTopo(Topo):
    "Single switch connected to n hosts."
    def build(self, n=2):
        switch = self.addSwitch('s1')
        # Python's range(N) generates 0..N-1
        for h in range(n):
            host = self.addHost('h%s' % (h + 1), cpu=.5 / n, queue = 5)
            self.addLink(host, switch, bw=10, delay='5ms')

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
        
def readFile(filename):
    queue = []
    cpu = []
    with open(fname) as f:
        content = f.readlines()
        # you may also want to remove whitespace characters like `\n` at the end of each line
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
        linklist.append(y)
        
def createTopology(links, nodes):
    #for node in nodes:
    #    net.addHost(node.id, cpu = node.cpu, queue = node.queue)
    net = Mininet()                                                                                                       
    hosts = [ net.addHost(node.id, cpu = node.cpu, queue = node.queue)                                                                                   
                  for node in nodes ]
    for link in links:
        net.addLink( hosts[link.source], hosts[link.destination], bw=link.bandwidth, delay= link.delay+'s') 
    return net                                                                                                             
    
def simpleTest():
    "Create and test a simple network"
    topo = SingleSwitchTopo(n=4)
    net = Mininet(topo)
    net.start()
    print "Dumping host connections"
    dumpNodeConnections(net.hosts)
    print "Testing network connectivity"
    net.pingAll()
    net.stop()

if __name__ == '__main__':
    # Tell mininet to print useful information
    setLogLevel('info')
    simpleTest()