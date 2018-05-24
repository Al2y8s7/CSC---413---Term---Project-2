package BustAMove;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Alvin Nguyen & Moses Martinez
 */
public class Main extends JFrame implements ActionListener {

    final static int WINDOW_WIDTH = 1417 / 2;
    final static int WINDOW_HEIGHT = 832;

    public static void main(String[] args) {
	Main newGame = new Main();
	newGame.gameStart();

    }

    public void gameStart() {
	//set game window
	setTitle("Bust A Move ");
	setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	setLocation(new Point(200, 200));

//	JButton start = new JButton("Start");
//	start.setActionCommand("Start");
//	this.add(start);
//	start.addActionListener(this);
//	this.addWindowListener(new WindowAdapter() {
//	    @Override
//	    public void windowClosing(WindowEvent e) {
//		actionPerformed(new ActionEvent(this,
//			ActionEvent.RESERVED_ID_MAX + 1, "None"));
//	    }
//	});
//	this.pack();

	add(new GameWorld());
	setResizable(false);
	setVisible(true);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setLocationRelativeTo(null);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
	if ("start".equals(e.getActionCommand())) {
	    final JFrame game = new JFrame();
	    //set game window
	    game.setTitle("Bust A Move ");
	    game.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	    game.setLocation(new Point(200, 200));
	    game.add(new GameWorld());
	    game.setResizable(false);
	    game.setVisible(true);
	    game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    game.setLocationRelativeTo(null);
	}
    }
}
