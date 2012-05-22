package org.eclipseday.javafx.rcpdemo.view;

import javafx.embed.swt.FXCanvas;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public class BrowserView extends ViewPart {

    public static final String ID = "org.eclipseday.javafx.rcpdemo.view.BrowserView";

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

        final WebView browser = new WebView();
        // load the web page
        browser.getEngine().load("file:///C://balls//balls.htm");

        root.setCenter(browser);

    }

    /**
     * Passing the focus request to the viewer's control.
     */
    @Override
    public void setFocus() {
        this.canvas.setFocus();
    }
}