package utilities;


import java.util.ArrayList;

public class InterestPoints {
	
    
	private ArrayList<ArrayList<Node>> interestPoints;

	public InterestPoints() {
		interestPoints = null;
	}
	
	
	public void setInterestPoints(ArrayList<ArrayList<Node>> iP)
	{
		this.interestPoints = iP;
	}


	/*
	 * Returns the number of interest points on the map.
	 * 
	 * @return Number of interest points on the map.
	 */
	public int numberOfInterestPoints() {
		return interestPoints.size();
	}
	

	/*
	 * Returns whether or not a road between only two given neighbor nodes as a interest point.
	 * 
	 * @return True if there is a interest point between two neighbor nodes.
	 * point
	 */
	public boolean isInterestPoint(Node n1, Node n2) {
		boolean interestPoint = false;

		// Check if the nodes are neighbor nodes
		if(n1.isNeighbourgNode(n2))
		{
                    // Check if there is a sightseen point between the nodes
                    for (ArrayList<Node> pairOfNodes : interestPoints) {
                        Node nodePair1 = pairOfNodes.get(0);
                        Node nodePair2 = pairOfNodes.get(1);
                        
                        if(((nodePair1.isTheSameAs(n1)) && (nodePair2.isTheSameAs(n2))) || ((nodePair1.isTheSameAs(n2)) && (nodePair2.isTheSameAs(n1))))
                        {
                            interestPoint = true;
                        }
                    }
		}
		
		return interestPoint;
	}

	/*
	 * Set two neighbourg nodes as a interest point.
	 * 
	 * @return True if nodes where sucessfully added as a interest point.
	 */
	public boolean setInterestPoint(Node n1, Node n2) {
		
		boolean addedpoint = false;

		ArrayList<Node> pairOfNodes = new ArrayList<Node>();
		pairOfNodes.add(n1);
		pairOfNodes.add(n2);

		int previousSize = interestPoints.size();
		interestPoints.add(pairOfNodes);

		// If the point was added successfully
		if(interestPoints.size() == (previousSize + 1))
		{
			addedpoint = true;
		}
		
		return addedpoint;
	}

}
