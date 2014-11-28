package info.emptycanvas.library.tribase;

import info.emptycanvas.library.object.Point2D;

public class SurfaceCercle implements Surface {

	private double r;
	private int n;

	public SurfaceCercle(double r) {
		this.r = r;
	}
	
	@Override
	public void setMax(int n) {
		this.n = n;
	}

	@Override
	public Point2D getPoint(int i) {
		return new Point2D(
					Math.cos(2*Math.PI*r/getLength()),
					Math.sin(2*Math.PI*r/getLength())
				);
	}

	private double getLength() {
			return n;
	}

}
