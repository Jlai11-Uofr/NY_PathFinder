import java.io.File;
import java.util.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
//jlai11
//Cr
public class graph {
    //Creates all the neccessary objects to store the nodes and the edge
    Map<String, double[]> Nodemap;
    static Map<String, node> Nodes;
    ArrayList<edge> edges;
    int edgesize;

    public graph() {
        //constructor
        Nodemap = new HashMap<String, double[]>();
        Nodes = new HashMap<String, node>();
        edges = new ArrayList<edge>();
        edgesize = 0;
    }

    public void addNode(String name, double Latitude, double Longitude) {
        Nodemap.put(name, new double[]{Longitude, Latitude});
    }
    //Calculates distanec between each node based on the latitude and logitude stored in the doube array
    public double haversign(String node1, String node2) {
        final int R = 6371;
        double result;

        double lon1 = Nodemap.get(node1)[0];
        double lon2 = Nodemap.get(node2)[0];
        double lat1 = Nodemap.get(node1)[1];
        double lat2 = Nodemap.get(node2)[1];
        Double latDistance = Math.toRadians(lat2 - lat1);
        Double lonDistance = Math.toRadians(lon2 - lon1);
        Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        Double distance = R * c;
        return distance*0.621371;
    }
    //new edge
    public void addedge(String ID, String from, String to) {
        edges.add(new edge(ID, from, to, this.haversign(from, to)));
        edgesize++;
    }
    //useless method that im not sure why it doent work
    public void addtheedges() {
        for (Map.Entry<String, node> entry : Nodes.entrySet()) {
            int number = 0;
            String node = entry.getKey();

            for (edge lol : edges) {
                if (lol.getStart().contains(node) || lol.getEnd().contains(node)) {
                    Nodes.get(node).nodeedge.add(lol);
                    ++number;
                    //System.out.println(lol.weight);
                }
            }

        }
    }
    //method to add edges to each node
    public void addstuff() {
        edge[] edges = new edge[this.edges.size()];
        for (int temp = 0; temp < edges.length; temp++) {
            edges[temp] = this.edges.get(temp);
        }

        for (String name : Nodemap.keySet()) {
            Nodes.put(name, new node(name));
        }
        this.edgesize = edges.length;
        for (int j = 0; j < edgesize; j++) {
            Nodes.get(edges[j].getStart()).getNodeedge().add(edges[j]);
            Nodes.get(edges[j].getEnd()).getNodeedge().add(edges[j]);
        }
    }

    //Shortest distance from a given node to all other nodes
    public void dijkstra(String start, String end) {//Starting with any node
        String next = start;
        Nodes.get(next).setDistance(0);
        for (String name : Nodes.keySet()) {//Iterate over all nodes
            List<edge> temp = new ArrayList<edge>();
            if (Nodes.get(end).isVisited()) {
                break;
                //If node is elimated persay ,we can stop
            }
            if (next.equals("")) {
                break;
            }
            temp = Nodes.get(next).getNodeedge();
            for (int j = 0; j < temp.size(); j++) {//Iterate over all edges of this node
                String neighbor = temp.get(j).getEdgeNeighbor(next);
                if (!Nodes.get(neighbor).isVisited()) {
                    double dist = Nodes.get(next).getDistance() + temp.get(j).getWeight();
                    if (dist < Nodes.get(neighbor).getDistance()) {
                        Nodes.get(neighbor).setDistance(dist);
                        Nodes.get(neighbor).setPrevious(next);
                    }
                }
            }
            Nodes.get(next).setVisited(true);
            String storage = this.getcnext();
            next = storage;
        }


    }

    //gets the nearest
    public String getcnext() {
        String storage = "";
        double storage2 = Integer.MAX_VALUE;
        for (String key : Nodes.keySet()) {
            double temp = Nodes.get(key).getDistance();
            if (!(Nodes.get(key).isVisited()) && (temp < storage2)) {
                storage2 = temp;
                storage = key;
            }
        }
        return storage;
    }

    public List<String> getShortestPath(String a, String b) {
        List<String> path = new ArrayList<String>();
        this.dijkstra(a, b);
        if (a.equals(b)) {
            path.add(a);
            return path;
        } else if (Nodes.get(b).getDistance() == Integer.MAX_VALUE) {
            return path;
        } else {
            Stack<String> stack = new Stack<String>();
            String temp;
            temp = b;
            stack.push(b);
            Nodes.get(b).setHighlighted(true);
            while (!(Nodes.get(temp).getPrevious().equals(a))) {
                temp = Nodes.get(temp).getPrevious();
                Nodes.get(temp).setHighlighted(true);
                stack.push(temp);
            }
            stack.push(a);
            Nodes.get(a).setHighlighted(true);
            while (!stack.isEmpty()) {
                path.add(stack.pop());
            }
            return path;
        }
    }
}

