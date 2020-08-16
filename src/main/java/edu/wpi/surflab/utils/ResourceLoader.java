package edu.wpi.surflab.utils;

import edu.wpi.surflab.utils.SystemInfo.ArchType;
import edu.wpi.surflab.utils.SystemInfo.OSType;
import java.io.IOException;
import java.util.Objects;
import javafx.fxml.FXMLLoader;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

/**
 * Used for loading application resources more easily.
 *
 * @author Matthew Spofford
 */
public final class ResourceLoader {

  public static final String BASE_RESOURCE_PATH = "edu/wpi/surflab/";
  public static final String SURF_API_PATH;

  // Paths used to construct SURF_API_PATH
  private static final String SURF_API_BASE = "utils/surfapi/";
  private static final String SURF_API_x64 = "x64/";
  private static final String SURF_API_x86 = "Win32/";

  // Initialize SURF_API_PATH
  static {
    // Check that Windows is being used
    switch (SystemInfo.getCurrOS()) {
      case WIN:
        // Continue to architecture check
        break;
      default:
        throw new IllegalStateException("System needs to be using a Windows operating system. In "
            + "order to import surfaces, this application needs run an internal DLL stored within "
            + "the JAR file, requiring a Windows OS.");
    }

    // Check and determine architecture
    String apiPath = BASE_RESOURCE_PATH + SURF_API_BASE;
    switch (SystemInfo.getCurrArch()) {
      case x64 -> apiPath += SURF_API_x64;
      case x86 -> apiPath += SURF_API_x86;
      default -> {
        System.err.println("Could not detect system architecture when initializing SurfAPI used for"
            + "surface importing. Defaulting to 32-bit (x86) architecture.");
        apiPath += SURF_API_x86;
      }
    }
    // Assign final value to API path
    SURF_API_PATH = apiPath;
  }

  /** Maintains FXML locations for easy retrieval. */
  public enum FXMLResources {
    APP("CurvatureApp.fxml"),
    ABOUT("MenuBar/About.fxml"),
    MENU_BAR("MenuBar/Bar.fxml");

    @Getter private final String location;

    FXMLResources(@NotNull String location) {
      this.location = location;
    }

    @Override
    public String toString() {
      return location;
    }
  }

  /**
   * More easily loads a specified fxml. Given path does not need to include "fxml" folder within
   * the path. Function also catches IOException, and throws an error if it is caught.
   *
   * @param path Location path of FXML being loaded.
   * @param <T> Output type of root object in the given FXML.
   * @return Return root object of the given FXML.
   */
  public static <T> T loadFXML(@NotNull String path) {
    T output = null;
    path = BASE_RESOURCE_PATH + "fxml/" + path;
    try {
      output =
          FXMLLoader.load(
              Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResource(path)));
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(-1);
    }
    return output;
  }

  /**
   * More easily loads a specified fxml.
   *
   * @param location Specified FXML resource to be loaded.
   * @param <T> Output type of root object in the given FXML.
   * @return Return root object of the given FXML.
   */
  public static <T> T loadFXML(FXMLResources location) {
    return loadFXML(location.toString());
  }
}
