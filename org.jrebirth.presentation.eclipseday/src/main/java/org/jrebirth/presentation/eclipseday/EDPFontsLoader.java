package org.jrebirth.presentation.eclipseday;

import org.jrebirth.core.resource.font.FontName;

/**
 * The class <strong>FontsLoader</strong>.
 * 
 * @author Sébastien Bordes
 */
public enum EDPFontsLoader implements FontName {

    /** . */
    DINk,

    /** . */
    OliJo,

    /** . */
    Harabara,

    /** . */
    Report_1942,

    /** . */
    arfmoochikncheez,

    /** . */
    Neuton_Cursive,

    BorisBlackBloxx;

    /**
     * {@inheritDoc}
     */
    @Override
    public String get() {
        return name();
    }

}
