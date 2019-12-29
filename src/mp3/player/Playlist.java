package mp3.player;


import javax.swing.*;
import java.util.Random;
import java.util.Vector;

/**
 * @author ServantOfEvil
 */
public class Playlist {
    private Vector<Song> songs;
    private JTable table;
    private Song songIsPlay;
    private int local = 0;



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

    public Song getSongIsPlay(){return songIsPlay;}

    public void StartPlay(){
        if(songs!=null) {
            local = 0;
            songIsPlay = songs.get(local);
            songIsPlay.play();
        } else {
            System.out.println("Playlist Null");
        }
    }


    public void Next(){
        if (local < songs.size()-1) {
                local++;
                songIsPlay.stop();
                songIsPlay = songs.get(local);
                songIsPlay.play();
            }
    }

    public void Prev(){
        if(local>0){
            local--;
            songIsPlay.stop();
            songIsPlay = songs.get(local);
            songIsPlay.play();
        }
    }

    public void Random(){
        Random s1 = new Random();
        local = s1.nextInt(songs.size());
    }

    public void Play(int local){
        this.local = local;
        songIsPlay = songs.get(local);
        songIsPlay.play();
    }
}
