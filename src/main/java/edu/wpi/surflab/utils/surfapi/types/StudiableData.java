package edu.wpi.surflab.utils.surfapi.types;

import com.sun.jna.NativeLong;
import com.sun.jna.Structure;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
/**
 * Maintains raw Studiable data collected from SurfAPI.
 * @author Matthew Spofford
 */
public class StudiableData extends Structure {

  /**
   * Unsigned byte size of structure.
   */
  @Getter private final NativeLong unsignedSize;

  /**
   * Type of surface.
   */
  @Getter private final int type;

  /**
   * Name of surface (maximum length of 31).
   */
  @Getter private final String name;

  /**
   * Name of operator who measured the surface (maximum length of 31).
   */
  @Getter private final String operator;

  /**
   * Type of sensor used for measuring the surface.
   */
  @Getter private final short sensorType;

  /**
   * Type of tracking used in measurements.
   */
  @Getter private final short trackingType;

  /**
   * Special point type, and whether there are non-measured points.
   */
  @Getter private final short specialPointType;

  /**
   * Defines if surface has absolute values.
   */
  @Getter private final boolean absolute;

  /**
   * Gauge resolution value. Defined as 0 if resolution not set.
   */
  @Getter private final float gaugeResolution;

  /**
   * Minimum (resampled) value.
   */
  @Getter private final NativeLong  zMin;
  /**
   * Maximum (resampled) value.
   */
  @Getter private final NativeLong  zMax;

  /**
   * Number of points by column.
   */
  @Getter private final NativeLong xCount;
  /**
   * Number of points by row.
   */
  @Getter private final NativeLong  yCount;
  /**
   * Number of points by depth (for hyperpectral measurements).
   */
  @Getter private final NativeLong  wCount;

  /**
   * X-axis step value.
   */
  @Getter private final float fXStep;
  /**
   * Y-axis step value.
   */
  @Getter private final float fYStep;
  /**
   * Z-axis step value.
   */
  @Getter private final float fZStep;

  /**
   * X-axis offset.
   */
  @Getter private final float fXOffset;
  /**
   * X-axis offset.
   */
  @Getter private final float fYOffset;
  /**
   * X-axis offset.
   */
  @Getter private final float fZOffset;

  /**
   * Name of the X-axis (max size of 17 bytes).
   */
  @Getter private final String xAxisName;
  /**
   * Name of the Y-axis (max size of 17 bytes).
   */
  @Getter private final String yAxisName;
  /**
   * Name of the Z-axis (max size of 17 bytes).
   */
  @Getter private final String zAxisName;

  /**
   * X-axis measurement units.
   */
  @Getter private final int xAxisUnit;
  /**
   * Y-axis measurement units.
   */
  @Getter private final int yAxisUnit;
  /**
   * Z-axis measurement units.
   */
  @Getter private final int zAxisUnit;

  /**
   * If unknown X-axis unit from enum, unit is present in this field (max size of 17 bytes).
   */
  @Getter private final String strXAxisUnknownUnit;
  /**
   * If unknown Y-axis unit from enum, unit is present in this field (max size of 17 bytes).
   */
  @Getter private final String strYAxisUnknownUnit;
  /**
   * If unknown Z-axis unit from enum, unit is present in this field (max size of 17 bytes).
   */
  @Getter private final String strZAxisUnknownUnit;

  /**
   * Defines if the studiable values are inverted.
   */
  @Getter private final boolean inverted;

  /**
   * Defines if the studiable is levelled.
   */
  @Getter private final short rectified;

  /**
   * Seconds value at recorded time of measurement.
   */
  @Getter private final short second;
  /**
   * Minutes value at recorded time of measurement.
   */
  @Getter private final short minute;
  /**
   * Hour value at recorded time of measurement.
   */
  @Getter private final short hour;
  /**
   * Day at recorded time of measurement.
   */
  @Getter private final short day;
  /**
   * Month at recorded time of measurement.
   */
  @Getter private final short month;
  /**
   * Year at recorded time of measurement.
   */
  @Getter private final short year;
  /**
   * Length (in seconds) of the measure.
   */
  @Getter private final float fMeasureLength;

  /**
   * Client information (max size of 128 bytes).
   */
  @Getter private final String clientInfo;

  /**
   * Size in bytes of the comment.
   */
  @Getter private final short commentSize;

  // *** T Axis ******************
  // *** only used with series ***
  /**
   * Step value of T-axis.
   */
  @Getter private final float tStep;
  /**
   * Offset of T-axis.
   */
  @Getter private final float tOffset;
  /**
   * T-axis measurement units.
   */
  @Getter private final int tAxisUnit;
  /**
   * If unknown T-axis unit from enum, unit is present in this field (max size of 14 bytes).
   */
  @Getter private final String strTAxisUnknownUnit;
  /**
   * Name of the T-axis (max size of 14 bytes).
   */
  @Getter private final String tAxisName;

  // *** T Axis ******************

}
