package edu.wpi.surflab;

import edu.wpi.surflab.utils.ResourceLoader;
import edu.wpi.surflab.utils.ResourceLoader.FXMLResources;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;

/**
 * Initializes and builds the Multiscale Curvature Analysis Application.
 *
 * @author Matthew Spofford
 */
public final class CurvatureApp extends Application {

  private static final String APP_TITLE = "Multiscale Curvature Analysis";
  private static final double MIN_WIDTH = 640;
  private static final double MIN_HEIGHT = 480;

  /**
   * Property name used by JNA to define DLL location.
   */
  private static final String JNA_LIB_PROPERTY = "jna.library.path";

  @Getter private static Application currentApp = null;
  @Getter private static Stage currentStage = null;

  @Override
  /** Launches the Curvature Analysis Application. */
  public void start(Stage primaryStage) {
    currentApp = this;
    currentStage = primaryStage;
    final Scene appScene = ResourceLoader.loadFXML(FXMLResources.APP);
    primaryStage.setScene(appScene);
    primaryStage.setTitle(APP_TITLE);
    primaryStage.setMinWidth(MIN_WIDTH);
    primaryStage.setMinHeight(MIN_HEIGHT);
    primaryStage.show();
  }

  public static void main(String[] args) {
    System.setProperty(JNA_LIB_PROPERTY, ResourceLoader.SURF_API_PATH);
    launch(args);
  }
}
