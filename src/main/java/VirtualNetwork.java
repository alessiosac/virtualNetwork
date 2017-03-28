import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class VirtualNetwork {

	public static Map<String,String> values = new HashMap<String,String>();
	public static List<Link> links = new ArrayList<Link>();
	public static List<Node> nodes = new ArrayList<Node>();
	
	public static void main(String[] args) {
		
		if (args.length != 2) {
			System.err.println("Usage: VirtualNetwork <fileIn> <fileOut>");
			System.exit(1);
		} 
		String fileIn = args[0];
		String fileOut = args[1];
		try (BufferedReader br = new BufferedReader(new FileReader(fileIn))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	// process the line.
		    	String[] array = line.split(": ");
		    	saveData(array[0], array[1]);   
		    }
		    br.close();		    
		} catch (IOException e) {
			System.out.println("Error in reading file " + fileIn);
		}
		calculateGraph();
	    try{
	    	PrintWriter writer = new PrintWriter(fileOut, "UTF-8");
	    	links.forEach((temp) -> {
	    		writer.println(temp);
	    	});
	    	nodes.forEach((temp) -> {
	    		writer.print(temp.getCpu() + " ");
	    	});
	    	writer.println();
	    	nodes.forEach((temp) -> {
	    		writer.print(temp.getQueue() + " ");
	    	});

	    	writer.close();
	    	System.out.println("File " + fileOut + " created.");
	    } catch (IOException e) {
	    	System.out.println("Error in writing file" + fileOut);
	    }
	}
	
	private static void calculateGraph() {
		try{			
			int number = Integer.parseInt(values.get("nodes"));
			String topology = values.get("topology");
			switch(topology){
			case "linear":
				calculateLinear(number,
						Integer.parseInt(values.get("link-min-bw")),
						Integer.parseInt(values.get("link-max-bw")),
						Float.parseFloat(values.get("link-min-delay")),
						Float.parseFloat(values.get("link-max-delay")),
						Integer.parseInt(values.get("node-min-cpu")),
						Integer.parseInt(values.get("node-max-cpu")),
						Integer.parseInt(values.get("node-min-queue")),
						Integer.parseInt(values.get("node-max-queue")));
				break;
			case "full" :
				calculateFull(number,
						Integer.parseInt(values.get("link-min-bw")),
						Integer.parseInt(values.get("link-max-bw")),
						Float.parseFloat(values.get("link-min-delay")),
						Float.parseFloat(values.get("link-max-delay")),
						Integer.parseInt(values.get("node-min-cpu")),
						Integer.parseInt(values.get("node-max-cpu")),
						Integer.parseInt(values.get("node-min-queue")),
						Integer.parseInt(values.get("node-max-queue")));
				break;
			case "star":
				calculateStar(number,
						Integer.parseInt(values.get("link-min-bw")),
						Integer.parseInt(values.get("link-max-bw")),
						Float.parseFloat(values.get("link-min-delay")),
						Float.parseFloat(values.get("link-max-delay")),
						Integer.parseInt(values.get("node-min-cpu")),
						Integer.parseInt(values.get("node-max-cpu")),
						Integer.parseInt(values.get("node-min-queue")),
						Integer.parseInt(values.get("node-max-queue")));
				break;
			case "random" :
				float bias = Float.parseFloat(values.get("alpha"));
				if ( bias <0 || bias > 1 )
					throw new NumberFormatException();
				calculateRandom(number,
						bias,
						Integer.parseInt(values.get("link-min-bw")),
						Integer.parseInt(values.get("link-max-bw")),
						Float.parseFloat(values.get("link-min-delay")),
						Float.parseFloat(values.get("link-max-delay")),
						Integer.parseInt(values.get("node-min-cpu")),
						Integer.parseInt(values.get("node-max-cpu")),
						Integer.parseInt(values.get("node-min-queue")),
						Integer.parseInt(values.get("node-max-queue")));
				break;
			default:
				System.err.println("Invalid topology value in configuration file.");
			}
		}
		catch(NullPointerException ex){
			System.err.println( "Error in the file. It's not well formatted. Requiried field is missing.");
		}catch(NumberFormatException ex){
			System.err.println("The configuration file contains a uncorrect value.");
			System.err.println(ex.getMessage());
		}
		
	}

	private static void saveData(String name, String value){
		values.put(name,value);
	}
	
	private static void calculateLinear(int number, int minBand, int maxBand, float minQueue, float maxQueue, 
			int minCpu, int maxCpu, int minBuffer, int maxBuffer){
		
		for(int i=0; i<(number-1); i++){
			//create new link
			Link link = new Link(i, i+1, randomValue(minBand, maxBand), randomValue(minQueue, maxQueue));
			links.add(link);
			//create new node
			Node node = new Node(randomValue(minCpu, maxCpu), randomValue(minBuffer, maxBuffer));
			nodes.add(node);
		}
		//add last node
		Node node = new Node(randomValue(minCpu, maxCpu), randomValue(minBuffer, maxBuffer));
		nodes.add(node);
	}
	
	private static void calculateFull(int number, int minBand, int maxBand, float minQueue, float maxQueue, 
			int minCpu, int maxCpu, int minBuffer, int maxBuffer) {
		
		for(int i=0; i<(number-1); i++){
			//for each node create to link to any other nodes
			for(int j = (i+1); j<number; j++){
				//create new link
				Link link = new Link(i, j, randomValue(minBand, maxBand), randomValue(minQueue, maxQueue));
				links.add(link);
			}
			//create new node
			Node node = new Node(randomValue(minCpu, maxCpu), randomValue(minBuffer, maxBuffer));
			nodes.add(node);
		}
		//add last node
		Node node = new Node(randomValue(minCpu, maxCpu), randomValue(minBuffer, maxBuffer));
		nodes.add(node);
	}
	
	private static void calculateStar(int number, int minBand, int maxBand, float minQueue, float maxQueue, 
			int minCpu, int maxCpu, int minBuffer, int maxBuffer) {
		
		//create new node
		Node node = new Node(randomValue(minCpu, maxCpu), randomValue(minBuffer, maxBuffer));
		nodes.add(node);
		for(int i=1; i<number; i++){
			//create new link
			Link link = new Link(0, i, randomValue(minBand, maxBand), randomValue(minQueue, maxQueue));
			links.add(link);
			//create new node
			node = new Node(randomValue(minCpu, maxCpu), randomValue(minBuffer, maxBuffer));
			nodes.add(node);
		}	
	}
	
	private static void calculateRandom(int number, float alpha, int minBand, int maxBand, float minQueue, float maxQueue, 
				int minCpu, int maxCpu, int minBuffer, int maxBuffer) {
		
		BiasedCoin coin = new BiasedCoin(alpha);
		for(int i=0; i<(number-1); i++){
			//for each node create to link to any other nodes
			for(int j = (i+1); j<number; j++){
				coin.flip();
				if(coin.getFace() == 1){
					//create new link
					Link link = new Link(i, j, randomValue(minBand, maxBand), randomValue(minQueue, maxQueue));
					links.add(link);
				}
			}
			//create new node
			Node node = new Node(randomValue(minCpu, maxCpu), randomValue(minBuffer, maxBuffer));
			nodes.add(node);
		}
		//add last node
		Node node = new Node(randomValue(minCpu, maxCpu), randomValue(minBuffer, maxBuffer));
		nodes.add(node);
	}
	
	private static int randomValue(int min, int max){
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}
	
	private static float randomValue(float min, float max){
		return ThreadLocalRandom.current().nextFloat() * (max - min) + min;
	}
	
}
