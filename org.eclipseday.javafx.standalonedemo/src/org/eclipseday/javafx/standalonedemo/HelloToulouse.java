package org.eclipseday.javafx.standalonedemo;

import java.io.IOException;

import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SceneBuilder;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * 
 * The class <strong>HelloToulouse</strong>.
 * 
 * Display an Hello Toulouse Message.
 * 
 * @author Sébastien Bordes
 */
public class HelloToulouse extends Application {

    /**
     * {@inheritDoc}
     * 
     * @throws IOException
     */
    @Override
    public void start(final Stage primaryStage) throws IOException {

        // Build the root node
        final StackPane parent = new StackPane();// StackPaneBuilder.create().build();

        // Build the scene
        final Scene scene = SceneBuilder.create()
                .root(parent)
                .width(400)
                .height(300)
                .fill(Color.CORAL)
                .build();

        // Build the hello label
        final Label label = LabelBuilder.create()
                .text("Hello Toulouse")
                // .style("-fx-font-size:24px")
                .textFill(Color.ANTIQUEWHITE)
                .build();

        // Manage stylesheet
        label.getStyleClass().add("icon");
        scene.getStylesheets().add("style.css");

        // Add an fxml node
        final Node fxmlNode = (Node) FXMLLoader.load(Thread.currentThread().getContextClassLoader().getResource("powered.fxml"));

        final String content = "Lorem ipsum sin dolor amut";
        final Text text = new Text(10, 20, "");

        final Animation animation = new Transition() {
            {
                setCycleDuration(Duration.millis(2000));
            }

            @Override
            protected void interpolate(final double frac) {
                final int length = content.length();
                final int n = Math.round(length * (float) frac);
                text.setText(content.substring(0, n));
            }

        };

        // Add visual components to the root node
        parent.getChildren().addAll(label, fxmlNode, text);

        // Manage layout constraints
        StackPane.setAlignment(parent.getChildren().get(0), Pos.CENTER);
        StackPane.setAlignment(parent.getChildren().get(1), Pos.BOTTOM_RIGHT);

        // Display the default window
        primaryStage.setTitle("JavaFX Demo");
        primaryStage.setScene(scene);
        primaryStage.show();

        scene.addEventFilter(KeyEvent.ANY, new EventHandler<KeyEvent>() {

            @Override
            public void handle(final KeyEvent arg0) {
                System.out.println("filter1");

            }
        });

        scene.addEventFilter(KeyEvent.ANY, new EventHandler<KeyEvent>() {

            @Override
            public void handle(final KeyEvent arg0) {
                System.out.println("filter2");

            }
        });

        scene.addEventHandler(KeyEvent.ANY, new EventHandler<KeyEvent>() {

            @Override
            public void handle(final KeyEvent arg0) {
                System.out.println("handler");
            }
        });

        animation.play();
    }

    /**
     * Launch the JavaFX application.
     * 
     * @param args program arguments
     */
    public static void main(final String[] args) {
        launch(args);
    }
}
