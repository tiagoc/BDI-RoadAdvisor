import java.util.Vector;

public class WorldMap {
	
	private int numberOfNodes;
	private Vector<Vector<Node>> sightseeingLocations;
	private Vector<Node> mapNodes;

	public WorldMap() {

		numberOfNodes = 0;
		// TODO - Create nodes and neighbor nodes of each node and set sightseeing locations
		
	}
	
	/*
	 * 
	 */
	public void addNodeToMap(Node newNode)
	{
		mapNodes.add(newNode);
		numberOfNodes++;
	}

	/*
	 * Returns the number of nodes of the map.
	 * 
	 * @return Number of nodes of the map.
	 */
	public int numberOfNodes() {
		return numberOfNodes;
	}
	
	/*
	 * Returns the number of sightseeing locations on the map.
	 * 
	 * @return Number of sightseeing locations on the map.
	 */
	public int numberOfSightseeingLocations() {
		return sightseeingLocations.size();
	}
	

	/*
	 * Returns whether or not a road between only two given neighbor nodes as a sightseeing location.
	 * 
	 * @return True if there is a sightseeing location between two neighbor nodes.
	 * location
	 */
	public boolean isSightseeingLocation(Node n1, Node n2) {
		boolean sightseeingLocation = false;

		// Check if the nodes are neighbor nodes
		if(n1.isNeighbourgNode(n2))
		{
			// Check if there is a sightseen location between the nodes
			for(int i = 0; i < sightseeingLocations.size(); i++)
			{
				Vector<Node> pairOfNodes = sightseeingLocations.get(i);
				
				Node nodePair1 = pairOfNodes.get(0);
				Node nodePair2 = pairOfNodes.get(1);
				
				if(((nodePair1.isTheSameAs(n1)) && (nodePair2.isTheSameAs(n2))) || ((nodePair1.isTheSameAs(n2)) && (nodePair2.isTheSameAs(n1))))
				{
					sightseeingLocation = true;
				}
			}
		}
		
		return sightseeingLocation;
	}

	/*
	 * Set two neighbourg nodes as a sightseeing location.
	 * 
	 * @return True if nodes where sucessfully added as a sightseeing location.
	 */
	public boolean setSightseeingLocation(Node n1, Node n2) {
		
		boolean addedLocation = false;

		Vector<Node> pairOfNodes = new Vector<Node>();
		pairOfNodes.add(n1);
		pairOfNodes.add(n2);

		int previousSize = sightseeingLocations.size();
		sightseeingLocations.add(pairOfNodes);

		// If the location was added successfully
		if(sightseeingLocations.size() == (previousSize + 1))
		{
			addedLocation = true;
		}
		
		return addedLocation;
	}

}
