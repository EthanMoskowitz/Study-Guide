package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for study guide controller
 */
class ControllerStudyGuideTest {

  /**
   * Tests run
   */
  @Test
  void run() {
    Controller goodArgs = new ControllerStudyGuide("src/test/resources/notes-root",
        "filename", "src/test/resources/output-examples/studyGuide.md");
    assertDoesNotThrow(() -> goodArgs.run());

    Controller goodArgs2 = new ControllerStudyGuide("src/test/resources/notes-root",
        "created", "src/test/resources/output-examples/studyGuide.md");
    assertDoesNotThrow(() -> goodArgs2.run());

    Controller goodArgs3 = new ControllerStudyGuide("src/test/resources/notes-root",
        "modified", "src/test/resources/output-examples/studyGuide.md");
    assertDoesNotThrow(() -> goodArgs3.run());

    Controller wrongFirstArgs = new ControllerStudyGuide("src/test/resources/notes",
        "filename", "src/test/resources/output-examples/studyGuide.md");
    assertThrows(RuntimeException.class,
        () -> wrongFirstArgs.run());

    Controller wrongSecondArgs = new ControllerStudyGuide("src/test/resources/notes-root",
        "wrong", "src/test/resources/output-examples/studyGuide.md");
    assertThrows(IllegalArgumentException.class,
        () -> wrongSecondArgs.run());

    Controller wrongThirdArgs = new ControllerStudyGuide("src/test/resources/notes-root",
        "filename", "src/wrong/studyGuide.md");
    assertThrows(RuntimeException.class,
        () -> wrongThirdArgs.run());

    Controller wrongThirdArgs2 = new ControllerStudyGuide("src/test/resources/notes-root",
        "filename", "src/main/studyGuide");
    assertThrows(IllegalArgumentException.class,
        () -> wrongThirdArgs2.run());


    Controller goodArgs4 = new ControllerStudyGuide("src/test/resources/notes-root",
        "filename", "src/test/resources/output-examples/studyGuide.md");
    goodArgs4.run();
    Path p = Path.of("src/test/resources/output-examples/studyGuide.md");
    Path s = Path.of("src/test/resources/output-examples/studyGuide.sr");
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