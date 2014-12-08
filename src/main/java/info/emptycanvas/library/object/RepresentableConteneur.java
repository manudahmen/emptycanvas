/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.emptycanvas.library.object;

import info.emptycanvas.library.object.TColor;
import info.emptycanvas.library.object.MODObjet;
import info.emptycanvas.library.object.Barycentre;
import info.emptycanvas.library.object.Representable;
import info.emptycanvas.library.object.TRIObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Atelier
 */
public class RepresentableConteneur extends Representable {

    private List<Representable> re = new ArrayList<Representable>();

    public RepresentableConteneur(Representable[] r) {
        re.addAll(Arrays.asList(r));
    }

    public RepresentableConteneur() {
    }

    public void add(Representable r) {
        re.add(r);
    }

    public void remove(Representable r2) {
        re.remove(r2);
    }

    public void clear() {
        re.clear();
    }

    public List<Representable> getListRepresentable() {
        return re;
    }

    public Iterator<Representable> iterator() {
        return re.iterator();
    }

    @Override
    public String toString() {
        String s = "conteneur (\n\n";

        Iterator<Representable> rs = iterator();

        while (rs.hasNext()) {
            Representable next = rs.next();

            s += next.toString();
        }


        s += "\n\n)\n\n";

        return s;
    }
}