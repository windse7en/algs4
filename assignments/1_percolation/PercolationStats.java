import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {
  private int n;
  private int trials;
  private double mean;
  private double stddev;
  private double[] results;
  // perform trials independent experiments on an n-by-n grid
  public PercolationStats(int n, int trials) {
    if (trials < 1 || n < 1) {
      throw new IllegalArgumentException("Argument must be bigger than 0");
    }
    this.n = n;
    this.trials = trials;
    this.results = new double[trials];
    while (trials-- > 0) {
      results[trials] = calculateRatio();
    }
    this.mean = StdStats.mean(results);
    this.stddev = StdStats.stddev(results);
  }

  // sample mean of percolation threshold
  public double mean() {
    return mean;
  }

  // sample standard deviation of percolation threshold
  public double stddev() {
    return stddev;
  }

  // low  endpoint of 95% confidence interval
  public double confidenceLo() {
    return mean - (1.96 * stddev) / Math.sqrt(trials);
  }

  // high endpoint of 95% confidence interval
  public double confidenceHi() {
    return mean + (1.96 * stddev) / Math.sqrt(trials);
  }

  private double calculateRatio() {
    double count = 0;
    boolean stop = false;
    Percolation pc = new Percolation(n);
    while (!stop) {
      // generate cell
      int x = (int) (StdRandom.uniform() * n + 1);
      int y = (int) (StdRandom.uniform() * n + 1);
      // check if it is already open
      if (pc.isOpen(x, y)) continue;
      pc.open(x, y);
      stop = pc.percolates();
      count++;
    }
    return count / (n * n);
  }

  // test client (described below)
  public static void main(String[] args) {
    if (args.length < 2) throw new IllegalArgumentException("Need more arguments.");
    PercolationStats ps = new PercolationStats(Integer.parseInt(args[0]),
                                               Integer.parseInt(args[1]));
    StdOut.println("mean                    = " + ps.mean());
    StdOut.println("stddev                  = " + ps.stddev());
    StdOut.println("95% confidence interval = " + ps.confidenceLo() +
                   ", " + ps.confidenceHi());
  }
}
