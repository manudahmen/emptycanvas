/*

 Copyright (C) 2010-2013  DAHMEN, Manuel, Daniel

 This library is free software; you can redistribute it and/or
 modify it under the terms of the GNU Lesser General Public
 License as published by the Free Software Foundation; either
 version 2.1 of the License, or (at your option) any later version.

 This library is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 Lesser General Public License for more details.

 You should have received a copy of the GNU Lesser General Public
 License along with this library; if not, write to the Free Software
 Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA

 */
package info.emptycanvas.library.object;

import java.awt.Color;
/***
 * 
 * Classe pour les Points à trois coordonnées de type double
 * 
 * @author Manuel Dahmen
 *
 */
public class Point3D extends Representable {
	/***
	 * axe X vector
	 */
	public static final Point3D X = new Point3D(1, 0, 0);
	/***
	 * axe Y vector
	 */
	public static final Point3D Y = new Point3D(0, 1, 0);
	/***
	 * axe Z vector
	 */
	public static final Point3D Z = new Point3D(0, 0, 1);
	/***
	 * O0 origin
	 */
	public static final Point3D O0 = new Point3D(0, 0, 0);
	/***
	 * Point "Infinite" limite pour Z-Buffer
	 */
    public static final Point3D INFINI = new Point3D(Double.MAX_VALUE,Double.MAX_VALUE,Double.MAX_VALUE);

    /***
     * Distance cartésienne entre 2 points    
     * @param p1 Point1
     * @param p2 Point2
     * @return
     */
	public static double distance(Point3D p1, Point3D p2) {
		return Math.sqrt((p1.getX() - p2.getX()) * (p1.getX() - p2.getX())
				+ (p1.getY() - p2.getY()) * (p1.getY() - p2.getY())
				+ (p1.getZ() - p2.getZ()) * (p1.getZ() - p2.getZ()));
	}
	/***
	 * Rotation dans un système d'axe positionné puis retour dans le système d'axes d'origine
	 * @param axes Matrice 3x3 
	 * @param origine Point d'origine du système d'axes 
	 * @param p Point à déplacers
	 * @return Point déplacé
	 */
	public static Point3D rotation(Matrix33 axes, Point3D origine, Point3D p) {
		Point3D ret = axes.mult(p.moins(origine)).plus(origine);
		ret.texture(p.texture());
		return ret;
	}

	/**
	 * @param pa Point origine
	 * @param pb Point extrémité
	 * @return Vecteur résultant pb-pa
	 */
	public static Point3D vecteur(Point3D pa, Point3D pb) {

		return pb.plus(pa.mult(-1));
	}
	
	private Color color;
	
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	/***
	 * id
	 */
    private String id;

    /***
     * Coordonnées (x,y,z) du point
     */
	private double[] x;

	/***
	 * Pour le tracé de surface normale au point 
	 */
	protected Point3D normale;

	private Barycentre position;

	/***
	 * Constructeur Point Origine
	 */
	public Point3D() {
		x = new double[3];
	}

	/***
	 * 
	 * @param x0 x-coordonnée
	 * @param y0 y-coordonnée
	 * @param z0 z-coordonnée
	 */
	public Point3D(double x0, double y0, double z0) {
		x = new double[3];
		x[0] = x0;
		x[1] = y0;
		x[2] = z0;
	}
	/***
	 * 
	 * @param x0 x-coordonnée
	 * @param y0 y-coordonnée
	 * @param z0 z-coordonnée
	 * @param co Couleur du "point" en vue d'un dessin
	 */
	public Point3D(double x0, double y0, double z0, Color co) {
		x = new double[3];
		x[0] = x0;
		x[1] = y0;
		x[2] = z0;
		texture(new  TColor(co));
	}
	/***
	 * Initialise à partir d'un vecteur
	 * @param x0 coordonnées (3)
	 * @param co Couleur
	 */
	public Point3D(double [] x0, Color co) {
		x = x0;
		texture(new TColor(co));
	}

	public Point3D(Point3D p0) {
		x = new double[3];
		x[0] = p0.getX();
		x[1] = p0.getY();
		x[2] = p0.getZ();
	}

	@Override
	public Object clone() {
		return new Point3D(x[0], x[1], x[2]);
	}

	public double get(int i) {
		return x[i];
	}

	public double[] getDoubleArray() {
		return x;
	}

	public String getId() {
		return id;
	}

	public Point3D getNormale() {
		return normale;
	}

	public double getX() {
		return x[0];
	}

	public double getY() {
		return x[1];
	}

	public double getZ() {
		return x[2];
	}

	@Deprecated
	public Point3D homothetie(Point3D c, double d) {
		return new Point3D(this).moins(c).mult(d).plus(c);
	}

	/*
	 * public Point3D rotation(Axe axe, double angle) { Point3D src = new
	 * Point3D(this); // TODO CHECK ALGOs Point3D vectAxe =
	 * axe.getP2().plus(axe.getP1().mult(-1));
	 * 
	 * double a =
	 * (src.plus(axe.getP1().mult(-1)).prodScalaire(vectAxe))/(vectAxe
	 * .prodScalaire(vectAxe));
	 * 
	 * Point3D p =
	 * axe.getP1().plus(axe.getP1().mult(-1).plus(axe.getP2()).mult(a));
	 * 
	 * Point3D vectX = p.mult(-1).plus(src).norme1();
	 * 
	 * Point3D vectY = vectX.prodVect(vectAxe).norme1();
	 * 
	 * Point3D vectZ = vectX.prodVect(vectY);
	 * 
	 * Matrix m = new Matrix(vectX, vectY, vectZ);
	 * 
	 * Point3D pPrim = m.mult(src.plus(p.mult(-1))).plus(p); * return pPrim; }
	 */
	/*
	 * public Point3D rotation(DoubleMatrix2D m, Point3D centre) { Point3D p =
	 * new Point3D(this); return m.mult(new
	 * Point3D(this).moins(centre)).plus(centre); }
	 * 
	 * public Point3D rotation(DoubleMatrix2D m, Point3D centre, Point3D p) {
	 * return m.mult(new Point3D(p).moins(centre)).plus(centre); }
	 */
	@Deprecated
	public Point3D homothetie(Point3D c, double d, Point3D p) {
		return new Point3D(p).moins(c).mult(d).plus(c);
	}

	public Point3D modificateurs(MODRotation r, MODTranslation t,
			MODHomothetie h) {
		return this;
		// return movePoint(rotation(r.matrice(), r.centre(),
		// homothetie(h.centre(), h.facteur(), (Point3D) clone())),
		// t.vecteur());
	}

	public Point3D moins(Point3D p) {
		Point3D p1 = new Point3D(this);
		p1.setX(p1.getX() - p.getX());
		p1.setY(p1.getY() - p.getY());
		p1.setZ(p1.getZ() - p.getZ());
		return p1;
	}

	@Deprecated
	public Point3D movePoint(Point3D translation) {
		return new Point3D(this).plus(translation);
	}

	@Deprecated
	public Point3D movePoint(Point3D translation, Point3D p) {
		return new Point3D(p).plus(translation);
	}

	/*
	 * public Point3D rotation(Axe axe, double angle) { return rotation(axe,
	 * angle); }
	 */
	/***
	 * Multiplication 
	 * @param xDIFF facteur 
	 * @return 
	 */
	public Point3D mult(double xDIFF) {
		Point3D p = new Point3D(this);
		p.setX(p.getX() * xDIFF);
		p.setY(p.getY() * xDIFF);
		p.setZ(p.getZ() * xDIFF);
		return p;
	}
	/***
	 * norme d'un vecteur (distance du point à l'origine)
	 * @return
	 */
	public double norme() {
		return Math.sqrt((getX()) * (getX()) + (getY()) * (getY()) + (getZ())
				* (getZ()));
	}

	/***
	 * "direction" (norme1) 
	 * @return  Vecteur normalisé à 1
	 */
	public Point3D norme1() {
		return mult(1 / norme());
	}
	/***
	 * Ajoute @param i à chaque coordonnée
	 * @param i 
	 * @return
	 */
	public Point3D plus(double i) {
		Point3D p = new Point3D(this);
		p.setX(p.getX() + i);
		p.setY(p.getY() + i);
		p.setZ(p.getZ() + i);
		return p;
	}

	public Point3D plus(Point3D p) {
		Point3D p1 = new Point3D(this);
		p1.setX(p1.getX() + p.getX());
		p1.setY(p1.getY() + p.getY());
		p1.setZ(p1.getZ() + p.getZ());
		return p1;
	}
	/***
	 * Produit scalaire
	 * @param p2
	 * @return
	 */
	public double prodScalaire(Point3D p2) {
		return x[0] * p2.getX() + x[1] * p2.getY() + x[2] * p2.getZ();
	}
	/***
	 * produit vectoriel
	 * @param p1
	 * @return
	 */
	public Point3D prodVect(Point3D p1) {
		return new Point3D(p1.getY() * getZ() + -p1.getZ() * getY(), p1.getZ()
				* getX() - p1.getX() * getZ(), p1.getX() * getY() - p1.getY()
				* getX());
	}

	public void set(int i, double d) {
		x[i] = d;
	}

	public void setC(Color c) {
		texture(new TColor(c));
	}

	public void setId(String id) {
		this.id = ID.GEN(this);
	}

	public void setNormale(Point3D normale) {
		this.normale = normale;
	}

	public void setX(double x0) {
		x[0] = x0;
	}

	public void setY(double x0) {
		x[1] = x0;
	}

	public void setZ(double x0) {
		x[2] = x0;
	}

	public String toLongString() {
            //Color c = texture.toString();
		return "p ( \n\t(" + x[0] + " , " + x[1] + " , " + x[2] + " )\n\t("
				+ texture.toString()
				+ ")\n)\n";
	}

	@Override
	public String toString() {
		return "( " + x[0] + " , " + x[1] + " , " + x[2] + " ) ";
	}
}
