package agents;

import java.awt.EventQueue;

import gui.WindowUI;
import jadex.bdiv3.BDIAgent;
import jadex.bdiv3.annotation.Goal;
import jadex.bridge.service.RequiredServiceInfo;
import jadex.bridge.service.annotation.Service;
import jadex.bridge.service.search.SServiceProvider;
import jadex.commons.future.IntermediateDefaultResultListener;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;
import jadex.micro.annotation.Description;
import jadex.micro.annotation.ProvidedService;
import jadex.micro.annotation.ProvidedServices;



/**
 * The Driver agent
 */
@Service
@Agent
@Description("The driver agent")
@ProvidedServices(@ProvidedService(type=ChatService.class))
public class DriverBDI implements ChatService{

	@Agent
	BDIAgent agent;
	
	
	/**
	 * Body of the agent. Called when the agent is started.
	 */
	@AgentBody
	public void body() {
		System.out.println("Driver is running.");
		
		sendMessage("Driver is on.");
		
		// Open GUI
		EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    WindowUI window = new WindowUI();
                    window.getFrame().setVisible(true); 
                } catch (Exception e) {
                }
            }
        });
		

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
		String[] ms=m.split("-|-");
		if(ms[0].equals(agent.getComponentIdentifier().getLocalName()))
			return true;
		else
			return false;
	}

}
