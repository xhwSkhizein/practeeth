package com.hongv.mp4parse;

import org.mp4parser.IsoFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
 * create by hongwei at 2018/4/12
 */
public class GetDurationTest {
    public static void main(String[] args) throws IOException {

        long t1 = System.currentTimeMillis();
        String filename = "/Users/hongweixu/Downloads/hjrj.mp4";


//        http://v.up.wealoha.com/v/QLOEdIQ7tL4JKOcc_AgfTQ.mp4
//        http://v.up.wealoha.com/v/G3q0L6y2booJKOcc_AgfTQ.mp4
//        http://v.up.wealoha.com/v/dbna_YllJxYJKOcc_AgfTQ.mp4            21s
        URL url = new URL("http://v.up.wealoha.com/v/4mKxQNdENnwJKOcc_AgfTQ.mp4"); //http://v.up.wealoha.com/v/Th9E-8Tn6i0JKOcc_AgfTQ.mp4
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestProperty("Range", "bytes=0-20480");
        urlConnection.connect();

        InputStream inputStream = urlConnection.getInputStream();
        ReadableByteChannel readableByteChannel = Channels.newChannel(inputStream);

        IsoFile isoFile = new IsoFile(readableByteChannel);
        double lengthInSeconds = (double)
                isoFile.getMovieBox().getMovieHeaderBox().getDuration() /
                isoFile.getMovieBox().getMovieHeaderBox().getTimescale();
        long t2 = System.currentTimeMillis();

        System.out.println(t2 - t1);

        System.err.println(lengthInSeconds);


        System.out.println("fin.");
        System.exit(0);
    }

}
