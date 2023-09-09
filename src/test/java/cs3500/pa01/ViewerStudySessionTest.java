package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests viewer
 */
class ViewerStudySessionTest {
  Viewer viewer;
  StringBuilder builder;
  Viewer failViewer;

  /**
   * Instantiates a viewer
   */
  @BeforeEach
  void setup() {
    builder = new StringBuilder();
    viewer = new ViewerStudySession(builder);
    failViewer = new ViewerStudySession(new MockAppendable());
  }

  /**
   * Tests prompt path
   */
  @Test
  void promptPath() {
    viewer.promptPath();
    String s = """
        Welcome!
        To Start, Please Provide a Full Path to an Sr File:\s""";
    assertEquals(s, builder.toString());

    assertThrows(RuntimeException.class,
        () -> failViewer.promptPath());
  }

  /**
   * Tests prompt num
   */
  @Test
  void promptNum() {
    viewer.promptNum();
    String s = """
        
        Please Provide How Many Questions You Would Like:\s""";
    assertEquals(s, builder.toString());

    assertThrows(RuntimeException.class,
        () -> failViewer.promptNum());
  }

  /**
   * Tests display question
   */
  @Test
  void displayQuestion() {
    String question = "How did the shrimp fry this rice?";
    viewer.displayQuestion(1, question);
    String s = """
        
        1. How did the shrimp fry this rice?
        """;
    assertEquals(s, builder.toString());

    assertThrows(RuntimeException.class,
        () -> failViewer.displayQuestion(1, question));
  }

  /**
   * Tests display menu
   */
  @Test
  void displayMenu() {
    viewer.displayMenu();
    String s = """
        1. Set to Easy  2. Set to Hard  3. Show Answer  4. Exit\s\s""";
    assertEquals(s, builder.toString());

    assertThrows(RuntimeException.class,
        () -> failViewer.displayMenu());
  }

  /**
   * Tests display answer
   */
  @Test
  void displayAnswer() {
    String answer = "Shrimp fried rice";
    viewer.displayAnswer(answer);
    String s = """
        Answer: Shrimp fried rice
        1. Set to Easy  2. Set to Hard  3. Show Answer  4. Exit\s\s""";
    assertEquals(s, builder.toString());

    assertThrows(RuntimeException.class,
        () -> failViewer.displayAnswer(answer));
  }

  /**
   * Test display final stats
   */
  @Test
  void displayFinalStats() {
    String s = """
        Questions Answered: 10
        Questions Changed From Easy to Hard: 3
        Questions Changed From Hard to Easy: 5
        Total Hard Questions: 12
        Total Easy Questions: 90
        """;
    viewer.displayFinalStats(s);
    String output = """
        
        Questions Answered: 10
        Questions Changed From Easy to Hard: 3
        Questions Changed From Hard to Easy: 5
        Total Hard Questions: 12
        Total Easy Questions: 90
        """;
    assertEquals(output, builder.toString());

    assertThrows(RuntimeException.class,
        () -> failViewer.displayFinalStats(s));
  }
}