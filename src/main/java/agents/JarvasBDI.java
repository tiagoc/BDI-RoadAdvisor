package agents;

import jadex.bdiv3.BDIAgent;
import jadex.bdiv3.annotation.Belief;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.PlanAborted;
import jadex.bdiv3.annotation.PlanBody;
import jadex.bdiv3.annotation.PlanFailed;
import jadex.bdiv3.annotation.PlanPassed;
import jadex.bdiv3.annotation.Trigger;
import jadex.bridge.service.RequiredServiceInfo;
import jadex.bridge.service.annotation.Service;
import jadex.bridge.service.search.SServiceProvider;
import jadex.commons.future.IntermediateDefaultResultListener;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;
import jadex.micro.annotation.Description;
import jadex.micro.annotation.ProvidedService;
import jadex.micro.annotation.ProvidedServices;
import agents.DriverBDI.FastestRoute;
import agents.DriverBDI.LessKilometersTraveled;
import agents.DriverBDI.MostInterestPointsBetweenNodes;
import agents.DriverBDI.VisitMostCities;



/**
 * The Jarvas agent is our main agent and serves as a trip counselor
 */
@Service
@Agent
@Description("The advisor agent")
@ProvidedServices(@ProvidedService(type=ChatService.class))
public class JarvasBDI implements ChatService{

	private String timePeriod;
    private long currentTime;
    private String weather;
    private String traffic;

    @Agent
    protected BDIAgent agent;

    /**
     * Body of the agent. Called when the agent is started.
     */
    @AgentBody
    public void body() {
        System.out.println("Jarvas is running.");
       // agent.adoptPlan("findFastestPathPlan");
        sendMessage("KILL");

    }  
    
    
    /* *************************** */
    /*           Beliefs           */
    /* *************************** */
    
    @Belief(updaterate=1000)
	protected long time = System.currentTimeMillis();

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

    @Belief
    public String getWeather() {
        return this.weather;
    }

    @Belief
    public void setWeather(String weather) {
        this.weather = weather;
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

    /* ************************************************************* */
    
    
    
    /* *************************** */
    /*            Plans            */
    /* *************************** */
    
    @Plan(trigger=@Trigger(goals=FastestRoute.class))
    public class findFastestPathPlan {

        @PlanBody
        public void FindFastestPathPlanBody() {      
            System.out.println("Testing fastestpath");
        }

        @PlanPassed
        public void passed() {
            System.out.println("Plan finished successfully.");
        }

        @PlanAborted
        public void aborted() {
            System.out.println("Plan aborted.");
        }

        @PlanFailed
        public void failed(Exception e) {
            System.out.println("Plan failed: " + e);
        }
    }

    @Plan(trigger=@Trigger(goals=VisitMostCities.class))
    public class findPathWithMostCitiesPlan {

        @PlanBody
        public void findPathWithMostCitiesPlanBody() {

        }

        @PlanPassed
        public void passed() {
            System.out.println("Plan finished successfully.");
        }

        @PlanAborted
        public void aborted() {
            System.out.println("Plan aborted.");
        }

        @PlanFailed
        public void failed(Exception e) {
            System.out.println("Plan failed: " + e);
        }
    }

    @Plan(trigger=@Trigger(goals=MostInterestPointsBetweenNodes.class))
    public class findPathWithMostInterestPointsPlan {

        @PlanBody
        public void findPathWithMostInterestPointsPlanBody() {

        }

        @PlanPassed
        public void passed() {
            System.out.println("Plan finished successfully.");
        }

        @PlanAborted
        public void aborted() {
            System.out.println("Plan aborted.");
        }

        @PlanFailed
        public void failed(Exception e) {
            System.out.println("Plan failed: " + e);
        }
    }

    @Plan(trigger=@Trigger(goals=LessKilometersTraveled.class))
    public class findShortestPathPlan {

        @PlanBody
        public void findShortestPathPlanBody() {

        }

        @PlanPassed
        public void passed() {
            System.out.println("Plan finished successfully.");
        }

        @PlanAborted
        public void aborted() {
            System.out.println("Plan aborted.");
        }

        @PlanFailed
        public void failed(Exception e) {
            System.out.println("Plan failed: " + e);
        }
    }
    
    /* ************************************************************* */
	
	
	/* ************************************************************* */
	/* *                         Messaging                           */
	/* ************************************************************* */

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
			
		}else
		if(!s0.equals(agent.getComponentIdentifier().getLocalName()))
		{
			if(checkMsgDest(s1))
			{
				//agent.dispatchTopLevelGoal(new AchieveGoal(s1)).get();
				
			}
		}
	}

	public boolean checkMsgDest(String m)
	{
		String[] ms=m.split("-T-");
		if(ms[0].equals(agent.getComponentIdentifier().getLocalName()))
			return true;
		else
			return false;
	}
    
    
}