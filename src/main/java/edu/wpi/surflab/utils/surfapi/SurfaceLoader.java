package edu.wpi.surflab.utils.surfapi;

import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.LongByReference;
import com.sun.jna.ptr.ShortByReference;
import edu.wpi.surflab.utils.ResourceLoader;
import edu.wpi.surflab.utils.surfapi.types.FileOpenFlags;
import edu.wpi.surflab.utils.surfapi.types.ResultType;
import edu.wpi.surflab.utils.surfapi.types.StudiableInfo;
import lombok.Getter;

/**
 * Enables calls to SurfAPI, using {@link SurfAPI} interface.
 * @see SurfAPI
 * @author Matthew Sppofford
 */
public class SurfaceLoader {
  /**
   * Stores implementation for SurfAPI.
   */
  private static final SurfAPI api = Native.load(ResourceLoader.SURF_API_PATH, SurfAPI.class);

  /**
   * Surface file path for loader to use.
   */
  @Getter private final String filePath;
  /**
   * Stores loaded surface data from API.
   */
  private StudiableInfo[] surfaces = null;

  /**
   * File handle address pointer.
   */
//  private NativeLong fileHandleAddr;
  private long fileHandleAddr;

  /**
   * Creates a surface loader object using given surface file to be loaded.
   * @param filePath Location of surface file to be opened. Recommended to be absolute file path.
   */
  public SurfaceLoader(final String filePath) {
    this.filePath = filePath;
  }

  /**
   * Outputs copy of previously loaded surface data.
   * @return Array of {@link StudiableInfo} from API. Returns null if {@link SurfaceLoader#load()}
   * has not been called yet.
   */
  public StudiableInfo[] getSurfaces() {
    if(surfaces == null) {
      return null;
    } else {
      return surfaces.clone();
    }
  }

  /**
   * Opens surface file and outputs raw surfaces data.
   * After the first load, the data is not reloaded.
   * @return Array of {@link StudiableInfo} from API.
   */
  public StudiableInfo[] load() throws UnsupportedOperationException {
    int result = 0;

    // Only use API if surfaces have not been loaded
    if(surfaces == null) {
      // Create array for surfaces being loaded later
      StudiableInfo[] loadedSurfaces;

      try {
        ShortByReference objCountPtr = new ShortByReference();
//        NativeLongByReference fileHandlePtr = new NativeLongByReference();
        LongByReference fileHandlePtr = new LongByReference();
        // Use surfAPI to open file for reading
        result = api.dsOpenStudiable(filePath, FileOpenFlags.kdsReadFile.getValue(),
                                     new IntByReference(), objCountPtr, fileHandlePtr);
        // Check if surface opened successfully
        checkSuccessful(result);

        // Update studiable attributes
        short objCount = objCountPtr.getValue();
        fileHandleAddr = fileHandlePtr.getValue();

        // Store loaded surfaces
        loadedSurfaces = new StudiableInfo[objCount];
        // Collect data for surfaces
        for(int i = 0; i < loadedSurfaces.length; i++) {
          // Initialize pointer to surface data
          StudiableInfo surfPtr = new StudiableInfo();
          // Read surface data
          result = api.dsReadObjectInfos(this.fileHandleAddr, new NativeLong(i+1), surfPtr);

          // Check if surface was read successfully
          checkSuccessful(result);
          loadedSurfaces[i] = surfPtr;
        }
      } finally {
        // Close opened surface if load was successful
        // If it wasn't successful, then the studiable has already been aborted
        if(ResultType.isSuccess(result)) {
          result = api.dsCloseStudiable(this.fileHandleAddr);
          // Check if surface closed successfully
          checkSuccessful(result);
        }
      }
      // Surface loading was successful, and can be assigned as the real surfaces
      surfaces = loadedSurfaces;
    }

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
   * Aborts file handler if possible.
   * @param result Check given result for .
   */
  private void checkSuccessful(ResultType result) throws UnsupportedOperationException {
    // If unsuccessful, throw error
    if (!ResultType.isSuccess(result)) {
      // Abort file opening process
      int newResult = api.dsAbortOpsOnStudiable(this.fileHandleAddr);
      // Throw error
      throw new UnsupportedOperationException("Unsupported return from surfAPI, "
          + "output was: " + result.name() + "(" + result.getValue() + ")");
    }

    // Otherwise, continue with success
  }
}
