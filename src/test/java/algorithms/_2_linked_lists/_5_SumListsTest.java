package algorithms._2_linked_lists;

import static algorithms._2_linked_lists._5_SumLists.sum;
import static java.util.stream.Collectors.joining;
import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.integers;
import static org.quicktheories.generators.SourceDSL.lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.Test;
import org.quicktheories.core.Gen;

class _5_SumListsTest {

  @Test
  void forAllLists() {
    qt()
        .forAll(numberLists(), numberLists())
        .check((a, b) ->
            toNumber(toList(sum(toLinked(a), toLinked(b)))) == toNumber(a) + toNumber(b)
        );
  }

  private Gen<List<Integer>> numberLists() {
    return lists().of(integers().between(0, 9)).ofSizeBetween(0, 9).map(this::toNumberList);
  }

  private List<Integer> toNumberList(final List<Integer> list) {
    if (list.isEmpty()) {
      return list;
    }
    while (!list.isEmpty() && list.get(list.size() - 1) == 0) {
      list.remove(list.size() - 1);
    }
    return list;
  }

  private Integer toNumber(final List<Integer> list) {
    if (list.isEmpty()) {
      return 0;
    }
    Collections.reverse(list);
    String stringNumber = list.stream().map(Objects::toString).collect(joining(""));
    return Integer.parseInt(stringNumber);
  }

  private List<Integer> toList(Node<Integer> list) {
    List<Integer> result = new ArrayList<>();
    Node<Integer> current = list;
    while (current != null) {
      result.add(current.data());
      current = current.next();
    }
    return result;
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