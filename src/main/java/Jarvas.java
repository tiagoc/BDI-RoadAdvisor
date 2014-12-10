import java.util.Random;

import jadex.bdiv3.annotation.Belief;
import jadex.bdiv3.annotation.Goal;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;


/**
 * The Jarvas agent is our main agent and serves as a trip counselor
 */
@Agent
public class Jarvas {

    private WorldMap map;
    private String timePeriod;
    private long currentTime;
    private String weather;
    private String traffic;

    /**
     * @TODO remove and do proper agent body
     * Body Stub. Called when the agent is started.
     */
    @AgentBody
    public void body() {
        System.out.println("Hello world!");
    }


    // Beliefs

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
    @Belief
    public boolean isInterestPoint(int xPos1, int yPos1, int xPos2,
            int yPos2) {
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

    }


    // Goals

    @Goal
    public class FastestRoute {

        public FastestRoute() {
        }

    }

    @Goal
    public class VisitMostCities {

        public VisitMostCities() {
        }

    }

    @Goal
    public class MostInterestPointsBetweenNodes {

        public MostInterestPointsBetweenNodes() {
        }

    }

    @Goal
    public class LessKilometersTraveled
    {
      public LessKilometersTraveled()
      {
      }

    }


    @Plan
    public class findFastestPath()
    {
      public findFastestPath()
      {
      }

      @AgentBody
      public void body()
      {
        agent.adoptPlan(new findFastestPath());
      }

      @PlanPassed
      public void passed()
      {
        System.out.println("Plan finished successfully.");
      }

      @PlanAborted
      public void aborted()
      {
        System.out.println("Plan aborted.");
      }

      @PlanFailed
      public void failed(Exception e)
      {
        System.out.println("Plan failed: "+e);
      }
    }


    @Plan
    public class findPathWithMostCities()
    {
      public findPathWithMostCities()
      {
      }

      @AgentBody
      public void body()
      {
        agent.adoptPlan(new findPathWithMostCities());
      }

      @PlanPassed
      public void passed()
      {
        System.out.println("Plan finished successfully.");
      }

      @PlanAborted
      public void aborted()
      {
        System.out.println("Plan aborted.");
      }

      @PlanFailed
      public void failed(Exception e)
      {
        System.out.println("Plan failed: "+e);
      }
    }


    @Plan
    public class findPathWithMostInterestPoints()
    {
      public findPathWithMostInterestPoints()
      {
      }

      @AgentBody
      public void body()
      {
        agent.adoptPlan(new findPathWithMostInterestPoints());
      }

      @PlanPassed
      public void passed()
      {
        System.out.println("Plan finished successfully.");
      }

      @PlanAborted
      public void aborted()
      {
        System.out.println("Plan aborted.");
      }

      @PlanFailed
      public void failed(Exception e)
      {
        System.out.println("Plan failed: "+e);
      }
    }


    @Plan
    public class findShortestPath()
    {
      public findShortestPath()
      {
      }

      @AgentBody
      public void body()
      {
        agent.adoptPlan(new findShortestPath());
      }

      @PlanPassed
      public void passed()
      {
        System.out.println("Plan finished successfully.");
      }

      @PlanAborted
      public void aborted()
      {
        System.out.println("Plan aborted.");
      }

      @PlanFailed
      public void failed(Exception e)
      {
        System.out.println("Plan failed: "+e);
      }
    }

}
