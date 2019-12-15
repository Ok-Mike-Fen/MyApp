package mp3.player;


import javax.swing.*;
import java.util.Vector;

/**
 * @author ServantOfEvil
 */
public class Playlist {
    private Vector<Song> songs;
    private JTable table;


    Playlist(JTable table){
        songs = new Vector<>();
        this.table = table;
    }

    public void addSongs(Song song){
        songs.add(song);
    }

    public Vector<Song> getSongs(){
        return songs;
    }

    public JTable getTable(){
        return  table;
    }
}
