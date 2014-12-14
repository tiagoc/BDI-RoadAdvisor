package agents;

import jadex.bdiv3.annotation.Goal;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;
import jadex.micro.annotation.Description;



/**
 * The Driver agent
 */
@Agent
@Description("The driver agent")
public class DriverBDI {


	/**
	 * Body of the agent. Called when the agent is started.
	 */
	@AgentBody
	public void body() {
		System.out.println("Driver is running.");

	}


	/* *************************** */
	/*            Goals            */
	/* *************************** */

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
	public class LessKilometersTraveled {

		public LessKilometersTraveled() {
		}

	}

	/* ************************************************************* */

}
