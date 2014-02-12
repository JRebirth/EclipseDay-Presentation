/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2013
 * Contact : sebastien.bordes@jrebirth.org
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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

import org.jrebirth.af.core.exception.CoreException;
import org.jrebirth.af.presentation.ui.base.AbstractSlideView;
import org.jrebirth.presentation.eclipseday.resources.EDPImages;

/**
 * The class <strong>IntroView</strong>.
 * 
 * The custom introduction slide.
 * 
 * @author Sébastien Bordes
 */
public final class PlaceView extends AbstractSlideView<PlaceModel, StackPane, PlaceController> {

    /** The intro name transition. */
    private FadeTransition fadeTransition;

    /** The transition. */
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
    protected void initView() {

        // getRootNode().getParent().setOpacity(0);

        final ImageView toulouse = ImageViewBuilder.create()
                .image(EDPImages.PLACE_BG.get())
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
     * Gets the fade transition.
     * 
     * @return Returns the fadeTransition.
     */
    FadeTransition getFadeTransition() {
        return this.fadeTransition;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        this.transition.playFromStart();
    }

}
