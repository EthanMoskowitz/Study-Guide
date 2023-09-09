package cs3500.pa01;

import java.io.IOException;

/**
 * Mock appendable for testing output
 */
public class MockAppendable implements Appendable {

  /**
   *
   * @param csq
   *         The character sequence to append.  If {@code csq} is
   *         {@code null}, then the four characters {@code "null"} are
   *         appended to this Appendable.
   *
   * @throws IOException for testing
   */
  @Override
  public Appendable append(CharSequence csq) throws IOException {
    throw new IOException();
  }

  /**
   *
   * @param csq
   *         The character sequence from which a subsequence will be
   *         appended.  If {@code csq} is {@code null}, then characters
   *         will be appended as if {@code csq} contained the four
   *         characters {@code "null"}.
   *
   * @param start
   *         The index of the first character in the subsequence
   *
   * @param end
   *         The index of the character following the last character in the
   *         subsequence
   *
   * @throws IOException for testing
   */
  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    throw new IOException();
  }

  /**
   *
   * @param c
   *         The character to append
   *
   * @throws IOException for testing
   */
  @Override
  public Appendable append(char c) throws IOException {
    throw new IOException();
  }
}
