package org.jrebirth.presentation.eclipseday.ui.slides.node;

import org.jrebirth.presentation.ui.template.AbstractTemplateModel;

/**
 * The class <strong>HandlerModel</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision: 55 $ $Author: sbordes $
 * @since $Date: 2011-10-14 19:23:59 +0200 (Fri, 14 Oct 2011) $
 */
public final class NodeModel extends AbstractTemplateModel<NodeModel, NodeView, NodeSlideStep> {

    /**
     * {@inheritDoc}
     */
    @Override
    protected NodeSlideStep[] initializeSlideStep() {
        return NodeSlideStep.values();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showSlideStep(final NodeSlideStep slideStep) {

        switch (slideStep) {

            case WoodNode:
                getView().showWoodNode();
                break;
            case ThinkNode:
                getView().showThinkNode();
                break;
            case Tree:
                getView().showTree();
                break;
            case ThinkTree:
                getView().showThinkTree();
                break;
            default:
                getView().showSlideStep(slideStep);
        }
    }
}