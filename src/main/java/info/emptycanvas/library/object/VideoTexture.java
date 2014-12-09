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
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author manu
 */
public class VideoTexture extends MediaListenerAdapter implements ITexture {

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
    private VideoPipe vp;
    private final Object e;
    private final ArrayList<BufferedImage> bufferImages;
    private int maxBuffSize = 25;
    IMediaReader reader;

    /**
     * The video stream index, used to ensure we display frames from one and
     * only one video stream from the media container.
     */
    private int mVideoStreamIndex = -1;

    public static void main(String[] args) {
        /*if (args.length <= 0) {
        throw new IllegalArgumentException(
        "must pass in a filename as the first argument");
        }*/
        // create a new mr. decode and capture frames

        VideoTexture videoTexture;
        videoTexture = new VideoTexture("D:\\Bibliothèque\\Films\\Cinema anglais" + "\\" + "Sailor.Et.Lula.1990.FRENCH.BRRiP.XViD.AC3-HuSh.avi");
    }

    /**
     * Construct a DecodeAndCaptureFrames which reads and captures frames from a
     * video file.
     *
     * @param filename the name of the media file to read
     */
    public VideoTexture(String filename) {
        e = null;

        vp = new VideoPipe();
        
        
        // create a media reader for processing video
        IMediaReader reader = ToolFactory.makeReader(filename);

        // stipulate that we want BufferedImages created in BGR 24bit color space
        reader.setBufferedImageTypeToGenerate(BufferedImage.TYPE_3BYTE_BGR);

        // note that DecodeAndCaptureFrames is derived from
        // MediaReader.ListenerAdapter and thus may be added as a listener
        // to the MediaReader. DecodeAndCaptureFrames implements
        // onVideoPicture().
        reader.addListener(this);

        // read out the contents of the media file, note that nothing else
        // happens here.  action happens in the onVideoPicture() method
        // which is called when complete video pictures are extracted from
        // the media source
        while (reader.readPacket() == null) {
            do {
            } while (false);
        }
        bufferImages = new ArrayList<BufferedImage>();
    }

    @Override
    public void onVideoPicture(IVideoPictureEvent event) {
        try {
            // if the stream index does not match the selected stream index,
            // then have a closer look

            if (event.getStreamIndex() != mVideoStreamIndex) {
                // if the selected video stream id is not yet set, go ahead an
                // select this lucky video stream

                if (-1 == mVideoStreamIndex) {
                    mVideoStreamIndex = event.getStreamIndex();
                } // otherwise return, no need to show frames from this video stream
                else {
                    return;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (event != null) {
            try {
                vp.add(event.getImage());
                vp.attendre();
            } catch (NullPointerException ex) {

            }
        }

    }

    public int getColorAt(double x, double y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Color getMaillageTexturedColor(int numQuadX, int numQuadY, double x, double y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     public void timeNext(long milli) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void timeNext() {
        nextFrame();
    }

  public boolean nextFrame()
    {
        vp.imageSuivante();
        return true;
    }
}

