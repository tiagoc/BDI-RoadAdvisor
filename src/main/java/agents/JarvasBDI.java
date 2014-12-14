package agents;

import jadex.bdiv3.BDIAgent;

import java.util.Random;

import agents.DriverBDI.FastestRoute;
import agents.DriverBDI.LessKilometersTraveled;
import agents.DriverBDI.MostInterestPointsBetweenNodes;
import agents.DriverBDI.VisitMostCities;
import jadex.bdiv3.annotation.Belief;
import jadex.bdiv3.annotation.Goal;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.PlanAborted;
import jadex.bdiv3.annotation.PlanBody;
import jadex.bdiv3.annotation.PlanFailed;
import jadex.bdiv3.annotation.PlanPassed;
import jadex.bdiv3.annotation.Trigger;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;
import jadex.micro.annotation.Description;



/**
 * The Jarvas agent is our main agent and serves as a trip counselor
 */
@Agent
@Description("The advisor agent")
public class JarvasBDI {

    

    @Agent
    protected BDIAgent agent;

    /**
     * Body of the agent. Called when the agent is started.
     */
    @AgentBody
    public void body() {
        System.out.println("Hello world!");
       // agent.adoptPlan("findFastestPathPlan");

    }  
    
    
    
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

    
    
    
}