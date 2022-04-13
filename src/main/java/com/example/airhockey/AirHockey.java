package com.example.airhockey;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.File;

public class AirHockey extends Application {
    @Override
    public void start(Stage stage){
        stage.setTitle("AIR HOCKEY");
        Setting set = new Setting();
        // create grid
        GridPane grid =new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // create pan
        Circle pan = new  Circle(0, 0, 15, Color.DARKORANGE);

        // add pan to grid
        grid.add(pan, 0, 0);

        //create scene
        Scene scene=new Scene(grid, 400, 400, Color.ALICEBLUE);

        // add scene to stage
        stage.setScene(scene);

        //set title to grid
        Text sceneTitle = new Text("AIR HOCKEY");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(sceneTitle, 0, 0, 2, 1);

        // add label to grid
        Label NameLab = new Label("Your Name");
        grid.add(NameLab, 0, 1);
        TextField name = new TextField();
        grid.add(name, 1, 1);

        // add welcome music
        String baseMusic=new File("C:\\Users\\kouro\\OneDrive\\Documents" +
                "\\Air\\src\\main\\media\\oceana.mp3").toURI().toString();
        MediaPlayer mediaPlayer = new MediaPlayer(new Media(baseMusic));
        mediaPlayer.setCycleCount(Timeline.INDEFINITE);
        mediaPlayer.play();

        //start button
        Button start = new Button("               Start                 ");
        HBox hbStart = new HBox(10);
        hbStart.setAlignment(Pos.BOTTOM_RIGHT);
        hbStart.getChildren().add(start);
        grid.add(hbStart, 1, 2);

        //start action
        start.setOnAction((event) -> stage.setScene(set.setting()));

        // rules button
        Button rules = new Button("Read Rules");
        HBox hbRules= new HBox(10);
        hbRules.setAlignment(Pos.BOTTOM_RIGHT);
        hbRules.getChildren().add(rules);
        grid.add(hbRules, 0, 2);

        //rules action
        rules.setOnAction((event) -> {
            Label rule = new Label ("""
                    Hi\s
                     Move Your Mouse\s
                     On Your Pan And
                    Move Your Pan !\s
                     Hit The Disc\s
                     And Try To Goal\s
                     No Time\s
                     If Your Score\s
                     Will Be 10\s
                     YOU WIN!\s
                     CLICK On Start\s
                     LET'S DO THIS!""");
            grid.add(rule, 2, 0);

        });

        //mute button
        Button mute = new Button("MUTE");
        HBox hbMute= new HBox(10);
        hbMute.setAlignment(Pos.BOTTOM_RIGHT);
        hbMute.getChildren().add(mute);
        grid.add(hbMute, 0, 3);

        //mute action
        mute.setOnAction((event) -> mediaPlayer.stop());


        //show stage
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}


