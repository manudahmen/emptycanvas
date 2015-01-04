/**
 * *
 * Global license : * Microsoft Public Licence
 *
 * author Manuel Dahmen <ibiiztera.it@gmail.com>
 *
 **
 */
package info.emptycanvas.library.nurbs;

import info.emptycanvas.library.object.Camera;
import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.nurbs.NurbsSurface;
import info.emptycanvas.library.object.ColorTexture;
import info.emptycanvas.library.testing.TestObjet;
import java.awt.Color;
//import nurbs.Axes;

/**
 *
 * @author Manuel Dahmen <ibiiztera.it@gmail.com>
 */
public class TestNurbs1 extends TestObjet {

    @Override
    public void testScene() throws Exception {
        NurbsSurface n = new NurbsSurface();
        n.setMaillage(new Point3D[][]{
            {
                new Point3D(0, 1, 2),
                new Point3D(20, 30, 10),
                new Point3D(4, 4, -2)},
            {
                new Point3D(3, 2, 50),
                new Point3D(8, 254, 45),
                new Point3D(566, 4, 4)},
            {
                new Point3D(1, 22, 1),
                new Point3D(45, 7, 45),
                new Point3D(50, 7, 45)}
        }, new double[][]{
            {1, 1, 1},
            {1, 1, 1},
            {1, 1, 1}
        });

        n.setDegre(3);

        n.setReseauFonction(new double[][]{
            {0, 0.5, 1, 1, 1},
            {0, 0.5, 1, 1, 1}
        });

        n.texture(new ColorTexture(Color.WHITE));

        n.setStartU(0);
        n.setStartV(0);
        n.setEndU(1);
        n.setEndV(1);
        n.setIncrU(0.01);
        n.setIncrV(0.01);

        n.creerNurbs();

        scene().add(n);
        System.out.println(n);
        //Axes axes = new Axes();

        //scene().add(axes);
        scene().cameraActive(new Camera(Point3D.Z.mult(-10), Point3D.O0));
    }

    public static void main(String[] args) {
        TestNurbs1 n = new TestNurbs1();
        n.loop(false);
        n.setGenerate(GENERATE_MODEL);
        new Thread(n).start();
    }
}
