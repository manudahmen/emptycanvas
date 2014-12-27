/*

    Vous êtes libre de :

*/

package info.emptycanvas.library.object;

/**
 * @author MANUEL DAHMEN
 * 
 *         dev
 * 
 *         8 nov. 2011
 * 
 */
@Deprecated
public class Matrix3D {
	/**
	 * @param axe1
	 * @param axe2
	 * @param origine
	 */
	public Matrix3D(Point3D axe1, Point3D axe2, Point3D origine) {
		super();
	}

	private Point3D mult(Point3D p) {
		Point3D pr = new Point3D(p);
		return pr;

	}

	private Point3D multInv(Point3D p) {

		Point3D pr = new Point3D(p);
		return pr;
	}

	private Point3D plus(Point3D p) {
		Point3D pr = new Point3D(p);
		return pr;

	}

	private Point3D moins(Point3D p) {
		Point3D pr = new Point3D(p);
		return pr;

	}

	public Point3D changementAxe(Point3D p, double angle) {
		Point3D pr = new Point3D(p);
		return multInv(moins(rotation(plus(mult(pr)))));

	}

	private Point3D rotation(Point3D p) {
		Point3D pr = new Point3D(p);
		return pr;
	}
}
