
public class Node {

	private int cpu;
	private int queue;
	
	public Node(int cpu, int queue) {
		this.cpu = cpu;
		this.queue = queue;
	}

	public int getCpu() {
		return cpu;
	}

	public void setCpu(int cpu) {
		this.cpu = cpu;
	}

	public int getQueue() {
		return queue;
	}

	public void setQueue(int queue) {
		this.queue = queue;
	}

}
