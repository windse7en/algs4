import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import java.util.Comparator;

public class Solver {
  private boolean solvable;
  private Node goal;

  private class Node {
    private int moves;
    private Board board;
    private Node preNode;
    public Node(Board b) {
      board = b;
      moves = 0;
      preNode = null;
    }
    public Node(Board b, int m, Node pre) {
      board = b;
      moves = m;
      preNode = pre;
    }
  }

  private class NodeComparator implements Comparator<Node> {
    @Override
    public int compare(Node x, Node y) {
      return x.board.manhattan() + x.moves - y.board.manhattan() - y.moves;
    }
  }
  // find a solution to the initial board (using the A* algorithm)
  public Solver(Board initial) {
    solvable = false;
    goal = null;
    if (initial == null) throw new NullPointerException();
    MinPQ<Node> pq = new MinPQ<Node>(new NodeComparator());
    MinPQ<Node> twinpq = new MinPQ<Node>(new NodeComparator());
    Node c1 = new Node(initial);
    Node t1 = new Node(initial.twin());
    pq.insert(c1);
    twinpq.insert(t1);
    while (!pq.isEmpty() && !twinpq.isEmpty()) {
      Node cur = pq.delMin();
      Node twincur = twinpq.delMin();
      if (cur.board.isGoal()) {
        solvable = true;
        goal = cur;
        return;
      }
      if (twincur.board.isGoal()) {
        solvable = false;
        return;
      }
      for (Board next : cur.board.neighbors()) {
        if (cur.preNode != null && cur.preNode.board.equals(next)) continue;
        pq.insert(new Node(next, cur.moves + 1, cur));
      }
      for (Board next : twincur.board.neighbors()) {
        if (twincur.preNode != null && twincur.preNode.board.equals(next)) continue;
        twinpq.insert(new Node(next, twincur.moves + 1, twincur));
      }
    }
  }
  // is the initial board solvable?
  public boolean isSolvable() {
    return solvable;
  }
  // min number of moves to solve initial board; -1 if unsolvable
  public int moves() {
    if (isSolvable()) return goal.moves;
    return -1;
  }
  // sequence of boards in a shortest solution; null if unsolvable
  public Iterable<Board> solution() {
    if (!isSolvable()) return null;
    Stack<Board> s = new Stack<Board>();
    Node cur = goal;
    while (cur != null) {
      s.push(cur.board);
      cur = cur.preNode;
    }
    return s;
  }
  // solve a slider puzzle (given below)
  public static void main(String[] args) {
    // create initial board from file
    In in = new In(args[0]);
    int n = in.readInt();
    int[][] blocks = new int[n][n];
    for (int i = 0; i < n; i++)
      for (int j = 0; j < n; j++)
        blocks[i][j] = in.readInt();
    Board initial = new Board(blocks);

    // solve the puzzle
    Solver solver = new Solver(initial);

    // print solution to standard output
    if (!solver.isSolvable())
      StdOut.println("No solution possible");
    else {
      StdOut.println("Minimum number of moves = " + solver.moves());
      for (Board board : solver.solution())
        StdOut.println(board);
    }
  }
}
