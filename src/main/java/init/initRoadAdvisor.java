package init;

import gui.WindowUI;
import jadex.base.Starter;
import jadex.bridge.IComponentIdentifier;
import jadex.bridge.IExternalAccess;
import jadex.bridge.service.RequiredServiceInfo;
import jadex.bridge.service.search.SServiceProvider;
import jadex.bridge.service.types.cms.IComponentManagementService;
import jadex.commons.future.ThreadSuspendable;

public class initRoadAdvisor {
	
	
	public static int nHelpers = 0;
	public static int nRunners = 10;

	public static boolean startJadex=false;
	
		
	private static ThreadSuspendable sus;
	private static IExternalAccess pl;
	private static IComponentManagementService cms;
	private static WindowUI gui=null;
	
	
	public static void main(String Args[])
	{
		(new Thread(new Runnable(){

			public void run() {
				gui=new WindowUI();
			}

		})).start();

		initJadex();
		start_agents();
		
		while(true)
		{
			while(startJadex==false)
			{
				if(gui!=null)
				{
					gui.getFrame().setVisible(true); 
				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
	
			start_agents();
			startJadex=false;		
		}
	}

	
	public static void initJadex()
	{
		sus = new ThreadSuspendable();

		/**
		 * The interface for accessing components from the outside.
		 */
		pl = Starter.createPlatform(new String[0]).get(sus);

		/**
		 * General interface for components that the container can execute.
		 */
		cms = SServiceProvider.getService(pl.getServiceProvider(),
				IComponentManagementService.class, RequiredServiceInfo.SCOPE_PLATFORM).get(sus);
	}
	

	public static void start_agents()
	{
		/*init world*/
		System.out.println("yo");
		String classPath1 = "../target/classes/agents/WorldBDI.class";
		@SuppressWarnings("unused")
		IComponentIdentifier hw1 = cms.createComponent(classPath1, null).getFirstResult(sus);
		System.out.println("shiet");
		
		/*init jarvas*/
		String classPath2 = "../target/classes/agents/JarvasBDI.class";
		@SuppressWarnings("unused")
		IComponentIdentifier hw2 = cms.createComponent(classPath2, null).getFirstResult(sus);
	    
		/*init driver*/
		String classPath3 = "../target/classes/agents/DriverBDI.class";
		@SuppressWarnings("unused")
		IComponentIdentifier hw3 = cms.createComponent(classPath3, null).getFirstResult(sus);
	}

}
