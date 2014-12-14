package utilities;

import java.util.ArrayList;


 /* Some third party code from
  * http://www.algolist.com/code/java/Dijkstra's_algorithm */



/**
 * Vertex implementation
 */
public class Vertex implements Comparable<Vertex> {

    public final int vertexID;
    private static int currentID = 0;
    public Edge[] adjacencies;
    public double minDistance = Double.POSITIVE_INFINITY;
    public Vertex previous;
    private int xPos;
	private int yPos;
	private ArrayList<Vertex> neighborVertexes;
    

    public Vertex(int x, int y) {
        vertexID = currentID;
        currentID++;
        this.xPos = x;
		this.yPos = y;
		this.neighborVertexes = new ArrayList<Vertex>();
    }

    public int compareTo(Vertex other) {
        return Double.compare(minDistance, other.minDistance);
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
	public ArrayList<Vertex> getNeighborgVertexes() {
		return this.neighborVertexes;
	}
	
	/*
	 * 
	 */
	public boolean isTheSameAs(Vertex v)
	{
		boolean isTheSame = false;
		
		if((this.xPos == v.getXPos()) && (this.yPos == v.getYPos()))
		{
			isTheSame = true;
		}
		
		return isTheSame;
	}

	/* *
	 * Check if a given vertex is neighbor of the vertex.
	 */
	public boolean isNeighborVertex(Vertex v) {

		boolean isNeighbor = false;

		// Check if the given vertex is neighbor of the vertex
		for (int i = 0; i < neighborVertexes.size(); i++) {
			
			Vertex currentVertex = neighborVertexes.get(i);

			// If one of the neighbor vertexes has the same position on the map as the vertex to check
			if ((currentVertex.getXPos() == v.getXPos())
			&& (currentVertex.getYPos() == v.getYPos())) {
				isNeighbor = true;
				i = neighborVertexes.size();
			}
		}

		return isNeighbor;
	}
	
	/*
	 * Add a given vertex as a neighbor of the vertex.
	 */
	public void addNeighborgVertex(int xPos, int yPos) {
		
		Vertex newVertex = new Vertex(xPos, yPos);
		
		neighborVertexes.add(newVertex);
	}

}