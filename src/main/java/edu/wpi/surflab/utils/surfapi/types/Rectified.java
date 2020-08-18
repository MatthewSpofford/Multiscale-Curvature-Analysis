package edu.wpi.surflab.utils.surfapi.types;

import java.util.HashMap;
import lombok.Getter;

/**
 * Supports nRectified value from TSurfObjectInfos type for SurfAPI DLL.
 * Corresponds to what kind of sensor has been used for the measure
 * @author Matthew Spofford
 */
public enum Rectified {
  no(0),
  yes(1);

  /**
   * Used for reverse lookup for C-type enum to Java-enum.
   */
  private static final HashMap<Integer, Rectified> LOOKUP = new HashMap<>();
  /**
   * Result value of result type. Used in C-type enum within API.
   */
  @Getter
  private final int value;

  /**
   * Defines enum with given value.
   * @param value Corresponds to value of nRectified for TSurfObjectInfos.
   */
  Rectified(final int value) {
    this.value = value;
  }
  static {
    // Generate reverse lookup table for C-type conversion
    for(Rectified s : values()) {
      LOOKUP.put(s.value, s);
    }
  }

  /**
   * Lookup C-like enums corresponding to given value.
   * @param value Value of the enum being looked up.
   * @return Output of the enum that was found.
   */
  public static Rectified lookup(Integer value) {
    return LOOKUP.get(value);
  }
}
