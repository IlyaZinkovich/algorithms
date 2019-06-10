package algorithms._2_linked_lists;

public class _6_Palindrome {

  public static <T> boolean palindrome(Node<T> head) {
    Node<T> current = head;
    Node<T> left = head;
    while (current != null) {
      Node<T> next  = new Node<>(current.data());
      next.setNext(left);
      left = next;
      current = current.next();
    }
    current = head;
    while (current != null) {
      if (!left.data().equals(current.data())) {
        return false;
      }
      current = current.next();
      left = left.next();
    }
    return true;
  }
}
