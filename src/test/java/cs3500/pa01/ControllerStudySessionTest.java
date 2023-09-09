package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.StringReader;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for ControllerStudySession
 */
class ControllerStudySessionTest {

  /**
   * Tests run and all other aspects
   */
  @Test
  void run() {
    String typicalSession = """
        README.md
        /src/main/fakeAgain.sr
        src/test/resources/output-examples/studyGuide.sr
        Not a number
        35
        Not a number again
        1
        2
        3
        4
        """;
    ControllerStudySession controller = new ControllerStudySession(SessionState.PATHSTART,
        new StringReader(typicalSession), System.out);
    assertDoesNotThrow(() -> controller.run());

    String fullSession = """
        src/test/resources/output-examples/sample.sr
        3
        1
        2
        1
        """;
    ControllerStudySession controller2 = new ControllerStudySession(SessionState.PATHSTART,
        new StringReader(fullSession), System.out);
    assertDoesNotThrow(() -> controller2.run());

    String doNothing = """
        
        """;
    StringBuilder tester = new StringBuilder();
    ControllerStudySession controller3 = new ControllerStudySession(SessionState.STANDBY,
        new StringReader(doNothing), tester);
    controller3.run();
    assertEquals("", tester.toString());

    String testOutput = """
        src/test/resources/output-examples/text.sr
        2
        2
        1
        """;
    StringBuilder tester2 = new StringBuilder();
    Controller controller4 = new ControllerStudySession(SessionState.PATHSTART,
        new StringReader(testOutput), tester2);
    controller4.run();
    String printText = """
        Welcome!
        To Start, Please Provide a Full Path to an Sr File:\s
        Please Provide How Many Questions You Would Like:\s
        1. Which country is known as the Land of the Rising Sun?
        1. Set to Easy  2. Set to Hard  3. Show Answer  4. Exit\s\s
        2. What is the capital of Canada?
        1. Set to Easy  2. Set to Hard  3. Show Answer  4. Exit\s\s
        Questions Answered: 2
        Questions Changed From Easy to Hard: 0
        Questions Changed From Hard to Easy: 0
        Total Hard Questions: 1
        Total Easy Questions: 1""";
    assertEquals(printText, tester2.toString());
  }
}