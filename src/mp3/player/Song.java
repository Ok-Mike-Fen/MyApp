package mp3.player;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
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
    private boolean isPlaying = false;
    private String artist_album = "";
    private String track_no = "";
    private String title_trackArtist = "";
    private String duration = "00:00";
    private Player player;
    private String path;
    private FileInputStream input;
    private boolean canResume;
    private int totalLength = 0;
    private int lastPosition = 0;

    Song(File file) {
        title_trackArtist = file.getName().substring(0, file.getName().lastIndexOf(".")) + " - Unknown";
        try {
            BodyContentHandler handler = new BodyContentHandler();
            Metadata metadata = new Metadata();

            FileInputStream input = new FileInputStream(file);
            this.path = file.getAbsolutePath();
            ParseContext pContext = new ParseContext();

            Mp3Parser parser = new Mp3Parser();

            parser.parse(input, handler, metadata, pContext);
            duration = getDurationInString(Double.parseDouble(metadata.get(XMPDM.DURATION)));

            artist_album = metadata.get(XMPDM.ALBUM_ARTIST).concat(" - ").concat(metadata.get(XMPDM.ALBUM));
            track_no = twoDigitsForm(metadata.get(XMPDM.TRACK_NUMBER));
            title_trackArtist = metadata.get(TikaCoreProperties.TITLE).concat(" - ").concat(metadata.get(XMPDM.ARTIST));
        } catch (Exception e) {
            /**
             * some songs might not contain any info - an exception can be thrown from Tika lib, just ignore it
             */
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

    public void pause() {
        if (isPlaying) {
            try {
                lastPosition = input.available();
                player.close();
                isPlaying = false;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Can't pause");
        }
    }

    public void resume() {
        if (!isPlaying) {
            try {
                input = new FileInputStream(path);
                totalLength = input.available();
                input.skip(totalLength - lastPosition);
                player = new Player(input);
            } catch (IOException | JavaLayerException e) {
                e.printStackTrace();
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
        if (isPlaying) player.close();
        isPlaying = false;
    }


    public void play() {
        try {
            input = new FileInputStream(path);
            player = new Player(input);
            this.totalLength = input.available();
        } catch (Exception e) {
            e.printStackTrace();
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
