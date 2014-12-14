package agents;

import jadex.micro.annotation.Agent;
import jadex.micro.annotation.Description;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import utilities.Edge;
import utilities.Vertex;



/**
 * The Worlds agents holds the world's information
 */
@Agent
@Description("The world agent")
public class WorldBDI {

    Vertex[] WorldMapGraph;
    private int numberOfVertexes;
    private ArrayList<Vertex> mapVertexes;

    public WorldBDI() {

    }

    public void setNumberOfVertexes(int num) {
        this.numberOfVertexes = num;
    }

    public void setMapVertexes(ArrayList<Vertex> m) {
        this.mapVertexes = m;
    }

    /*
     * Returns the number of vertexes of the map.
     * 
     * @return Number of vertexes of the map.
     */
    public int getNumberOfVertexes() {
        return numberOfVertexes;
    }

    /*
     * Returns the vertexes of the map.
     * 
     * @return Vertexes of the map.
     */
    public ArrayList<Vertex> getMapVertexes() {
        return mapVertexes;
    }

    /*
     * Returns the number of interest points on the map.
     * 
     * @return Number of interest points on the map.
     */
    public void getNumberOfInterestPoints() {

        // TODO
    }

    /*
     * Returns whether or not a road between only two given neighbor vertexes as a interest point.
     * 
     * @return True if there is a interest point between two neighbor vertexes.
     * point
     */
    public boolean isInterestPoint(Vertex v1, Vertex v2) {
        boolean interestPoint = false;

        // Check if the vertexes are neighbors
        if (v1.isNeighborVertex(v2)) {
					// TODO - Check if there is an adjacency that is an interest point between the two vertexes
            // Check if there is a interest point between the nodes
        }

        return interestPoint;
    }

    /*
     * Set two neighbor nodes as a interest point.
     * 
     * @return True if nodes where sucessfully added as a interest point.
     */
    public boolean setInterestPoint(Vertex v1, Vertex v2) {

        boolean addedpoint = false;

        /*ArrayList<Node> pairOfNodes = new ArrayList<Node>();
         pairOfNodes.add(n1);
         pairOfNodes.add(n2);

         int previousSize = interestPoints.size();
         interestPoints.add(pairOfNodes);

         // If the point was added successfully
         if(interestPoints.size() == (previousSize + 1))
         {
         addedpoint = true;
         }*/
		// TODO
        return addedpoint;
    }

    /**
     * Compute using Djikstra's algorithm
     *
     * @param source Path to compute
     */
    public static void computePaths(Vertex source) {

        source.minDistance = 0.;
        PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
        vertexQueue.add(source);

        while (!vertexQueue.isEmpty()) {
            Vertex u = vertexQueue.poll();

            // Visit each edge exiting u
            for (Edge e : u.adjacencies) {
                Vertex v = e.target;
                double weight = e.weight;
                double distanceThroughU = u.minDistance + weight;
                if (distanceThroughU < v.minDistance) {
                    vertexQueue.remove(v);
                    v.minDistance = distanceThroughU;
                    v.previous = u;
                    vertexQueue.add(v);
                }
            }
        }
    }

    public static List<Vertex> getShortestPathTo(Vertex target) {
        List<Vertex> path = new ArrayList<Vertex>();
        for (Vertex vertex = target; vertex != null; vertex = vertex.previous) {
            path.add(vertex);
        }
        Collections.reverse(path);
        return path;
    }

    /*
     * Testing 
     * TODO remove
     
     public static void main(String[] args) {

     Vertex v0 = new Vertex("Redvile");
     Vertex v1 = new Vertex("Blueville");
     Vertex v2 = new Vertex("Greenville");
     Vertex v3 = new Vertex("Orangeville");
     Vertex v4 = new Vertex("Purpleville");

     v0.adjacencies = new Edge[]{
     new Edge(v1, 5),
     new Edge(v2, 10),
     new Edge(v3, 8)};
     v1.adjacencies = new Edge[]{
     new Edge(v0, 5),
     new Edge(v2, 3),
     new Edge(v4, 7)};
     v2.adjacencies = new Edge[]{
     new Edge(v0, 10),
     new Edge(v1, 3)};
     v3.adjacencies = new Edge[]{
     new Edge(v0, 8),
     new Edge(v4, 2)};
     v4.adjacencies = new Edge[]{
     new Edge(v1, 7),
     new Edge(v3, 2)};

     Vertex[] vertices = {v0, v1, v2, v3, v4};
     computePaths(v0);

     for (Vertex v : vertices) {
     System.out.println("Distance to " + v + ": " + v.minDistance);
     List<Vertex> path = getShortestPathTo(v);
     System.out.println("Path: " + path);
     }
     }
     */
}
