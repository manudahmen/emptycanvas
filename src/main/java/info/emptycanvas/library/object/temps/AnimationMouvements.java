/*

    Vous êtes libre de :

*/
/**
 * 
 */
package info.emptycanvas.library.object.temps;

import info.emptycanvas.library.object.Matrix33;
import info.emptycanvas.library.object.Point2D;
import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.object.Position;
import info.emptycanvas.library.object.Representable;
import java.awt.Dimension;

/**
 * @author MANUEL DAHMEN
 * 
 *         dev
 * 
 *         14 déc. 2011
 * 
 */
public class AnimationMouvements {
    private Animation animation;
    private FonctionTemps fonction;
    
    private Representable representable;
    
    private FonctionTemps fp;
    
    public interface FonctionTemps
    {
        public boolean fonctionTempsD0(double time);
        public Double fonctionTempsD1(double time);
        public Integer fonctionTempsD1I(double time);
        public Point2D fonctionTempsD2(double time);
        public Point3D fonctionTempsD3(double time);
        public Matrix33 fonctionTempsD33(double time);
        public Position fonctionTempsD43(double time);

        public Representable fonctionRepresentableN(double time);
    }
    
    
    public AnimationMouvements(Representable representable, FonctionTemps fp) {
        this.representable = representable;
        fonction = fp;
    }
    
    protected void setFonctionPosition(FonctionTemps fp)
    {
        this.fonction = fp;
    }
    protected Position position(double time)
    {
        return fonction.fonctionTempsD43(time);
    }
}
