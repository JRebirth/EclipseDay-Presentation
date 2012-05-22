package org.eclipseday.javafx.standalonedemo;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SceneBuilder;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

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

        // Add visual components to the root node
        parent.getChildren().addAll(label, fxmlNode);

        // Manage layout constraints
        StackPane.setAlignment(parent.getChildren().get(0), Pos.CENTER);
        StackPane.setAlignment(parent.getChildren().get(1), Pos.BOTTOM_RIGHT);

        // Display the default window
        primaryStage.setTitle("JavaFX Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
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
