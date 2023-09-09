package cs3500.pa01;

/**
 * Represents the state of the study session
 */
public enum SessionState {
  /**
   * Initial state where user must provide a path
   */
  PATHSTART,
  /**
   * Second state where user must provide a number of questions
   */
  NUMSTART,
  /**
   * Third state where displays question and prompts user on menu input
   */
  QUESTION,
  /**
   * Standby state where it is transitioning to next
   */
  STANDBY
}
