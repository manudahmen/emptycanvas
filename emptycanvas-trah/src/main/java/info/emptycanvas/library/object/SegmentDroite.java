/*

    Vous êtes libre de :

*/
package info.emptycanvas.library.object;

import info.emptycanvas.library.lighting.Infini;
import java.awt.Color;

/**
 * @author MANUEL DAHMEN
 *
 * dev
 *
 * 15 oct. 2011
 *
 */
public class SegmentDroite extends  Representable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1390105309325238894L;
	private Point3D origine;
	private Point3D extremite;
        private Color c;
	public SegmentDroite(Point3D p1, Point3D p2)
	{
		this.setOrigine(p1);
		this.setExtremite(p2);
	}
	public SegmentDroite(Point3D camera, Point3D camera2, Color couleur) {
		this(camera, camera2);
		this.c = couleur;
	}
	
	/**
	 * @return the origine
	 */
	public Point3D getOrigine() {
		return origine;
	}
	/**
	 * @param origine the origine to set
	 */
	public void setOrigine(Point3D origine) {
		this.origine = origine;
	}
	/**
	 * @return the extremite
	 */
	public Point3D getExtremite() {
		return extremite;
	}
	/**
	 * @param extremite the extremite to set
	 */
	public void setExtremite(Point3D extremite) {
		this.extremite = extremite;
	}

    public Color getC() {
        return c;
    }

    public void setC(Color c) {
        this.c = c;
    }

    @Override
    public String toString() {
        return "Droite (\n\t" + origine.toString()+"\n\t"+extremite.toString()+"\n\t( "+c.getRed()+" , "+c.getGreen()+" , "+c.getBlue()+" )\n)\n";
    }

    public Representable place(MODObjet aThis) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Point3D calculerPoint3D(double d) {
        return origine.plus(extremite.moins(origine).mult(d));
    }

    @Override
    public void texture(ITexture tc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean supporteTexture() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
	@Override
	public void position(Barycentre p) {
        throw new UnsupportedOperationException("Not supported yet.");
	}

    public TColor texture() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Barycentre position() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Representable intersection(TRI tri) {
        return intersect3D_RayTriangle(this, tri);
    }


public double SMALL_NUM  = 0.00000001; // anything that avoids division overflow
// prodScalaire product (3D) which allows vector operations in arguments

 


// intersect3D_RayTriangle(): find the 3D intersection of a ray with a triangle
//    Input:  a ray R, and a triangle T
//    Output: *I = intersection point (when it exists)
//    Return: -1 = triangle is degenerate (a segment or point)
//             0 =  disjoint (no intersect)
//             1 =  intersect in unique point I1
//             2 =  are in the same plane
private Representable
intersect3D_RayTriangle( SegmentDroite R, TRI T)
{
    Point3D    u, v, n = null;              // triangle vectors
    Point3D    dir, w0, w;           // ray vectors
    double     r, a, b;              // params to calc ray-plane intersect

    Point3D I;
    // get triangle edge vectors and plane normal
    u = T.getSommet()[1].moins(T.getSommet()[0]);
    v = T.getSommet()[2].moins(T.getSommet()[0]);
    n = u.prodVect(v);
    if (n.equals(Point3D.O0))             // triangle is degenerate
        return Infini.Default;                  // do not deal with this case

    dir = R.getOrigine().moins(R.getExtremite());              // ray direction vector
    w0 = R.getOrigine().moins(T.getSommet()[0]);
    a = -n.prodScalaire(w0);
    b = n.prodScalaire(dir);
    if (Math.abs(b) < SMALL_NUM) {     // ray is  parallel to triangle plane
        if (a == 0)                 // ray lies in triangle plane
            return T;
        else return Infini.Default;              // ray disjoint from plane
    }

    // get intersect point of ray with triangle plane
    r = a / b;
    if (r < 0.0)                    // ray goes away from triangle
        return Infini.Default;                   // => no intersect
    // for a segment, also test if (r > 1.0) => no intersect

    I = R.getOrigine().plus(dir.mult(r));            // intersect point of ray and plane

    // is I inside T?
    double    uu, uv, vv, wu, wv, D;
    uu = u.prodScalaire(u);
    uv = u.prodScalaire(v);
    vv = v.prodScalaire(v);
    w  = I.moins(T.getSommet()[0]);
    wu = w.prodScalaire(u);
    wv = w.prodScalaire(v);
    D = uv * uv - uu * vv;

    // get and test parametric coords
    double s, t;
    s = (uv * wv - vv * wu) / D;
    if (s < 0.0 || s > 1.0)         // I is outside T
        return Infini.Default;
    t = (uv * wu - uu * wv) / D;
    if (t < 0.0 || (s + t) > 1.0)  // I is outside T
        return Infini.Default;

    return I;                       // I is in T
}
 


}
