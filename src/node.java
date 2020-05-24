import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
//jlai11



//pretty basic node class - nothing too cool here
public class node implements Comparable<node> {
   public  String interssection;
   public  Double Longtitude;
   public  Double latitude;

    public node getPrevius() {
        return previus;
    }
    @Override
    public int compareTo(node v) {
        if(this.distance < v.distance) return -1;
        if(this.distance == v.distance) return 0;
        else return 1;
    }
    public void setPrevius(node previus) {
        this.previus = previus;
    }
    public edge getBackEdge(){
        for(edge e: this.nodeedge){
            if(e.start == this.previous || e.end == this.previous){
                return e;
            }
        }
        throw new RuntimeException("Vertex was not contained in edge");
    }

    public  node previus   = null;
    public String previous;
    public boolean visited =false;
   public List<edge> nodeedge = new ArrayList<edge>();
    private boolean highlighted;
    public double distance = Integer.MAX_VALUE;

    public node(String interssection, Double Longtitude, Double latitude) {
        this.interssection = interssection;
        this.latitude = latitude;
        this.Longtitude = Longtitude;
    }
    public node(String interssection) {
        this.interssection = interssection;

    }

    public boolean isHighlighted() {
        return highlighted;
    }

    public void setHighlighted(boolean highlighted) {
        this.highlighted = highlighted;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public List<edge> getNodeedge() {
        return nodeedge;
    }

    public void setNodeedge(List<edge> lol) {
        this.nodeedge = lol;
    }

    public String getInterssection() {
        return interssection;
    }

    public void setInterssection(String interssection) {
        this.interssection = interssection;
    }



    public Double getLongtitude() {
        return Longtitude;
    }

    public void setLongtitude(Double longtitude) {
        Longtitude = longtitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }


}
