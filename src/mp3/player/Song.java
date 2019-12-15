package mp3.player;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.metadata.TikaCoreProperties;
import org.apache.tika.metadata.XMPDM;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.apache.tika.sax.BodyContentHandler;

import java.io.File;
import java.io.FileInputStream;


/**
 * @author ServantOfEvil
 */
public class Song {
    private boolean isPlaying;
    private String artist_album;
    private String track_no;
    private String title_trackArtist;
    private String duration;
    //private MediaPlayer player;

    Song(File file) {
        try {
           /* new JFXPanel();
            player = new MediaPlayer(new Media(file.toURI().toString()));
            player.setAutoPlay(true);*/
            BodyContentHandler handler = new BodyContentHandler();
            Metadata metadata = new Metadata();
            FileInputStream input = new FileInputStream(file);
            ParseContext pContext = new ParseContext();

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
