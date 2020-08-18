package edu.wpi.surflab.utils.surfapi.types;

import java.util.HashMap;
import lombok.Getter;

/**
 * Supports DSFILEFLAGS type from SurfAPI DLL.
 * Flags used to open a file.
 * @author Matthew Spofford
 */
public enum FileOpenFlags {
  kdsNoFile(0), // used for internal purpose
  kdsReadFile(1),
  kdsWriteFile(2);

  // Used for reverse lookup for C-type enum to Java-enum
  private static final HashMap<Integer, FileOpenFlags> LOOKUP = new HashMap<>();
  @Getter
  private final int value;

  /**
   * Defines enum with given value.
   * @param value Corresponds to value of DSFILEFLAGS enum.
   */
  FileOpenFlags(final int value) {
    this.value = value;
  }
  static {
    // Generate reverse lookup table for C-type conversion
    for(FileOpenFlags s : FileOpenFlags.values()) {
      FileOpenFlags.LOOKUP.put(s.value, s);
    }
  }

  /**
   * Lookup C-like enums corresponding to given value.
   * @param value Value of the enum being looked up.
   * @return Output of the enum that was found.
   */
  public static FileOpenFlags lookup(Integer value) {
    return LOOKUP.get(value);
  }
}
