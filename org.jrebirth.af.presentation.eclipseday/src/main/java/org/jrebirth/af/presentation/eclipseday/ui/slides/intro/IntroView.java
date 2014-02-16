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
package org.jrebirth.af.presentation.eclipseday.ui.slides.intro;

import java.util.Random;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.BoxBlurBuilder;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import org.jrebirth.af.core.exception.CoreException;
import org.jrebirth.af.presentation.eclipseday.resources.EDPImages;
import org.jrebirth.af.presentation.ui.base.AbstractSlideView;

/**
 * 
 * The class <strong>IntroView</strong>.
 * 
 * The custom introduction slide.
 * 
 * @author Sébastien Bordes
 */
public final class IntroView extends AbstractSlideView<IntroModel, StackPane, IntroController> {

    /** The intro name transition. */
    private FadeTransition fadeTransition;

    /** The transition. */
    private ParallelTransition transition;

    /** The sequence. */
    private SequentialTransition sequence;

    /** The typewriter animation. */
    private Timeline typeWriter;

    /** The label shown. */
    private Label label;

    /** The label shown. */
    private ImageView background;

    /**
     * Default Constructor.
     * 
     * @param model the IntroModel view model
     * 
     * @throws CoreException if build fails
     */
    public IntroView(final IntroModel model) throws CoreException {
        super(model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initView() {

        this.background = ImageViewBuilder.create()
                .image(EDPImages.PLACE_BG.get())
                .build();

        getRootNode().getChildren().add(this.background);
        StackPane.setAlignment(this.background, Pos.CENTER);

        this.label = LabelBuilder
                .create()
                // .text(getModel().getSlide().getTitle().replaceAll("\\\\n", "\n").replaceAll("\\\\t", "\t"))
                .styleClass("label", "introTitle")
                // .font(EDPFonts.TYPEWRITER.get())
                // .textFill(Color.BLACK) // web("#7F0055")
                .textFill(Color.WHITE)
                .alignment(Pos.CENTER_LEFT)
                .minWidth(800)
                .minHeight(500)
                .build();

        // this.label.getStyleClass().add("introTitle");

        // label.scaleXProperty().bind(Bindings.divide(getModel().getLocalFacade().getGlobalFacade().getApplication().getStage().widthProperty(), 1024));
        // label.scaleYProperty().bind(Bindings.divide(getModel().getLocalFacade().getGlobalFacade().getApplication().getStage().heightProperty(), 768));

        getRootNode().getStyleClass().clear();
        getRootNode().getStyleClass().add(getModel().getSlide().getStyle());

        getRootNode().getChildren().add(this.label);
        StackPane.setAlignment(this.label, Pos.CENTER);

        this.fadeTransition = new FadeTransition(Duration.seconds(2), this.background);
        this.fadeTransition.setFromValue(1.0f);
        this.fadeTransition.setToValue(0.0f);
        this.fadeTransition.setCycleCount(1);
        this.fadeTransition.setAutoReverse(false);

        final BoxBlur fx = BoxBlurBuilder.create()
                .width(1)
                .height(1)
                .iterations(3)
                .build();
        this.background.setEffect(fx);

        final Timeline blurFx = new Timeline(
                new KeyFrame(Duration.seconds(0), new KeyValue(fx.widthProperty(), 1), new KeyValue(fx.heightProperty(), 1)),
                new KeyFrame(Duration.seconds(3), new KeyValue(fx.widthProperty(), 15.0), new KeyValue(fx.heightProperty(), 15.0))
                );

        this.transition = new ParallelTransition();
        this.transition.getChildren().addAll(this.fadeTransition, blurFx);

        this.typeWriter = new Timeline();
        String content = "";
        Duration d = Duration.ZERO;
        final Random r = new Random();
        for (final char c : getModel().getSlide().getTitle().replaceAll("\\\\n", "\n").replaceAll("\\\\t", "\t").toCharArray()) {

            d = d.add(Duration.millis(r.nextInt() % 90 + 140));
            this.typeWriter.getKeyFrames().add(new KeyFrame(d, new KeyValue(this.label.textProperty(), new String(content + c))));
            content += c;
        }

        this.sequence = new SequentialTransition();
        this.sequence.getChildren().addAll(this.transition, this.typeWriter);
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
        this.sequence.playFromStart();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reload() {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hide() {
        // Nothing to do yet
    }

}
