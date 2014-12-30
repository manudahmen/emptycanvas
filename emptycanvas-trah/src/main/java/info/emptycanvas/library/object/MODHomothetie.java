/*

    Vous êtes libre de :

*/
package info.emptycanvas.library.object;

	public class MODHomothetie {
		private Point3D centre;
		private double f;
		public void centre(Point3D p)
		{
			this.centre = p;
		}
		public Point3D centre()
		{
			return centre;
		}
		public void facteur(double d)
		{
			this.f = d;
			return;
		}
		public double facteur()
		{
			return f;
		}
	}
