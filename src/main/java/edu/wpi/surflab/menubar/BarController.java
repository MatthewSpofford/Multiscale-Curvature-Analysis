package edu.wpi.surflab.menubar;

import edu.wpi.surflab.CurvatureApp;
import edu.wpi.surflab.utils.ResourceLoader;
import edu.wpi.surflab.utils.ResourceLoader.FXMLResources;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Controller for menu bar in app window.
 * @author Matthew Spofford
 */
public class BarController {
  private boolean isAboutStageOpen = false;

  @FXML
  private void openAboutStage() {
    if(!isAboutStageOpen) {
      final Stage aboutStage = ResourceLoader.loadFXML(FXMLResources.ABOUT);
      aboutStage.setOnCloseRequest(event -> isAboutStageOpen = false);
      aboutStage.show();
      isAboutStageOpen= true;
    }
  }

  @FXML
  private void openFile() {
    FileChooser chooser = new FileChooser();
    chooser.setTitle("Open File");
    chooser.showOpenDialog(CurvatureApp.getCurrentStage());
  }

  @FXML
  private void exitApp() {
    System.exit(0);
  }
}
