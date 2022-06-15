package vic.colortools.controller;

import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import vic.colortools.model.ViewerState;
import vic.colortools.toolservice.ViewerToolService;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ViewerController implements Initializable {
    public AnchorPane apMaster;
    public AnchorPane apDetails;
    public AnchorPane apView;
    public VBox vbPalettesContainer;

    ViewerToolService viewerToolService = new ViewerToolService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<ViewerState> viewerStates = viewerToolService.createAllToolsStates();
        for(ViewerState viewerState:viewerStates) {
            FlowPane flowPane = new FlowPane();
            for(String hex:viewerState.hexs){
                Color color = Color.web(hex);
                Rectangle rectangle = new Rectangle(40,40,color);
                flowPane.getChildren().add(rectangle);
            }
            vbPalettesContainer.getChildren().add(flowPane);

        }
        vbPalettesContainer.requestLayout();
    }
}
