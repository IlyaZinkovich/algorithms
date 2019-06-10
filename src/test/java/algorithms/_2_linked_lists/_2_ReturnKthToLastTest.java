package algorithms._2_linked_lists;

import static algorithms._2_linked_lists._2_ReturnKthToLast.kthToLast;
import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.integers;
import static org.quicktheories.generators.SourceDSL.lists;
import static org.quicktheories.impl.Constraint.between;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.quicktheories.api.Tuple3;
import org.quicktheories.core.Gen;

class _2_ReturnKthToLastTest {

  private static final int MAX_SIZE = 1000;

  @Test
  void forAllLists() {
    qt()
        .forAll(linkedListHeadKAndElement())
        .check(headKAndElement ->
            headKAndElement._3.equals(kthToLast(headKAndElement._1, headKAndElement._2)));
  }

  private Gen<Tuple3<Node<Integer>, Integer, Integer>> linkedListHeadKAndElement() {
    return lists().of(integers().all()).ofSizeBetween(1, MAX_SIZE)
        .flatMap(this::toLinkedListHeadKAndElement);
  }

  private Gen<Tuple3<Node<Integer>, Integer, Integer>> toLinkedListHeadKAndElement(
      List<Integer> source) {
    Node<Integer> head = new Node<>(source.get(0));
    Node<Integer> current = head;
    int size = source.size();
    for (int i = 1; i < size; i++) {
      final Node<Integer> next = new Node<>(source.get(i));
      current.setNext(next);
      current = next;
    }
    return in -> {
      int k = (int) in.next(between(0, size - 1));
      return Tuple3.of(head, k, source.get(size - k - 1));
    };
  }
}