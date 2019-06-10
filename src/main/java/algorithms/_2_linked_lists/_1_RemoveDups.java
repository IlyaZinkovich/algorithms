package algorithms._2_linked_lists;

import java.util.HashSet;
import java.util.Set;

public class _1_RemoveDups {

  public static Node<Integer> removeDuplicates(Node<Integer> head) {
    Set<Integer> elements = new HashSet<>();
    Node<Integer> current = head;
    elements.add(current.data());
    while (current.hasNext()) {
      Node<Integer> next = current.next();
      boolean duplicate = elements.contains(next.data());
      if (duplicate) {
        current.setNext(next.next());
      } else {
        elements.add(next.data());
        current = current.next();
      }
    }
    return head;
  }
}
