package info.emptycanvas.library.export;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;


import info.emptycanvas.library.object.Polygone;
import info.emptycanvas.library.object.Representable;
import info.emptycanvas.library.object.Scene;
import info.emptycanvas.library.object.TColor;
import info.emptycanvas.library.object.TRI;
import info.emptycanvas.library.object.TRIConteneur;
import info.emptycanvas.library.object.TRIObject;
import info.emptycanvas.library.tribase.TRIGenerable;
import info.emptycanvas.library.tribase.TRIObjetGenerateur;
import info.emptycanvas.library.nurbs.ParametrizedSurface;
import info.emptycanvas.library.object.RepresentableConteneur;
import java.awt.Color;

public class STLExport {
	public static void save(File file, Scene scene, boolean override)
			throws FileNotFoundException, IOException {
		if (!file.exists() || file.exists() && override)  {
			file.createNewFile();
			PrintWriter pw = new PrintWriter(new FileOutputStream(file));

				pw.println("solid O_"+scene.description);

				Iterator<Representable> it = scene.iterator();

				while (it.hasNext()) {
					Representable r = it.next();

					

					pw.print(
							traite(r)
							);
				}

                                pw.println("endsolid");
                                
				pw.close();
		}
	}

	public static String traite(ParametrizedSurface n)
	{
	    String s = "";
	    double incr1 = 1.0/n.incr1;
	        double incr2 = 1.0/n.incr2;
	        for (double i = 0; i <= 1-incr1; i +=incr1) {
	        for (double j = 0; j <= 1-incr2; j +=incr2) {
	            double u = i;
	            double v = j;
	                s+=traite(new TRI(n.calculerPoint3D(u, v),
	                        n.calculerPoint3D(u + incr1, v),
	                        n.calculerPoint3D(u + incr2, 
	                        v + incr2),                                
	                        new TColor(Color.MAGENTA)));
	                s+=traite(new TRI(n.calculerPoint3D(u, v),
	                        n.calculerPoint3D(u , v+incr2),
	                        n.calculerPoint3D(u + incr2, 
	                        v + incr2),                                
	                        new TColor(Color.MAGENTA)));
	            }

	    
	    
	}return s;
	}

	private static String traite(Polygone r) {
		String w = "facet normal 0 0 0 \n" + "outer loop\n";
		for (int s = 0; s < r.getPoints().size(); s++) {
			w += "vertex ";
			for (int c = 0; c < 3; c++)
				w += r.getPoints().get(s).get(c) + " ";
			w += "\n";
		}
		w += "endloop\n";
		return w += "endfacet\n";
	}

	private static String traite(Representable r) {
		String s = "";
		
		if(r instanceof RepresentableConteneur)
		{
			s = traite((RepresentableConteneur)r);
		}
		if(r instanceof TRIObject)
		{
			s = traite((TRIObject)r);
		}
		if(r instanceof TRIGenerable)
		{
			s = traite((TRIGenerable)r);
		}
		if(r instanceof Polygone)
		{
			s = traite((Polygone)r);
		}
		if(r instanceof TRI)
		{
			s = traite((TRI)r);
		}
		if(r instanceof TRIObjetGenerateur)
		{
			s = traite((TRIObjetGenerateur)r);
		}
		if(r instanceof TRIConteneur)
		{
			s = traite((TRIConteneur)r);
		}
		if(r instanceof ParametrizedSurface)
		{
			s = traite((ParametrizedSurface)r);
		}
		return s;
	}

	private static String traite(RepresentableConteneur r) {
		String s = "";
		Iterator<Representable> it = r.iterator();
		while (it.hasNext()) {
			s += traite(it.next());
		}
		return s;
	}

	private static String traite(TRI r) {
		String w = "facet normal 0 0 0 \n" + "outer loop\n";
		for (int s = 0; s < 3; s++) {
			w += "vertex ";
			for (int c = 0; c < 3; c++)
				w += r.getSommet()[s].get(c) + " ";
			w += "\n";
		}
		w += "endloop\n";
		return w += "endfacet\n\n";

	}

	public static String traite(TRIConteneur TC)
	{
	    String s = "";
	    
	    Iterator<TRI> it = TC.iterable().iterator();
	    
	    while(it.hasNext())
	    {
	        TRI t = it.next();
	        
	        s += traite(t);
	    }
	    
	    return s;
	}

	private static String traite(TRIGenerable r) {
		return traite(r.generate());
	}
        private static String traite(TRIObject r) {
			String s = "";
			Iterator<TRI> it = r.getTriangles().iterator();
			while(it.hasNext())
			{
				
				s+= traite(it.next());
			}
			return s;
		}
        private static String traite(TRIObjetGenerateur r) {
			String s = "";
			int x = r.getMaxX();
			int y = r.getMaxY();
			TRI[] tris = new TRI[2];
			for (int i = 0; i < x; i++)
				for (int j = 0; j < y; j++) {
					r.getTris(i, j, tris);
					s += traite(tris[0]);
					s += traite(tris[1]);

				}
			return s;
		}
}