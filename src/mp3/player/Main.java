package mp3.player;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JSlider;
import javax.swing.border.EmptyBorder;

public class Main {

	private JFrame frame;
	private JTable table;

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

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 650, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Simple MP3 Player");

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmNewMenuItem = new JMenuItem("Open...");
		mnFile.add(mntmNewMenuItem);

		// mnFile.add(new JSeparator());

		JMenuItem mntmOpen = new JMenuItem("Add files...");
		mnFile.add(mntmOpen);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Add folder...");
		mnFile.add(mntmNewMenuItem_1);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("New playlist");
		mnFile.add(mntmNewMenuItem_2);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Load playlist...");
		mnFile.add(mntmNewMenuItem_3);

		mnFile.setBorderPainted(true);

		JMenuItem mntmSavePlaylist = new JMenuItem("Save playlist...");
		mnFile.add(mntmSavePlaylist);

		JMenuItem mntmPreferences = new JMenuItem("Preferences");
		mnFile.add(mntmPreferences);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);

		JMenu mnNewMenu = new JMenu("Edit");
		menuBar.add(mnNewMenu);

		JMenuItem mntmUndo = new JMenuItem("Undo");
		mnNewMenu.add(mntmUndo);

		JMenuItem mntmRedo = new JMenuItem("Redo");
		mnNewMenu.add(mntmRedo);

		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Clear");
		mnNewMenu.add(mntmNewMenuItem_4);

		JMenuItem mntmSelectAll = new JMenuItem("Select All");
		mnNewMenu.add(mntmSelectAll);

		JMenuItem mntmSelection = new JMenuItem("Selection");
		mnNewMenu.add(mntmSelection);

		JMenuItem mntmSort = new JMenuItem("Sort");
		mnNewMenu.add(mntmSort);

		JMenuItem mntmSearch = new JMenuItem("Search");
		mnNewMenu.add(mntmSearch);

		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Remove duplicates");
		mnNewMenu.add(mntmNewMenuItem_5);

		JMenuItem mntmRemoveDeadItems = new JMenuItem("Remove dead items");
		mnNewMenu.add(mntmRemoveDeadItems);

		JMenu mnView = new JMenu("View");
		menuBar.add(mnView);

		JMenuItem mntmConsole = new JMenuItem("Console");
		mnView.add(mntmConsole);

		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Playlist Manager");
		mnView.add(mntmNewMenuItem_6);

		JMenu mnPlayback = new JMenu("Playback");
		menuBar.add(mnPlayback);

		JMenuItem mntmStop = new JMenuItem("Stop");
		mnPlayback.add(mntmStop);

		JMenuItem mntmPause = new JMenuItem("Pause");
		mnPlayback.add(mntmPause);

		JMenuItem mntmPlay = new JMenuItem("Play");
		mnPlayback.add(mntmPlay);

		JMenuItem mntmPrevious = new JMenuItem("Previous");
		mnPlayback.add(mntmPrevious);

		JMenuItem mntmNext = new JMenuItem("Next");
		mnPlayback.add(mntmNext);

		JMenuItem mntmRandom = new JMenuItem("Random");
		mnPlayback.add(mntmRandom);

		JMenu mnOrder = new JMenu("Order");
		mnPlayback.add(mnOrder);

		JRadioButtonMenuItem rdbtnmntmNewRadioItem = new JRadioButtonMenuItem("Default");
		mnOrder.add(rdbtnmntmNewRadioItem);

		JRadioButtonMenuItem rdbtnmntmRepeatplaylist = new JRadioButtonMenuItem("Repeat (playlist)");
		mnOrder.add(rdbtnmntmRepeatplaylist);

		JRadioButtonMenuItem rdbtnmntmRepeattrack = new JRadioButtonMenuItem("Repeat (track)");
		mnOrder.add(rdbtnmntmRepeattrack);

		JRadioButtonMenuItem rdbtnmntmRandom = new JRadioButtonMenuItem("Random");
		mnOrder.add(rdbtnmntmRandom);

		JRadioButtonMenuItem rdbtnmntmShuffle = new JRadioButtonMenuItem("Shuffle (tracks)");
		mnOrder.add(rdbtnmntmShuffle);

		JRadioButtonMenuItem rdbtnmntmShufflealbums = new JRadioButtonMenuItem("Shuffle (albums)");
		mnOrder.add(rdbtnmntmShufflealbums);

		JRadioButtonMenuItem rdbtnmntmShufflefolders = new JRadioButtonMenuItem("Shuffle (folders)");
		mnOrder.add(rdbtnmntmShufflefolders);

		JCheckBoxMenuItem chckbxmntmStopAfter = new JCheckBoxMenuItem(" Stop after current");
		mnPlayback.add(chckbxmntmStopAfter);

		JMenu mnNewMenu_1 = new JMenu("Library");
		menuBar.add(mnNewMenu_1);

		JMenu mnNewMenu_2 = new JMenu("Help");
		menuBar.add(mnNewMenu_2);

		JMenuItem mntmAbout = new JMenuItem("About");
		mnNewMenu_2.add(mntmAbout);
		
		JButton btnPlay = new JButton("Stop");
		menuBar.add(btnPlay);
		
		JButton btnPlay_1 = new JButton("Play");
		menuBar.add(btnPlay_1);
		
		JButton btnPause = new JButton("Pause");
		menuBar.add(btnPause);
		
		JButton btnPrevious = new JButton("Previous");
		menuBar.add(btnPrevious);
		
		JButton btnNext = new JButton("Next");
		menuBar.add(btnNext);
		
		JButton btnPlaybackRandom = new JButton("Playback / Random");
		menuBar.add(btnPlaybackRandom);
		
		JSlider slider = new JSlider();
		slider.setSize(5, 5);
		slider.setBorder(null);
		menuBar.add(slider);
		
		JMenuBar menuBar_1 = new JMenuBar();
		menuBar.add(menuBar_1);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Default", null, panel, null);
		panel.setLayout(new CardLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setBackground(Color.WHITE);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"Playing", "Artist/album", "Track no", "Title/track artist", "Duration", ""
			}
		));
		panel.add(scrollPane, "name_5800961137758");
		table.getColumnModel().getColumn(0).setPreferredWidth(162);
		table.getColumnModel().getColumn(3).setPreferredWidth(204);

	}

}
