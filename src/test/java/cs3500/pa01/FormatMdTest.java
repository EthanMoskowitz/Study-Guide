package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Represents tests for FormatMd
 */
class FormatMdTest {

  /**
   * Tests if the string is properly formatted
   */
  @Test
  void formatString() {
    String string = """
        # Example md file for testing

        - Example text
        - [[More Examples]]
        - [[Brackets Example[] Here]]

        ## More Example
        - [[Line Break Example Here
         Still Going]]
        - [[Maybe a question?:::and answer]]

        """;
    FormatMd format = new FormatMd(string);

    String toMd = """
        # Example md file for testing
        - More Examples
        - Brackets Example[] Here
        
        ## More Example
        - Line Break Example Here Still Going
        """;

    assertEquals(toMd, format.formatString());
  }

  /**
   * Tests get next from abstract class
   */
  @Test
  void getNext() {
    String string = """
        Hello\s""";
    FormatMd format = new FormatMd(string);

    assertEquals('e', format.getNext(0));
    assertEquals('l', format.getNext(1));
    assertEquals('l', format.getNext(2));
    assertEquals('o', format.getNext(3));
    assertEquals(' ', format.getNext(4));
    assertEquals(' ', format.getNext(5));
  }

  /**
   * Tests get prev from abstract class
   */
  @Test
  void getPrev() {
    String string = """
        Hello\s""";
    FormatMd format = new FormatMd(string);

    assertEquals(' ', format.getPrev(0));
    assertEquals('H', format.getPrev(1));
    assertEquals('e', format.getPrev(2));
    assertEquals('l', format.getPrev(3));
    assertEquals('l', format.getPrev(4));
    assertEquals('o', format.getPrev(5));
  }

  /**
   * Tests create important from abstract class
   */
  @Test
  void createImportant() {
    String string = """
        Hello\s""";
    FormatMd format = new FormatMd(string);

    assertEquals("Hello ", format.createImportant(0).toString());

    String hasBrackets = """
        [ brackets ] ] ]]""";
    FormatMd formatBrackets = new FormatMd(hasBrackets);

    assertEquals("[ brackets ] ] \n", formatBrackets.createImportant(0).toString());

  }

}