package info.emptycanvas.library.tribase;

import info.emptycanvas.library.object.Point3D;

public interface Chemin {
	public void setMax(int n);

	public Point3D getPoint(int i);

	public int getLength();
}
