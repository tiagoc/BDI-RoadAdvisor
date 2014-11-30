import java.util.Vector;

import jadex.commons.future.IFuture;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;

/**
 * A simple agent to be used as a basis for own developments.
 */
@Agent
public class Jarvas {
	
	private WorldMap map;
	private String timePeriod;
	private long currentTime; 
	private String weather;
	private String traffic;
	
	/**
	 * Called when the agent is started.
	 */
	@AgentBody
	public void body() {
		System.out.println("Hello world!");
	}

	
	/* Time Period - "Day" or "Night" */

	@Belief
	public String getTimePeriod()
	{
	  return timePeriod;
	}

	@Belief
	public void setTimePeriod(String timePeriod)
	{
	  this.timePeriod = timePeriod;
	}


	/* Current Time */

	@Belief
	public long getCurrentTime()
	{
	  return currentTime;
	}

	@Belief
	public void setCurrentTime(long currentTime)
	{
	  this.currentTime = currentTime;
	}


	/* Weather */

	@Belief
	public String getWeather()
	{
		// TODO -> update weather???

	  return weather;
	}

	@Belief
	public void setWeather(String weather)
	{
	  this.weather = weather;
	}


	/* Traffic */

	@Belief
	public String getTraffic()
	{
		// TODO -> update traffic???

	  return traffic;
	}

	@Belief
	public void setTraffic(String traffic)
	{
	  this.traffic = traffic;
	}

	/* Sightseeing Locations */

	@Belief
	public boolean isSightseeingLocation(int xPos1, int yPos1, int xPos2, int yPos2)
	{
	  boolean isSightseeingLoc = false;
		
	  Node n1 = new Node(xPos1, yPos1);
	  Node n2 = new Node(xPos2, yPos2);
	  
	  if(this.map.isSightseeingLocation(n1, n2) == true)
	  {
		  isSightseeingLoc = true;
	  }

	  return isSightseeingLoc;
	}

	@Belief
	public void setSightseeingLocation(int xPos1, int yPos1, int xPos2, int yPos2)
	{
	   Node n1 = new Node(xPos1, yPos1);
	   Node n2 = new Node(xPos2, yPos2);
	  
	  this.map.setSightseeingLocation(n1, n2);
	  
	}
	
}