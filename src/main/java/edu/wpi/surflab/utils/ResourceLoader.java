package edu.wpi.surflab.utils;

import com.sun.jna.Platform;
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

  /**
   * Base location for all application resources.
   */
  public static final String BASE_RESOURCE_PATH = "edu/wpi/surflab/";
  /**
   * Defines location for SurfAPI in resources.
   */
  public static final String SURF_API_PATH;
  /**
   * Defines name of SurfAPI in resources.
   */
  public static final String SURF_API = "surfapi.dll";

  // Initialize SURF_API_PATH
  static {
    // Paths used to construct SURF_API_PATH
    final String SURF_API_BASE = "utils/surfapi/";
    final String SURF_API_x64 = "x64/";
    final String SURF_API_x86 = "Win32/";

    // Check that Windows is being used
    if (!Platform.isWindows()) {
        throw new IllegalStateException("System needs to be using a Windows operating system. In "
            + "order to import surfaces, this application needs run an internal DLL stored within "
            + "the JAR file, requiring a Windows OS.");
    }

    // Check and determine architecture
    String apiPath = BASE_RESOURCE_PATH + SURF_API_BASE;
    if(Platform.is64Bit()) {
      apiPath += SURF_API_x64;
    } else {
      apiPath += SURF_API_x86;
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
