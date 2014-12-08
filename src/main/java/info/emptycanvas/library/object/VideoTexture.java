/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.emptycanvas.library.object;

import com.xuggle.mediatool.IMediaReader;
import com.xuggle.mediatool.MediaListenerAdapter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.mediatool.demos.DecodeAndCaptureFrames;
import com.xuggle.mediatool.event.IVideoPictureEvent;
import com.xuggle.xuggler.Global;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author manu
 */
public class VideoTexture extends MediaListenerAdapter implements ITexture {

    private final Object e;
    private ArrayList<BufferedImage> bufferImages;
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
        new VideoTexture("D:\\Bibliothèque\\Films\\Cinema anglais"+"\\"+"Sailor.Et.Lula.1990.FRENCH.BRRiP.XViD.AC3-HuSh.avi");
    }

    /**
     * Construct a DecodeAndCaptureFrames which reads and captures frames from a
     * video file.
     *
     * @param filename the name of the media file to read
     */
    public VideoTexture(String filename) {
        e = null;

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
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
      if(event!=null)
          try
          {
            bufferImages.add(event.getImage());
          }
          catch(NullPointerException ex)
          {
              
          }

    }

    public int getColorAt(double x, double y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Color getMaillageTexturedColor(int numQuadX, int numQuadY, double x, double y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
