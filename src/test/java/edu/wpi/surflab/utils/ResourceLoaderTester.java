package edu.wpi.surflab.utils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.wpi.surflab.utils.ResourceLoader.FXMLResources;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

public class ResourceLoaderTester extends ApplicationTest {

  @Test
  /** Test loadFXML with filepath as input */
  void loadStage() {
    final Scene testScene = ResourceLoader.loadFXML("TestScene.fxml");

    // Make sure new stage is null
    assertFalse(testScene == null);

    // Make sure anchor pane was initialized
    AnchorPane pane = (AnchorPane) testScene.getRoot();
    for (Node child : pane.getChildren()) {
      // Make sure Text was initialized
      if (child instanceof Text) {
        Text testText = (Text) child;
        assertTrue(testText.getText().equals("This is a test"));
        break;
      }
    }
  }

  @Test
  /** Goal is to test loadFXML with enum as input. */
  void loadApp() {
    final MenuBar bar = ResourceLoader.loadFXML(FXMLResources.MENU_BAR);

    // Check if scene is null
    assertFalse(bar == null);

    // Check if menu's are valid
    List<Menu> barMenu = bar.getMenus();
    for (Menu menu : barMenu) {
      String label = menu.getText();
      // Make sure file menu exists
      if (label.equals("File")) {
        return;
      }
    }

    // Assert false, since File menu was not initialized
    assert false;
  }
}
