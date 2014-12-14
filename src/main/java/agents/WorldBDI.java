package agents;

import jadex.bdiv3.annotation.Belief;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;
import jadex.micro.annotation.Description;
import jadex.platform.service.clock.Timer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

import utilities.Edge;
import utilities.Vertex;



/**
 * The Worlds agents holds the world's information
 */
@Agent
@Description("The world agent")
public class WorldBDI {

	Vertex[] WorldMapGraph;
	private int numberOfVertexes;
	private ArrayList<Vertex> mapVertexes;
	
	private String timePeriod;
    private long currentTime;
    private String weather;
    private String traffic;
    
    
    /* *************************** */
    /*           Beliefs           */
    /* *************************** */

    /* Time Period - "Day" or "Night" */
    @Belief
    public String getTimePeriod() {
        return timePeriod;
    }

    @Belief
    public void setTimePeriod(String timePeriod) {
        this.timePeriod = timePeriod;
    }

    /* Current Time */
    @Belief
    public long getCurrentTime() {
        return currentTime;
    }

    @Belief
    public void setCurrentTime(long currentTime) {
        this.currentTime = currentTime;
    }

    /* Weather */
    public boolean updateWeather() {

        Random rand = new Random();

        // 100 is the maximum and 1 is the minimum
        int n = rand.nextInt(100) + 1;

        // Weather Probability
        int weatherP = 1 / n;

        boolean weatherUpdated = false;

        if (weatherP < 0) {
            return weatherUpdated;
        } else if (weatherP < 0.1) {
            this.weather = "Snow";
            weatherUpdated = true;
        } else if (weatherP < 0.2) {
            this.weather = "Hail";
            weatherUpdated = true;
        } else if (weatherP < 0.4) {
            this.weather = "HighRain";
            weatherUpdated = true;
        } else if (weatherP < 0.6) {
            this.weather = "LightRain";
            weatherUpdated = true;
        } else if (weatherP < 0.8) {
            this.weather = "Cloudy";
            weatherUpdated = true;
        } else if (weatherP < 1) {
            this.weather = "Sunny";
            weatherUpdated = true;
        }

        return weatherUpdated;
    }

    @Belief
    public String getWeather() {
        return this.weather;
    }

    @Belief
    public void setWeather(String weather) {
        this.weather = weather;
    }

    /* Traffic */
    public boolean updateTraffic() {
        Random rand = new Random();

        // 100 is the maximum and 1 is the minimum
        int n = rand.nextInt(100) + 1;

        // Traffic Probability
        int trafficP = 1 / n;

        boolean trafficUpdated = false;

        if (trafficP < 0) {
            return trafficUpdated;
        } else if (trafficP < 0.2) {
            this.traffic = "None";
            trafficUpdated = true;
        } else if (trafficP < 0.4) {
            this.traffic = "Light";
            trafficUpdated = true;
        } else if (trafficP < 0.6) {
            this.traffic = "Moderate";
            trafficUpdated = true;
        } else if (trafficP < 0.8) {
            this.traffic = "High";
            trafficUpdated = true;
        } else if (trafficP < 1) {
            this.traffic = "Stopped";
            trafficUpdated = true;
        }

        return trafficUpdated;
    }

    /* Traffic */
    @Belief
    public String getTraffic() {
        return this.traffic;
    }

    @Belief
    public void setTraffic(String traffic) {
        this.traffic = traffic;
    }

    /* Interest Points */
    /*
    
        TODO Change to Vertex
    
    @Belief
    public boolean isInterestPoint(int xPos1, int yPos1, int xPos2, int yPos2) {
        boolean isInterestPoint = false;

        Node n1 = new Node(xPos1, yPos1);
        Node n2 = new Node(xPos2, yPos2);

        if (this.map.isInterestPoint(n1, n2) == true) {
            isInterestPoint = true;
        }

        return isInterestPoint;
    }

    @Belief
    public void setInterestPoint(int xPos1, int yPos1, int xPos2,
            int yPos2) {
        Node n1 = new Node(xPos1, yPos1);
        Node n2 = new Node(xPos2, yPos2);

        this.map.setInterestPoint(n1, n2);

    }*/

    /* ************************************************************* */


	@Belief(updaterate=1000)
	protected long time = System.currentTimeMillis();

	@AgentBody
	public void body() {
		System.out.println("Hello from world!");
		System.out.println(time);

	}

	public void setNumberOfVertexes(int num) {
		this.numberOfVertexes = num;
	}

	public void setMapVertexes(ArrayList<Vertex> m) {
		this.mapVertexes = m;
	}

	/*
	 * Returns the number of vertexes of the map.
	 * 
	 * @return Number of vertexes of the map.
	 */
	public int getNumberOfVertexes() {
		return numberOfVertexes;
	}

	/*
	 * Returns the vertexes of the map.
	 * 
	 * @return Vertexes of the map.
	 */
	public ArrayList<Vertex> getMapVertexes() {
		return mapVertexes;
	}

	/*
	 * Returns the number of interest points on the map.
	 * 
	 * @return Number of interest points on the map.
	 */
	public void getNumberOfInterestPoints() {

		// TODO
	}

	/*
	 * Returns whether or not a road between only two given neighbor vertexes as a interest point.
	 * 
	 * @return True if there is a interest point between two neighbor vertexes.
	 * point
	 */
	public boolean isInterestPoint(Vertex v1, Vertex v2) {
		boolean interestPoint = false;

		// Check if the vertexes are neighbors
		if (v1.isNeighborVertex(v2)) {
			// TODO - Check if there is an adjacency that is an interest point between the two vertexes
			// Check if there is a interest point between the nodes
		}

		return interestPoint;
	}

	/*
	 * Set two neighbor nodes as a interest point.
	 * 
	 * @return True if nodes where sucessfully added as a interest point.
	 */
	public boolean setInterestPoint(Vertex v1, Vertex v2) {

		boolean addedpoint = false;

		/*ArrayList<Node> pairOfNodes = new ArrayList<Node>();
         pairOfNodes.add(n1);
         pairOfNodes.add(n2);

         int previousSize = interestPoints.size();
         interestPoints.add(pairOfNodes);

         // If the point was added successfully
         if(interestPoints.size() == (previousSize + 1))
         {
         addedpoint = true;
         }*/
		// TODO
		return addedpoint;
	}

	/**
	 * Compute using Djikstra's algorithm
	 *
	 * @param source Path to compute
	 */
	public static void computePaths(Vertex source) {

		source.minDistance = 0.;
		PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
		vertexQueue.add(source);

		while (!vertexQueue.isEmpty()) {
			Vertex u = vertexQueue.poll();

			// Visit each edge exiting u
			for (Edge e : u.adjacencies) {
				Vertex v = e.target;
				double weight = e.weight;
				double distanceThroughU = u.minDistance + weight;
				if (distanceThroughU < v.minDistance) {
					vertexQueue.remove(v);
					v.minDistance = distanceThroughU;
					v.previous = u;
					vertexQueue.add(v);
				}
			}
		}
	}

	public static List<Vertex> getShortestPathTo(Vertex target) {
		List<Vertex> path = new ArrayList<Vertex>();
		for (Vertex vertex = target; vertex != null; vertex = vertex.previous) {
			path.add(vertex);
		}
		Collections.reverse(path);
		return path;
	}


}
