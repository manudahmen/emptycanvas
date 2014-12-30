/*

    Vous êtes libre de :

 */
package info.emptycanvas.library.object;

import java.awt.Color;


public class Cube extends Representable implements TRIGenerable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3437509687221141764L;
	private String id;
	private double mlc = 1.0;
	private Point3D position = new Point3D(0.0, 0.0, 0.0);
	private Color couleur = Color.RED;
        private boolean generated = false;
        private TRIObject ts = new TRIObject();
        
	public Cube() {
	}

	public Cube(Color c) {
		this.couleur = c;
	}

	public Cube(double mlc, Point3D position) {
		this.mlc = mlc;
		this.bc.position = position;
	}

	public Cube(double mlc, Point3D position, Color c) {
		this.mlc = mlc;
		this.bc.position = position;
		this.couleur = c;
	}

	public double getMlc() {
		return mlc*bc.agrandissement;
	}

	public void setMlc(double mlc) {
		this.mlc = mlc;
	}


	public Point3D getPosition() {
		return position;
	}

	public void setPosition(Point3D position) {
		this.position = position;
	}

	public String getId() {
		return id;
	}

	    private double [][][] coordCube = new double [][][]
            {
                
                {
                    {1.0, -1.0, -1.0},
                    {1.0, 1.0, -1.0},
                    {1.0, 1.0, 1.0},
                },
                {
                    {1.0, -1.0, -1.0},
                    {1.0, -1.0, 1.0},
                    {1.0, 1.0, 1.0},
                },
                
                {
                    {-1.0, -1.0, -1.0},
                    {-1.0, 1.0, -1.0},
                    {-1.0, 1.0, 1.0},
                },
                {{-1.0, -1.0, -1.0},
                    {-1.0, -1.0, 1.0},
                    {-1.0, 1.0, 1.0},
},{		{-1.0, 1.0, -1.0},
    {1.0, 1.0, -1.0},
    {1.0, 1.0, 1.0}
},{		{-1.0, 1.0, -1.0},
    {-1.0, 1.0, 1.0},
    {1.0, 1.0, 1.0},
},{		{-1.0, -1.0, -1.0},
    {1.0, -1.0, -1.0},
    {1.0, -1.0, 1.0}},{
    {-1.0, -1.0, -1.0},
    {-1.0, -1.0, 1.0},
    {1.0, -1.0, 1.0}
},{		{-1.0, -1.0, -1.0},
    {-1.0, 1.0, -1.0},
    {1.0, 1.0, -1.0}
},{		{-1.0, -1.0, -1.0},
    {1.0, -1.0, -1.0},
{1.0, 1.0, -1.0}
},{		{-1.0, -1.0, 1.0},
{-1.0, 1.0, 1.0},
{1.0, 1.0, 1.0}
},
{		{-1.0, -1.0, 1.0},
{1.0, -1.0, 1.0},
{1.0, 1.0, 1.0}
}
            };

	public TRIObject generate() {
            ts.clear();
            
            for(int i=0; i<12; i++)
            {
                TRI t = new  TRI(
                        new Point3D(coordCube[i][0], couleur).mult(mlc).plus(bc.position),
                         new Point3D(coordCube[i][1], couleur).mult(mlc).plus(bc.position),
                         new Point3D(coordCube[i][2], couleur).mult(mlc).plus(bc.position)
                        );
                t.setCouleur(couleur);
                
            ts.add(t);
            
            }
            return ts;
        }
 	@Override
	public String toString() {
		return "cube(\n\t" + position.toString() + "\n\t" + mlc + "\n)\n";
	}

	public Color getCouleur() {
		return couleur;
	}

	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}

	public Representable place(MODObjet aThis) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void texture(ITexture tc) {
		this.couleur = new Color(tc.getColorAt(0.5,0.5));
	}

	@Override
	public boolean supporteTexture() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public static String DATA = null;

    public TColor texture() {
        return new TColor(couleur);
    }

    public Barycentre position() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
