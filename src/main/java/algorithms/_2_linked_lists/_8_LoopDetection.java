package algorithms._2_linked_lists;

import java.util.HashSet;
import java.util.Set;

public class _8_LoopDetection {

  public static <T> Node<T> detectLoop(Node<T> head) {
    Node<T> fast = head;
    Node<T> slow = head;
    while (fast != null && fast.next() != null) {
      fast = fast.next().next();
      slow = slow.next();
      if (fast == slow) {
        break;
      }
    }
    fast = head;
    while (fast != null || slow != null) {
      if (fast == slow) {
        break;
      }
      fast = fast.next();
      slow = slow.next();
    }
    return fast;
  }

  public static <T> Node<T> detectLoopWithSet(Node<T> head) {
    Set<Node<T>> nodes = new HashSet<>();
    Node<T> current = head;
    while (current != null) {
      if (nodes.contains(current)) {
        return current;
      }
      nodes.add(current);
      current = current.next();
    }
    return null;
  }
}
