import edu.princeton.cs.algs4.StdOut;
import java.util.LinkedList;

public class Board {
  private final int[][] board;
  private final int n;
  // construct a board from an n-by-n array of blocks
  // (where blocks[i][j] = block in row i, column j)
  public Board(int[][] blocks) {
    if (blocks == null) throw new NullPointerException();
    if (blocks.length == 0
        || blocks[0].length == 0
        || blocks.length != blocks[0].length) {
      throw new IllegalArgumentException();
    }
    n = blocks.length;
    board = new int[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        board[i][j] = blocks[i][j];
      }
    }
  }
  // board dimension n
  public int dimension() {
    return n;
  }
  // number of blocks out of place
  public int hamming() {
    int count = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (board[i][j] == 0) {
          continue;
        } else if (board[i][j] != i * n + j + 1) {
          count++;
        }
      }
    }
    return count;
  }
  // sum of Manhattan distances between blocks and goal
  public int manhattan() {
    int count = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (board[i][j] == 0) {
          continue;
        } else if (board[i][j] != i * n + j + 1) {
          int r = (board[i][j] - 1) / n;
          int c = (board[i][j] - 1) % n;
          count += Math.abs(r - i) + Math.abs(c - j);
        }
      }
    }
    return count;
  }
  // is this board the goal board?
  public boolean isGoal() {
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (board[i][j] == 0) {
          if (i == n - 1 && j == n - 1) continue;
          return false;
        }
        if (board[i][j] != i * n + j + 1) return false;
      }
    }
    return true;
  }
  // a board that is obtained by exchanging any pair of blocks
  public Board twin() {
    Board newBoard = null;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n - 1; j++) {
        if (board[i][j] != 0 && board[i][j + 1] != 0) {
          newBoard = exch(i, j, i, j + 1);
          return newBoard;
        }
      }
    }
    return newBoard;
  }
  // does this board equal y?
  public boolean equals(Object y) {
    if (y == this) return true;
    if (y == null || y.getClass() != this.getClass()) return false;
    String s = toString();
    String comS = ((Board) y).toString();
    return s.equals(comS);
  }
  // all neighboring boards
  public Iterable<Board> neighbors() {
    LinkedList<Board> al = new LinkedList<Board>();
    int i = 0, j = 0;
    for (int r = 0; r < n; r++) {
      for (int c = 0; c < n; c++) {
        if (board[r][c] == 0) {
          i = r;
          j = c;
          break;
        }
      }
    }
    if (i > 0) al.add(exch(i, j, i - 1, j));
    if (j > 0) al.add(exch(i, j, i, j - 1));
    if (i < n - 1) al.add(exch(i, j, i + 1, j));
    if (j < n - 1) al.add(exch(i, j, i, j + 1));
    return al;
  }
  private Board exch(int r1, int c1, int r2, int c2) {
    int temp = board[r1][c1];
    board[r1][c1] = board[r2][c2];
    board[r2][c2] = temp;
    Board newBoard = new Board(board);
    board[r2][c2] = board[r1][c1];
    board[r1][c1] = temp;
    return newBoard;
  }
  // string representation of this board (in the output format specified below)
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(n + "\n");
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        sb.append("  " + board[i][j]);
      }
      sb.append("\n");
    }
    return sb.toString();
  }
  // unit tests (not graded)
  public static void main(String[] args) {
    int[][] b = {
      {1, 2, 3},
      {4, 5, 6},
      {7, 8, 0},
    };
    int[][] b2 = {
      {1, 2, 3},
      {4, 5, 6},
      {7, 0, 8},
    };
    Board bo = new Board(b);
    Board bo2 = new Board(b2);
    StdOut.println(bo.isGoal());
    StdOut.println(bo.toString());
    StdOut.println(bo.hamming());
    for (Board boa : bo.neighbors()) {
      StdOut.println(boa);
    }
    StdOut.println(bo.manhattan());
    StdOut.println(bo.equals(bo2));
  }
}
