package agents;

import jadex.bdiv3.BDIAgent;
import jadex.bridge.service.RequiredServiceInfo;
import jadex.bridge.service.annotation.Service;
import jadex.bridge.service.search.SServiceProvider;
import jadex.commons.future.IntermediateDefaultResultListener;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;
import jadex.micro.annotation.Description;
import jadex.micro.annotation.ProvidedService;
import jadex.micro.annotation.ProvidedServices;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

import utilities.Edge;
import utilities.Vertex;



/**
 * The Worlds agents holds the world's information
 */
@Service
@Agent
@Description("The world agent")
@ProvidedServices(@ProvidedService(type=ChatService.class))
public class WorldBDI implements ChatService{

	Vertex[] WorldMapGraph;
	private int numberOfVertexes;
	private ArrayList<Vertex> mapVertexes;
	
	private String timePeriod;
    private long currentTime;
    private String weather;
    private String traffic;
    
    @Agent
    protected BDIAgent agent;
    
	protected long time = System.currentTimeMillis();
	
	
	protected void print_time(){
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");

		Date resultdate = new Date(time);
		System.out.println(sdf.format(resultdate));
	}

	
	@AgentBody
	public void body() {
		System.out.println("World Agent is running");
		System.out.println("Current time is: ");
		print_time();
		sendWorldStatus();

	}
	

    /* Time Period - "Day" or "Night" */
    public String getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(String timePeriod) {
        this.timePeriod = timePeriod;
    }

    /* Current Time */
    public long getCurrentTime() {
        return currentTime;
    }

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

    public String getWeather() {
        return this.weather;
    }

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
    public String getTraffic() {
        return this.traffic;
    }

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

	
	
	/* ************************************************************* */
	
	
	/* ************************************************************* */
	/* *                         Messaging                           */
	/* ************************************************************* */
	
	
	public void sendWorldStatus(){
		sendMessage(time + "-" + weather + "-" + traffic);
	}
	
	public void sendMessage(final String messageToSend) {

		SServiceProvider.getServices(agent.getServiceProvider(), ChatService.class, RequiredServiceInfo.SCOPE_PLATFORM)
		.addResultListener(new IntermediateDefaultResultListener<ChatService>() {
			public void intermediateResultAvailable(ChatService t) {
				t.message(agent.getComponentIdentifier().getLocalName(), messageToSend);
			}
		});
	}

	public void message(String s0, String s1) {
		
		if(s1.equals("KILL"))
		{
			agent.killAgent();
			
		}/*else
		if(!s0.equals(agent.getComponentIdentifier().getLocalName()))
		{
			if(checkMsgDest(s1))
			{
				//agent.dispatchTopLevelGoal(new AchieveGoal(s1)).get();
				
			}
		}*/
	}

	public boolean checkMsgDest(String m)
	{
		String[] ms=m.split("-|-");
		if(ms[0].equals(agent.getComponentIdentifier().getLocalName()))
			return true;
		else
			return false;
	}


}
