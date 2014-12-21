/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.emptycanvas.library.object;

import com.xuggle.mediatool.IMediaReader;
import com.xuggle.mediatool.MediaListenerAdapter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.mediatool.event.IVideoPictureEvent;
import info.emptycanvas.library.testing.TestObjet;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import org.monte.media.avi.AVIReader;

/**
 *
 * @author manu
 */
public class VideoTexture extends MediaListenerAdapter implements ITexture {
    private ECBufferedImage image;
    private VideoPipe vp ;
    private final Object e = null;
    private int maxBuffSize = 25;
    private final ArrayList<BufferedImage> bufferImages= new ArrayList<BufferedImage>(maxBuffSize);
    IMediaReader reader;

    
   public VideoTexture(String film)
   {
       this.file = new File(film);
   }
    public VideoTexture() {
        
    }
    public BufferedImage getImage() {
        return image;
    }

    public void setImage(ECBufferedImage image) {
        this.image = image;
    }




    private Color couleur = Color.BLACK;
    private String nom = "texture";
    private String nomFichier = "image.png";
    private Scene scene;
    private int track = 0;
    private File file = null;
    private Color transparent = Color.WHITE;


    public Color couleur(double rx, double ry) {
            int x = (int) (rx * image.getWidth());
            int y = (int) (ry * image.getHeight());
            if (x < 0) {
                x = 0;
            }
            if (y < 0) {
                y = 0;
            }
            if (x >= image.getWidth()) {
                x = image.getWidth() - 1;
            }
            if (y >= image.getHeight()) {
                y = image.getHeight() - 1;
            }
            return new Color(image.getRGB(x, y));
    }



    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public Color getCouleur() {
        return couleur;
    }

   
    /**
     * Quadrilatère numQuadX = 1, numQuadY = 1, x, y 3----2 ^2y |\ | | 4 |
     * 0--\-1 1 -> 2x
     *
     * @param numQuadX nombre de maillage en x
     * @param numQuadY nombre de maillage en y
     * @param x valeur de x
     * @param y valeur de y
     * @return
     */
    public Color getMaillageTexturedColor(int numQuadX, int numQuadY, double x,
            double y) {
   
        int xi = ((int) (1d * image.getWidth() * x));
        int yi = ((int) (1d * image.getHeight() * y));
        if (xi >= image.getWidth()) {
            xi = image.getWidth() - 1;
        }
        if (yi >= image.getHeight()) {
            yi = image.getHeight() - 1;
        }
        Color c = new Color(image.getRGB(xi, yi));
        if(c.equals(transparent))
            return null;
        else
            return c;
    }

    /**
     * +|--r11->/-----| y^r12^ 0/1 ^r12^ -|-----/<-r11--|+x
     *
     * @param numQuadX
     * @param numQuadY
     * @param x
     * @param y
     * @param r11
     * @param r12
     * @param numTRI
     * @return
     */
    public Color getMaillageTRIColor(int numQuadX, int numQuadY, double x,
            double y, double r11, double r12, int numTRI) {
        double dx = 0;
        double dy = 0;
        if (numTRI == 0) {
            dx = r11;
            dy = r12;
        } else if (numTRI == 1) {
            dx = 1 - r11;
            dy = r12;
        }
        int xi = ((int) ((((int) x + dx) / numQuadX + Math.signum(numTRI - 0.5)
                * image.getWidth())));
        int yi = ((int) ((((int) y + dy) / numQuadY * image.getHeight())));
        return new Color(image.getRGB(xi, yi));
    }


    public void setNomFichier(String nomFichier) {
        this.nomFichier = nomFichier;
    }

    public String getNomFichier() {
        return nomFichier;
    }

    public int getColorAt(double a, double b) {
        int c = new Color( image
                .getRGB((int) (a * image
                .getWidth()),
                (int) (b * image
                .getHeight()))
        ) .getRGB();
        if(new Color(c).equals(transparent))
            return 0xFFFFFF00;
        else
            return c;
    }

    void scene(Scene scene) {
        this.scene = scene;
    }
    
    public void setTransparent(Color WHITE) {
        this.transparent = WHITE;
    }

    public void timeNext(long milli) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void timeNext() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public class VideoPipe extends Thread
    {
        
        private boolean verrou;
        private BufferedImage image;
        private boolean fin;
        
        private boolean verrou() {
            return verrou;
        }

        private void enleverVerrou() {
            verrou = false;
        }

        private void mettreVerrou() {
            verrou = true;

        }

        public void attendre() {
            mettreVerrou();
        }
        public void reprendre()
        {
            enleverVerrou();
        }
        public void fin()
        {
            this.fin = true;
        }
        public void add(BufferedImage bi)
        {
            while(isProcesseeding())
            {
                
            }
            image = bi;
        }
        
        public boolean isProcesseeding()
        {
            return verrou();
        }

        @Override
        public void run() {
            while(!fin)
            {
                
            }
        }

        private BufferedImage imageSuivante() {
            if(image!=null)
            {
                BufferedImage ret = image;
                reprendre();
                return ret;
            }
            else
                return null;
        }
        
    }

    /**
     * The video stream index, used to ensure we display frames from one and
     * only one video stream from the media container.
     */
    private int mVideoStreamIndex = -1;
    static class sTestObjet extends TestObjet{
            
            @Override
            public void ginit() {
                VideoTexture videoTexture;
                videoTexture = new VideoTexture("D:\\Bibliothèque\\Films\\Cinema anglais" + "\\" + "Sailor.Et.Lula.1990.FRENCH.BRRiP.XViD.AC3-HuSh.avi");
                TRI tri = new TRI(new Point3D [] {P.n(0,0,0),P.n(0,1,0),P.n(1,1,0)}, videoTexture);

                scene().add(tri);
                
                Camera c = new Camera(Point3D.Z, Point3D.O0);
                
                
            }
            
        }
    public static void testing ()
    {
        TestObjet to;
        to = new sTestObjet();
        to.setMaxFrames(2000);
        to.loop(true);
        
        new Thread(to).start();
    }
    public static void main(String[] args) {
        /*if (args.length <= 0) {
        throw new IllegalArgumentException(
        "must pass in a filename as the first argument");
        }*/
        // create a new mr. decode and capture frames
        testing();

        
        
       
    }

    public void onVideoPicture(IVideoPictureEvent event) {
        
    }

  public boolean nextFrame()
    {
        vp.imageSuivante();
        return true;
    }
  
  
}

