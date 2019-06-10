package algorithms._2_linked_lists;

public class _3_DeleteMiddleNode {

  public static <T> void delete(Node<T> node) {
    if (node != null && node.hasNext()) {
      Node<T> next = node.next();
      node.setData(next.data());
      node.setNext(next.next());
    }
  }
}
