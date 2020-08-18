package edu.wpi.surflab.utils.surfapi.types;

import java.util.HashMap;
import lombok.Getter;

/**
 * Supports nSpecialPoints value from TSurfObjectInfos type for SurfAPI DLL.
 * Corresponds to what kind of sensor has been used for the measure
 * @author Matthew Spofford
 */
public enum SpecialPoints {
  normal(0),
  nonMeasured(1);

  /**
   * Used for reverse lookup for C-type enum to Java-enum.
   */
  private static final HashMap<Integer, SpecialPoints> LOOKUP = new HashMap<>();
  /**
   * Result value of result type. Used in C-type enum within API.
   */
  @Getter
  private final int value;

  /**
   * Defines enum with given value.
   * @param value Corresponds to value of nSpecialPoints for TSurfObjectInfos.
   */
  SpecialPoints(final int value) {
    this.value = value;
  }
  static {
    // Generate reverse lookup table for C-type conversion
    for(SpecialPoints s : values()) {
      LOOKUP.put(s.value, s);
    }
  }

  /**
   * Lookup C-like enums corresponding to given value.
   * @param value Value of the enum being looked up.
   * @return Output of the enum that was found.
   */
  public static SpecialPoints lookup(Integer value) {
    return LOOKUP.get(value);
  }
}
