/*

    Vous êtes libre de :

*/
package info.emptycanvas.library.object;

public interface Trajectoire 
{
	public int frame();
	public void frame(int f);
	public void tDebut(double t);
	public double tDebut();
	public void tFin(double t);
	public double tFin();
	public double t();
	public void t(double t);
	
	public Point3D point();
	public boolean asuivant(); 
}