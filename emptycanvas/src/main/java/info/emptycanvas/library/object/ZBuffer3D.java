package info.emptycanvas.library.object;



public interface ZBuffer3D extends ZBuffer
{
	public ECBufferedImage imageGauche();
	public ECBufferedImage imageDroite();
	public int LR();
	public void LR(int lr);
	public void genererEtRetourner(Scene sc);
}