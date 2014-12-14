package utilities;



/**
 * Weighted Edge
 */
public class Edge {

    public final Vertex target;
    public final double weight;
    protected Boolean isInterestPoint;
    protected int WeatherState; // 0-5 Lower is better
    protected int TrafficState; // 0-5 Lower is better
    
    

    public Edge(Vertex argTarget, double argWeight, Boolean IP) {
        target = argTarget;
        weight = argWeight;
        isInterestPoint = IP;
        WeatherState = 0;
        TrafficState = 0;
    }
    
    public Edge(Vertex argTarget, double argWeight) {
        target = argTarget;
        weight = argWeight;
        isInterestPoint = false;
    }
    
    public Boolean isInterestPoint(){
    	return isInterestPoint;
    }
    
    public void setWeatherState(int WS){
    	WeatherState=WS;
    }
    
    public void setTrafficState(int TS){
    	TrafficState=TS;
    }
    
    public void setInterestPoint(Boolean IP){
    	isInterestPoint=IP;
    }
}
