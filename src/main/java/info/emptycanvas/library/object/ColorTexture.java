/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.emptycanvas.library.object;

import java.awt.Color;

/**
 *
 * @author manu
 */
public class ColorTexture implements ITexture
{
    private Color color;
    public ColorTexture()
    {
        color = Color.BLACK;
    }
    public ColorTexture(Color c)
    {
        color = c;
    }
    public void color(Color c)
    {
        color = c;
    }
    public Color color()
    {
        return color;
    }

    public int getColorAt(double x, double y) {
        return color.getRGB();
    }

    public Color getMaillageTexturedColor(int numQuadX, int numQuadY, double x, double y) {
        return color;
    }

    public void timeNext(long milli) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void timeNext() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
