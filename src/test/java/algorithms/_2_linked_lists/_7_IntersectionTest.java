package algorithms._2_linked_lists;

import static algorithms._2_linked_lists._7_Intersection.intersect;
import static algorithms._2_linked_lists._7_Intersection.intersectReversing;
import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.integers;

import org.junit.jupiter.api.Test;

class _7_IntersectionTest {

  @Test
  void forAllIntersectingLists() {
    qt()
        .forAll(
            integers().between(0, 100),
            integers().between(0, 100),
            integers().between(0, 100)
        )
        .check((shared, nonShared1, nonShared2) -> {
          Node<Integer> intersection = generateSharedNodes(shared);
          Node<Integer> head1 = generateNodes(nonShared1, intersection);
          Node<Integer> head2 = generateNodes(nonShared2, intersection);
          return intersection == intersect(head1, head2);
        });
  }

  @Test
  void forAllIntersectingListsReversing() {
    qt()
        .forAll(
            integers().between(0, 100),
            integers().between(0, 100),
            integers().between(0, 100)
        )
        .check((shared, nonShared1, nonShared2) -> {
          Node<Integer> intersection = generateSharedNodes(shared);
          Node<Integer> head1 = generateNodes(nonShared1, intersection);
          Node<Integer> head2 = generateNodes(nonShared2, intersection);
          return intersection == intersectReversing(head1, head2);
        });
  }

  private Node<Integer> generateSharedNodes(final Integer shared) {
    Node<Integer> intersection = null;
    Node<Integer> lastSharedNode = null;
    for (int i = 0; i < shared; i++) {
      if (lastSharedNode == null) {
        intersection = new Node<>(i);
        lastSharedNode = intersection;
      } else {
        lastSharedNode.setNext(new Node<>(i));
        lastSharedNode = lastSharedNode.next();
      }
    }
    return intersection;
  }

  private Node<Integer> generateNodes(Integer count, Node<Integer> start) {
    Node<Integer> head = start;
    for (int i = 0; i < count; i++) {
      Node<Integer> newHead = new Node<>(i);
      newHead.setNext(head);
      head = newHead;
    }
    return head;
  }
}