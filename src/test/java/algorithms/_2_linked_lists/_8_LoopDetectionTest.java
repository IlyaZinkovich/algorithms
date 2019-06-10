package algorithms._2_linked_lists;

import static algorithms._2_linked_lists._8_LoopDetection.detectLoop;
import static algorithms._2_linked_lists._8_LoopDetection.detectLoopWithSet;
import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.integers;

import org.junit.jupiter.api.Test;

class _8_LoopDetectionTest {

  @Test
  void forAllLoopsWithFastAndSlowRunners() {
    qt()
        .forAll(
            integers().between(0, 100),
            integers().between(0, 100))
        .check((beforeLoop, inLoop) -> {
          Node<Integer> loopNode = new Node<>(Integer.MAX_VALUE);
          Node<Integer> head = createListWithLoop(beforeLoop, inLoop, loopNode);
          return loopNode == detectLoop(head);
        });
  }

  @Test
  void forAllLoopsWithSet() {
    qt()
        .forAll(
            integers().between(0, 100),
            integers().between(0, 100))
        .check((beforeLoop, inLoop) -> {
          Node<Integer> loopNode = new Node<>(Integer.MAX_VALUE);
          Node<Integer> head = createListWithLoop(beforeLoop, inLoop, loopNode);
          return loopNode == detectLoopWithSet(head);
        });
  }

  private Node<Integer> createListWithLoop(final Integer beforeLoop, final Integer inLoop,
      final Node<Integer> loopNode) {
    Node<Integer> lastNodeInLoop = new Node<>(Integer.MIN_VALUE);
    Node<Integer> head = lastNodeInLoop;
    head = addNodes(inLoop, head);
    loopNode.setNext(head);
    head = loopNode;
    head = addNodes(beforeLoop, head);
    lastNodeInLoop.setNext(loopNode);
    return head;
  }

  private Node<Integer> addNodes(final Integer count, Node<Integer> head) {
    for (int i = 0; i < count; i++) {
      Node<Integer> node = new Node<>(i);
      node.setNext(head);
      head = node;
    }
    return head;
  }
}