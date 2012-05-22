package org.eclipseday.javafx.rcpdemo;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipseday.javafx.rcpdemo.view.BrowserView;
import org.eclipseday.javafx.rcpdemo.view.EffectView;
import org.eclipseday.javafx.rcpdemo.view.MediaView;
import org.eclipseday.javafx.rcpdemo.view.PieChartView;

public class Perspective implements IPerspectiveFactory {

    @Override
    public void createInitialLayout(final IPageLayout layout) {
        layout.setEditorAreaVisible(false);
        layout.setFixed(false);

        layout.addView(PieChartView.ID, IPageLayout.RIGHT, IPageLayout.DEFAULT_VIEW_RATIO, IPageLayout.ID_EDITOR_AREA);

        layout.addView(BrowserView.ID, IPageLayout.LEFT, IPageLayout.DEFAULT_VIEW_RATIO, PieChartView.ID);

        layout.addView(MediaView.ID, IPageLayout.BOTTOM, IPageLayout.DEFAULT_VIEW_RATIO, BrowserView.ID);

        layout.addView(EffectView.ID, IPageLayout.BOTTOM, IPageLayout.DEFAULT_VIEW_RATIO, PieChartView.ID);
    }

}
