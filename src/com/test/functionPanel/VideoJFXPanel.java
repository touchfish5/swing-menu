package com.test.functionPanel;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Screen;

import java.io.File;

public class VideoJFXPanel extends JFXPanel {
    Media media = null;
    MediaPlayer mediaPlayer = null;
    public VideoJFXPanel() {
        File videoPath = new File("src/video/video.mp4");
        media = new Media(videoPath.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);
        StackPane stackPane = new StackPane();
        Scene scene = new Scene(stackPane);

        Rectangle2D rectangle2D = Screen.getPrimary().getVisualBounds();
        mediaView.setX((rectangle2D.getWidth() - getWidth()) / 2);
        mediaView.setX((rectangle2D.getHeight() - getHeight()) / 2);

        DoubleProperty width = mediaView.fitWidthProperty();
        DoubleProperty height = mediaView.fitHeightProperty();
        width.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
        height.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));
//        mediaView.setPreserveRatio(true);

        mediaPlayer.play();
//        mediaPlayer.stop();
        stackPane.getChildren().add(mediaView);
        setScene(scene);
    }
}
