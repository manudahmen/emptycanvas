package info.emptycanvas.library.object;

import java.awt.Color;

public class LumiereIdent implements Lumiere
{

	public Color getCouleur(Color base, Point3D p, Point3D n) {
		return base;
	}

    public Color getCouleur(Point3D p) {
        return getCouleur(p.getC(), p, p.getNormale());
    }
	
}
