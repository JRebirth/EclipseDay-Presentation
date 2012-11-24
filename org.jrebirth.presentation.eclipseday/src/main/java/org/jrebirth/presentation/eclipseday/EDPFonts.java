package org.jrebirth.presentation.eclipseday;

import javafx.scene.text.Font;

import org.jrebirth.core.resource.ResourceBuilders;
import org.jrebirth.core.resource.font.FontEnum;
import org.jrebirth.core.resource.font.FontBuilder;
import org.jrebirth.core.resource.font.FontParams;
import org.jrebirth.core.resource.font.RealFont;

/**
 * The class <strong>PrezFonts</strong>.
 * 
 * @author Sébastien Bordes
 * 
 */
public enum EDPFonts implements FontEnum {

    /** The splash font. */
    PAGE(new RealFont(EDPFontsLoader.DINk, 24)),

    /** The slide title font. */
    SLIDE_TITLE(new RealFont(EDPFontsLoader.Harabara, 30)),

    /** The slide subtitle font. */
    SLIDE_SUBTITLE(new RealFont(EDPFontsLoader.Harabara, 20)),

    /** The typewriter font. */
    TYPEWRITER(new RealFont(EDPFontsLoader.Report_1942, 72)),

    /** The typewriter font. */
    TYPEWRITER2(new RealFont(EDPFontsLoader.OliJo, 72)),

    /** The splash font. */
    SPLASH(new RealFont(EDPFontsLoader.BorisBlackBloxx, 30));

    /**
     * Default Constructor.
     * 
     * @param fontParams the font size
     */
    EDPFonts(final FontParams fontParams) {
        factory().storeParams(this, fontParams);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Font get() {
        return factory().get(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FontBuilder factory() {
        return (FontBuilder) ResourceBuilders.FONT_BUILDER.use();
    }

}
