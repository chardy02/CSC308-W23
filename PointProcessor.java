import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class PointProcessor extends Observable {
    /**
     * @Field
     */
    private static PointProcessor p;
    /**
     * @Field
     */
    private ArrayList<Observer> observers = new ArrayList<Observer>();
    /**
     * @Field
     */
    private ArrayList<Point> pts;
    /**
     * @Field
     */
    private ArrayList<Point>[] clusters = new ArrayList[2];
    /**
     * @Field
     */
    private ArrayList<Point> lineData;
    /**
     * @Field
     */
    private boolean cluster = false;
    /**
     * @Field
     */
    private boolean lines = false;

    /**
     * Constructor for our PointProcessor
     */
    private PointProcessor() {
        pts = new ArrayList<Point>();
    }

    /**
     * Method to get a reference to the global/static PointProcessor object
     * @return - reference to the object
     */
    public static PointProcessor getRef() {
        if(p == null) {
            p = new PointProcessor();
        }
        return p;
    }

    /**
     * Method to get data of all Points
     * @return - Array list with all Points
     */
    public ArrayList<Point> getPts() {
        return pts;
    }

    /**
     * Method to add a Point to our data
     * @param pt - Point to be added
     */
    public void add(Point pt) {
        pts.add(pt);
    }

    /**
     * Method called by Main class to indicate that a button has been pressed and a state needs
     * to be changed or the algorithms need to be run
     * @param stateChange - String containing which button was pressed/which mode to switch
     */
    public void updateState(String stateChange) {
        switch(stateChange) {
            case "Cluster - K-means":
                cluster = !cluster;
                break;
            case "Lines - Nearest Neighbor":
                lines = !lines;
                break;
            case "Run":
                if(cluster) {
                    if(pts.size() == 0) {
                        System.out.println("No Points to process!");
                    }
                    else {
                        Clustering cluster = new Clustering(pts);
                        clusters = cluster.getClusters();
                        notifyObservers(new PointInfo("Cluster", clusters));
                    }
                }
                if(lines) {
                    Lines lineSet = new Lines(pts);
                    lineData = lineSet.getLines();
                    ArrayList<Point>[] linesToDraw = new ArrayList[2];
                    linesToDraw[0] = lineData;
                    notifyObservers(new PointInfo("Lines", linesToDraw));
                }
                break;
        }
    }

    /**
     * Method to add and Observer to this Observable
     * @param o - Observer to be added
     */
    public void addObserver(Observer o) {
        observers.add(o);
    }

    /**
     * Overridden method to notify all observers that there has been an update in information
     * @param arg - The information wished to be sent to the observer
     */
    public void notifyObservers(Object arg) {
        for(Observer o : observers) {
            o.update(this, arg);
        }
    }
}
