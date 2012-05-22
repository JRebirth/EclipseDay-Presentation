package org.eclipseday.javafx.rcpdemo.view;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swt.FXCanvas;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public class PieChartView extends ViewPart {

    public static final String ID = "org.eclipseday.javafx.rcpdemo.view.PieChartView";

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

        final PieChart pc = new PieChart();
        // setup chart
        pc.setTitle("Top 5 Systèmes d'exploitation - Janvier 2012 - France");
        pc.setLegendSide(Side.RIGHT);
        pc.setAnimated(true);
        pc.setLabelLineLength(30);
        pc.setStartAngle(180);

        root.setCenter(pc);

        final List<PieChart.Data> dataList = new ArrayList<>();
        dataList.add(new PieChart.Data("Win7", 44.67));
        dataList.add(new PieChart.Data("WinXP", 23.05));
        dataList.add(new PieChart.Data("Win Vista", 17.68));
        dataList.add(new PieChart.Data("MacOSX", 10.38));
        dataList.add(new PieChart.Data("Linux", 1.95));

        final List<PieChart.Data> dataList2 = new ArrayList<>();
        dataList2.add(new PieChart.Data("Win7", 44.67));
        dataList2.add(new PieChart.Data("WinXP", 23.05));
        dataList2.add(new PieChart.Data("Win Vista", 17.68));
        dataList2.add(new PieChart.Data("MacOSX", 10.38));
        dataList2.add(new PieChart.Data("Linux", 1.95));

        final ObservableList<PieChart.Data> data = FXCollections.observableArrayList(dataList);

        final EventHandler<ActionEvent> removeLast = new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent t) {
                data.remove(data.size() - 1);
            }
        };

        final EventHandler<ActionEvent> addNext = new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent t) {
                data.add(dataList2.get(data.size()));
            }
        };

        final Timeline loading = new Timeline();
        loading.getKeyFrames().addAll(
                new KeyFrame(Duration.millis(500), new KeyValue(pc.dataProperty(), data)),
                new KeyFrame(Duration.millis(2000), removeLast),
                new KeyFrame(Duration.millis(2500), removeLast),
                new KeyFrame(Duration.millis(3000), removeLast),
                new KeyFrame(Duration.millis(3500), removeLast),
                new KeyFrame(Duration.millis(4000), removeLast),

                new KeyFrame(Duration.millis(4500), addNext),
                new KeyFrame(Duration.millis(5000), addNext),
                new KeyFrame(Duration.millis(5500), addNext),
                new KeyFrame(Duration.millis(6000), addNext),
                new KeyFrame(Duration.millis(6500), addNext)

                );
        loading.play();

    }

    /**
     * Passing the focus request to the viewer's control.
     */
    @Override
    public void setFocus() {
        this.canvas.setFocus();
    }
}