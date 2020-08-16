package edu.wpi.surflab.utils;

/**
 * Interface is used to check if an enum property is being used.
 */
public interface UsageCheckableEnum {

  /**
   * Check if current system is using the enum type called with.
   * @return Output true if type is being used by current system.
   *         Output false otherwise.
   */
  boolean checkUsed();

  /**
   * Output enum value if the output from {@link #checkUsed} for that type is true.
   * If {@link #checkUsed} is not true for any value, output given unknown value.
   * @param values The enum values to run {@link #checkUsed} against.
   * @param unknownType
   * @param <T> Enum object that implements the UsageCheckableEnum interface.
   * @return Output used type according to {@link #checkUsed}. Output unknownType otherwise.
   */
  static <T extends UsageCheckableEnum> T getUsed(final T[] values, final T unknownType) {
    T foundType = unknownType;

    // Run through OS types and see what OS is being used.
    for(T type : values) {
      // Skip unknown type, has no check function to call
      if(type == unknownType) {
        continue;
      } else {
        // Check current OS type is currently in use
        if(type.checkUsed()){
          foundType = type;
          break;
        }
      }
    }
    // Return type that was found (or given unknown type if none were found)
    return foundType;
  }
}
