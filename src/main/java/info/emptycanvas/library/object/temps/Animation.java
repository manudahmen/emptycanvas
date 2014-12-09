/*

 Vous êtes libre de :

 */
package info.emptycanvas.library.object.temps;

import info.emptycanvas.library.object.Scene;
import info.emptycanvas.library.object.ZBuffer;
import info.emptycanvas.library.object.ZBufferImpl;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import javax.imageio.ImageIO;

/**
 *
 * @author Manuel
 */
public class Animation {

    protected Dimension resolution;

    protected Scene scene;

    private int noImage;

    private String repertoire;

    private String patternFichier;

    private String nom;
    ZBuffer z;

    public Animation(Scene s, Dimension dim) {
        this.resolution = dim;
        this.scene = s;
        z = new ZBufferImpl((int) resolution.getWidth(), (int) resolution.getHeight());
    }

}
