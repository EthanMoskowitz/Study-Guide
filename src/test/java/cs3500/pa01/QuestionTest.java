package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests question class
 */
class QuestionTest {
  Question sample;

  @BeforeEach
  void setup() {
    sample = new Question("Sample question?", "Answer.", "Hard");
  }

  /**
   * Tests get question
   */
  @Test
  void getQuestion() {
    assertEquals("Sample question?", sample.getQuestion());
  }

  /**
   * Tests get answer
   */
  @Test
  void getAnswer() {
    assertEquals("Answer.", sample.getAnswer());
  }

  /**
   * Tests get difficulty
   */
  @Test
  void getDifficulty() {
    assertEquals(Difficulty.Hard, sample.getDifficulty());
  }

  /**
   * Tests set difficulty
   */
  @Test
  void setDifficulty() {
    sample.setDifficulty(Difficulty.Easy);
    assertEquals(Difficulty.Easy, sample.getDifficulty());
  }
}