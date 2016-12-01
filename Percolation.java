import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
// import java.io.IOException;

public class Percolation {
  private int n;
  private boolean[][] board;
  private WeightedQuickUnionUF uf;
  // create n-by-n grid, with all sites blocked
  // false is blocked
  // true is unblocked
  public Percolation(int n) {
    if (n < 1) {
      throw new IllegalArgumentException("Argument must be bigger than 0");
    }
    this.n = n;
    // the last 2 is top and bottom line
    board = new boolean[n][n];
    uf = new WeightedQuickUnionUF(n * n + 2);
    // connect top line with n * n
    for (int i = 0; i < n; i++) {
      uf.union(n * n, i);
    }
    // connect bottom line with n * n + 1;
    for (int i = 0; i < n; i++) {
      uf.union(n * n + 1, (n - 1) * n + i);
    }
  }

  // open site (row, col) if it is not open already
  public void open(int row, int col) {
    row--;
    col--;
    if (row < 0 || row >= n || col < 0 || col >= n) {
      throw new IllegalArgumentException("non-negative and less than n.");
    }
    if (board[row][col]) return;
    board[row][col] = true;
    // top
    if (row > 0 && board[row - 1][col]) {
      uf.union(row * n + col, (row - 1) * n + col);
    }
    // bottom
    if (row < n - 1 && board[row + 1][col]) {
      uf.union(row * n + col, (row + 1) * n + col);
    }
    // left
    if (col > 0 && board[row][col - 1]) {
      uf.union(row * n + col, row * n + (col - 1));
    }
    // right
    if (col < n - 1 && board[row][col + 1]) {
      uf.union(row * n + col, row * n + (col + 1));
    }
  }

  // is site (row, col) open?
  public boolean isOpen(int row, int col) {
    row--;
    col--;
    if (row < 0 || row >= n || col < 0 || col >= n) {
      throw new IllegalArgumentException("non-negative and less than n.");
    }
    return board[row][col];
  }

  // is site (row, col) full?
  // to check if it's conected to the top row
  public boolean isFull(int row, int col) {
    row--;
    col--;
    if (row < 0 || row >= n || col < 0 || col >= n) {
      throw new IllegalArgumentException("non-negative and less than n.");
    }
    if (!board[row][col]) return board[row][col];
    int index = row * n + col;
    if (uf.connected(index, n * n)) {
      return true;
    }
    return false;
  }

  // does the system percolate?
  public boolean percolates() {
    // print show();
    return uf.connected(n * n, n * n + 1);
  }

  // private void show() {
  //   for (int i = 0; i < n; i++) {
  //     StringBuilder sb = new StringBuilder();
  //     for (int j = 0; j < n; j++) {
  //       char ch = '1';
  //       if (board[i][j]) ch = '0';
  //       sb.append(ch);
  //     }
  //     System.out.println(sb.toString());
  //   }
  // }

  // test client (optional)
  public static void main(String[] args) {
    /*
    // for testing, output n grid
    int n = 3;
    Percolation pc = new Percolation(n);
    // int x = (int) (StdRandom.uniform() * n + 1);
    // int y = (int) (StdRandom.uniform() * n + 1);
    pc.open(1, 1);
    System.out.println(pc.isOpen(1, 1));
    System.out.println(pc.isFull(1, 1));
    */
    int count = 0, n = 200;
    boolean stop = false;
    Percolation pc = new Percolation(n);
    while (!stop) {
      int x = (int) (StdRandom.uniform() * n), y = (int) (StdRandom.uniform() * n);
      if (pc.isOpen(x + 1, y + 1)) continue;
      pc.open(x + 1, y + 1);
      stop = pc.percolates();
      count++;
    }
    System.out.println(count);
  }
}
