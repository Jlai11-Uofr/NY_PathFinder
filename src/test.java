import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.io.*;
import java.sql.Time;
import java.util.*;
import java.util.List;

//jlai11
public class test extends JComponent {
    //Static variances to change based on GUI argments as specified later.

    public static graph graph = new graph();
    public static int xsize = 2000;
    public static int ysize = 1000;


    //Variabes to all allow for scaling to the window so nys ur and monroe should take around the same amount of space
    double minx = 200;
    double maxy = 0;
    double maxx = -200;
    double miny = 100;
    //public Color [] color = {Color.BLACK,Color.RED, Color.CYAN, Color.GREEN, Color.blue};
    public static Color calor = Color.BLACK;
    public static Color linecalor = Color.CYAN;

    //COnstructor of the test class  scans the file and creates new nodes and new edges
    public test(String file) throws FileNotFoundException, IOException, NoSuchElementException {
        File fy = new File(file);
        Scanner scan = new Scanner(fy);
        while ((scan.hasNextLine())) {
            String type = scan.next();
            String name = scan.next();
            String num1 = scan.next();
            String num2 = scan.next();


            if (type.equalsIgnoreCase("i")) {
                double num11 = Double.parseDouble(num1);
                double num22 = Double.parseDouble(num2);


                graph.addNode(name, num11, num22);
                //finds the scaling factor as to ensure everything fis onto the map
                if (minx > num22) {
                    minx = num22;
                }
                if (maxx < num22) {
                    maxx = num22;
                }
                if (maxy < num11) {
                    maxy = num11;
                }
                if (miny > num11) {
                    miny = num11;
                }
            } else if (type.equalsIgnoreCase("r")) {
                graph.addedge(name, num1, num2);
            }

        }
    }

    //main class that determines if we should show the map or not
    public static void main(String[] args) throws FileNotFoundException, IOException,InterruptedException {


        String fileName = args[0];
        test test1 = new test(fileName);
        //Worthless method that dosent actually create a nodemap  test1.graph.addtheedges();


        //method to create a graph object with edgesand nodes
        test1.graph.addstuff();
        System.out.println(graph.haversign("i62", "i71"));
        if (Arrays.asList(args).contains("show")) {
            if (Arrays.asList(args).contains("directions")) {
                String from = args[3];
                String to = args[4];
                test1.graph.dijkstra(from,to);
                System.out.println("hi");

               List<String> temp = test1.graph.getShortestPath(from, to);
                if (temp.size() == 0) {
                    System.out.println("No path between " + from + " and " + to + ".");
                } else {
                    System.out.println("Shortest path: " + temp);
                    System.out.println("Total distance: " + test1.graph.Nodes.get(to).getDistance() + " miles.");


               }
            }

        } else if (Arrays.asList(args).contains("directions")) {
            String from = args[2];
            String to = args[3];

            //finds shortest path using djisktras
            List<String> temp = test1.graph.getShortestPath(from, to);
            if (temp.size() == 0) {
                System.out.println("No path between " + from + " and " + to + ".");
            } else {
                System.out.println("Shortest path: " + temp);
                System.out.println("Total distance: " + test1.graph.Nodes.get(to).getDistance() + " miles.");

            }
        }

        //draws the picture
        JFrame frame = new JFrame("test");
        frame.setSize(test.xsize, test.ysize);

        frame.add(test1);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }


    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        boolean text = false;
//if it is highlighted it means it is nt he path to the shrtest path
        for (edge e : graph.edges) {
            text = false;
            if ((graph.Nodes.get(e.getStart()).isHighlighted()) && (graph.Nodes.get(e.getEnd()).isHighlighted())) {
                g2.setColor(Color.CYAN);
                g2.setStroke(new BasicStroke((float) 3.0,
                        BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER,
                        10.0f));
                //text = true;
            } else {
                g2.setColor(calor);
                g2.setStroke(new BasicStroke((float) 1.2,
                        BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER,
                        10.0f));
            }


            //scaling factor
            double x1 = ((xsize / 3.22580645) / (maxx - minx)) * ((graph.Nodemap.get(e.getStart())[0]) - minx) + xsize / 6.66666667;
            double x2 = ((xsize / 3.22580645) / (maxx - minx)) * ((graph.Nodemap.get(e.getEnd())[0]) - minx) + xsize / 6.66666667;
            double y1 = (-ysize / 1.38888889 / (maxy - miny)) * ((graph.Nodemap.get(e.getStart())[1]) - maxy) + ysize / 50;
            double y2 = (-ysize / 1.38888889 / (maxy - miny)) * ((graph.Nodemap.get(e.getEnd())[1]) - maxy) + ysize / 50;
            Line2D line = new Line2D.Double(x1, y1, x2, y2);

            g2.draw(line);
            if ((graph.Nodes.get(e.getStart()).isHighlighted()) && (graph.Nodes.get(e.getEnd()).isHighlighted())) {
                g2.drawString(graph.Nodes.get(e.getStart()).getInterssection(), (int) x1, (int) y1);
                g2.drawString(graph.Nodes.get(e.getEnd()).getInterssection(), (int) x2, (int) y2);
            }
            if (graph.Nodes.get(e.getStart()).visited) {
                g2.setColor(Color.ORANGE);
                g2.drawOval((int) x1, (int) y1, 5, 5);
            }


        }

    }


}
