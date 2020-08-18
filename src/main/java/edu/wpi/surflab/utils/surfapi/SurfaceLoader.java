package edu.wpi.surflab.utils.surfapi;

import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import edu.wpi.surflab.utils.ResourceLoader;
import edu.wpi.surflab.utils.surfapi.types.FileOpenFlags;
import edu.wpi.surflab.utils.surfapi.types.ResultType;
import edu.wpi.surflab.utils.surfapi.types.StudiableData;

/**
 * Enables calls to SurfAPI, using {@link SurfAPI} interface.
 * @see SurfAPI
 * @author Matthew Sppofford
 */
public class SurfaceLoader {

  /**
   * Stores DLL implementation for SurfAPI.
   */
  private static SurfAPI api = Native.load(ResourceLoader.SURF_API, SurfAPI.class);;

  /**
   * Opens surface file and outputs raw surfaces data.
   * @param filePath Location of surface file to be opened.
   * @return Array of {@link StudiableData} from API.
   */
  public StudiableData[] loadSurface(final String filePath) throws UnsupportedOperationException {
    int[] typePtr = new int[1]; // Type pointer
    short[] countPtr = new short[1]; // Count pointer
    NativeLong[] fileHndlAddr = new NativeLong[1]; // File handle address pointer

    // Use surfAPI to open file for reading
    int result = api.dsOpenStudiable(filePath, FileOpenFlags.kdsReadFile.getValue(),
                                                     typePtr, countPtr, fileHndlAddr);
    // Check if surface opened successfully
    checkSuccessful(result);

    // Create array of data "pointers" using 2D array
    StudiableData[] surfaces = new StudiableData[countPtr[0]];
    // Collect data for surfaces
    for(int i = 0; i < surfaces.length; i++) {
      // Read surface data
      StudiableData[] surfPtr = new StudiableData[1];
      result = api.dsReadObjectInfos(fileHndlAddr[0], new NativeLong(i), surfPtr);
      // Check if surface was read successfully
      checkSuccessful(result);
      surfaces[i] = surfPtr[0];
    }

    // Close opened surface
    result = api.dsCloseStudiable(fileHndlAddr[0]);
    // Check if surface closed successfully
    checkSuccessful(result);

    return surfaces;
  }

  /**
   * Throw exception if unsupported result occurred while using SurfAPI.
   * @param result Given unsupported result value collected while running openSurfaces.
   */
  private void checkSuccessful(int result) throws UnsupportedOperationException {
    // Check success using enum
    checkSuccessful(ResultType.lookup(result));
  }
  /**
   * Unsupported result occured during runtime of openSurfaces, and an error needs to be thrown.
   * @param result Check given result for .
   */
  private void checkSuccessful(ResultType result) throws UnsupportedOperationException {
    // If unsuccessful, throw error
    if(!ResultType.isSuccess(result)) {
      throw new UnsupportedOperationException("Unsupported return from surfAPI, "
          + "output was:" + result.name() + "(" + result.getValue() + ")");
    }
    // Otherwise, continue with success
  }
}
