package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setResizable(false);

        GridPane pane = new GridPane();
        pane.setAlignment(Pos.TOP_LEFT);
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setPadding(new Insets(25, 25, 25, 25));

        TextField original = new TextField();
        original.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 12));
        pane.add(original, 0, 0);

        TextField interval = new TextField();
        interval.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 12));
        pane.add(interval, 1, 0);

        TextField transposed = new TextField();
        original.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 12));
        transposed.setEditable(false);
        pane.add(transposed, 0, 1);

        Button button = new Button("Transpose");
        button.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 12));
        pane.add(button, 2, 0);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                transposed.setText(Transposer.transpose(original.getText(), Integer.parseInt(interval.getText())));
            }
        });

        primaryStage.setScene(new Scene(pane/*, 300, 275*/));
        primaryStage.show();
    }


    public static void main(String[] args) {
        System.out.println(-1%12);
        launch(args);
    }
}
