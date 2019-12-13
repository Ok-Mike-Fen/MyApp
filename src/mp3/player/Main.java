package mp3.player;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * @author ServantOfEvil
 */
public class Main implements ActionListener {

    private JFrame frame;
    private JTable table;

    private JMenuItem file_open;
    private JMenuItem file_addFiles;
    private JMenuItem file_addFolder;
    private JMenuItem file_newPlaylist;
    private JMenuItem file_loadPlaylist;
    private JMenuItem file_savePlaylist;
    private JMenuItem file_exit;
    private JMenuItem file_preferences;


    private JMenuItem edit_undo;
    private JMenuItem edit_redo;
    private JMenuItem edit_clear;
    private JMenuItem edit_selectAll;
    private JMenuItem edit_selection;
    private JMenuItem edit_sort;
    private JMenuItem edit_search;
    private JMenuItem edit_rmDeadItems;
    private JMenuItem edit_rmDuplicates;

    private JMenuItem view_console;
    private JMenuItem view_playlistMng;

    private JMenuItem pb_play;
    private JMenuItem pb_stop;
    private JMenuItem pb_pause;
    private JMenuItem pb_next;
    private JMenuItem pb_prev;
    private JMenuItem pb_random;
    private JCheckBoxMenuItem pb_stopAfterCurrent;
    private JMenuItem pb_order_default;
    private JMenuItem pb_order_rpTrack;
    private JMenuItem pb_order_rpPL;
    private JMenuItem pb_order_random;
    private JMenuItem pb_order_shuffleTracks;
    private JMenuItem pb_order_shuffleFolders;
    private JMenuItem pb_order_shuffleAlbums;

    private JMenuItem help_about;

    private JButton btnPlay;
    private JButton btnPause;
    private JButton btnStop;
    private JButton btnPrev;
    private JButton btnNext;
    private JButton btnPBRD;

    private JSlider sliderVolume;

    private DefaultTableModel tableModel;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Main window = new Main();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Main() {
        try {

            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {

                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        initialize();
        addControls();

    }

    private void addControls() {

    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 900, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Simple MP3 Player");

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu mnFile = new JMenu("File");
        menuBar.add(mnFile);

        file_open = new JMenuItem("Open...");
        mnFile.add(file_open);
        file_open.addActionListener(this);

        file_addFiles = new JMenuItem("Add files...");
        mnFile.add(file_addFiles);

        file_addFolder = new JMenuItem("Add folder...");
        mnFile.add(file_addFolder);

        file_newPlaylist = new JMenuItem("New playlist");
        mnFile.add(file_newPlaylist);

        file_loadPlaylist = new JMenuItem("Load playlist...");
        mnFile.add(file_loadPlaylist);

        file_savePlaylist = new JMenuItem("Save playlist...");
        mnFile.add(file_savePlaylist);

        file_preferences = new JMenuItem("Preferences");
        mnFile.add(file_preferences);

        file_exit = new JMenuItem("Exit");
        mnFile.add(file_exit);
        file_exit.addActionListener(this);

        JMenu mnNewMenu = new JMenu("Edit");
        menuBar.add(mnNewMenu);

        edit_undo = new JMenuItem("Undo");
        mnNewMenu.add(edit_undo);

        edit_redo = new JMenuItem("Redo");
        mnNewMenu.add(edit_redo);

        edit_clear = new JMenuItem("Clear");
        mnNewMenu.add(edit_clear);

        edit_selectAll = new JMenuItem("Select All");
        mnNewMenu.add(edit_selectAll);

        edit_selection = new JMenuItem("Selection");
        mnNewMenu.add(edit_selection);

        edit_sort = new JMenuItem("Sort");
        mnNewMenu.add(edit_sort);

        edit_search = new JMenuItem("Search");
        mnNewMenu.add(edit_search);

        edit_rmDuplicates = new JMenuItem("Remove duplicates");
        mnNewMenu.add(edit_rmDuplicates);

        edit_rmDeadItems = new JMenuItem("Remove dead items");
        mnNewMenu.add(edit_rmDeadItems);

        JMenu mnView = new JMenu("View");
        menuBar.add(mnView);

        view_console = new JMenuItem("Console");
        mnView.add(view_console);

        view_playlistMng = new JMenuItem("Playlist Manager");
        mnView.add(view_playlistMng);

        JMenu mnPlayback = new JMenu("Playback");
        menuBar.add(mnPlayback);

        pb_stop = new JMenuItem("Stop");
        mnPlayback.add(pb_stop);

        pb_pause = new JMenuItem("Pause");
        mnPlayback.add(pb_pause);

        pb_play = new JMenuItem("Play");
        mnPlayback.add(pb_play);

        pb_prev = new JMenuItem("Previous");
        mnPlayback.add(pb_prev);

        pb_next = new JMenuItem("Next");
        mnPlayback.add(pb_next);

        pb_random = new JMenuItem("Random");
        mnPlayback.add(pb_random);

        JMenu mnOrder = new JMenu("Order");
        mnPlayback.add(mnOrder);

        pb_order_default = new JRadioButtonMenuItem("Default");
        mnOrder.add(pb_order_default);

        pb_order_rpPL = new JRadioButtonMenuItem("Repeat (playlist)");
        mnOrder.add(pb_order_rpPL);

        pb_order_rpTrack = new JRadioButtonMenuItem("Repeat (track)");
        mnOrder.add(pb_order_rpTrack);

        pb_order_random = new JRadioButtonMenuItem("Random");
        mnOrder.add(pb_order_random);

        pb_order_shuffleTracks = new JRadioButtonMenuItem("Shuffle (tracks)");
        mnOrder.add(pb_order_shuffleTracks);

        pb_order_shuffleAlbums = new JRadioButtonMenuItem("Shuffle (albums)");
        mnOrder.add(pb_order_shuffleAlbums);

        pb_order_shuffleFolders = new JRadioButtonMenuItem("Shuffle (folders)");
        mnOrder.add(pb_order_shuffleFolders);

        pb_stopAfterCurrent = new JCheckBoxMenuItem(" Stop after current");
        mnPlayback.add(pb_stopAfterCurrent);

        JMenu mnNewMenu_1 = new JMenu("Library");
        menuBar.add(mnNewMenu_1);

        JMenu mnNewMenu_2 = new JMenu("Help");
        menuBar.add(mnNewMenu_2);

        help_about = new JMenuItem("About");
        mnNewMenu_2.add(help_about);

        btnStop = new JButton("");
        btnStop.setIcon(new ImageIcon(Main.class.getResource("/Icon/stop.png")));
        menuBar.add(btnStop);

        btnPlay = new JButton("");
        btnPlay.setIcon(new ImageIcon(Main.class.getResource("/Icon/play.png")));
        menuBar.add(btnPlay);

        btnPause = new JButton("");
        btnPause.setIcon(new ImageIcon(Main.class.getResource("/Icon/pause.png")));
        menuBar.add(btnPause);

        btnPrev = new JButton("");
        btnPrev.setIcon(new ImageIcon(Main.class.getResource("/Icon/prev.png")));
        menuBar.add(btnPrev);

        btnNext = new JButton("");
        btnNext.setIcon(new ImageIcon(Main.class.getResource("/Icon/next.png")));
        menuBar.add(btnNext);

        btnPBRD = new JButton("Playback / Random");
        menuBar.add(btnPBRD);

        sliderVolume = new JSlider();
        sliderVolume.setSize(5, 5);
        sliderVolume.setBorder(null);
        menuBar.add(sliderVolume);


        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

        JPanel panelDefault = new JPanel();
        tabbedPane.addTab("Default", null, panelDefault, null);
        panelDefault.setLayout(new CardLayout(0, 0));

        JScrollPane scrollPane = new JScrollPane();

        table = new JTable();
        scrollPane.setViewportView(table);
        table.setBorder(new LineBorder(new Color(0, 0, 0)));
        table.setBackground(Color.WHITE);
        table.setModel(tableModel = new DefaultTableModel(
                new Object[][]{
                },
                new String[]{
                        "Playing", "Artist/album", "Track no", "Title/track artist", "Duration", ""
                }
        ));
        panelDefault.add(scrollPane, "name_5800961137758");
        table.getColumnModel().getColumn(0).setPreferredWidth(162);
        table.getColumnModel().getColumn(3).setPreferredWidth(204);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() instanceof JMenuItem) {
            JMenuItem action = (JMenuItem) e.getSource();
            if (action == file_open) {
                JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    Song song = new Song(fileChooser.getSelectedFile());
                    tableModel.addRow(new Object[]{null, song.getArtist_album(), song.getTrack_no(), song.getTitle_trackArtist(), song.getDuration(), null});
                }
            } else if (action == file_exit) {
                System.exit(0);
            }
        } else if (e.getSource() instanceof JButton) {
            JButton action = (JButton) e.getSource();
        }

    }


}
