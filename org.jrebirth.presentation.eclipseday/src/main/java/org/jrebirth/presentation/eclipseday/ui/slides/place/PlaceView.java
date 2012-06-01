package org.jrebirth.presentation.eclipseday.ui.slides.place;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.BoxBlurBuilder;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.presentation.ui.base.AbstractSlideView;

/**
 * 
 * The class <strong>IntroView</strong>.
 * 
 * The custom introduction slide.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision: 72 $ $Author: sbordes $
 * @since $Date: 2011-10-17 22:26:35 +0200 (Mon, 17 Oct 2011) $
 */
public final class PlaceView extends AbstractSlideView<PlaceModel, StackPane, PlaceController> {

    /** The intro name transition. */
    private FadeTransition fadeTransition;

    private ParallelTransition transition;

    /**
     * Default Constructor.
     * 
     * @param model the IntroModel view model
     * 
     * @throws CoreException if build fails
     */
    public PlaceView(final PlaceModel model) throws CoreException {
        super(model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customInitializeComponents() {

        // getRootNode().getParent().setOpacity(0);

        final ImageView toulouse = ImageViewBuilder.create()
                .image(loadImage("images/intro/eclipseDay_intro.jpg"))
                // .fitHeight(Double.MAX_VALUE)
                // .fitWidth(Double.MAX_VALUE)
                .build();

        final BoxBlur fx = BoxBlurBuilder.create()
                .width(1)
                .height(1)
                .iterations(3)
                .build();
        toulouse.setEffect(fx);

        getRootNode().getChildren().add(toulouse);

        getRootNode().getStyleClass().add(getModel().getSlide().getStyle());

        this.fadeTransition = new FadeTransition(Duration.seconds(4), getRootNode());
        this.fadeTransition.setFromValue(0.0f);
        this.fadeTransition.setToValue(1.0f);
        this.fadeTransition.setCycleCount(1);
        this.fadeTransition.setAutoReverse(false);

        final Timeline blurFx = new Timeline(
                new KeyFrame(Duration.seconds(0), new KeyValue(fx.widthProperty(), 15.0), new KeyValue(fx.heightProperty(), 15.0)),
                new KeyFrame(Duration.seconds(3), new KeyValue(fx.widthProperty(), 1), new KeyValue(fx.heightProperty(), 1))
                );

        this.transition = new ParallelTransition();
        this.transition.getChildren().addAll(this.fadeTransition, blurFx);

    }

    /**
     * @return Returns the fadeTransition.
     */
    FadeTransition getFadeTransition() {
        return this.fadeTransition;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doStart() {
        this.transition.playFromStart();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doReload() {
        // Nothing to do yet

    }

}
