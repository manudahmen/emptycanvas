/***
Global license : 

    Microsoft Public Licence
    
    author Manuel Dahmen <ibiiztera.it@gmail.com>

***/


package info.emptycanvas.library.nurbs;

import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.object.Representable;
import info.emptycanvas.library.tribase.TRIObjetGenerateurAbstract;

/**
 *
 * @author Manuel Dahmen <ibiiztera.it@gmail.com>
 */
public abstract class ParametrizedSurface extends TRIObjetGenerateurAbstract{
  
    protected double start1=0, start2=0;
    protected double end1=1, end2=1;
        public int incr1 = 100;
    public int incr2 = 100;


    public abstract Point3D calculerPoint3D(double u, double v);
            ;
    public abstract Point3D calculerVitesse3D(double u, double v);
    
    public int incr1()
    {
        return incr1;
    }
    public int incr2()
    {
        return incr1;
    }
    public double Start1() {
        return start1;
    }
    public double Start2() {
        return start2;
    }

    public double Stop1() {
        return end1;
   }
    public double Stop2() {
        return end2;
   }

    public Point3D velocity(double u1, double v1, double u2, double v2)
    {
        return calculerPoint3D(u2, v2).moins(calculerPoint3D(u1, v1));
    }
    
    public Point3D coordPoint3D(int x, int y) {
        return calculerPoint3D(1.0*x/getMaxX(), 1.0*y/getMaxY());
    }

    
}
