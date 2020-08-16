package edu.wpi.surflab.utils;

import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Runs tests against UsageCheckableEnum
 * @see edu.wpi.surflab.utils.UsageCheckableEnumTester
 */
public class UsageCheckableEnumTester {

  /**
   * Enum to be tested with for UsageCheckableEnum.
   */
  private enum TestCheckable implements UsageCheckableEnum {
    Val1(false),
    Val2(false),
    Val3(true),
    Val4(false),
    Val5(false),
    UNKNOWN(false);

    boolean val;
    TestCheckable(boolean val) {
      this.val = val;
    }

    @Override
    public boolean checkUsed() {
      return this.val;
    }
  }

  @Test
  /**
   * Checks if {@link TestCheckable} enum values contain a true.
   */
  public void testCheckableTrue() {
    Assertions.assertEquals(TestCheckable.Val3,
        UsageCheckableEnum.getUsed(TestCheckable.values(), TestCheckable.UNKNOWN));
  }

  @Test
  /**
   * By removing true value, check if null is now output
   */
  public void testCheckUnknown() {
    ArrayList<TestCheckable> newList = new ArrayList<>();
    for (TestCheckable val : TestCheckable.values()) {
      if(!val.val) {
        newList.add(val);
      }
    }

    TestCheckable[] newArray = newList.toArray(new TestCheckable[newList.size()]);
    Assertions.assertEquals(TestCheckable.UNKNOWN,
        UsageCheckableEnum.getUsed(newArray, TestCheckable.UNKNOWN));
  }
}
