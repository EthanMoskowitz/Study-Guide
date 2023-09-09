package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

/**
 * Represents test for FileOutput
 */
class FileOutputTest {

  /**
   * Tests if the file is written into
   */
  @Test
  void writeFile() {
    FileOutput fileOutput = new FileOutput();
    File input = Path.of("src/test/resources/output-examples/input.md").toFile();
    File output = Path.of("src/test/resources/output-examples/output.md").toFile();

    String s = """
        # Example Output
        - Making random text
        - Doing Correct things
                
        ## This is all correct
        - Trust me
                
        ## It's right
        - I think so
        - I know so
                
        # Just testing to be sure
        - Can't be too cautious
        - Just in case
                
        ## Could be wrong
        """;

    try {
      fileOutput.writeFile(input, s);
      assertEquals(-1, Files.mismatch(output.toPath(), input.toPath()));
    } catch (Exception e) {
      fail();
    }

    File inputSr = Path.of("src/test/resources/output-examples/input.sr").toFile();
    File outputSr = Path.of("src/test/resources/output-examples/output.sr").toFile();

    String sr = """
        {Q}What is the capital of Canada?{A}The capital is Ottawa.{D}Hard
        {Q}Which country is known as the Land of the Rising Sun?{A}Japan.{D}Hard
        """;

    try {
      fileOutput.writeFile(inputSr, sr);
      assertEquals(-1, Files.mismatch(outputSr.toPath(), inputSr.toPath()));
    } catch (Exception e) {
      fail();
    }

    File failure = Path.of("src/test/resources/output/failure").toFile();
    assertThrows(Exception.class,
        () -> fileOutput.writeFile(failure, s));
  }
}