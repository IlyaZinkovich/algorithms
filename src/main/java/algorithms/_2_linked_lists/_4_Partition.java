package algorithms._2_linked_lists;

public class _4_Partition {

  public static Node<Integer> partition(Node<Integer> head, Integer value) {
    Node<Integer> leftHead = null;
    Node<Integer> left = null;
    Node<Integer> rightHead = null;
    Node<Integer> right = null;
    Node<Integer> current = head;
    while (current != null) {
      if (current.data() < value) {
        if (left == null) {
          leftHead = new Node<>(current.data());
          left = leftHead;
        } else {
          left.setNext(new Node<>(current.data()));
          left = left.next();
        }
      } else {
        if (right == null) {
          rightHead = new Node<>(current.data());
          right = rightHead;
        } else {
          right.setNext(new Node<>(current.data()));
          right = right.next();
        }
      }
      current = current.next();
    }
    if (left == null) {
      return rightHead;
    } else {
      left.setNext(rightHead);
      return leftHead;
    }
  }
}
