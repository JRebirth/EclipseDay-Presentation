package org.jrebirth.presentation.eclipseday.ui.slides.node;

import javafx.animation.FadeTransitionBuilder;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransitionBuilder;
import javafx.animation.ScaleTransitionBuilder;
import javafx.animation.SequentialTransitionBuilder;
import javafx.animation.TimelineBuilder;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.BoxBlurBuilder;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.StackPaneBuilder;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.presentation.PrezFonts;
import org.jrebirth.presentation.ui.template.AbstractTemplateView;

/**
 * 
 * The class <strong>HandlerView</strong>.
 * 
 * The custom introduction slide.
 * 
 * @author Sébastien Bordes
 * 
 */
public final class NodeView extends AbstractTemplateView<NodeModel, BorderPane, NodeController> {

    private ImageView woodNode;

    private ImageView tree;

    private StackPane sp;

    private Label thinkNode;

    private Label thinkTree;

    /**
     * Default Constructor.
     * 
     * @param model the controls view model
     * 
     * @throws CoreException if build fails
     */
    public NodeView(final NodeModel model) throws CoreException {
        super(model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customInitializeComponents() {
        super.customInitializeComponents();

        this.woodNode = ImageViewBuilder.create()
                .image(loadImage("images/node/think_node.jpg"))
                .build();

        this.tree = ImageViewBuilder.create()
                .image(loadImage("images/node/think_tree.jpg"))
                .opacity(0.0)
                .build();

        getRootNode().setCenter(this.sp);

    }

    /**
     * TODO To complete.
     */
    public void showWoodNode() {

        this.sp.getChildren().add(this.woodNode);
        StackPane.setAlignment(this.woodNode, Pos.CENTER);

    }

    /**
     * TODO To complete.
     */
    public void showThinkNode() {

        this.thinkNode = LabelBuilder.create()
                .text("Think Node")
                .font(PrezFonts.SPLASH.get())
                .textFill(Color.WHITE)
                .scaleX(1000)
                .scaleY(1000)
                .build();

        this.sp.getChildren().add(this.thinkNode);
        StackPane.setAlignment(this.thinkNode, Pos.CENTER);

        ScaleTransitionBuilder.create().toX(3).toY(3).node(this.thinkNode).duration(Duration.seconds(1)).interpolator(Interpolator.LINEAR).build().play();

    }

    /**
     * TODO To complete.
     */
    public void showTree() {

        this.tree.setScaleX(1000);
        this.tree.setScaleY(1000);

        final BoxBlur fx = BoxBlurBuilder.create()
                .width(1)
                .height(1)
                .iterations(3)
                .build();
        this.tree.setEffect(fx);

        this.sp.getChildren().add(0, this.tree);
        StackPane.setAlignment(this.tree, Pos.CENTER);

        SequentialTransitionBuilder.create().children(

                FadeTransitionBuilder.create().node(this.thinkNode).duration(Duration.seconds(1)).toValue(0).build(),

                ParallelTransitionBuilder.create().children(
                        ScaleTransitionBuilder.create().toX(0.001).toY(0.001).node(this.woodNode).duration(Duration.seconds(2)).interpolator(Interpolator.LINEAR).build(),
                        TimelineBuilder.create().keyFrames(
                                new KeyFrame(Duration.seconds(0), new KeyValue(fx.widthProperty(), 15.0), new KeyValue(fx.heightProperty(), 15.0)),
                                new KeyFrame(Duration.seconds(1), new KeyValue(fx.widthProperty(), 1), new KeyValue(fx.heightProperty(), 1))
                                ).build(),
                        FadeTransitionBuilder.create().node(this.tree).fromValue(0.1).toValue(1.0).duration(Duration.millis(1300)).build(),
                        ScaleTransitionBuilder.create().toX(1).toY(1).node(this.tree).duration(Duration.seconds(2)).build()).interpolator(Interpolator.LINEAR).build()
                )
                .build().play();

    }

    /**
     * TODO To complete.
     */
    public void showThinkTree() {

        this.thinkTree = LabelBuilder.create()
                .text("Think Tree")
                .font(PrezFonts.SPLASH.get())
                .textFill(Color.WHITE)
                .scaleX(1000)
                .scaleY(1000)
                .build();

        this.sp.getChildren().add(this.thinkTree);
        StackPane.setAlignment(this.thinkTree, Pos.CENTER);

        ScaleTransitionBuilder.create().toX(3).toY(3).node(this.thinkTree).duration(Duration.seconds(1)).interpolator(Interpolator.LINEAR).build().play();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Node getContentPanel() {
        this.sp = StackPaneBuilder.create().build();
        return this.sp;
    }

}
