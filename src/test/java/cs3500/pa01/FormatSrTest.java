package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Represents tests for FormatSr
 */
class FormatSrTest {

  /**
   * Tests if the string is properly formatted
   */
  @Test
  void formatString() {
    String string = """
        # Example md file for testing

        - Example text
        - [[Sample Question?:::Answer.]]
        - [[Brackets Example[] Here]]
        - [[Question with spaces? ::: Answer2.]]

        ## More Example
        - [[Line Break Example Here
         Still Going]]
        - [[Maybe a question?:::and answer.]]

        """;
    FormatSr format = new FormatSr(string);

    String toSr = """
        {Q}Sample Question?{A}Answer.{D}Hard
        {Q}Question with spaces?{A}Answer2.{D}Hard
        {Q}Maybe a question?{A}and answer.{D}Hard
        """;

    assertEquals(toSr, format.formatString());
  }
}