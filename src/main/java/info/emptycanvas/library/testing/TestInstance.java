/***
Global license : 

    Microsoft Public Licence
    
    author Manuel Dahmen <ibiiztera.it@gmail.com>

***/


package info.emptycanvas.library.testing;

import java.util.ArrayList;

/**
 *
 * @author Manuel Dahmen <ibiiztera.it@gmail.com>
 */
public abstract class TestInstance {
    protected TestObjet test;
    public class Parameter
    {
        public String name;
        public Class zclass;
        public Object value;
    }
    
    public void theTest(TestObjet test)
    {
        this.test = test;
    }
    
    public abstract ArrayList<Parameter> getInitParameters();
    
    public abstract boolean newInstance(ArrayList<Parameter> parameter);
    
    public abstract ArrayList<Parameter> getDynParameters();
    
    public abstract Parameter getDynParameter(String name);

    public abstract boolean setDynParameter(Parameter parameter);
    
}
