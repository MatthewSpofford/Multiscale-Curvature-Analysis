package edu.wpi.surflab;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CurvatureApp extends Application {

  private final String APP_TITLE = "Multiscale Curvature Analysis";
  private final String APP_FXML = "fxml/Controller.fxml";
  private final double MIN_WIDTH = 640;
  private final double MIN_HEIGHT = 480;

  @Override
  public void start(Stage primaryStage) throws Exception {
    final Scene appScene = FXMLLoader.load(getClass().getResource(APP_FXML));
    primaryStage.setScene(appScene);
    primaryStage.setTitle(APP_TITLE);
    primaryStage.setMinWidth(MIN_WIDTH);
    primaryStage.setMinHeight(MIN_HEIGHT);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
