import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
//jlai11

public class GUI extends JFrame implements ChangeListener, KeyListener, ActionListener {
    protected JLabel Xsize, Ysize, Color, Map;
    protected JComboBox Maptype, x, y, colormap, colorpath;
    protected JButton gogog;
    protected JRadioButton show, directions;
    protected String s1[] = {"U of R", "Monroe", " NYS"};
    protected String x1[] = {"500", "1000", "1500", "2000"};
    protected String y1[] = {"250", "500", "750", "1000"};
    protected String colors[] = {"Purple", "Blue", "Green", "Black", "Cyan"};
    protected String colorsofmap[] = {"Red", "Blue", "Green", "Black", "Cyan"};
    protected JFrame lol;
    public static boolean truth = false;
    public static boolean dtruth = false;
    public static String file;
    public static test test3;
    public static String start;
    public static String end;
    protected JTextField starts, ends;

    public GUI() {
        starts = new JTextField("Start");
        ends = new JTextField("End");

        starts.addActionListener(this);
        ends.addActionListener(this);
        show = new JRadioButton("Show");
        directions = new JRadioButton("Directions");
        setLayout(new FlowLayout());
        setTitle("GUI for control");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        x = new JComboBox(x1);
        y = new JComboBox(y1);
        colormap = new JComboBox(colors);
        colorpath = new JComboBox(colorsofmap);
        Xsize = new JLabel("Set the Width of the Map");
        Ysize = new JLabel("Set height of the Map");
        Color = new JLabel("Color of path");
        gogog = new JButton("Start");
        add(colormap);
        Map = new JLabel("Map type");
        this.add(Xsize);
        this.add(x);
        this.add(Ysize);
        this.add(y);
        this.add(Color);
        this.add(colorpath);

        this.add(Map);

        Maptype = new JComboBox(s1);
        this.add(Maptype);
        Maptype.addActionListener(this);
        x.addActionListener(this);
        y.addActionListener(this);
        colorpath.addActionListener(this);
        colormap.addActionListener(this);
        this.add(gogog);
        gogog.addActionListener(this);
        Canvas canvas = new Canvas();

        add(canvas);
        canvas.setVisible(true);
        canvas.repaint();
        show.addActionListener(this);
        directions.addActionListener(this);
        add(show);
        add(directions);
        add(starts);
        add(ends);



    pack();

}

    public class Canvas extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            g.fillOval(250, 250, 90, 90);
        }
    }


    @Override
    public void actionPerformed(ActionEvent o) {

        if (o.getSource() == gogog) {
            try {
                System.out.println(file);
                test3 = new test(file);
                test3.graph.addstuff();
                if (dtruth == true) {
                    String from = starts.getText();
                    String to = ends.getText();
                    List<String> temp = test3.graph.getShortestPath(from, to);
                    if (temp.size() == 0) {
                        System.out.println("No path between " + from + " and " + to + ".");
                    } else {
                        System.out.println("Shortest path: " + temp);
                        System.out.println("Total distance: " + test3.graph.Nodes.get(to).getDistance() + " miles.");


                    }
                }


                JFrame lol = new JFrame("YAY");
                lol.setSize(test.xsize, test.ysize);
                lol.add(test3);
                lol.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                if (truth == true) {
                    lol.setVisible(true);
                }
            } catch (FileNotFoundException e) {

            } catch (IOException p) {

            }
        }
        if (show.isSelected()) {
            truth = true;

        } else {
            truth = false;
        }
        if (directions.isSelected()) {
            dtruth = true;
        } else {
            dtruth = false;
        }

        if (o.getSource() == colormap) {
            if (colormap.getSelectedItem() == "Purple") {
                test.calor = java.awt.Color.MAGENTA;
            }


            if (colormap.getSelectedItem() == "Blue") {
                test.calor = java.awt.Color.BLUE;
                System.out.println(test.calor);
            }
            if (colormap.getSelectedItem() == "Green") {
                test.calor = java.awt.Color.GREEN;
            }
            if (colormap.getSelectedItem() == "Black") {
                test.calor = java.awt.Color.BLACK;
            }
            if (colormap.getSelectedItem() == "Cyan") {
                test.calor = java.awt.Color.CYAN;
            }


        }

        if (o.getSource() == colorpath) {
            if (colorpath.getSelectedItem() == "Red") {
                test.linecalor = java.awt.Color.RED;
            }


            if (colorpath.getSelectedItem() == "Blue") {
                test.linecalor = java.awt.Color.BLUE;
                System.out.println(test.calor);
            }
            if (colorpath.getSelectedItem() == "Green") {
                test.linecalor = java.awt.Color.GREEN;
            }
            if (colorpath.getSelectedItem() == "Black") {
                test.linecalor = java.awt.Color.BLACK;
            }
            if (colorpath.getSelectedItem() == "Cyan") {
                test.linecalor = java.awt.Color.CYAN;
            }


        }


        if (o.getSource() == Maptype) {
            if (Maptype.getSelectedItem() == "U of R") {
                System.out.println("hi");
                file = "C:\\Users\\Jonathan Lai\\Desktop\\dfdsfsf\\Project04\\CSC172 Project4\\ur.txt";
                System.out.println(file);
            }
            if (Maptype.getSelectedItem() == "Monroe") {
                file = "monroe.txt";
                System.out.println(file);
            }
            if (Maptype.getSelectedItem() == " NYS") {
                file = "nys.txt";
                System.out.println(file);
            }
        }

        if (o.getSource() == x) {
            if (x.getSelectedItem() == "500") {
                test.xsize = 500;
            }
            if (x.getSelectedItem() == "1000") {
                test.xsize = 1000;
            }
            if (x.getSelectedItem() == "1500") {
                test.xsize = 1500;
            }
            if (x.getSelectedItem() == "2000") {
                test.xsize = 2000;
            }
            System.out.println(test.xsize);
        }

        if (o.getSource() == y) {
            if (y.getSelectedItem() == "250") {
                test.ysize = 250;
            }
            if (y.getSelectedItem() == "500") {
                test.ysize = 500;
            }
            if (y.getSelectedItem() == "750") {
                test.ysize = 750;
            }
            if (y.getSelectedItem() == "1000") {
                test.ysize = 1000;
            }
            System.out.println(test.ysize);
        }
    }


    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public synchronized void addKeyListener(KeyListener l) {
        super.addKeyListener(l);
    }

    public static void main(String[] args) {

        new GUI().setVisible(true);


    }

}

