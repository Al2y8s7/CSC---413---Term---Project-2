
package BustAMove;

import static BustAMove.Main.WINDOW_HEIGHT;
import static BustAMove.Main.WINDOW_WIDTH;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Alnguye
 */
public class GameWorld extends JPanel {
   
    BufferedImage cannon1, cannon2;
    private BufferedImage background;
    private static BufferedImage gameMap, setBB, setRB, setGB, setYB, setOB, setPB;
    private int width, height;
    private Timer timer;
    private Graphics2D worldMapGraphics;
    Graphics dbg;
    
    ArrayList<GameObject> GOList;
    ArrayList<BreakableBubble> BBList;
    
    
    
    //constructor
    public GameWorld(){
	setGameLists();
	setFocusable(true);
	initResources();
	setBackground();
	setMap();
	initTimer();
	
    }
    
    public void paintComponenet(Graphics g){
	super.paintComponents(g);
	drawEverything();
	
	
    }
    
    
     private void initTimer() {
	timer = new Timer(1000 / 144, (ActionEvent e) -> {
	    GameWorld.this.repaint();
	});
    }
    
    public void drawEverything(){
	worldMapGraphics = gameMap.createGraphics();
	worldMapGraphics.drawImage(background, 0, 0, null);
	drawBubble(worldMapGraphics);
    }
    
    public void initResources(){
	try{
	     background = ImageIO.read(GameWorld.class.getResource("/BustAMove/resources/Background2.png"));
	     setBB = ImageIO.read(GameWorld.class.getResource("/BustAMove/resources/setBB.png"));
	     setRB = ImageIO.read(GameWorld.class.getResource("/BustAMove/resources/setRB.png"));
	     setGB = ImageIO.read(GameWorld.class.getResource("/BustAMove/resources/setGB.png"));
	     setYB = ImageIO.read(GameWorld.class.getResource("/BustAMove/resources/setYB.png"));
	     setOB = ImageIO.read(GameWorld.class.getResource("/BustAMove/resources/setOB.png"));
	     setPB = ImageIO.read(GameWorld.class.getResource("/BustAMove/resources/setPB.png"));
	     //C:\Users\alnguye\csc413-secondgame-Team12\src\BustAMove\resources\Background.PNG
	     //C:\Users\alnguye\csc413-03-tankgame-Team12\src\TankWars\resources\Background2.png
	     
	}catch(IOException ex){
	    ex.printStackTrace();
	}
    }
    
    
    public void setMap() {
	//instantiate World
	GameMap currMap = new GameMap();
	BreakableBubble  breakableBubble;
	PowerUp power;
	//create reference for 2D array
	int[][] GameMap = currMap.getGameMap();
	//tile map with walls
	for (int y = 0; y < 25; y++) {
	    for (int x = 0; x <= 43; x++) {
		switch (GameMap[y][x]) {
		    case 1:
			breakableBubble = new BreakableBubble(x * 32, y * 32, setBB , 32, 32);
			BBList.add(breakableBubble);
			break;
		    case 2:
			breakableBubble = new BreakableBubble(x * 32, y * 32, setRB, 32, 32);
			BBList.add(breakableBubble);
			break;
		    case 3:
			breakableBubble = new BreakableBubble(x * 32, y * 32, setGB, 32, 32);
			BBList.add(breakableBubble);
			break;
		    case 4:
			breakableBubble = new BreakableBubble(x * 32, y * 32, setYB, 32, 32);
			BBList.add(breakableBubble);
			break;
		    case 5:
			breakableBubble = new BreakableBubble(x * 32, y * 32, setOB, 32, 32);
			BBList.add(breakableBubble);
			break;
		    case 6:
			breakableBubble = new BreakableBubble(x * 32, y * 32, setPB, 32, 32);
			BBList.add(breakableBubble);
			break;
		    default:
			break;
		}
	    }
	}
    }
    
    
    public void setBackground(){
	width = background.getWidth(this);
	height = background.getHeight(this);
	setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
	gameMap = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }
    
    public void drawBubble(Graphics g){
	for(int i = 0; i < BBList.size(); i++){
	    BreakableBubble breakableBubble = BBList.get(i);
	    if(!breakableBubble.getVisibility()){
		BBList.remove(i);
	    }else{
		g.drawImage(breakableBubble.getImage(), breakableBubble.getX(), breakableBubble.getY(), null);
	    }
	}
    }
    
    public void setGameLists(){
	BBList = new ArrayList();
    }
    
}
