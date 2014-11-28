/*
 * 2013 Manuel Dahmen
 */
package info.emptycanvas.library.object;

import java.awt.Color;
import java.io.Serializable;

public class Representable implements Serializable {
    protected Barycentre bc = new Barycentre();
    protected Representable parent;
    protected Scene scene;
    protected ITexture texture = new TColor(Color.WHITE);
    private String id;
    public Representable() {
    }
    
    
      
    public void position(Barycentre p)
    {
        bc = p;
    }
    
    public Barycentre position()
    {
        return bc;
    }
    
    public Point3D calculerPoint(Point3D p)
    {
        return bc.calculer(p);
       
    }
    
    
   public void texture(ITexture tc) {
        this.texture = tc;
    }

    public boolean supporteTexture() {
        return false;
    }


    public ITexture texture() {
        return this.texture;
    }
    public void informer(Representable parent)
    {
        this.parent = parent;
    }
    public void scene(Scene scene)
    {
        this.scene = scene;
        
    }
    
    public String id()
    {
        return id;
    }
    public void id(String id)
    {
        this.id = id;
    }
    
    public void replace(String moo)
    {
        throw new UnsupportedOperationException("Operation non supportee");
    }
 }
