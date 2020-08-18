package edu.wpi.surflab.utils.surfapi;

import com.sun.jna.Library;
import com.sun.jna.NativeLong;
import edu.wpi.surflab.utils.surfapi.types.StudiableData;

/**
 * Mirrors DLL implementation of SurfAPI created by Digital Surf.
 * @author Matthew Spofford
 */
interface SurfAPI extends Library {
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
  int dsOpenStudiable(String strFilename, int rw, int[] pType, short[] pCount, NativeLong[] hFile);

  /**
   * When the file has been successfully written/read, this function validates and closes the file.
   * Original function from DLL:
   *   DSRESULT dsCloseStudiable(
   *     DSFILE hFile
   *   );
   * @param hFile Address of handle to the file you want to close.
   * @return kdsOK if successful.
   */
  int dsCloseStudiable(NativeLong hFile);

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
   * @param pInfos Pointer to a {@link StudiableData} (TSurfObjectInfos) structure which has been previously initialized.
   * @return
   */
  int dsReadObjectInfos(NativeLong hFile, NativeLong nObject, StudiableData[] pInfos);
}
