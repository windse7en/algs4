import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
  private class Node<Item> {
    private Item val;
    private Node<Item> next;
    private Node<Item> pre;
    public Node(Item item) {
      val = item;
      next = null;
      pre = null;
    }
  }

  // construct an empty deque
  private Node<Item> head;
  private Node<Item> tail;
  private int size;
  public Deque() {
    head = new Node<Item>(null);
    tail = head;
    size = 0;
  }
  // is the deque empty?
  public boolean isEmpty() {
    return size == 0;
  }
  // return the number of items on the deque
  public int size() {
    return size;
  }
  // add the item to the front
  public void addFirst(Item item) throws NullPointerException {
    isNull(item);
    if (size == 0) {
      head.val = item;
      size++;
      return;
    }
    Node<Item> newOne = new Node<Item>(item);
    head.pre = newOne;
    newOne.next = head;
    head = newOne;
    size++;
  }
  // add the item to the end
  public void addLast(Item item) throws NullPointerException {
    isNull(item);
    if (size == 0) {
      head.val = item;
      size++;
      return;
    }
    Node<Item> newOne = new Node<Item>(item);
    tail.next = newOne;
    newOne.pre = tail;
    tail = newOne;
    size++;
  }
  // remove and return the item from the front
  public Item removeFirst() {
    emptyToRemove();
    Item res = head.val;
    Node<Item> nextOne = head.next;
    head = null;
    if (nextOne != null) nextOne.pre = null;
    head = nextOne;
    size--;
    return res;
  }
  // remove and return the item from the end
  public Item removeLast() {
    emptyToRemove();
    Item res = tail.val;
    Node<Item> preOne = tail.pre;
    tail = null;
    if (preOne != null) preOne.next = null;
    tail = preOne;
    size--;
    return res;
  }
  // return an iterator over items in order from front to end
  public Iterator<Item> iterator() {
    Iterator<Item> it = new Iterator<Item>() {
      private Node<Item> cur = head;

      public boolean hasNext() {
        return cur.next == null;
      }

      public Item next() throws NoSuchElementException {
        if (cur.next == null) {
          throw new NoSuchElementException();
        }
        cur = cur.next;
        return cur.val;
      }

      public void remove() {
        throw new UnsupportedOperationException();
      }
    };
    return it;
  }
  // test
  private void isNull(Item item) {
    if (item == null) {
      throw new NullPointerException();
    }
  }
  private void emptyToRemove() {
    if (size == 0) {
      throw new NoSuchElementException();
    }
  }
  // unit testing
  public static void main(String[] args) {
    Deque<Integer> dq = new Deque<Integer>();
    dq.addFirst(123);
    dq.addLast(324);
    System.out.println(dq.removeFirst());
    System.out.println(dq.removeLast());
  //  System.out.println(dq.removeLast());
    Iterator<Integer> it = dq.iterator();
    //  it.remove();
    //  it.next();
  }
}
