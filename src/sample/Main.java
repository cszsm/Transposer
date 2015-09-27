package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setResizable(false);

        HBox topPane = new HBox();
        topPane.setSpacing(10);
        topPane.setPadding(new Insets(25, 25, 25, 25));
        topPane.setAlignment(Pos.TOP_LEFT);

        VBox chordPane = new VBox();
        chordPane.setAlignment(Pos.TOP_LEFT);
        chordPane.setSpacing(5);

        VBox intervalPane = new VBox();
        intervalPane.setAlignment(Pos.TOP_LEFT);
        intervalPane.setSpacing(5);

        HBox buttonPane = new HBox();
        buttonPane.setAlignment(Pos.TOP_LEFT);
        buttonPane.setSpacing(10);

//        VBox buttonPane = new VBox();
//        buttonPane.setAlignment(Pos.TOP_LEFT);

//        GridPane intervalPane = new GridPane();
//        intervalPane.setAlignment(Pos.TOP_LEFT);
//        intervalPane.setHgap(10);
//        intervalPane.setVgap(10);
//        intervalPane.setPadding(new Insets(25, 25, 25, 25));

        Font segoe = Font.font("Segoe UI", FontWeight.NORMAL, 12);

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

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    transposedText.setText(Controller.transpose(originalText.getText(), Integer.parseInt(intervalText.getText())));
                } catch (NumberFormatException e) {
                    intervalError.setText("Interval must be a number!");
                }
            }
        });

        topPane.getChildren().add(chordPane);
        topPane.getChildren().add(intervalPane);
        topPane.getChildren().add(buttonPane);

        chordPane.getChildren().add(originalLabel);
        chordPane.getChildren().add(originalText);
        chordPane.getChildren().add(transposedLabel);
        chordPane.getChildren().add(transposedText);

        intervalPane.getChildren().add(intervalLabel);
        intervalPane.getChildren().add(buttonPane);
        intervalPane.getChildren().add(intervalError);

        buttonPane.getChildren().add(intervalText);
        buttonPane.getChildren().add(button);

        primaryStage.setScene(new Scene(topPane/*, 300, 275*/));
        primaryStage.show();
    }


    public static void main(String[] args) {
//        String[] words = Splitter.split("Cm D#7 B Emaj7 \n Bm");
//        for (String string : words) {
//            System.out.println(string);
//        }
//        System.out.println();
//        System.out.println(Splitter.concat(words));
        launch(args);
    }
}
