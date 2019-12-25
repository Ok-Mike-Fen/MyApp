package mp3.player;

import com.sun.xml.fastinfoset.tools.FI_DOM_Or_XML_DOM_SAX_SAXEvent;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.metadata.TikaCoreProperties;
import org.apache.tika.metadata.XMPDM;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.apache.tika.sax.BodyContentHandler;

import java.io.*;


/**
 * @author ServantOfEvil
 */
public class Song {
    private boolean isPlaying = true;
    private String artist_album ;
    private String track_no ;
    private String title_trackArtist;
    private String duration ;
    private Player player;
    private String path;
    private FileInputStream FIS;//đặt tên đi vào lòng người vcl
    private BufferedInputStream BIS;
    private boolean canResume;
    private int total = 0;
    private int stopped = 0;

    Song(File file) {
        try {
            //System.out.println(file.toURI().toString());

            BodyContentHandler handler = new BodyContentHandler();
           Metadata metadata = new Metadata();

            FileInputStream input = new FileInputStream(file);
            this.path = file.getAbsolutePath();ParseContext pContext = new ParseContext();

           Mp3Parser parser = new Mp3Parser();

            parser.parse(input, handler, metadata, pContext);

            artist_album = metadata.get(XMPDM.ALBUM_ARTIST).concat(" - ").concat(metadata.get(XMPDM.ALBUM));
            track_no = twoDigitsForm(metadata.get(XMPDM.TRACK_NUMBER));
            title_trackArtist = metadata.get(TikaCoreProperties.TITLE).concat(" - ").concat(metadata.get(XMPDM.ARTIST));
            duration = getDurationInString(Double.parseDouble(metadata.get(XMPDM.DURATION)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private String twoDigitsForm(String s) {
        while (s.length() < 2) s = "0" + s;
        return s;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    private String getDurationInString(double duration) {
        double d = duration / 60000;
        int minutes = (int) d;
        int seconds = (int) ((d - minutes) * 6000) / 100;
        return minutes + ":" + seconds;
    }


    public String getArtist_album() {
        return artist_album;
    }

    public String getTrack_no() {
        return track_no;
    }

    public String getTitle_trackArtist() {
        return title_trackArtist;
    }

    public String getDuration() {
        return duration;
    }



    public void pause(){
        if(isPlaying) {
            try {
                stopped = FIS.available();
                player.close();
                FIS = null;
                BIS = null;
                player = null;
                isPlaying = false;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("Can't pause");
        }
    }

    public void resume(){
        if(!isPlaying) {
            try {
                FIS = new FileInputStream(path);
                total = FIS.available();
                FIS.skip(total - stopped);
                BIS = new BufferedInputStream(FIS);
                player = new Player(BIS);

            } catch (IOException | JavaLayerException e) {
                System.out.println("Err");
            }
            new Thread(() -> {
                try {
                    player.play();
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                }
            }).start();
            isPlaying = true;
        }
    }


    public void stop() {
        if(isPlaying) pause();
            try {
                FIS = new FileInputStream(path);
                stopped = FIS.available();
            }catch (IOException e){
                System.out.println("Can't stop");
            }

    }


    public void play(){
        try{
            FIS = new FileInputStream(path);
            BIS = new BufferedInputStream(FIS);
            player = new Player(BIS);
            this.total = FIS.available();
            System.out.println("total "+total);
        }catch(Exception e){
            System.out.println("Err-Play-1");
        }
        new Thread(() -> {
            try {
                player.play();
            } catch (JavaLayerException e) {
                e.printStackTrace();
            }
        }).start();
        isPlaying = true;
    }

    @Override
    public String toString() {
        return "Song{" +
                "isPlaying=" + isPlaying +
                ", artist_album='" + artist_album + '\'' +
                ", track_no='" + track_no + '\'' +
                ", title_trackArtist='" + title_trackArtist + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }




}
