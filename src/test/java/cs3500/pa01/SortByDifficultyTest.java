package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests sort by difficulty comparator
 */
class SortByDifficultyTest {

  /**
   * Tests compare
   */
  @Test
  void compare() {
    Question easy1 = new Question("Q", "A", "Easy");
    Question easy2;
    easy2 = new Question("Q2", "A2", "Easy");
    Question hard1 = new Question("Q", "A", "Hard");
    Question hard2 = new Question("Q2", "A2", "Hard");
    SortByDifficulty dif = new SortByDifficulty();

    assertEquals(-1, dif.compare(hard1, easy1));
    assertEquals(-1, dif.compare(hard1, hard2));

    assertEquals(1, dif.compare(easy1, hard1));
    assertEquals(1, dif.compare(easy2, hard2));

    assertEquals(0, dif.compare(easy1, easy2));
    assertEquals(0, dif.compare(easy2, easy1));
  }
}