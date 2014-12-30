package info.emptycanvas.library.object;

import java.awt.Color;

/**
 *
 * @author Atelier
 */
public interface Lumiere {
       public Color getCouleur(Color base, Point3D p, Point3D n);
       public Color getCouleur(Point3D p);
}