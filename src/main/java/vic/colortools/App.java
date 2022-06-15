package vic.colortools;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {
    private static final Logger logger = LogManager.getLogger();
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        logger.info("Initializing ColortoolsFX3 with primary");
        scene = new Scene(loadFXML("primary"),1024,600);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        Image icon = new Image(App.class.getResourceAsStream("logoTriangles96.png"));
        stage.getIcons().add(icon);
        stage.setTitle("Color Tools - 0.0.1");
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}