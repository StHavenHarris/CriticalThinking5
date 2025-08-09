import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class menu extends Application {
    private TextArea textArea;
    private Color initialRandomGreenHue;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Menu Application");

        // Set up the text area
        textArea = new TextArea();
        textArea.setEditable(false);

        // Set up menu items
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");

        MenuItem showDateTime = new MenuItem("Show Date and Time");
        MenuItem saveToFile = new MenuItem("Save to File");
        MenuItem changeBackground = new MenuItem("Change background color");
        MenuItem exit = new MenuItem("Exit");

        fileMenu.getItems().addAll(showDateTime, saveToFile, changeBackground, new SeparatorMenuItem(), exit);
        menuBar.getMenus().add(fileMenu);

        // Add actions
        showDateTime.setOnAction(e -> {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            textArea.appendText("Date and Time: " + now.format(formatter) + "\n");
        });

        saveToFile.setOnAction(e -> {
            try (FileWriter writer = new FileWriter("log.txt")) {
                writer.write(textArea.getText());
                showAlert(Alert.AlertType.INFORMATION, "Success", "Content saved to log.txt");
            } catch (IOException ex) {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to save file: " + ex.getMessage());
            }
        });

        changeBackground.setOnAction(e -> {
            if (initialRandomGreenHue == null) {
                Random rand = new Random();
                float hue = 90 + rand.nextFloat() * 30; // between 90 and 120 degrees in hue
                initialRandomGreenHue = Color.hsb(hue, 0.8, 0.9);
            }
            textArea.setStyle("-fx-control-inner-background: " + toRgbString(initialRandomGreenHue) + ";");
            showAlert(Alert.AlertType.INFORMATION, "Background Changed", "Background changed to a random green hue.");
        });

        exit.setOnAction(e -> {
            primaryStage.close();
        });

        // Layout
        BorderPane root = new BorderPane();
        root.setTop(menuBar);
        root.setCenter(textArea);

        Scene scene = new Scene(root, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private String toRgbString(Color color) {
        int r = (int)(color.getRed() * 255);
        int g = (int)(color.getGreen() * 255);
        int b = (int)(color.getBlue() * 255);
        return String.format("rgb(%d, %d, %d)", r, g, b);
    }
}
