package edu.wpi.surflab.utils.surfapi;

import edu.wpi.surflab.utils.ResourceLoader;
import edu.wpi.surflab.utils.surfapi.types.StudiableInfo;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Objects;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests usage of SurfAPI in order to load surfaces.
 * Also tests error handling methods
 * @see SurfaceLoader
 * @author Matthew Spofford
 */
public class SurfaceLoaderTester {

  /**
   * Refers to location of example surfaces.
   */
  private static final String EXMPL_SURF_PATH = ResourceLoader.BASE_RESOURCE_PATH + "example-surfaces/";
  private static final String COIN_SURF_PATH = exmplSurfPathToAbosolute("CoinHK.sur");
  private static final String BONE_SURF_PATH = exmplSurfPathToAbosolute("Bone.sur");

  /**
   * Takes in a surface path and converts it to an absolute path.
   * @param path The given surface path to be converted.
   * @return Converted absolute path of the surface file.
   */
  private static String exmplSurfPathToAbosolute(final String path) {
    String coinPath = null;
    try {
      final URL coinResource = ClassLoader.getSystemClassLoader().getResource(EXMPL_SURF_PATH + path);
      final File coinFile = Paths.get(Objects.requireNonNull(coinResource).toURI()).toFile();
      coinPath = coinFile.getAbsolutePath();
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
    return coinPath;
  }

  /**
   * Confirms that input surface path is accurate.
   */
  @Test
  public void getSurfPath() {
    SurfaceLoader loader = new SurfaceLoader(COIN_SURF_PATH);
    Assertions.assertEquals(COIN_SURF_PATH, loader.getFilePath());
  }

  /**
   * Tries to run get surface without loading a surface.
   */
  @Test
  public void getSurfWithoutLoad() {
    SurfaceLoader loader = new SurfaceLoader(COIN_SURF_PATH);
    Assertions.assertNull(loader.getSurfaces());
  }

  /**
   * Test loading one surface successfully with {@link SurfaceLoader#load()}.
   */
  @Test
  public void loadSingleSurface() {
    SurfaceLoader loader = new SurfaceLoader(BONE_SURF_PATH);
    StudiableInfo[] data = loader.load();
    // Check that output is not bad
    Assertions.assertNotNull(data);
    // Check that length is correct
    Assertions.assertEquals(1, data.length);

    // Checks that surface can still be retrieved after loading
    Assertions.assertArrayEquals(data, loader.getSurfaces());
  }
}
