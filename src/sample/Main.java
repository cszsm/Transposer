package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setTitle("Transposer");
        primaryStage.setResizable(false);

        HBox topPane = new HBox();
        topPane.setSpacing(10);
        topPane.setPadding(new Insets(25, 25, 25, 25));
        topPane.setAlignment(Pos.TOP_LEFT);

        VBox chordPane = new VBox();
        chordPane.setAlignment(Pos.TOP_LEFT);
        chordPane.setSpacing(5);

        VBox settingsPane = new VBox();
        settingsPane.setAlignment(Pos.TOP_LEFT);
        settingsPane.setSpacing(5);

        HBox buttonPane = new HBox();
        buttonPane.setAlignment(Pos.TOP_LEFT);
        buttonPane.setSpacing(10);

        HBox togglePane = new HBox();
        togglePane.setAlignment(Pos.TOP_LEFT);
        togglePane.setSpacing(10);

        Label originalLabel = new Label("Original chords:");

        Label transposedLabel = new Label("Transposed chords:");

        Label intervalLabel = new Label("Interval:");

        Text intervalError = new Text();
        intervalError.setId("interval-error");

        TextArea originalText = new TextArea();
        originalText.setPrefColumnCount(20);
        originalText.setPrefRowCount(4);

        TextArea transposedText = new TextArea();
        transposedText.setPrefColumnCount(20);
        transposedText.setPrefRowCount(4);
        transposedText.setEditable(false);

        TextField intervalText = new TextField();
        intervalText.setPrefColumnCount(4);
        intervalText.setAlignment(Pos.TOP_CENTER);

        Button button = new Button("Transpose");

        button.setOnAction(event -> {

            try {
                intervalError.setText("");
                int interval;
                if (intervalText.getText().equals("")) {
                    interval = 0;
                } else {
                    interval = Integer.parseInt(intervalText.getText());
                }
                transposedText.setText(Controller.transpose(originalText.getText(), interval));
            } catch (NumberFormatException e) {
                intervalError.setText("Interval must be a number!");
            }
        });

        final ToggleGroup toggleGroup = new ToggleGroup();

        ToggleButton bButton = new ToggleButton("B");
        bButton.setUserData("B");
        bButton.setToggleGroup(toggleGroup);

        ToggleButton hButton = new ToggleButton("H");
        hButton.setUserData("H");
        hButton.setToggleGroup(toggleGroup);
        hButton.setSelected(true);

        toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if(toggleGroup.getSelectedToggle().getUserData().equals("B")) {
                Transposer.setH(false);
            } else {
                Transposer.setH(true);
            }
        });

        topPane.getChildren().add(chordPane);
        topPane.getChildren().add(settingsPane);
        topPane.getChildren().add(buttonPane);

        chordPane.getChildren().add(originalLabel);
        chordPane.getChildren().add(originalText);
        chordPane.getChildren().add(transposedLabel);
        chordPane.getChildren().add(transposedText);

        settingsPane.getChildren().add(intervalLabel);
        settingsPane.getChildren().add(buttonPane);
        settingsPane.getChildren().add(intervalError);
        settingsPane.getChildren().add(togglePane);

        buttonPane.getChildren().add(intervalText);
        buttonPane.getChildren().add(button);

        togglePane.getChildren().add(bButton);
        togglePane.getChildren().add(hButton);

        Scene scene = new Scene(topPane);
        primaryStage.setScene(scene);
        scene.getStylesheets().add(Main.class.getResource("/sample/transposer.css").toExternalForm());
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
