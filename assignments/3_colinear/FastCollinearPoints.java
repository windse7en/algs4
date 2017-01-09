import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import java.util.Arrays;
import java.util.ArrayList;

public class FastCollinearPoints {
  private Point[] pointsArr;
  private ArrayList<LineSegment> list;
  // finds all line segments containing 4 or more points
  public FastCollinearPoints(Point[] points) {
    if (points == null) throw new NullPointerException();
    for (Point p : points)
      if (p == null) throw new NullPointerException();
    this.pointsArr = points;
    list = new ArrayList<LineSegment>();
    if (points.length < 4) return;
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
      Arrays.sort(pointsArr);
      Arrays.sort(pointsArr, pointsArr[i].slopeOrder());
      int p = 0, first = 1, last = 2;
      while (last < pointsArr.length) {
        // find last collinear to p point
        double s1 = pointsArr[p].slopeTo(pointsArr[first]);
        while (last < pointsArr.length
          && Double.compare(s1, pointsArr[p].slopeTo(pointsArr[last])) == 0) {
          last++;
        }
        // if found at least 3 elements, make segment if it's unique
        if (last - first >= 3) {
          if (pointsArr[p].compareTo(pointsArr[first]) < 0) {
            list.add(new LineSegment(pointsArr[p], pointsArr[last - 1]));
          }
        }
        // Try to find next
        first = last;
        last++;
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
    FastCollinearPoints collinear = new FastCollinearPoints(points);
    for (LineSegment segment : collinear.segments()) {
      StdOut.println(segment);
      segment.draw();
    }
    StdDraw.show();
  }
}
