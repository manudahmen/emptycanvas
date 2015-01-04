/**
 * *
 * Global license : * Microsoft Public Licence
 *
 * author Manuel Dahmen <ibiiztera.it@gmail.com>
 *
 **
 */
package info.emptycanvas.library.nurbs;

import info.emptycanvas.library.object.Point3D;

/**
 *
 * @author Manuel Dahmen <ibiiztera.it@gmail.com>
 */
public class NurbsSurface extends ParametrizedSurface {

    /**
     * *
     *
     * degree:
     *
     * Degré de la fonction de base
     *
     * params[]:
     *
     * Tableau de noeuds weights[]: * Tableau de pondérations pour des courbes
     * NURBS rationnelles ; sinon NULL ou 1.0 pour une b-spline polynomiale.
     * c_pnts[][3]:
     *
     * Tableau de points de contrôle Définition : k = degree of basis function N
     * = number of knots, degree -2 wi = weights Ci = control points (x, y, z) *
     * wi Bi,k = basis functions Par cette équation, le nombre de points de
     * contrôle est égal à N+1.
     */
    private int degree = 1;

    @Override
    public Point3D coordPoint3D(int x, int y) {
        return calculerNurbs(1.0 * x / getMaxX(), 1.0 * y / getMaxY());
    }

    @Override
    public Point3D calculerPoint3D(double u, double v) {
        return calculerNurbs(u, v);
    }

    /**
     * *
     * "Knots"
     */
    class Intervalle {

        private final double[][] Data;
        private final int m, n;

        private Intervalle(double[][] T) {
            this.Data = T;
            m = T.length;
            n = T[0].length;
        }

        public double get(int i, int j) {
            try {
                return this.Data[i][j];
            } catch (java.lang.ArrayIndexOutOfBoundsException ex) {
                return 0;
            }
        }

        public void set(int i, int j, double v) {
            this.Data[i][j] = v;
        }
    }

    /**
     * *
     * Point3D Weight associated
     */
    class Point3DPoids {

        private final Point3D[][] points;
        private final double[][] poids;
        final int m, n;

        public Point3DPoids(Point3D [][] poins, double [][] poids) {
            this.points = poins;
            this.poids = poids;
            m = points.length;
            n = points[0].length;
        }

        private double getPoids(int i, int j) {
            return poids[i][j];
        }

        public Point3D getPoint3D(int i, int j) {
            return points[i][j];
        }

        public void set(int i, int j, Point3D p, double w) {
            if (i >= 0 && i < m && j >= 0 && j < n) {
                points[i][j] = p;
                poids[i][j] = w;
            }

        }
    }

    public NurbsSurface() {
    }

    @Override
    public Point3D calculerVitesse3D(double u, double v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void creerNurbs() {
        if (points != null && T != null && poids != null) {
            intervalle = new Intervalle(T);
            forme = new Point3DPoids(points, poids);


            for (int i = 0; i < forme.m; i++) {
                for (int j = 0; j < forme.n; j++) {
                    forme.set(i, j, points[i][j], poids[i][j]);
                }
            }
            
            
        }
    }

    public double f0sur0egal0(double t1, double t2) {
        if (t2 == 0 && t1 == 0) {
            return 0;
        } else {
            return t1 / t2;
        }
    }

    public int coefficients(int type_coord, double t) {
        for (int i = 0; i < intervalle.m; i++) {
            if ((t >= intervalle.get(type_coord, i)) && (t <intervalle.get(type_coord, i + 1) )) {
                return i;
            }
        }
        return 0;
    }

    public void setMaillage(Point3D[][] points, double[][] poids) {
        this.points = points;
        this.poids = poids;
    }

    public void setReseauFonction(double[][] T) {
        this.T = T;
    }

    public double N(int type_coord, int place, int deg, double t) {
        if (place >= intervalle.m) {
            return 0;
        }
        if (deg <= 0) {
            return 1;

        }
        return N(type_coord, place, deg - 1, t)
                * f0sur0egal0(intervalle.get(type_coord, place + 1) - t, t - intervalle.get(type_coord, place))
                + N(type_coord, place + 1, deg - 1, t)
                * f0sur0egal0(intervalle.get(type_coord, place + deg + 1) - t, t - intervalle.get(type_coord, place + 1));

    }

    public long C(int i, int n) {
        return factorielle(n) / factorielle(i) / factorielle(n - i);
    }

    protected long factorielle(int n) {
        long sum = 1;
        for (int i = 1; i <= n; i++) {
            sum *= i;
        }
        return sum;
    }

    public void setDegre(int deg) {
        this.degree = deg;
    }

    public Point3D calculerNurbs(double u, double v) {
        int M = intervalle.m + degree - 1;
        int N = intervalle.n + degree - 1;
        //System.out.println("Fact = " + M);
        double sum = 0;
        Point3D ret = Point3D.O0;
        for (int i = 0; i < forme.m; i++) {
            for (int j = 0; j < forme.n; j++) {
                double sumP = (double) (C(i, forme.m) * C(j, forme.n)) * N(type_coordX, i, degree, u) * N(type_coordY, j, degree, v);
                ret = ret.plus(points[j][i].mult(sumP));
                sum += sumP;
            }
        }
        return ret.mult(1 / sum);
    }

    @Override
    public String toString() {
        String s = "nurbs ( \n";
        for (int i = 0; i < intervalle.m; i++) {
            for (int j = 0; j < intervalle.n; j++) {
                s += "knot (" + i + "," + j + ")=" + intervalle.get(i, j) + "\n\t";
            }
        }
        for (int i = 0; i < forme.m; i++) {
            for (int j = 0; j < forme.n; j++) {
                s += "point (" + i + "," + j + ")=" + forme.getPoint3D(i, j) + "  Poids : (" + i + "," + j + ")" + forme.getPoids(i, j) + "\n\t";
            }
        }
        return s + "\n\n)";
    }

    public static final int type_coordX = 0;
    public static final int type_coordY = 1;

    private Point3D[][] points;
    private double[][] poids;
    private double[][] T;

    private Intervalle intervalle;
    private Point3DPoids forme;

}
