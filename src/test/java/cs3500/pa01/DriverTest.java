package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for Driver
 */
class DriverTest {
  String[] goodArgs;
  String[] goodArgs2;
  String[] goodArgs3;
  String[] tooManyArgs;
  String[] wrongFirstArgs;
  String[] wrongSecondArgs;
  String[] wrongThirdArgs;
  String[] wrongThirdArgs2;
  String[] noArgs;

  /**
   * Initializes inputs for main
   */
  @BeforeEach
  void setup() {
    goodArgs = new String[] {"src/test/resources/notes-root", "filename",
        "src/test/resources/output-examples/studyGuide.md"};
    goodArgs2 = new String[] {"src/test/resources/notes-root", "created",
        "src/test/resources/output-examples/studyGuide.md"};
    goodArgs3 = new String[] {"src/test/resources/notes-root", "modified",
        "src/test/resources/output-examples/studyGuide.md"};
    tooManyArgs = new String[] {"src/test/resources/notes-root",
        "filename", "src/test/resources/output-examples/studyGuide.md", "Too many"};
    wrongFirstArgs =
        new String[] {"src/test/resources/notes", "filename",
            "src/test/resources/output-examples/studyGuide.md"};
    wrongSecondArgs =
        new String[] {"src/test/resources/notes-root", "wrong",
            "src/test/resources/output-examples/studyGuide.md"};
    wrongThirdArgs =
        new String[] {"src/test/resources/notes-root", "filename", "src/wrong/studyGuide.md"};
    wrongThirdArgs2 =
        new String[] {"src/test/resources/notes-root", "filename", "src/main/studyGuide"};
    noArgs = new String[] {};
  }

  /**
   * Tests aspects of the main method
   */
  @Test
  void mainTest() {
    assertDoesNotThrow(() -> Driver.main(goodArgs));
    assertDoesNotThrow(() -> Driver.main(goodArgs2));
    assertDoesNotThrow(() -> Driver.main(goodArgs3));
    assertThrows(IllegalArgumentException.class,
        () -> Driver.main(tooManyArgs));
    assertThrows(RuntimeException.class,
        () -> Driver.main(wrongFirstArgs));
    assertThrows(IllegalArgumentException.class,
        () -> Driver.main(wrongSecondArgs));
    assertThrows(RuntimeException.class,
        () -> Driver.main(wrongThirdArgs));
    assertThrows(IllegalArgumentException.class,
        () -> Driver.main(wrongThirdArgs2));

    ByteArrayInputStream input;
    String fullSession = """
        src/test/resources/output-examples/sample.sr
        3
        1
        2
        1
        """;
    input = new ByteArrayInputStream(String.format(fullSession).getBytes());
    System.setIn(input);
    assertDoesNotThrow(() -> Driver.main(noArgs));

    Driver.main(goodArgs);
    Path p = Path.of(goodArgs[2]);
    Path s = Path.of(goodArgs[2].replace(".md", ".sr"));
    assertTrue(p.toFile().exists());
    assertTrue(s.toFile().exists());
    try {
      assertEquals(-1, Files.mismatch(
          p,
          Path.of("src/test/resources/output-examples/test.md")));
      assertEquals(-1, Files.mismatch(
          s,
          Path.of("src/test/resources/output-examples/test.sr")));
    } catch (IOException e) {
      fail();
    }

  }

}