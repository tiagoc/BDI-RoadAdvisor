import java.util.Vector;

public class Node {
	private Vector<Node> neighborNodes;
	private int xPos;
	private int yPos;

	/*
	 * 
	 */
	Node(int x, int y) {
		this.xPos = x;
		this.yPos = y;
	}

	/*
	 * 
	 */
	public int getXPos() {
		return this.xPos;
	}

	/*
	 * 
	 */
	public void setXPos(int x) {
		this.xPos = x;
	}

	/*
	 * 
	 */
	public int getYPos() {
		return this.yPos;
	}

	/*
	 * 
	 */
	public void setYPos(int y) {
		this.yPos = y;
	}
	
	/*
	 * 
	 */
	public boolean isTheSameAs(Node n)
	{
		boolean isTheSame = false;
		
		if((this.xPos == n.getXPos()) && (this.yPos == n.getYPos()))
		{
			isTheSame = true;
		}
		
		return isTheSame;
	}

	/* *
	 * Check if a given node is neighbor of the node.
	 */
	public boolean isNeighbourgNode(Node n) {

		boolean isNeighbour = false;

		// Check if the given node is neighbor of the node
		for (int i = 0; i < neighborNodes.size(); i++) {
			
			Node currentNode = neighborNodes.get(i);

			// If one of the neighbor nodes has the same position on the map as the node to check
			if ((currentNode.getXPos() == n.getXPos())
			&& (currentNode.getYPos() == n.getYPos())) {
				isNeighbour = true;
				i = neighborNodes.size();
			}
		}

		return isNeighbour;
	}
	
	/*
	 * Add a given node as a neighbor of the node.
	 */
	public void addNeighbourgNode(int xPos, int yPos) {
		
		Node newNeighbor = new Node(xPos, yPos);
		
		neighborNodes.add(newNeighbor);
	}

}