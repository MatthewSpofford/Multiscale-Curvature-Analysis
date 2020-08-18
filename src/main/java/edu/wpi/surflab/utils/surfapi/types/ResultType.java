package edu.wpi.surflab.utils.surfapi.types;

import java.util.HashMap;
import lombok.Getter;

/**
 * Supports DSRESULT type from SurfAPI DLL.
 * Result error codes.
 * @author Matthew Spofford
 */
public enum ResultType {
  kdsOK( 1), // success !
  kdsError( 0), // generic error
  kdsInvalidFilename(-1), // invalid filename
  kdsInvalidObject(-2), // invalid object number
  kdsInvalidInfos(-3), // structure infos
  kdsInvalidComment(-4), // invalid comment
  kdsInvalidPoints(-5), // invalid array of points
  kdsAlreadyOpen(-6), // file already open
  kdsNotEnoughMemory (-7), // not enough memory to run the operation
  kdsNotImplemented(-8), // function not yet implemented
  kdsInvalidPointSize(-9), // the size of the points is incorrect
  kdsInvalid1stArg(-10), //
  kdsInvalid2ndArg(-11), //
  kdsInvalid3rdArg(-12), //
  kdsInvalid4thArg(-13), //
  kdsUnknownStudiable(-14), // invalid studiable type
  kdsWriteOnRead(-15), // can't write in a file when open for reading
  kdsWrongObjectIndex(-16), // the index of the object is not in the authorised range
  kdsWrongWriteOperation(-17), // error in the order of the writing operations
  kdsIOFileError(-18), // error when accessing the file
  kdsNullPointer(-19), // a NULL pointer has been given as a parameter
  kdsReadOnWrite(-20),
  kdsUnknownFileFlags(-21), // an argument of type DSFILEFLAGS is invalid
  kdsBadSize(-22),
  kdsRecordAlreadyInList(-23), // a record is already in the list, cannot be replaced
  kdsRecordNotInList(-24), // can't find the record in the file
  kdsUnknownRecordID(-25), // the record ID (IDSURF2_...) is unknown
  kdsUnknownFileFormat(-26), // the file format is unknown
  kdsBadObjectCount(-27), // the number of objects in the file is invalid
  kdsInvalidStudiableSize(-28), // size in points is invalid
  kdsInvalidXStep(-29), // invalid X step
  kdsInvalidYStep(-30), // invalid Y step
  kdsInvalidZStep(-31), // invalid Z step
  kdsEmptyComment(-32), // dsWriteObjectComment should not be called when the size
  // of the comment in the header is set to 0
  kdsWrongAPIVersion(-33), // the version of the API compiled and the version of the user
  // are too different
  kdsInvalidFileHandle(-34), // invalid handle of type DSFILE.
  kdsInvalidParamProfileInfo(-35), // invalid parametric profile information
  kdsInvalidWCount(-36), // nWCount seems invalid
  kdsTotalPointsCountOverflow(-37), // too many points in the studiable
  // dimensions (nXCount * nYCount * nWCount) seems to generate an overflow
  kdsCantChangeCompression(-38), // cannot change/set the compression if data has already been written to disk
  // compression must be set when opening the file and before writing the first object
  kdsInvalidBooleanArg(-39),     // boolean parameter must be TRUE (1) or FALSE (0)
  kdsCompressionFailure(-40),
  kdsInvalidBitsPerPoint(-41),
  kdsInvalidGroupArray(-42), // invalid group array (null pointer?)
  kdsInvalidSectionArray(-43); // invalid section array (null pointer?)

  /**
   * Used for reverse lookup for C-type enum to Java-enum.
   */
  private static final HashMap<Integer, ResultType> LOOKUP = new HashMap<>();
  /**
   * Result value of result type. Used in C-type enum within API.
   */
  @Getter private final int value;

  /**
   * Defines enum with given value.
   * @param value Corresponds to value of DSRESULT enum.
   */
  ResultType(final int value) {
    this.value = value;
  }
  static {
    // Generate reverse lookup table for C-type conversion
    for(ResultType s : ResultType.values()) {
      ResultType.LOOKUP.put(s.value, s);
    }
  }

  /**
   * Lookup C-like enums corresponding to given value.
   * @param value Value of the enum being looked up.
   * @return Output of the enum that was found.
   */
  public static ResultType lookup(Integer value) {
    return LOOKUP.get(value);
  }

  /**
   * Checks if input result was successful.
   * @param result Result value being checked for success.
   * @return True if successful.
   */
  public static boolean isSuccess(final ResultType result) {
    return isSuccess(result.value);
  }

  /**
   * Checks if input result was successful.
   * @param result Result value being checked for success.
   * @return True if successful.
   */
  public static boolean isSuccess(final int result) {
    return result == ResultType.kdsOK.value;
  }
}
