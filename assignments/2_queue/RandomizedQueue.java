import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
  private Item[] queue;
  private int size;
  private int capacity;
  // construct an empty randomized queue
  public RandomizedQueue() {
    size = 0;
    capacity = 4;
    queue = (Item[]) new Object[capacity];
  }
  // is the queue empty?
  public boolean isEmpty() {
    return size == 0;
  }
  // return the number of items on the queue
  public int size() {
    return size;
  }
  // add the item
  public void enqueue(Item item) {
    isNull(item);
    if (size == capacity) resize();
    queue[size++] = item;
  }
  // remove and return a random item
  public Item dequeue() {
    emptyToRemove();
    int i = StdRandom.uniform(size);
    Item temp = queue[i];
    queue[i] = queue[--size];
    queue[size] = null;
    resize();
    return temp;
  }
  // return (but do not remove) a random item
  public Item sample() {
    emptyToRemove();
    int i = StdRandom.uniform(size);
    return queue[i];
  }
  // return an independent iterator over items in random order
  public Iterator<Item> iterator() {
    Iterator<Item> it = new Iterator<Item>() {
      private int[] indexes = new int[size];
      private int cur = 0;
      public boolean hasNext() {
        if (cur == 0) {
          for (int i = 0; i < size; i++) {
            indexes[i] = i;
          }
          StdRandom.shuffle(indexes);
        }
        return cur < size;
      }
      public Item next() {
        if (!hasNext()) throw new NoSuchElementException();
        return queue[indexes[cur++]];
      }
      public void remove() {
        throw new UnsupportedOperationException();
      }
    };
    return it;
  }
  private void emptyToRemove() {
    if (size == 0) {
      throw new NoSuchElementException();
    }
  }
  private void isNull(Item item) {
    if (item == null) {
      throw new NullPointerException();
    }
  }
  // resize the queue
  private void resize() {
    if (size == capacity) {
      capacity *= 2;
    } else if (size < (capacity / 4)) {
      capacity /= 4;
    } else {
      return;
    }
    Item[] newQueue = (Item[]) new Object[capacity];
    for (int i = 0; i < size; i++) {
      newQueue[i] = queue[i];
    }
    queue = null;
    queue = newQueue;
  }
  // unit testing
  public static void main(String[] args) {
    RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
    rq.enqueue(12);
    rq.enqueue(32);
    rq.enqueue(1);
    rq.enqueue(123);
    rq.enqueue(2);
    rq.enqueue(132);
    rq.enqueue(412);
    rq.enqueue(392);
    rq.enqueue(372);
    rq.enqueue(342);
    rq.enqueue(312);
    rq.enqueue(302);
    for (int i : rq) {
      System.out.println(i);
    }
    Iterator<Integer> it = rq.iterator();
    // it.remove();
  }
}
