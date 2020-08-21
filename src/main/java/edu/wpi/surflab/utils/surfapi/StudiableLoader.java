package edu.wpi.surflab.utils.surfapi;

import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import com.sun.jna.Structure;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.LongByReference;
import com.sun.jna.ptr.ShortByReference;
import edu.wpi.surflab.utils.ResourceLoader;
import edu.wpi.surflab.utils.surfapi.types.FileOpenFlags;
import edu.wpi.surflab.utils.surfapi.types.ResultType;
import edu.wpi.surflab.utils.surfapi.types.StudiableInfo;
import java.io.UnsupportedEncodingException;
import lombok.Getter;

/**
 * Enables calls to SurfAPI, using {@link SurfAPI} interface.
 * @see SurfAPI
 * @author Matthew Sppofford
 */
public class StudiableLoader {
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
  private StudiableCollection[] surfaces = null;

  /**
   * File handle address pointer.
   */
//  private NativeLong fileHandleAddr;
  private long fileHandleAddr;

  /**
   * Creates a surface loader object using given surface file to be loaded.
   * @param filePath Location of surface file to be opened. Recommended to be absolute file path.
   */
  public StudiableLoader(final String filePath) {
    this.filePath = filePath;
  }

  /**
   * Outputs copy of previously loaded surface data.
   * @return Array of {@link StudiableInfo} from API. Returns null if {@link StudiableLoader#load()}
   * has not been called yet.
   */
  public StudiableCollection[] getSurfaces() {
    if(surfaces == null) {
      return null;
    } else {
      return surfaces.clone();
    }
  }

  /**
   * Opens surface file and outputs raw surfaces data.
   * After the first load, the data is not reloaded and instead uses the previously loaded data.
   * @return Array of {@link StudiableCollection} from API. Outputs null if load was unsuccessful.
   *         Outputs null elements if that studiable could not be loaded correctly.
   */
  public StudiableCollection[] load() throws UnsupportedOperationException {
    // Only use API if surfaces have not been loaded
    if(surfaces == null) {
      // Create array for surfaces being loaded later
      StudiableCollection[] loadedSurfaces;

      boolean successful = false;
      try {
        ShortByReference objCountPtr = new ShortByReference();
//        NativeLongByReference fileHandlePtr = new NativeLongByReference();
        LongByReference fileHandlePtr = new LongByReference();
        // Use surfAPI to open file for reading
        checkSuccessful(api.dsOpenStudiable(filePath, FileOpenFlags.kdsReadFile.getValue(),
                                     new IntByReference(), objCountPtr, fileHandlePtr));

        // Update studiable attributes
        short objCount = objCountPtr.getValue();
        fileHandleAddr = fileHandlePtr.getValue();

        // Store loaded surfaces
        loadedSurfaces = new StudiableCollection[objCount];
        // Collect data for surfaces
        for(int i = 0; i < loadedSurfaces.length; i++) {
          NativeLong currObj = new NativeLong(i+1);

          // Read surface metadata
          StudiableInfo info = new StudiableInfo();
          checkSuccessful(api.dsReadObjectInfos(this.fileHandleAddr, currObj, info));

          Structure[] structs = info.toArray(info.unsignedSize.intValue());

          // Read surface comment data
          byte[] comment = new byte[info.commentSize];
          if(info.commentSize > 0) {
            checkSuccessful(api.dsReadObjectComment(this.fileHandleAddr, currObj, comment));
          }

          // Read surface point data
          final int size = info.xCount.intValue() * info.yCount.intValue();
          int[] points = new int[size];
          if(size > 0) {
            checkSuccessful(api.dsReadObjectPoints(this.fileHandleAddr, currObj, points));
          }

          // Check if surface was read successfully
          try {
            loadedSurfaces[i] = new StudiableCollection(info, new String(comment, "UTF-8"), points);
          } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            loadedSurfaces[i] = null;
          }
        }
        successful = true;
      } finally {
        int result = api.dsCloseStudiable(this.fileHandleAddr);
        // Check if surface closed successfully only if a previous error was not thrown
        if(successful) {
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
      api.dsAbortOpsOnStudiable(this.fileHandleAddr);
      // Throw error
      throw new UnsupportedOperationException("Unsupported return from surfAPI, "
          + "output was: " + result.name() + "(" + result.getValue() + ")");
    }

    // Otherwise, continue with success
  }
}
