package algorithms._2_linked_lists;

import static algorithms._2_linked_lists._6_Palindrome.palindrome;
import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.strings;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.quicktheories.core.Gen;
import org.quicktheories.core.RandomnessSource;
import org.quicktheories.impl.Constraint;

class _6_PalindromeTest {

  @Test
  void forAllPalindromes() {
    qt()
        .forAll(palindromes())
        .check(string -> palindrome(toLinked(string)));
  }

  @Test
  void forAllStrings() {
    qt()
        .forAll(strings().allPossible().ofLengthBetween(2, 1000))
        .check(string -> palindromeTest(string) == palindrome(toLinked(string)));
  }

  private boolean palindromeTest(String string) {
    return string.equals(new StringBuilder(string).reverse().toString());
  }

  private Gen<String> palindromes() {
    return strings().allPossible().ofLengthBetween(0, 1000).map(this::toPalindrome);
  }

  private String toPalindrome(String string) {
    StringBuilder builder = new StringBuilder(string);
    int size = string.length();
    for (int i = 0; i < size; i++) {
      builder.append(string.charAt(size - i - 1));
    }
    return builder.toString();
  }

  private Node<Character> toLinked(String string) {
    if (string.isEmpty()) {
      return null;
    }
    Node<Character> head = new Node<>(string.charAt(0));
    Node<Character> current = head;
    int size = string.length();
    for (int i = 1; i < size; i++) {
      current.setNext(new Node<>(string.charAt(i)));
      current = current.next();
    }
    return head;
  }
}