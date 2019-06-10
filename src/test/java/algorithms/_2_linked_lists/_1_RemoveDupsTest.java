package algorithms._2_linked_lists;

import static algorithms._2_linked_lists._1_RemoveDups.removeDuplicates;
import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.integers;
import static org.quicktheories.generators.SourceDSL.lists;
import static org.quicktheories.impl.Constraint.between;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.quicktheories.core.Gen;
import org.quicktheories.core.RandomnessSource;

class _1_RemoveDupsTest {

  private static final int MAX_SOURCE_LIST_SIZE = 100;

  @Test
  void forAllListsWithDuplicates() {
    qt()
        .forAll(listsWithDuplicates())
        .check(head -> hasNoDuplicates(removeDuplicates(head)));
  }

  private boolean hasNoDuplicates(Node<Integer> listWithoutDuplicates) {
    Map<Integer, Boolean> duplicates = new HashMap<>();
    Node<Integer> current = listWithoutDuplicates;
    while (current != null) {
      if (duplicates.getOrDefault(current.data(), false)) {
        return false;
      } else {
        duplicates.put(current.data(), true);
      }
      current = current.next();
    }
    return true;
  }

  private Gen<Node<Integer>> listsWithDuplicates() {
    return lists().of(integers().allPositive()).ofSizeBetween(1, MAX_SOURCE_LIST_SIZE)
        .flatMap(source -> in -> generateLinkedListWithDuplicatesFromSource(source, in));
  }

  private Node<Integer> generateLinkedListWithDuplicatesFromSource(
      List<Integer> source, RandomnessSource in) {
    int sourceSize = source.size();
    int targetSize = sourceSize - 1;
    Node<Integer> head = new Node<>(source.get(randomElement(in, sourceSize)));
    Node<Integer> current = head;
    for (int i = 0; i < targetSize; i++) {
      Node<Integer> next = new Node<>(source.get(randomElement(in, sourceSize)));
      current.setNext(next);
      current = next;
    }
    return head;
  }

  private int randomElement(RandomnessSource in, int sourceSize) {
    return (int) in.next(between(0, sourceSize - 1));
  }
}