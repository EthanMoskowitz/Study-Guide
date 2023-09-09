package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests study session model
 */
class StudySessionModelTest {
  StudySessionModel model;
  ArrayList<Question> questions;

  /**
   * Instantiates a study session
   */
  @BeforeEach
  void setup() {
    Question easy1;
    easy1 = new Question("Q", "A", "Easy");
    Question easy2;
    easy2 = new Question("Q2", "A2", "Easy");
    Question hard1;
    hard1 = new Question("Q", "A", "Hard");
    Question hard2;
    hard2 = new Question("Q2", "A2", "Hard");
    questions = new ArrayList<>();
    questions.add(hard1);
    questions.add(hard2);
    questions.add(easy1);
    questions.add(easy2);
    model = new StudySessionModel(questions);
  }

  /**
   * Tests update difficulty
   */
  @Test
  void updateDifficulty() {
    assertEquals(Difficulty.Hard, model.getList().get(0).getDifficulty());
    model.updateDifficulty(0, Difficulty.Easy);
    assertEquals(Difficulty.Easy, model.getList().get(0).getDifficulty());

    assertEquals(Difficulty.Easy, model.getList().get(2).getDifficulty());
    model.updateDifficulty(2, Difficulty.Hard);
    assertEquals(Difficulty.Hard, model.getList().get(2).getDifficulty());
  }

  /**
   * Tests get string
   */
  @Test
  void getString() {
    String tempString = """
        {Q}Q{A}A{D}Hard
        {Q}Q2{A}A2{D}Hard
        {Q}Q{A}A{D}Easy
        {Q}Q2{A}A2{D}Easy
        """;

    String string = model.getString();
    assertEquals(tempString, string);
  }

  /**
   * Test package stats
   */
  @Test
  void packageStats() {
    model.updateDifficulty(0, Difficulty.Easy);
    model.updateDifficulty(2, Difficulty.Hard);
    String tempString = """
        Questions Answered: 2
        Questions Changed From Easy to Hard: 1
        Questions Changed From Hard to Easy: 1
        Total Hard Questions: 2
        Total Easy Questions: 2""";

    String string = model.packageStats();
    assertEquals(tempString, string);
  }

  /**
   * Tests get list
   */
  @Test
  void getList() {
    assertEquals(questions, model.getList());
  }
}