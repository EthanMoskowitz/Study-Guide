package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for GenerateList
 */
class GenerateListTest {
  GenerateList generateList;
  ArrayList<Question> questions;
  GenerateList generateFail;
  GenerateList generateUnFormat;

  /**
   * Instantiates a GenerateTest and a list of questions from a file
   */
  @BeforeEach
  void setup() {
    File f = Path.of("src/test/resources/output-examples/generate.sr").toFile();
    generateList = new GenerateList(f);
    File fail = Path.of("src/test/resources/output-examples/fail.sr").toFile();
    generateFail = new GenerateList(fail);
    File unFormat = Path.of("src/test/resources/output-examples/unformatted.sr").toFile();
    generateUnFormat = new GenerateList(unFormat);
  }

  /**
   * Tests generate
   */
  @Test
  void generate() {
    ArrayList<Question> list = new ArrayList<>();
    Question q1 = new Question("What is the capital of Canada?",
        "The capital is Ottawa.", "Hard");
    Question q2 = new Question("Which country is known as the Land of the Rising Sun?",
        "Japan.", "Hard");
    list.add(q1);
    list.add(q2);
    questions = generateList.generate();
    for (int i = 0; i < list.size(); i++) {
      assertEquals(list.get(i).getQuestion(), questions.get(i).getQuestion());
      assertEquals(list.get(i).getAnswer(), questions.get(i).getAnswer());
      assertEquals(list.get(i).getDifficulty(), questions.get(i).getDifficulty());
    }
    assertEquals("Sample question?", questions.get(2).getQuestion());
    assertEquals("Sample answer.", questions.get(3).getAnswer());
    assertEquals(Difficulty.Hard, questions.get(4).getDifficulty());

    assertThrows(RuntimeException.class,
        () -> generateFail.generate());

    assertThrows(IllegalArgumentException.class,
        () -> generateUnFormat.generate());
  }
}