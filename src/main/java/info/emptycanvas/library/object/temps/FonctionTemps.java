/**
 * *
 * Global license :  *
 * CC Attribution
 *
 * author Manuel Dahmen <ibiiztera.it@gmail.com>
 *
 **
 */
package info.emptycanvas.library.object.temps;

import info.emptycanvas.library.object.Representable;


/**
 *
 * @author Manuel Dahmen <ibiiztera.it@gmail.com>
 */
public abstract class FonctionTemps<R> {
    public abstract R fonctionTemps(double time);
    public void inject(Representable r, String name, R value)
    {
        r.setProperty(name, value);
    }
}
