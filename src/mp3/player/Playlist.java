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
    private Song playedSong;
    private int playedSongIndex = 0;


    Playlist(JTable table) {
        songs = new Vector<>();
        this.table = table;
    }

    public void addSongs(Song song) {
        songs.add(song);
    }

    public Vector<Song> getSongs() {
        return songs;
    }

    public JTable getTable() {
        return table;
    }

    public Song getPlayedSong() {
        return playedSong;
    }

    public int getPlayedSongIndex() {
        return playedSongIndex;
    }

    public void next() {
        if (playedSongIndex < songs.size() - 1) {
            playedSongIndex++;
            play(playedSongIndex);
        }
    }

    public void prev() {
        if (playedSongIndex > 0) {
            playedSongIndex--;
            play(playedSongIndex);
        }
    }

    public void randomPlay() {
        if (songs.size() < 1) return;
        Random s1 = new Random();
        playedSongIndex = s1.nextInt(songs.size());
        play(playedSongIndex);
    }

    public void play(int index) {
        if (index == -1) index = 0;
        if (playedSong != null) playedSong.stop();
        if (index < 0 || index >= songs.size()) return;
        this.playedSongIndex = index;
        playedSong = songs.elementAt(index);
        playedSong.play();
    }

    public void setPlayedSong(Song playedSong){
        this.playedSong = playedSong;
    }
}
