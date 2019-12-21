package mp3.player;


import javafx.util.Duration;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.Vector;

/**
 * @author ServantOfEvil
 */
public class Playlist {
    private Vector<Song> songs;
    private JTable table;
    private Song SongisPlay;
    private int local=0;
    private Vector<Duration> listDuration;


    Playlist(JTable table){
        songs = new Vector<>();
        this.table = table;
        songs.add(new Song(new File("/Users/ngovinh/Downloads/Sound.mp3")));
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

    public Song getSongisPlay(){return SongisPlay;}

    public void StartPlaylist(){
        if(!(songs == null)) {
            SongisPlay = songs.get(local);
            SongisPlay.StartPlay();
            listDuration.add(SongisPlay.getControl().getDuration());
        }
    }

    public void next(){
            if(local<songs.size()){
                local++;
                SongisPlay.getControl().exit();
                SongisPlay = songs.get(local);
                SongisPlay.StartPlay();
            }
    }

    public void Prev(){
        if(local>0){
            local++;
            SongisPlay.getControl().exit();
            SongisPlay = songs.get(local);
            SongisPlay.StartPlay();
        }
    }



}
