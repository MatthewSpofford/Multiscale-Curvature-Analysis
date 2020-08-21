package edu.wpi.surflab.utils.surfapi;

import com.sun.jna.NativeLong;
import com.sun.jna.ptr.FloatByReference;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.LongByReference;
import com.sun.jna.ptr.NativeLongByReference;
import com.sun.jna.ptr.ShortByReference;
import com.sun.jna.win32.StdCallLibrary;
import edu.wpi.surflab.utils.surfapi.types.StudiableInfo;

/**
 * Mirrors DLL implementation of SurfAPI created by Digital Surf.
 * @author Matthew Spofford
 */
interface SurfAPI extends StdCallLibrary {
  /**
   * Initialisation of a file with which we are going to work.
   * Original function from DLL:
   *   DSRESULT dsOpenStudiable(const char* strFilename,
   *     DSFILEFLAGS rw,
   *     DSSTUDIABLE* pType,
   *     short* pCount,
   *     DSFILE* hFile
   *   );
   * @param strFilename Name of the file you want to open.
   * @param rw Kind of access to the file : kdsReadFile for reading, kdsWriteFile for writing.
   * @param pType When reading, receives the kind of studiable which is in the file.
   *              When writing, specifies the kind of studiable you are going to store.
   * @param pCount When reading, receives the object count of the studiable stored in the file.
   *               When writing, defines the object count of the studiable.
   * @param hFile Address of a handle to a Surf file. This variable will be used in all the
   *              following calls to the API.
   * @return kdsOK if successful.
   */
  int dsOpenStudiable(String strFilename, int rw, IntByReference pType, ShortByReference pCount,
                      LongByReference hFile);

  /**
   * When the file has been successfully written/read, this function validates and closes the file.
   * Original function from DLL:
   *   DSRESULT dsCloseStudiable(
   *     DSFILE hFile
   *   );
   * @param hFile Address of handle to the file you want to close.
   * @return kdsOK if successful.
   */
  int dsCloseStudiable(long hFile);

  /**
   * When an error occurs, this functions stops all operations on this file, and frees all data
   * associated.
   * Original function from DLL:
   *   DSRESULT dsAbortOpsOnStudiable(
   *     DSFILE hFile
   *   );
   * @param hFile Address of handle to the file you want to close.
   * @return kdsOK if successful.
   */
  int dsAbortOpsOnStudiable(long hFile);

  /**
   * Obtains information about the specified surface object.
   * Original function from DLL:
   *   DSRESULT dsReadObjectInfos(
   *     DSFILE hFile,
   *     long nObject,
   *     struct TSurfObjectInfos* pInfos
   *   );
   * @param hFile Handle of the file from which you want to obtain information.
   * @param nObject Object number from which you want to obtain information.
   * @param pInfos Pointer to a {@link StudiableInfo} (TSurfObjectInfos) structure which has been previously initialized.
   * @return kdsOK if successful.
   */
  int dsReadObjectInfos(long hFile, NativeLong nObject, StudiableInfo pInfos);

  /**
   * Obtains the comment associated with an object.
   * Original function from DLL:
   *   DSRESULT dsReadObjectComment(
   *     DSFILE hFile,
   *     long nObject,
   *     char* strComment
   *   );
   */
  int dsReadObjectComment(long hFile, NativeLong nObject, byte[] strComment);

  /**
   * Obtains the comment associated with an object.
   * Original function from DLL:
   *   DSRESULT dsReadObjectPoints(
   *     DSFILE hFile,
   *     long nObject,
   *     long* pBuffer
   *   );
   */
  int dsReadObjectPoints(long hFile, NativeLong nObject, int[] pBuffer);

  /**
   * Obtains information about the specified surface object.
   * This is similar to {@link SurfAPI#dsReadObjectInfos} except no struct is needed.
   * Original function from DLL:
   *   DSRESULT dsReadObjectInfosWithoutStruct(
   *     DSFILE file,
   *     long nObject,
   *     DSSTUDIABLE* Type,
   *     char* strName,
   *     char* strOperatorName,
   *     short* nAcquisitionType,
   *     short* nTracking,
   *     short* nSpecialPoints,
   *     BOOL* bAbsolute,
   *     float* fGaugeResolution,
   *     long* nZMin,
   *     long* nZMax,
   *     long* nXCount,
   *     long* nYCount,
   *     float* fXStep,
   *     float* fYStep,
   *     float* fZStep,
   *     float* fXOffset,
   *     float* fYOffset,
   *     float* fZOffset,
   *     char* strXAxisName,
   *     char* strYAxisName,
   *     char* strZAxisName,
   *     TUNIT* tXAxisUnit,
   *     TUNIT* tYAxisUnit,
   *     TUNIT* tZAxisUnit,
   *     BOOL* bInverted,
   *     short* nRectified,
   *     short* nSecond,
   *     short* nMinute,
   *     short* nHour,
   *     short* nDay,
   *     short* nMonth,
   *     short* nYear,
   *     float* fMeasureLength,
   *     char* ClientInfo,
   *     short* nCommentSize
   *   );
   * @param file Handle of the file from which you want to obtain information.
   * @param nObject Object number from which you want to obtain information.
   * @param nType Type of surface.
   * @param strName Name of surface (maximum length of 31).
   * @param strOperatorName Name of operator who measured the surface (maximum length of 31).
   * @param nAcquisitionType Type of sensor used for measuring the surface.
   * @param nTracking Type of tracking used in measurements.
   * @param nSpecialPoints Special point type, and whether there are non-measured points.
   * @param bAbsolute Defines if surface has absolute values.
   * @param fGaugeResolution Gauge resolution value. Defined as 0 if resolution not set.
   * @param nZMin Minimum (resampled) value.
   * @param nZMax Maximum (resampled) value.
   * @param nXCount Number of points by column.
   * @param nYCount Number of points by row.
   * @param fXStep X-axis step value.
   * @param fYStep Y-axis step value.
   * @param fZStep Z-axis step value.
   * @param fXOffset X-axis offset.
   * @param fYOffset Y-axis offset.
   * @param fZOffset Z-axis offset.
   * @param strXAxisName Name of the X-axis (max size of 17 bytes).
   * @param strYAxisName Name of the Y-axis (max size of 17 bytes).
   * @param strZAxisName Name of the Z-axis (max size of 17 bytes).
   * @param tXAxisUnit X-axis measurement units.
   * @param tYAxisUnit Y-axis measurement units.
   * @param tZAxisUnit Z-axis measurement units.
   * @param bInverted Defines if the studiable values are inverted.
   * @param nRectified Defines if the studiable is levelled.
   * @param nSecond Seconds value at recorded time of measurement.
   * @param nMinute Minutes value at recorded time of measurement.
   * @param nHour Hour value at recorded time of measurement.
   * @param nDay Day at recorded time of measurement.
   * @param nMonth Month at recorded time of measurement.
   * @param nYear Year at recorded time of measurement.
   * @param fMeasureLength Length (in seconds) of the measure.
   * @param ClientInfo Client information (max size of 128 bytes).
   * @param nCommentSize Size in bytes of the comment.
   * @return kdsOK if successful.
   */
  int dsReadObjectInfosWithoutStruct(
      long file,
      long nObject,
      IntByReference nType,
      char[] strName,
      char[] strOperatorName,
      ShortByReference nAcquisitionType,
      ShortByReference nTracking,
      ShortByReference nSpecialPoints,
      IntByReference bAbsolute,
      FloatByReference fGaugeResolution,
      NativeLongByReference nZMin,
      NativeLongByReference nZMax,
      NativeLongByReference nXCount,
      NativeLongByReference nYCount,
      FloatByReference fXStep,
      FloatByReference fYStep,
      FloatByReference fZStep,
      FloatByReference fXOffset,
      FloatByReference fYOffset,
      FloatByReference fZOffset,
      char[] strXAxisName,
      char[] strYAxisName,
      char[] strZAxisName,
      IntByReference tXAxisUnit,
      IntByReference tYAxisUnit,
      IntByReference tZAxisUnit,
      IntByReference bInverted,
      ShortByReference nRectified,
      ShortByReference nSecond,
      ShortByReference nMinute,
      ShortByReference nHour,
      ShortByReference nDay,
      ShortByReference nMonth,
      ShortByReference nYear,
      FloatByReference fMeasureLength,
      char[] ClientInfo,
      ShortByReference nCommentSize
  );
}
