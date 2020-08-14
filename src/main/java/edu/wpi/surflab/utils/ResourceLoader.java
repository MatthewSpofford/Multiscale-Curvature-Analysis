package edu.wpi.surflab.utils;

import java.io.IOException;
import java.util.Objects;
import javafx.fxml.FXMLLoader;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

/**
 * Used for loading application resources more easily.
 * @author Matthew Spofford
 */
public final class ResourceLoader {

  public static final String BASE_RESOURCE_PATH = "edu/wpi/surflab/";

  /**
   * Maintains FXML locations for easy retrieval.
   */
  public enum FXMLResources {
    APP("CurvatureApp.fxml"),
    ABOUT("MenuBar/About.fxml"),
    MENU_BAR("MenuBar/Bar.fxml");

    @Getter
    private final String location;
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
   * @param path Location path of FXML being loaded.
   * @param <T> Output type of root object in the given FXML.
   * @return Return root object of the given FXML.
   */
  public static <T> T loadFXML(@NotNull String path) {
    T output = null;
    path = BASE_RESOURCE_PATH + "fxml/" + path;
    try {
      output = FXMLLoader.load(
          Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResource(path)));
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(-1);
    }
    return output;
  }

  /**
   * More easily loads a specified fxml.
   * @param location Specified FXML resource to be loaded.
   * @param <T> Output type of root object in the given FXML.
   * @return Return root object of the given FXML.
   */
  public static <T> T loadFXML(FXMLResources location) {
    return loadFXML(location.toString());
  }
}
