package edu.wpi.surflab.utils.surfapi;

import com.sun.jna.NativeLong;
import edu.wpi.surflab.utils.ResourceLoader;
import edu.wpi.surflab.utils.surfapi.types.StudiableInfo;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Objects;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests usage of SurfAPI in order to load surfaces. Also tests error handling methods
 *
 * @see StudiableLoader
 * @author Matthew Spofford
 */
public class StudiableLoaderTester {

  /** Refers to location of example surfaces. */
  private static final String EXMPL_SURF_PATH =
      ResourceLoader.BASE_RESOURCE_PATH + "example-surfaces/";

  private static final String COIN_SURF_PATH = exmplSurfPathToAbosolute("CoinHK.sur");
  private static final String BONE_SURF_PATH = exmplSurfPathToAbosolute("Bone.sur");

  /**
   * Takes in a surface path and converts it to an absolute path.
   *
   * @param path The given surface path to be converted.
   * @return Converted absolute path of the surface file.
   */
  private static String exmplSurfPathToAbosolute(final String path) {
    String coinPath = null;
    try {
      final URL coinResource =
          ClassLoader.getSystemClassLoader().getResource(EXMPL_SURF_PATH + path);
      final File coinFile = Paths.get(Objects.requireNonNull(coinResource).toURI()).toFile();
      coinPath = coinFile.getAbsolutePath();
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
    return coinPath;
  }

  /** Confirms that input surface path is accurate. */
  @Test
  public void getSurfPath() {
    StudiableLoader loader = new StudiableLoader(COIN_SURF_PATH);
    Assertions.assertEquals(COIN_SURF_PATH, loader.getFilePath());
  }

  /** Tries to run get surface without loading a surface. */
  @Test
  public void getSurfWithoutLoad() {
    StudiableLoader loader = new StudiableLoader(COIN_SURF_PATH);
    Assertions.assertNull(loader.getSurfaces());
  }

  /** Test loading one surface successfully with {@link StudiableLoader#load()}. */
  @Test
  public void loadSingleSurface() throws UnsupportedEncodingException {
    StudiableLoader loader = new StudiableLoader(BONE_SURF_PATH);
    Assertions.assertEquals(BONE_SURF_PATH, loader.getFilePath());

    StudiableCollection[] collectionArr = loader.load();

    // Checks that surface can still be retrieved after loading
    Assertions.assertArrayEquals(collectionArr, loader.getSurfaces());

    // Check that length is correct
    Assertions.assertEquals(1, collectionArr.length);
    StudiableCollection collect = collectionArr[0];

    // Check that metadata is accurate
    StudiableInfo info = collect.getMetadata();
    Assertions.assertEquals(2, info.type);
    Assertions.assertEquals("Magdalenian carved bone  - Pr", new String(info.name, "UTF-8").trim());
    Assertions.assertEquals(true, info.absolute);
    Assertions.assertEquals(1, info.specialPointType);
    Assertions.assertEquals(new NativeLong(-18438), info.zMin);
    Assertions.assertEquals(new NativeLong(7395), info.zMax);

    // Check that comment is accurate
    Assertions.assertEquals("", collect.getComment());

    // Check that points are accurate
    int[] points = collect.getPoints();
    Assertions.assertEquals(345158, points.length);
  }
}
