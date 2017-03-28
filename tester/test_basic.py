 
#!/usr/bin/python                                                                            
                                                                                             
from mininet.topo import Topo
from mininet.net import Mininet
from mininet.node import CPULimitedHost
from mininet.link import TCLink
from mininet.util import dumpNodeConnections
from mininet.log import setLogLevel, info

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
        
def readFile(filename, linkList, nodeList):
    queue = []
    cpu = []
    with open(filename) as f:
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
        print(y.Source)
        #Creates a list of Links
        linkList.append(y)
        
def createTopology(links, nodes):
    net = Mininet(host=CPULimitedHost, link=TCLink)
    h1 = net.addHost( 'h1', cpu=20, queue=320)
    h2 = net.addHost( 'h2', cpu=30, queue=520)
    h3 = net.addHost( 'h3', cpu=30, queue=400)
    h4 = net.addHost( 'h4', cpu=10, queue=500) 

    info( '*** Creating links\n' )
    net.addLink( h1, h2, bw= 30, delay = '0.2s' )
    net.addLink( h2, h3, bw= 30, delay = '0.2s' )
    net.addLink( h3, h4, bw= 30, delay = '0.4s' )
    #hosts = [ net.addHost(str(node.Id), cpu = node.Cpu, queue = node.Queue) for node in nodes ]
    #for link in links:
    #   net.addLink( hosts[int(link.Source)], hosts[int(link.Destination)], bw=float(link.Bandwidth), delay= str(link.Delay)+'s')
	#print( hosts[int(link.Source)]) 
    return net                                                                                                             
    
def simpleTest():
    "Create and test a simple network"
    #topo = SingleSwitchTopo(n=4)
    #net = Mininet(topo)
    links = []
    nodes = []
    readFile('topology', links, nodes)
    net = createTopology(links, nodes)
    net.start()
    print ("Dumping host connections")
    dumpNodeConnections(net.hosts)
    print ("Testing network connectivity")
    net.pingAll()
    net.stop()

if __name__ == '__main__':
    # Tell mininet to print useful information
    setLogLevel('info')
    simpleTest()
