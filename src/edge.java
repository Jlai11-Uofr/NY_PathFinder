import java.util.LinkedList;

//jlai11
public class edge implements Comparable<edge>{
    String name;
    String end;
    String start;
    Double weight;

    //pretty basic edge class -- nothing too cool here
    public edge(String name, String start, String end, Double weight) {
        this.name = name;
        this.end = end;
        this.start = start;
        this.weight = weight;
    }
    @Override
    public int compareTo(edge e) {
        return Double.compare(this.weight, e.weight);
    }
    public String getEnd() {
        return end;
    }

    //makes sure that we get the correct node as it is undirected
    public String getEdgeNeighbor(String name) {
        if (this.start.equals(name)) {
            return this.end;
        } else {
            return this.start;
        }
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }


}
