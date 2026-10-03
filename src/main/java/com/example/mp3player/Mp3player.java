package com.example.mp3player;

import javafx.application.Application;
import javafx.geometry.Pos; // Layout positioning dependency
import javafx.scene.control.Button; // Button dependency
import javafx.scene.layout.HBox; // Layout dependency
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.Scene;
import javafx.scene.text.Text; // Text dependency
import javafx.stage.Stage; // Main method that runs whole media
import java.io.File;

public class Mp3player extends Application {
    @Override
    public void start(Stage stage) {
        // 1. Locate the file and convert it to a valid URI string
        File file = new File("sample.mp3");
        String sourceUri = file.toURI().toString();

        // 2. Instantiate the Media, MediaPlayer, and MediaView
        Media media = new Media(sourceUri);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true); // Automatically starts when ready

        // 2. Create buttons
        Button pauseButton = new Button("Pause");
        Button resumeButton = new Button("Resume");

        //3. Set Buttons' actions
        pauseButton.setOnAction(e -> {
            mediaPlayer.pause();
            System.out.println("Paused");
        });
        resumeButton.setOnAction(e -> {
            mediaPlayer.play();
            System.out.println("Resumed");
        });

        // 3. Add the button to a layout pane
        HBox root = new HBox(10); // Create a HBox with 10 pixels between buttons
        root.setAlignment(Pos.CENTER); // Align buttons to the center
        root.getChildren().addAll(pauseButton, resumeButton); // Add buttons to the layout container

        // 3. Get metadata + add it t layout pane
        mediaPlayer.setOnReady(() -> {
            String artist = (String) media.getMetadata().get("artist"); // Get artist name
            String title = (String) media.getMetadata().get("title"); // Get song title
            Text songTitle = new Text(50, 50, title);
            Text songArtist = new Text(50, 50, artist);
            root.getChildren().addAll(songTitle, songArtist); // Add title and artist to layout pane
        });

        // 4. Create the scene with the layout pane and define window dimensions
        Scene scene = new Scene(root, 700, 300);

        stage.setScene(scene);
        stage.setTitle("MP3 Player");
        stage.show();
    }


}
