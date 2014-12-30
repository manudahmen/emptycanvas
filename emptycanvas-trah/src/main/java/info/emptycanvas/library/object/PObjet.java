/*

    Vous êtes libre de :

*/
package info.emptycanvas.library.object;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

public class PObjet extends  Representable{
private ArrayList<Point3D> points;
	private Barycentre position;
	public PObjet()
	{
		points = new ArrayList<Point3D>();
	}
	public Point3D remove(int arg0) {
		return points.remove(arg0);
	}

	public ArrayList<Point3D> getPoints() {
		return points;
	}

	public void setPoints(ArrayList<Point3D> points) {
		this.points = points;
	}

	public boolean add(Point3D arg0) {
		return points.add(position==null ? arg0 : position.calculer(arg0));
	}

	public void add(int arg0, Point3D arg1) {
		points.add(arg0, position==null ? arg1 : position.calculer(arg1));
	}

	public Point3D get(int arg0) {
		return calculerPoint(points.get(arg0));
	}

	public boolean isEmpty() {
		return points.isEmpty();
	}
	@Deprecated
	public Iterator<Point3D> iterator() {
		return points.iterator();
	}

	public boolean remove(Object arg0) {
		return points.remove(arg0);
	}

	public int size() {
		return points.size();
	}


	
	public void add(Point3D point3d, Color c) {
	}
	public String toString()
	{
		String s = "tri \n(\n\n";
		Iterator<Point3D> tris =  iterator();
		while(tris.hasNext())
		{
			s+="\n"+tris.next().toString()+"\n";
		}
		return s +"\n)\n";
	}
	public Iterable<Point3D> iterable() {
		return points;
	}


	@Override
	public void position(Barycentre p) {
		this.position = p;
		
	}

    public TColor texture() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Barycentre position() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}