/***
Global license : 

    CC Attribution
    
    author Manuel Dahmen <ibiiztera.it@gmail.com>

***/


package info.emptycanvas.library.object;

/**
 *
 * @author Manuel Dahmen <ibiiztera.it@gmail.com>
 */
public class AnimationCreationTime implements Runnable
{
    protected long temps;
    protected int intervalleEntreDeuxImages;
    public AnimationCreationTime(long temps)
    {
        this.temps = temps;
    }

    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
