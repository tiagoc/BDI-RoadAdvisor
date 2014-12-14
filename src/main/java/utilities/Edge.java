package utilities;



/**
 * Weighted Edge
 */
public class Edge {

    public final Vertex target;
    public final double weight;
    protected Boolean isInterestPoint;
    protected int weatherState; // 0-6 Lower is better
    protected int trafficState; // 0-4 Lower is better

    public Edge(Vertex argTarget, double argWeight, Boolean ip) {
        target = argTarget;
        weight = argWeight;
        isInterestPoint = ip;
        weatherState = 0;
        trafficState = 0;
    }
    
    public Edge(Vertex argTarget, double argWeight) {
        target = argTarget;
        weight = argWeight;
        isInterestPoint = false;
    }
    
    public Boolean isInterestPoint(){
    	return isInterestPoint;
    }
    
    public void setInterestPoint(Boolean ip){
    	isInterestPoint = ip;
    }
    
    public void setWeatherState(int state){
    	weatherState = state;
    }
    
    public void setTrafficState(int state){
    	trafficState = state;
    }
    
}
