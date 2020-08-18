package edu.wpi.surflab.utils.surfapi.types;

import java.util.HashMap;
import lombok.Getter;

/**
 * Supports nAcquisitionType value from TSurfObjectInfos type for SurfAPI DLL.
 * Corresponds to what kind of sensor has been used for the measure
 * @author Matthew Spofford
 */
public enum SensorType {
  unknown(0),
  unknown2(4),
  contact(1),
  optic(2),
  thermocouple(3),
  contactProbe(5),
  AFM(6),
  STM(7),
  video(8),
  interferometer(9),
  structuredLight(1),
  confocal(1);

  /**
   * Used for reverse lookup for C-type enum to Java-enum.
   */
  private static final HashMap<Integer, SensorType> LOOKUP = new HashMap<>();
  /**
   * Result value of result type. Used in C-type enum within API.
   */
  @Getter
  private final int value;

  /**
   * Defines enum with given value.
   * @param value Corresponds to value of nAcquisitionType for TSurfObjectInfos.
   */
  SensorType(final int value) {
    this.value = value;
  }
  static {
    // Generate reverse lookup table for C-type conversion
    for(SensorType s : SensorType.values()) {
      SensorType.LOOKUP.put(s.value, s);
    }
  }

  /**
   * Lookup C-like enums corresponding to given value.
   * @param value Value of the enum being looked up.
   * @return Output of the enum that was found.
   */
  public static SensorType lookup(Integer value) {
    return LOOKUP.get(value);
  }

  /**
   * Checks if input sensor type is unknown.
   * @param result Sensor value being checked for unknown.
   * @return True if unknown.
   */
  public static boolean isUnknown(final SensorType result) {
    return isUnknown(result.value);
  }

  /**
   * Checks if input sensor type is unknown.
   * @param result Sensor value being checked for unknown.
   * @return True if unknown.
   */
  public static boolean isUnknown(final int result) {
    return result == SensorType.unknown.value ||
           result == SensorType.unknown2.value;
  }
}
