package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

/**
 * Represents test for CreateQuestionString
 */
class CreateQuestionStringTest {

  /**
   * Tests creating the string
   */
  @Test
  void createString() {
    CreateQuestionString create = new CreateQuestionString();
    ArrayList<Question> questions = new ArrayList<>();
    Question q1 = new Question("Sample question?", "Sample Answer.", "Hard");
    Question q2 = new Question("What is my favorite color?", "Blue.", "Easy");
    questions.add(q1);
    questions.add(q2);
    String sample = """
        {Q}Sample question?{A}Sample Answer.{D}Hard
        {Q}What is my favorite color?{A}Blue.{D}Easy
        """;
    assertEquals(sample, create.createString(questions));
  }
}