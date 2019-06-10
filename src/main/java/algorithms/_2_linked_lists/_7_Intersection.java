package algorithms._2_linked_lists;

public class _7_Intersection {

  public static <T> Node<T> intersect(Node<T> list1, Node<T> list2) {
    int size1 = size(list1);
    int size2 = size(list2);
    Node<T> chopList1 = list1;
    Node<T> chopList2 = list2;
    while (size1 > size2 && chopList1 != null) {
      chopList1 = chopList1.next();
      size1--;
    }
    while (size2 > size1 && chopList2 != null) {
      chopList2 = chopList2.next();
      size2--;
    }
    while (chopList1 != chopList2 && chopList1 != null) {
      chopList1 = chopList1.next();
      chopList2 = chopList2.next();
    }
    return chopList1;
  }

  private static <T> int size(final Node<T> list1) {
    int size = 0;
    Node<T> head = list1;
    while (head != null) {
      size++;
      head = head.next();
    }
    return size;
  }

  public static <T> Node<T> intersectReversing(Node<T> list1, Node<T> list2) {
    Node<Node<T>> reversed1 = reverse(list1);
    Node<Node<T>> reversed2 = reverse(list2);
    Node<T> intersection = null;
    while (reversed1 != null && reversed2 != null && reversed1.data() == reversed2.data()) {
      intersection = reversed1.data();
      reversed1 = reversed1.next();
      reversed2 = reversed2.next();
    }
    return intersection;
  }

  private static <T> Node<Node<T>> reverse(final Node<T> list) {
    if (list == null) {
      return null;
    }
    Node<Node<T>> head = new Node<>(list);
    Node<T> current = list.next();
    while (current != null) {
      Node<Node<T>> next = new Node<>(current);
      next.setNext(head);
      head = next;
      current = current.next();
    }
    return head;
  }
}
