package org.jrebirth.presentation.eclipseday;

import javafx.scene.paint.Color;

import org.jrebirth.core.resource.ResourceFactories;
import org.jrebirth.core.resource.color.ColorEnum;
import org.jrebirth.core.resource.color.ColorFactory;
import org.jrebirth.core.resource.color.ColorParams;
import org.jrebirth.core.resource.color.RGB255Color;
import org.jrebirth.core.resource.color.WebColor;

/**
 * The class <strong>PrezColors</strong>.
 * 
 * @author Sébastien Bordes
 * 
 */
public enum EDPColors implements ColorEnum {

    SLIDE_TITLE(new WebColor("#0088D3", 1.0)),

    SHAPE_BLUE(new WebColor("#3495CE", 1.0)),

    DROP_SHADOW(new WebColor("#000000", 0.8)),
    INNER_SHADOW(new WebColor("#FFFFFF", 0.3)),

    GRADIENT_1(new WebColor("#1AA2AC", 1.0)),
    GRADIENT_2(new WebColor("#F04F24", 1.0)),
    GRADIENT_3(new WebColor("#FFF200", 1.0)),

    SPLASH_TEXT(new RGB255Color(60, 60, 70));

    /**
     * Private Constructor.
     * 
     * @param colorParams the primitive values for the color
     */
    private EDPColors(final ColorParams colorParams) {
        factory().storeParams(this, colorParams);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Color get() {
        return factory().get(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ColorFactory factory() {
        return (ColorFactory) ResourceFactories.COLOR_FACTORY.use();
    }
}