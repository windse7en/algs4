import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
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
    // check the point visited or not
    Map<Point, HashSet<Double>> slopeMap = new HashMap<Point, HashSet<Double>>();
    Map<Double, ArrayList<Integer>> hash;
    for (int i = 0; i < pointsArr.length - 3; i++) {
      hash = new HashMap<Double, ArrayList<Integer>>();
      Point cur = pointsArr[i];
      for (int j = i + 1; j < pointsArr.length; j++) {
        Point p2 = pointsArr[j];
        double slope = cur.slopeTo(p2);
        if (slopeMap.containsKey(cur) && slopeMap.get(cur).contains(slope)) continue;
        if (!hash.containsKey(slope)) {
          ArrayList<Integer> arr = new ArrayList<Integer>();
          arr.add(j);
          hash.put(slope, arr);
        } else {
          ArrayList<Integer> arr = hash.get(slope);
          arr.add(j);
          hash.put(slope, arr);
        }
      }
      // check all related points
      for (double slope : hash.keySet()) {
        ArrayList<Integer> arr = hash.get(slope);
        if (arr.size() < 3) continue;
        list.add(new LineSegment(cur, pointsArr[arr.get(arr.size() - 1)]));
        for (int index : arr) {
          Point cp = pointsArr[index];
          if (slopeMap.containsKey(cp)) {
            // StdOut.println(cp);
            slopeMap.get(cp).add(slope);
          } else {
            HashSet<Double> set = new HashSet<Double>();
            set.add(slope);
            slopeMap.put(cp, set);
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
    FastCollinearPoints collinear = new FastCollinearPoints(points);
    for (LineSegment segment : collinear.segments()) {
      StdOut.println(segment);
      segment.draw();
    }
    StdDraw.show();
  }
}
