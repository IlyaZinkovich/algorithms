package algorithms._2_linked_lists;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class _2_ReturnKthToLast {

  public static <T> T kthToLast(Node<T> head, int k) {
    Node<T> right = head;
    Node<T> left = head;
    int steps = k;
    while (steps > 0) {
      if (!right.hasNext()) {
        throw new IndexOutOfBoundsException();
      } else {
        right = right.next();
        steps--;
      }
    }
    while (right.hasNext()) {
      right = right.next();
      left = left.next();
    }
    return left.data();
  }
}
