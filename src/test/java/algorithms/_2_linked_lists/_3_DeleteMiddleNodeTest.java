package algorithms._2_linked_lists;

import static algorithms._2_linked_lists._3_DeleteMiddleNode.delete;
import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.integers;
import static org.quicktheories.generators.SourceDSL.lists;

import java.util.List;
import org.junit.jupiter.api.Test;

class _3_DeleteMiddleNodeTest {

  @Test
  void forAllLists() {
    qt()
        .forAll(lists().of(integers().all()).ofSizeBetween(3, 1000))
        .check(list -> {
          Node<Integer> head = new Node<>(list.get(0));
          Node<Integer> current = head;
          int size = list.size();
          Node<Integer> middle = null;
          for (int i = 1; i < size; i++) {
            Node<Integer> next = new Node<>(list.get(i));
            current.setNext(next);
            current = next;
            if (i == middleIndex(size)) {
              middle = current;
            }
          }
          delete(middle);
          list.remove(middleIndex(size));
          return listsAreEqual(list, head);
        });
  }

  private int middleIndex(final int size) {
    return Math.floorDiv(size, 2);
  }

  private boolean listsAreEqual(List<Integer> list, Node<Integer> head) {
    Node<Integer> currentModified = head;
    for (Integer element : list) {
      if (!element.equals(currentModified.data())) {
        return false;
      } else {
        currentModified = currentModified.next();
      }
    }
    return true;
  }
}