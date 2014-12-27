/*

    Vous êtes libre de :

*/
package info.emptycanvas.library.script;

@SuppressWarnings("serial")
public class InterpreteException extends Exception {
    private String repertoire;
  
	public InterpreteException(String string) {
		super(string);
	}
	public InterpreteException(Exception ex) {
		super(ex);
	}

	/**
	 * @param string
	 * @param e
	 */
	public InterpreteException(String string, Exception e) {
		super(string, e);
	}
}
