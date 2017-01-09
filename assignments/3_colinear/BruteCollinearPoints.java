import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import java.util.Arrays;
import java.util.ArrayList;

public class BruteCollinearPoints {
  private Point[] pointsArr;
  private ArrayList<LineSegment> list;
  // finds all line segments containing 4 points
  public BruteCollinearPoints(Point[] points) {
    // Null pointer check
    if (points == null) throw new NullPointerException();
    for (Point p : points)
      if (p == null) throw new NullPointerException();
    this.pointsArr = points;
    list = new ArrayList<LineSegment>();
    if (points.length < 2) return;
    Arrays.sort(this.pointsArr);
    // check if there is no repeated points
    for (int i = 1; i < pointsArr.length; i++) {
      if (pointsArr[i - 1].slopeTo(pointsArr[i]) == Double.NEGATIVE_INFINITY) {
        throw new IllegalArgumentException();
      }
    }
    generateLineSegment();
  }
  // the number of line segments
  public int numberOfSegments() {
    return list.size();
  }
  // the line segments
  public LineSegment[] segments() {
    return list.toArray(new LineSegment[list.size()]);
  }
  // generate all LineSegment
  private void generateLineSegment() {
    for (int i = 0; i < pointsArr.length - 3; i++) {
      for (int j = i + 1; j < pointsArr.length - 2; j++) {
        double slope1 = pointsArr[i].slopeTo(pointsArr[j]);
        for (int k = j + 1; k < pointsArr.length - 1; k++) {
          double slope2 = pointsArr[i].slopeTo(pointsArr[k]);
          if (slope1 != slope2) {
            continue;
          }
          for (int l = k + 1; l < pointsArr.length; l++) {
            double slope3 = pointsArr[i].slopeTo(pointsArr[l]);
            if (slope1 != slope3) {
              continue;
            }
            list.add(new LineSegment(pointsArr[i], pointsArr[l]));
          }
        }
      }
    }
  }
  public static void main(String[] args) {
    // read the n points from a file
    In in = new In(args[0]);
    int n = in.readInt();
    Point[] points = new Point[n];
    for (int i = 0; i < n; i++) {
      int x = in.readInt();
      int y = in.readInt();
      points[i] = new Point(x, y);
    }

    // draw the points
    StdDraw.enableDoubleBuffering();
    StdDraw.setXscale(0, 32768);
    StdDraw.setYscale(0, 32768);
    for (Point p : points) {
      p.draw();
    }
    StdDraw.show();

    // print and draw the line segments
    BruteCollinearPoints collinear = new BruteCollinearPoints(points);
    for (LineSegment segment : collinear.segments()) {
      StdOut.println(segment);
      segment.draw();
    }
    StdDraw.show();
  }
}
