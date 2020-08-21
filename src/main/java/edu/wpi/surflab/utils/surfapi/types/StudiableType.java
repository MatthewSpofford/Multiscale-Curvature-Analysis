package edu.wpi.surflab.utils.surfapi.types;

import java.util.HashMap;
import lombok.Getter;

/**
 * Supports DSSTUDIABLE type from SurfAPI DLL. Supported studiable types.
 *
 * @author Matthew Spofford
 */
public enum StudiableType {
  kdsUnknown(-1),

  kdsFirstStudiable(1),

  kdsProfile(1),
  kdsSurface(2),
  kdsBinaryImage(3),
  kdsProfileSeries(4),
  kdsSurfaceSeries(5),
  kdsMeridianDisc(6),
  kdsMultiLayerProfile(7),
  kdsMultiLayerSurface(8),

  kdsParallelDisc(9), // not yet implemented

  kdsIntensityImage(10),
  kdsIntensitySurface(11),

  kdsRGBImage(12),
  kdsRGBSurface(13),

  kdsForceCurve(14),
  kdsSeriesOfForceCurve(15),

  kdsRGBIntensitySurface(16),

  kdsParametricProfile(17),

  kdsRGBImagesSeries(18),

  kdsParametricSurface(19), // not yet implemented

  kdsSpectrum(20),

  kdsHyperSpectral(21),

  kdsRoundnessProfile(22), // not yet implemented
  kdsMultiRoundnessProfile(23), // not yet implemented
  kdsFlatnessProfile(24), // not yet implemented
  kdsMultiFlatnessProfile(25), // not yet implemented

  kdsForceVolume(26),
  kdsForceVolumeSpringConst(27),
  kdsForceVolumeSensitivityConst(28),
  kdsCITSSpectrum(29),
  kdsCITSCube(30),

  kdsMultiHorizontalStraightnessProfile(31), // not yet implemented
  kdsMultiVerticalStraightnessProfile(32), // not yet implemented
  kdsHelicoidalRoundnessProfile(33), // not yet implemented
  kdsSpiralFlatnessProfile(34), // not yet implemented

  kdsLastStudiable(kdsSpiralFlatnessProfile.value);

  // Used for reverse lookup for C-type enum to Java-enum
  private static final HashMap<Integer, StudiableType> LOOKUP = new HashMap<>();
  @Getter private final int value;

  /**
   * Defines enum with given value.
   *
   * @param value Corresponds to value of DSSTUDIABLE enum.
   */
  StudiableType(final int value) {
    this.value = value;
  }

  static {
    // Generate reverse lookup table for C-type conversion
    for (StudiableType s : StudiableType.values()) {
      StudiableType.LOOKUP.put(s.value, s);
    }
  }

  /**
   * Lookup C-like enums corresponding to given value.
   *
   * @param value Value of the enum being looked up.
   * @return Output of the enum that was found.
   */
  public static StudiableType lookup(Integer value) {
    return LOOKUP.get(value);
  }
}
