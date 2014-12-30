/***
Global license : 

    Microsoft Public Licence
    
    author Manuel Dahmen <ibiiztera.it@gmail.com>

***/

package info.emptycanvas.library.object;

import java.awt.Color;
import java.awt.Point;


public interface ZBuffer {
    /***
     * Instancie un zbuffer. Si l'instance demandée (x, y) existe déjà,
     * elle est retournée.
     * 
     * @param x largeur (resx)
     * @param y hauteur (resy)
     * @return instance 
     */
    public ZBuffer getInstance(int x, int y);

    /***
     * Résolution X
     * @return résolution x
     */
    public int resX();

    /**
     * Résolution Y
     * @return résolution y
     */
    public int resY();

    /**
     * Retourne la scène en cours de traitement
     * @return scene
     */
    public Scene scene();

    /**
     * Assigne une nouvelle scène
     * @param s scene
     */
    public void scene(Scene s);

    /**
     * Assigne une couleur de fond
     * @param c couleur de fond
     */
    public void couleurDeFond(Color c);

    /**
     * Ajuste le facteur de zoom (cadre) en 3D isométrique
     * @param z 
     */
    public void zoom(float z);

    /**
     * 
     * @deprecated
     */
    @Deprecated
    public void dessinerContours();

    /**
     * 
     * @deprecated
     */
    @Deprecated
    public void dessinerSilhouette();

    /**
     * Dessine la scène complète
     */
    public void dessinerSilhouette3D();

    /**
     * Retourne l'image, après dessin par dessinerSilhouette3D
     * @return image
     */
    public ECBufferedImage image();

    /**
     * Dessine un point
     * @param p point
     * @param c couleur
     */
    public void plotPoint(Point3D p, Color c);

    /**
     * Dessine un point
     * @param p point
     * @param c couleur
     */
    public void testPoint(Point3D p, Color c);

    /**
     * Teste le point p
     * @param point3D point
     */
    public void testPoint(Point3D point3D);

    /**
     * Passe une nouvelle image
     */
    public void suivante();

    /**
     * Rendu en 3D isométrique
     */
    public void isometrique();

    /**
     * Rendu en 3D caméra-oeil
     */
    public void perspective();

    /**
     * Coordonnées du point sur écran
     * @param p
     * @return 
     */
    public Point coordonneesPoint2D(Point3D p);

    public Point3D coordonneesPoint3D(Point p, double zdistance);
    
    /**
     * Distance à la caméra
     * @param p
     * @return 
     */
    public double distanceCamera(Point3D p);

    /**
     * Fixe une caméra dans la scène virtuelle
     * L'appel est inutile si la cameraActive de 
     * la scène est définie.
     * @param c 
     */
    public void camera(Camera c);

    /**
     * Retourne la caméra de la scène virtuelle
     * @return 
     */
    public Camera camera();

    /**
     * Verouille le zbuffer pendant les calculs.
     * @return false si le zbuffer a été préalablement verrouillé.
     *         true si verrouillage par appel de cette méthode.
     */
    public boolean lock();

    /**
     * Déverrouille le zbuffer
     * @return true si déverrouillage. False si non-verrouillé
     */
    public boolean unlock();

    /**
     * Verrou
     * @return Verrou?
     */
    public boolean isLocked();
	
	public Point3D camera(Point3D p);

	public Color getColorAt(Point p);
        public Representable getObjectAt(Point p);

	public void dessinerStructure();

	public void isobox(boolean isBox);

	public void couleurDeFond(TColor couleurFond);
        
        public void tracerLumineux();
}
