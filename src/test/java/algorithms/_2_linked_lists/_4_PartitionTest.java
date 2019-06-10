package algorithms._2_linked_lists;

import static algorithms._2_linked_lists._4_Partition.partition;
import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.integers;
import static org.quicktheories.generators.SourceDSL.lists;

import java.util.List;
import org.junit.jupiter.api.Test;

class _4_PartitionTest {

  @Test
  void forAllListsAndNumbers() {
    qt()
        .forAll(
            lists().of(integers().all()).ofSizeBetween(0, 1000),
            integers().all()
        ).check((list, value) -> {
      Node<Integer> partitioned = partition(toLinked(list), value);
      int partitionedSize = 0;
      while (partitioned != null && partitioned.data() < value) {
        partitioned = partitioned.next();
        partitionedSize++;
      }
      while (partitioned != null) {
        if (partitioned.data() < value) {
          return false;
        } else {
          partitioned = partitioned.next();
        }
        partitionedSize++;
      }
      return partitionedSize == list.size();
    });
  }

  private Node<Integer> toLinked(List<Integer> list) {
    if (list.isEmpty()) {
      return null;
    }
    Node<Integer> head = new Node<>(list.get(0));
    Node<Integer> current = head;
    int size = list.size();
    for (int i = 1; i < size; i++) {
      current.setNext(new Node<>(list.get(i)));
      current = current.next();
    }
    return head;
  }
}