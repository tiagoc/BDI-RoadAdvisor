package utilities;



/**
 * Weighted Edge
 */
public class Edge {

    public final Vertex target;
    public final double weight;
    public Boolean isInterestPoint;

    public Edge(Vertex argTarget, double argWeight, Boolean IP) {
        target = argTarget;
        weight = argWeight;
        isInterestPoint = IP;
    }
    
    public Edge(Vertex argTarget, double argWeight) {
        target = argTarget;
        weight = argWeight;
        isInterestPoint = false;
    }
    
    public Boolean isInterestPoint(){
    	return isInterestPoint;
    }
    
    public void setInterestPoint(Boolean IP){
    	isInterestPoint=IP;
    }
}
