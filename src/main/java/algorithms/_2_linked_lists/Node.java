package algorithms._2_linked_lists;

public class Node<T> {

  private Node<T> next;
  private T data;

  public Node(T data) {
    this.next = null;
    this.data = data;
  }

  public Node<T> next() {
    return next;
  }

  public void setNext(Node<T> next) {
    this.next = next;
  }

  public T data() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public boolean hasNext() {
    return next != null;
  }

  @Override
  public String toString() {
    return "Node{" +
        "next=" + next +
        ", data=" + data +
        '}';
  }
}
