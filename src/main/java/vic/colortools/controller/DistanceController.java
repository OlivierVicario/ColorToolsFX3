package vic.colortools.controller;

import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import vic.colortools.App;
import vic.colortools.Context;
import vic.colortools.model.DistanceState;
import vic.colortools.model.ToolState;
import vic.colortools.toolservice.DistanceToolService;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import static vic.colortools.colorspace.RGB.rgbToHex;

public class DistanceController implements Initializable {


    @FXML
    private ColorPicker colorPicker;
    @FXML
    private CheckBox cbBackground;
    @FXML
    private Label labelDistance;
    @FXML
    private Slider distanceSlider;
    @FXML
    private Label labelSteps;
    @FXML
    private Slider stepsSlider;
    @FXML
    private Label labelGap;
    @FXML
    private Slider gapSlider;
    @FXML
    private FlowPane flowPane;
    @FXML
    private Slider edgeSlider;
    @FXML
    private Button btnSave;

    DistanceToolService distanceToolService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        distanceToolService = new DistanceToolService();
        distanceSlider.valueProperty()
                .addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
                    labelDistance.setText("distance : " + new_val.intValue());
                    miseAJour(null);
                });
        stepsSlider.valueProperty()
                .addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
                    labelSteps.setText("steps : " + new_val.intValue());
                    miseAJour(null);
                });
        gapSlider.valueProperty()
                .addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
                    labelGap.setText("gap : " + new_val.intValue());
                    miseAJour(null);
                });
        edgeSlider.valueProperty()
                .addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
                    miseAJour(null);
                });
        labelDistance.setText("distance : " + (int) distanceSlider.getValue());
        labelSteps.setText("steps : " + (int) stepsSlider.getValue());
        labelGap.setText("gap : " + (int) gapSlider.getValue());
        miseAJour(null);
    }

    // Event Listener on ColorPicker[#colorPicker].onAction
    @FXML
    public void miseAJour(ActionEvent event) {

        ArrayList<String> hexs = distanceToolService.findHexs(distanceSlider.getValue(), colorToRgb(colorPicker.getValue()),
                stepsSlider.getValue(), gapSlider.getValue());

        //currentToolState Update
        ToolState currentState = Context.getInstance().currentToolState();
        for (String hex : hexs) {
            currentState.hexs.add(hex);
        }
        currentState.parameters[0][0] = "distance";
        currentState.parameters[0][1] = String.valueOf(distanceSlider.getValue());
        currentState.parameters[1][0] = "centre";
        currentState.parameters[1][1] = rgbToHex(colorToRgb((colorPicker.getValue())));
        currentState.parameters[2][0] = "step";
        currentState.parameters[2][1] = String.valueOf(stepsSlider.getValue());
        currentState.parameters[3][0] = "gap";
        currentState.parameters[3][1] = String.valueOf(gapSlider.getValue());

        //layout update
        ArrayList<Color> colors = hexsToColors(hexs);

        if (cbBackground.isSelected()) {
            Background bkFill = new Background(new BackgroundFill(colorPicker.getValue(), null, null));
            flowPane.setBackground(bkFill);
        } else {
            flowPane.setBackground(null);
        }
        flowPane.getChildren().clear();
        Rectangle[] rectangles = new Rectangle[colors.size()];
        int id = 0;
        double edge = edgeSlider.getValue();
        for (Color c : colors) {
            Rectangle r = new Rectangle(edge, edge);
            r.setFill(c);
            r.setStroke(Color.TRANSPARENT);
            r.setStrokeWidth(1);
            rectangles[id] = r;
            id++;
        }
        flowPane.getChildren().addAll(rectangles);
    }

    public void updateCurrentToolState() {

    }

    // Event Listener on CheckBox[#cbBackground].onAction
    @FXML
    public void cbBackgroundActionHandler(ActionEvent event) {
        miseAJour(null);
    }

    // Event Listener on Button[#btnSave].onAction
    @FXML
    public void btnSaveActionHandler(ActionEvent event) {
        WritableImage snapshot;

        snapshot = flowPane.snapshot(null, null);

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save the snapshot as...");
        // String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new
        // Date());
        String fileName = colorPicker.getValue().toString() + "_";
        fileName += Math.round(distanceSlider.getValue()) + "_";
        fileName += Math.round(stepsSlider.getValue()) + "_";
        fileName += Math.round(gapSlider.getValue());
        // fileChooser.setInitialFileName(timeStamp + ".png");
        fileChooser.setInitialFileName(fileName + ".png");
        ArrayList<String> ext = new ArrayList<String>();
        ext.add(".png");
        fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("Snapshot", ext));
        File file = fileChooser.showSaveDialog(flowPane.getScene().getWindow());
        if (file != null) {
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", file);
            } catch (IOException e) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("IOException");
                alert.setContentText(e.getLocalizedMessage());
                alert.showAndWait();
            }
        }
    }

    public int[] colorToRgb(Color color) {
        int[] rgb = new int[]{(int) Math.round(color.getRed() * 255), (int) Math.round(color.getGreen() * 255), (int) Math.round(color.getBlue() * 255)};
        return rgb;
    }

    public ArrayList<Color> hexsToColors(ArrayList<String> hexs) {
        ArrayList<Color> colors = new ArrayList<Color>();
        for (String hex : hexs) {
            Color color = Color.web(hex);
            colors.add(color);
        }
        return colors;
    }


}
