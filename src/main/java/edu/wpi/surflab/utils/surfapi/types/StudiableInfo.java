package edu.wpi.surflab.utils.surfapi.types;

import com.sun.jna.NativeLong;
import com.sun.jna.Structure;
import com.sun.jna.Structure.FieldOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Maintains raw Studiable data collected from SurfAPI.
 * @author Matthew Spofford
 */
@NoArgsConstructor
@FieldOrder({ "unsignedSize",
              "type",
              "name",
              "operator",
              "sensorType",
              "trackingType",
              "specialPointType",
              "absolute",
              "gaugeResolution",
              "zMin", "zMax",
              "xCount", "yCount", "wCount",
              "xStep", "yStep", "zStep",
              "xOffset", "yOffset", "zOffset",
              "xAxisName", "yAxisName", "zAxisName",
              "xAxisUnit", "yAxisUnit", "zAxisUnit",
              "strXAxisUnknownUnit", "strYAxisUnknownUnit", "strZAxisUnknownUnit",
              "inverted",
              "rectified",
              "second", "minute", "hour", "day", "month", "year", "fMeasureLength",
              "clientInfo",
              "commentSize",
              "tStep", "tOffset", "tAxisUnit", "strTAxisUnknownUnit", "tAxisName"})
public class StudiableInfo extends Structure {

  private static final int DATA_SIZE = 339;

  /**
   * Unsigned byte size of structure.
   */
  @Getter public NativeLong unsignedSize = new NativeLong(DATA_SIZE, true);

  /**
   * Type of surface.
   */
  @Getter public int type;

  /**
   * Name of surface (maximum length of 31).
   */
  @Getter public byte[] name = new byte[31];

  /**
   * Name of operator who measured the surface (maximum length of 31).
   */
  @Getter public byte[] operator = new byte[31];

  /**
   * Type of sensor used for measuring the surface.
   */
  @Getter public short sensorType;

  /**
   * Type of tracking used in measurements.
   */
  @Getter public short trackingType;

  /**
   * Special point type, and whether there are non-measured points.
   */
  @Getter public short specialPointType;

  /**
   * Defines if surface has absolute values.
   */
  @Getter public boolean absolute;

  /**
   * Gauge resolution value. Defined as 0 if resolution not set.
   */
  @Getter public float gaugeResolution;

  /**
   * Minimum (resampled) value.
   */
  @Getter public NativeLong  zMin = new NativeLong();
  /**
   * Maximum (resampled) value.
   */
  @Getter public NativeLong  zMax = new NativeLong();

  /**
   * Number of points by column.
   */
  @Getter public NativeLong xCount = new NativeLong();
  /**
   * Number of points by row.
   */
  @Getter public NativeLong  yCount = new NativeLong();
  /**
   * Number of points by depth (for hyperpectral measurements).
   */
  @Getter public NativeLong  wCount = new NativeLong();

  /**
   * X-axis step value.
   */
  @Getter public float xStep;
  /**
   * Y-axis step value.
   */
  @Getter public float yStep;
  /**
   * Z-axis step value.
   */
  @Getter public float zStep;

  /**
   * X-axis offset.
   */
  @Getter public float xOffset;
  /**
   * X-axis offset.
   */
  @Getter public float yOffset;
  /**
   * X-axis offset.
   */
  @Getter public float zOffset;

  /**
   * Name of the X-axis (max size of 17 bytes).
   */
  @Getter public byte[] xAxisName = new byte[17];
  /**
   * Name of the Y-axis (max size of 17 bytes).
   */
  @Getter public byte[] yAxisName = new byte[17];
  /**
   * Name of the Z-axis (max size of 17 bytes).
   */
  @Getter public byte[] zAxisName = new byte[17];

  /**
   * X-axis measurement units.
   */
  @Getter public int xAxisUnit;
  /**
   * Y-axis measurement units.
   */
  @Getter public int yAxisUnit;
  /**
   * Z-axis measurement units.
   */
  @Getter public int zAxisUnit;

  /**
   * If unknown X-axis unit from enum, unit is present in this field (max size of 17 bytes).
   */
  @Getter public byte[] strXAxisUnknownUnit = new byte[17];
  /**
   * If unknown Y-axis unit from enum, unit is present in this field (max size of 17 bytes).
   */
  @Getter public byte[] strYAxisUnknownUnit = new byte[17];
  /**
   * If unknown Z-axis unit from enum, unit is present in this field (max size of 17 bytes).
   */
  @Getter public byte[] strZAxisUnknownUnit = new byte[17];

  /**
   * Defines if the studiable values are inverted.
   */
  @Getter public boolean inverted;

  /**
   * Defines if the studiable is levelled.
   */
  @Getter public short rectified;

  /**
   * Seconds value at recorded time of measurement.
   */
  @Getter public short second;
  /**
   * Minutes value at recorded time of measurement.
   */
  @Getter public short minute;
  /**
   * Hour value at recorded time of measurement.
   */
  @Getter public short hour;
  /**
   * Day at recorded time of measurement.
   */
  @Getter public short day;
  /**
   * Month at recorded time of measurement.
   */
  @Getter public short month;
  /**
   * Year at recorded time of measurement.
   */
  @Getter public short year;
  /**
   * Length (in seconds) of the measure.
   */
  @Getter public float fMeasureLength;

  /**
   * Client information (max size of 128 bytes).
   */
  @Getter public byte[] clientInfo = new byte[128];

  /**
   * Size in bytes of the comment.
   */
  @Getter public short commentSize;

  // *** T Axis ******************
  // *** only used with series ***
  /**
   * Step value of T-axis.
   */
  @Getter public float tStep;
  /**
   * Offset of T-axis.
   */
  @Getter public float tOffset;
  /**
   * T-axis measurement units.
   */
  @Getter public int tAxisUnit;
  /**
   * If unknown T-axis unit from enum, unit is present in this field (max size of 14 bytes).
   */
  @Getter public byte[] strTAxisUnknownUnit = new byte[14];
  /**
   * Name of the T-axis (max size of 14 bytes).
   */
  @Getter public byte[] tAxisName = new byte[14];

  // *** T Axis ******************
}
