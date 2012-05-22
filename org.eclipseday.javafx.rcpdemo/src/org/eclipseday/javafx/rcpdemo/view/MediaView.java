package org.eclipseday.javafx.rcpdemo.view;

import java.net.MalformedURLException;
import java.net.URL;

import javafx.embed.swt.FXCanvas;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public class MediaView extends ViewPart {

    public static final String ID = "org.eclipseday.javafx.rcpdemo.view.MediaView";

    private FXCanvas canvas;

    /**
     * This is a callback that will allow us to create the viewer and initialize it.
     */
    @Override
    public void createPartControl(final Composite parent) {

        this.canvas = new FXCanvas(parent, SWT.NONE);

        final BorderPane root = new BorderPane();
        final Scene scene = new Scene(root, 800, 600, Color.WHITE);
        this.canvas.setScene(scene);

        URL mediaURL;
        try {
            mediaURL = new URL("file:///C://Strobo.flv");

            final MediaPlayer mediaPlayer = new MediaPlayer(new Media(mediaURL.toString()));
            final javafx.scene.media.MediaView mv = new javafx.scene.media.MediaView(mediaPlayer);
            mediaPlayer.setCycleCount(-1);

            mediaPlayer.play();

            root.setCenter(mv);

        } catch (final MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * Passing the focus request to the viewer's control.
     */
    @Override
    public void setFocus() {
        this.canvas.setFocus();
    }
}