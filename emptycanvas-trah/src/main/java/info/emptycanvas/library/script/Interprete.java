/*

    Vous êtes libre de :

*/
package info.emptycanvas.library.script;

public interface Interprete {

    public void setRepertoire(String r);

    public Object interprete(String text, int pos) throws InterpreteException;

    public int getPosition();

    public InterpreteConstants constant();

    public void setConstant(InterpreteConstants c);

}
