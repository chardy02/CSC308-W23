import java.awt.*;
import java.util.ArrayList;

public class Clustering {
    /**
     * @Field panelSize - Point containing the dimensions of the panel
     */
    private Point panelSize = new Point(319, 463);
    /**
     * @Field allPts - Array list containing all the points that are to be clustered
     */
    private ArrayList<Point> allPts;
    /**
     * @Field clusters - List of Array lists containing the final
     * point information for both clusters
     */
    private ArrayList[] clusters;
    /**
     * @Field c1 - Array list containing the first cluster
     */
    private ArrayList<Point> c1 = new ArrayList<Point>();
    /**
     * @Field c2 - Array list containing the second cluster
     */
    private ArrayList<Point> c2 = new ArrayList<Point>();
    /**
     * @Field centroid1 - centroid for cluster 1
     * @Field centroid2 - centroid for cluster 2
     * @Field tmpC1 - temporary centroid for cluster 1
     * @Field tmpC2 - temporary centroid for cluster 2
     */
    private Point centroid1, centroid2, tmpC1, tmpC2;

    /**
     * Constructor for Clustering object
     * @param _allPts - All points to be clustered
     */
    public Clustering(ArrayList<Point> _allPts) {
        allPts = (ArrayList<Point>) _allPts.clone();
    }

    /**
     * Take all points and get k = 2 clusters
     * @return - Array of Array lists containing the two clusters
     */
    public ArrayList[] getClusters() {

        // Create two random centroids to start
        centroid1 = new Point((int) (Math.random() * panelSize.x),(int) (Math.random() * panelSize.y));
        centroid2 = new Point((int) (Math.random() * panelSize.x),(int) (Math.random() * panelSize.y));

        // Loop until the new centroids are the same as the old centroids
        while(true) {
            // Clear clusters
            c1.clear(); c2.clear();

            // Assign all points then step the centroids
            assign();
            step();

            // Condition to break the loop, else make temporary centroids the new centroids
            if(tmpC1.equals(centroid1) && tmpC2.equals(centroid2)) {
                break;
            }
            else {
                centroid1 = tmpC1;
                centroid2 = tmpC2;
            }
        }

        // Create array list to contain the clusters, put clusters in, return clusters
        clusters = new ArrayList[2];
        clusters[0] = c1;
        clusters[1] = c2;

        return clusters;
    }

    /**
     * Function used during clustering process...
     * Assigns each point to one of the two centroids
     */
    private void assign() {
        for(Point p : allPts) {
            double d1, d2;
            d1 = centroid1.distance(p);
            d2 = centroid2.distance(p);

            if(d1 < d2) { c1.add(p); }
            else { c2.add(p); }
        }
    }

    /**
     * Function used during clustering process...
     * Moves centroids to reflect avg of all points in each potential cluster
     */
    private void step() {
        tmpC1 = new Point(0, 0);
        tmpC2 = new Point(0, 0);

        for(Point p : c1) {
            tmpC1.x += p.x;
            tmpC1.y += p.y;
        }
        for(Point p : c2) {
            tmpC2.x += p.x;
            tmpC2.y += p.y;
        }
        if(c1.size() != 0) {
            tmpC1.x /= c1.size();
            tmpC1.y /= c1.size();
        }
        else {
            tmpC1 = centroid1;
        }
        if(c2.size() != 0) {
            tmpC2.x /= c2.size();
            tmpC2.y /= c2.size();
        }
        else {
            tmpC2 = centroid2;
        }
    }
}
