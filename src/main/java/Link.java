public class Link {

	private int source;
	private int destination;
	private int bandwidth;
	private float delay;
	
	public Link(int source, int destination, int bandwidth, float delay) {
		this.source = source;
		this.destination = destination;
		this.bandwidth = bandwidth;
		this.delay = delay;
	}
	
	public int getSource() {
		return source;
	}

	public void setSource(int source) {
		this.source = source;
	}

	public int getDestination() {
		return destination;
	}

	public void setDestination(int destination) {
		this.destination = destination;
	}

	public int getBandwidth() {
		return bandwidth;
	}

	public void setBandwidth(int bandwidth) {
		this.bandwidth = bandwidth;
	}

	public float getQueue() {
		return delay;
	}

	public void setQueue(float delay) {
		this.delay = delay;
	}

	@Override
	public String toString(){
		return source + " " + destination + " " + bandwidth + " " + String.format("%.01f", delay);
	}
}
