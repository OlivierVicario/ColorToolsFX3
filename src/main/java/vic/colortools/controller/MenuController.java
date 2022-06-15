package vic.colortools.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import vic.colortools.App;
import vic.colortools.Context;
import vic.colortools.model.DistanceState;
import vic.colortools.model.ToolState;
import vic.colortools.model.ViewerState;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MenuController {
    private static final Logger logger = LogManager.getLogger();

    public void miHelpAboutActionHandler(ActionEvent actionEvent) {
        logger.info("into miHelpAboutActionHandler");
        Alert alert = new Alert(Alert.AlertType.NONE);
        Image image = new Image(App.class.getResourceAsStream("logoTriangles96.png"));
        ImageView iView = new ImageView();
        iView.setImage(image);
        alert.setHeaderText("Color Tools 0.0.1");
        alert.setGraphic(iView);
        alert.setContentText("copyright 2022 - Olivier Vicario" + "\n" + "MIT license");
        alert.setTitle("About");
        alert.getButtonTypes().add(ButtonType.CLOSE);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.CLOSE) {
            alert.close();
        }
        logger.info("out of miHelpAboutActionHandler");
    }

    public void miPaletteNewActionHandler(ActionEvent actionEvent) throws IOException {
        logger.info("into miPaletteNewActionHandler");
        List<String> choices = new ArrayList<>();
        choices.add("Around a color");
        choices.add("Between black or white and pure hue");
        choices.add("Triangles");
        choices.add("Viewer");

        ChoiceDialog<String> dialog = new ChoiceDialog<>("Around a color", choices);
        dialog.setTitle("New palette");
        dialog.setHeaderText("Choose a palette type");
        dialog.setContentText("color tool : ");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            switch (result.get()) {
                case "Around a color":
                    Context.getInstance().setToolState(new DistanceState());
                    App.setRoot("distance");
                    logger.info("tool choice is distance");
                    break;
               /* case "Between black or white and pure hue":
                    App.setRoot("distance2");
                    break;*/
                case "Viewer":
                    //Context.getInstance().setToolState(new ViewerState());
                    App.setRoot("viewer");
                    logger.info("tool choice is viewer");
                    break;
                default:
                    // Print statement corresponding case
                    System.out.println("no match");
            }
        }
        logger.info("out of miPaletteNewActionHandler");
    }

    public void miPaletteSaveActionHandler(ActionEvent actionEvent) {
        logger.info("into miPaletteSaveActionHandler");
        ToolState currentToolState = Context.getInstance().currentToolState();
        currentToolState.create();
        logger.info("out of miPaletteSaveActionHandler");
    }

    public void miPaletteOpenActionHandler(ActionEvent actionEvent) throws IOException {
        logger.info("into miPaletteOpenActionHandler");
        App.setRoot("viewer");
        logger.info("out of miPaletteSaveActionHandler");
    }
}

