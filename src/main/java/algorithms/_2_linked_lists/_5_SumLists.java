package algorithms._2_linked_lists;

import java.util.Optional;

public class _5_SumLists {

  public static Node<Integer> sum(Node<Integer> a, Node<Integer> b) {
    Node<Integer> currentA = a;
    Node<Integer> currentB = b;
    Node<Integer> result = null;
    Node<Integer> currentResult = null;
    int move = 0;
    while (!(currentA == null && currentB == null && move == 0)) {
      Integer currentAValue = dataOrZero(currentA);
      Integer currentBValue = dataOrZero(currentB);
      if (result == null) {
        int value = currentAValue + currentBValue + move;
        result = new Node<>(value % 10);
        currentResult = result;
        move = move(value);
      } else {
        int value = currentAValue + currentBValue + move;
        currentResult.setNext(new Node<>(value % 10));
        currentResult = currentResult.next();
        move = move(value);
      }
      currentA = Optional.ofNullable(currentA).map(Node::next).orElse(null);
      currentB = Optional.ofNullable(currentB).map(Node::next).orElse(null);
    }
    return result;
  }

  private static Integer dataOrZero(final Node<Integer> currentA) {
    return Optional.ofNullable(currentA).map(Node::data).orElse(0);
  }

  private static int move(final int value) {
    int move;
    if (value - 9 > 0) {
      move = 1;
    } else {
      move = 0;
    }
    return move;
  }
}
