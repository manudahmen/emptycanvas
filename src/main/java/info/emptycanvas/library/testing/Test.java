package info.emptycanvas.library.testing;

import info.emptycanvas.library.object.Camera;
import info.emptycanvas.library.object.Scene;
import info.emptycanvas.library.object.ZBuffer;
import java.io.File;
import java.util.ArrayList;
import java.util.Properties;

/**
 *
 * @author Manuel DAHMEN
 */
public interface Test  extends Runnable {
    public Scene scene();
    public void camera(Camera c);
    public Camera camera();
    public void testScene() throws Exception;
    public void testScene(File f) throws Exception;
    public void prezbuffer(ZBuffer z);
    public void run();
    public boolean loop();
    public boolean nextFrame();
    public void loop(boolean isLooping);
    String getTemplate();
    String applyTemplate(String template, Properties properties);
    public void publishResult();
    public void afterRender();

    public ArrayList<TestInstance.Parameter> getInitParams();
 
}
