package org.eclipseday.javafx.rcpdemo.view;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swt.FXCanvas;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.control.Slider;
import javafx.scene.control.SliderBuilder;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.ColorAdjustBuilder;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPaneBuilder;
import javafx.scene.layout.HBox;
import javafx.scene.layout.HBoxBuilder;
import javafx.scene.paint.Color;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public class EffectView extends ViewPart {

    public static final String ID = "org.eclipseday.javafx.rcpdemo.view.EffectView";

    private static final String CUSTOM_CAR = "Custom_Car.jpg";

    private FXCanvas canvas;

    private BorderPane rootNode;

    private static final NumberFormat DECIMAL = new DecimalFormat("0.0");

    /**
     * This is a callback that will allow us to create the viewer and initialize it.
     */
    @Override
    public void createPartControl(final Composite parent) {

        this.canvas = new FXCanvas(parent, SWT.NONE);

        this.rootNode = new BorderPane();
        final Scene scene = new Scene(this.rootNode, 800, 600, Color.WHITE);
        this.canvas.setScene(scene);

        final ColorAdjust fx = ColorAdjustBuilder.create()
                .hue(0.5)
                .saturation(0.4)
                .brightness(0.5)
                .contrast(0.5)
                .build();

        displayImage(CUSTOM_CAR, fx);

        displaySliders(
                buildSlider("Hue", fx.hueProperty(), -1.0, 1.0),
                buildSlider("Saturation", fx.saturationProperty(), -1.0, 1.0),
                buildSlider("Brightness", fx.brightnessProperty(), -1.0, 1.0),
                buildSlider("Contrast", fx.contrastProperty(), -1.0, 1.0)

        );
        BorderPane.setAlignment(this.rootNode.getCenter(), Pos.CENTER);

    }

    /**
     * TODO To complete.
     * 
     * @param buildSlider
     */
    private void displaySliders(final Node... node) {
        this.rootNode.setRight(FlowPaneBuilder.create().orientation(Orientation.VERTICAL).alignment(Pos.CENTER).children(node).build());
        BorderPane.setMargin(this.rootNode.getRight(), new Insets(10));
        BorderPane.setAlignment(this.rootNode.getRight(), Pos.CENTER);

        this.rootNode.getRight().setStyle("-fx-border-color:#000000;-fx-border-width:2px;");
    }

    /**
     * TODO To complete.
     * 
     * @param string
     * @param fx
     */
    private void displayImage(final String image, final Effect fx) {
        final ImageView iv = ImageViewBuilder.create()
                .effect(fx)
                .image(new Image(this.getClass().getResourceAsStream(image)))
                .build();
        BorderPane.setMargin(iv, new Insets(10));
        this.rootNode.setCenter(iv);
    }

    /**
     * TODO To complete.
     * 
     * @param string
     * @param levelProperty
     * @param i
     * @param d
     */
    private HBox buildSlider(final String name, final DoubleProperty property, final double min, final double max) {

        final Slider slider = SliderBuilder.create()
                .min(min)
                .max(max)
                .maxWidth(80)
                .value(property.doubleValue())
                .blockIncrement(0.1)
                .build();

        slider.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(final MouseEvent event) {
                event.consume();
            }

        });

        final Label valueLabel = LabelBuilder.create().text(name).build();

        slider.adjustValue(property.doubleValue());
        property.bind(slider.valueProperty());

        slider.valueProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(final ObservableValue<? extends Number> observable, final Number oldValue, final Number newValue) {
                valueLabel.setText(DECIMAL.format(newValue));
            }
        });

        return HBoxBuilder.create()
                .children(
                        LabelBuilder.create().text(name).build(),
                        slider,
                        valueLabel
                )
                .build();
    }

    /**
     * Passing the focus request to the viewer's control.
     */
    @Override
    public void setFocus() {
        this.canvas.setFocus();
    }
}