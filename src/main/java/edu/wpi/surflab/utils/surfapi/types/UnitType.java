package edu.wpi.surflab.utils.surfapi.types;

import java.util.HashMap;
import lombok.Getter;

/**
 * Supports TUNIT type from SurfAPI DLL.
 * Measurement unit used by surfaces.
 * @author Matthew Spofford
 */
public enum UnitType {
  kUnit_unknown(0),   // unknown unit
  kUnit_none(0),   // no unit (unknown)
  kUnit_inch(1),   // inch
  kUnit_mm(2),   // millimeter
  kUnit_mA(3),   // milliampere
  kUnit_V(4),   // volt
  kUnit_N(5),   // newton
  kUnit_deg ( 6),   // degree
  kUnit_rad ( 7),   // radian
  kUnit_degK( 8),   // Kelvin degree
  kUnit_degC( 9),   // Celsius degree
  kUnit_degF(10),   // Fahrenheit degree
  kUnit_PerCent(11),   // %
  kUnit_sec(12),   // second
  kUnit_Hz(13),   // Hertz
  kUnit_Digit(14),   // digit
  kUnit_Pa(15),   // pascals
  kUnit_kbT(16),   // kbT
  kUnit_eV(17);    // electronvolt

  // Used for reverse lookup for C-type enum to Java-enum
  private static final HashMap<Integer, UnitType> LOOKUP = new HashMap<>();
  @Getter
  private final int value;

  /**
   * Defines enum with given value.
   * @param value Corresponds to value of TUNIT enum.
   */
  UnitType(final int value) {
    this.value = value;
  }
  static {
    // Generate reverse lookup table for C-type conversion
    for(UnitType s : UnitType.values()) {
      UnitType.LOOKUP.put(s.value, s);
    }
  }

  /**
   * Lookup C-like enums corresponding to given value.
   * @param value Value of the enum being looked up.
   * @return Output of the enum that was found.
   */
  public UnitType lookup(Integer value) {
    return LOOKUP.get(value);
  }
}
