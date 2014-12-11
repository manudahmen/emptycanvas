/***
Global license : 

    CC Attribution
    
    author Manuel Dahmen <ibiiztera.it@gmail.com>

***/


package info.emptycanvas.library.object.temps;

/**
 *
 * @author Manuel Dahmen <ibiiztera.it@gmail.com>
 */
public interface PropertiesUpdater {
    public Model modele();
    public void addMouvements(AnimationMouvements mvt);
    public void updatePropeties();
    public void updateModel();
}
