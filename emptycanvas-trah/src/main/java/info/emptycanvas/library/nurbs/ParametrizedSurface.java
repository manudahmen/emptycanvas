/***
Global license : 

    Microsoft Public Licence
    
    author Manuel Dahmen <ibiiztera.it@gmail.com>

***/


package info.emptycanvas.library.nurbs;

import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.object.Representable;

/**
 *
 * @author Manuel Dahmen <ibiiztera.it@gmail.com>
 */
public abstract class ParametrizedSurface extends Representable{
  
    protected double start1, start2;
    protected double end1, end2;
        public int incr1 = 100;
    public int incr2 = 100;

    public abstract Point3D calculerPoint3D(double u, double v);
    public abstract Point3D calculerVitesse3D(double u, double v);
    
    public Point3D velocity(double u1, double v1, double u2, double v2)
    {
        return calculerPoint3D(u2, v2).moins(calculerPoint3D(u1, v1));
    }
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

    public double Stop1() {
        return end1;
   }
    public double Start2() {
        return start2;
    }

    public double Stop2() {
        return end2;
   }
}
