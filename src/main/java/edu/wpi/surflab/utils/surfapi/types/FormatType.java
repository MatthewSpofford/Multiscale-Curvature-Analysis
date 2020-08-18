package edu.wpi.surflab.utils.surfapi.types;

import java.util.HashMap;
import lombok.Getter;

/**
 * Supports DSFORMAT type from SurfAPI DLL.
 * Supported formats for surface.
 * @author Matthew Spofford
 */
public enum FormatType {
  kFormatUnknown(0), // unknown format
  kFormatSurf1(1), // format Surf 1
  kFormatSurf2(2), // format Surf 2
  kFormatSDF(3), // format SDF
  kFormatCompressedSurf1(4); // format Surf 1, data compressed using zlib

  // Used for reverse lookup for C-type enum to Java-enum
  private static final HashMap<Integer, FormatType> LOOKUP = new HashMap<>();
  @Getter
  private final int value;

  /**
   * Defines enum with given value.
   * @param value Corresponds to value of DSFORMAT enum.
   */
  FormatType(final int value) {
    this.value = value;
  }
  static {
    // Generate reverse lookup table for C-type conversion
    for(FormatType s : FormatType.values()) {
      FormatType.LOOKUP.put(s.value, s);
    }
  }

  /**
   * Lookup C-like enums corresponding to given value.
   * @param value Value of the enum being looked up.
   * @return Output of the enum that was found.
   */
  public static FormatType lookup(Integer value) {
    return LOOKUP.get(value);
  }
}
