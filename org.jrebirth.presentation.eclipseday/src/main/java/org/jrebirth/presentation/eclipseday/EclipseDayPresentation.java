package org.jrebirth.presentation.eclipseday;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import org.jrebirth.core.application.AbstractApplication;
import org.jrebirth.core.resource.font.FontEnum;
import org.jrebirth.core.ui.Model;
import org.jrebirth.core.wave.Wave;
import org.jrebirth.presentation.ui.stack.StackModel;

/**
 * The class <strong>JRebirthAnalyzer</strong>.
 * 
 * The application that demonstrate how to use JRebirth Framework. It also allow to show all JRebirth events.
 * 
 * @author Sébastien Bordes
 * 
 */
public final class EclipseDayPresentation extends AbstractApplication<StackPane> {

    /**
     * Application launcher.
     * 
     * @param args the command line arguments
     */
    public static void main(final String... args) {
        Application.launch(EclipseDayPresentation.class, args);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<? extends Model> getFirstModelClass() {
        return StackModel.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getApplicationTitle() {
        return "EclipseDay - JavaFX2 + Eclipse";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customizeStage(final Stage stage) {
        stage.setFullScreen(false);
        stage.setWidth(1040);
        stage.setHeight(800);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customizeScene(final Scene scene) {

        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                scene.getStylesheets().add("style/template.css");
                // scene.getStylesheets().add("style/candle.css");

                // Manage F11 button to switch full screen
                scene.addEventFilter(javafx.scene.input.KeyEvent.KEY_RELEASED, new EventHandler<javafx.scene.input.KeyEvent>() {

                    @Override
                    public void handle(final javafx.scene.input.KeyEvent event) {
                        if (event.isControlDown()) {
                            if (event.getCode() == KeyCode.ADD || event.getCode() == KeyCode.PLUS) {
                                getScene().getRoot().setScaleX(getScene().getRoot().getScaleX() + 0.05);
                                getScene().getRoot().setScaleY(getScene().getRoot().getScaleY() + 0.05);
                                event.consume();
                            } else if (event.getCode() == KeyCode.SUBTRACT || event.getCode() == KeyCode.MINUS) {
                                getScene().getRoot().setScaleX(getScene().getRoot().getScaleX() - 0.05);
                                getScene().getRoot().setScaleY(getScene().getRoot().getScaleY() - 0.05);
                                event.consume();
                            } else if (event.getCode() == KeyCode.DIGIT0 || event.getCode() == KeyCode.NUMPAD0) {
                                getScene().getRoot().setScaleX(1.0);
                                getScene().getRoot().setScaleY(1.0);
                                event.consume();
                            }
                        }
                    }
                });
            }
        });

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<FontEnum> getFontToPreload() {
        return Arrays.asList(new FontEnum[] {
                EDPFonts.SPLASH,
                EDPFonts.TYPEWRITER
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Wave> getPreBootWaveList() {
        return Collections.emptyList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Wave> getPostBootWaveList() {
        return Collections.emptyList();
    }

}
