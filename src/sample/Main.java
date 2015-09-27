package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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

        HBox radioPane = new HBox();
        radioPane.setAlignment(Pos.TOP_LEFT);
        radioPane.setSpacing(10);

        Label originalLabel = new Label("Original chords:");

        Label transposedLabel = new Label("Transposed chords:");

        Label intervalLabel = new Label("Interval:");

        Text intervalError = new Text();
        intervalError.setFill(Color.FIREBRICK);

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

        final ToggleGroup b_group = new ToggleGroup();

        RadioButton rb_b = new RadioButton("B");
        rb_b.setUserData("B");
        rb_b.setToggleGroup(b_group);

        RadioButton rb_h = new RadioButton("H");
        rb_h.setUserData("H");
        rb_h.setToggleGroup(b_group);
        rb_h.setSelected(true);

        b_group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if(b_group.getSelectedToggle().getUserData().equals("B")) {
                    Transposer.setH(false);
                } else {
                    Transposer.setH(true);
                }
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
        settingsPane.getChildren().add(radioPane);

        buttonPane.getChildren().add(intervalText);
        buttonPane.getChildren().add(button);

        radioPane.getChildren().add(rb_b);
        radioPane.getChildren().add(rb_h);

        primaryStage.setScene(new Scene(topPane/*, 300, 275*/));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
