
package BustAMove;

import static BustAMove.Main.WINDOW_HEIGHT;
import static BustAMove.Main.WINDOW_WIDTH;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.Random;

/**
 *
 * @author Alnguye
 */
public class GameWorld extends JPanel {
    
    private Cannon cannon1, cannon2;
    BufferedImage cannonImage;
    private Controller cannonControls;
    private BufferedImage background, leftScreen, rightScreen;
    private static BufferedImage gameMap, setBB, setRB, setGB, setYB, setOB, setPB, bullet1;
    private int width, height;
    private Timer timer;
    private Graphics2D worldMapGraphics;
    private KeyMapping player1, player2;
    
    ArrayList<GameObject> GOList;
    ArrayList<BreakableBubble> BBList;
    ArrayList<BubbleShot> BSList;
    
    
    
    //constructor
    public GameWorld(){
	setGameLists();
	setFocusable(true);
	initResources();
	setBackground();
	setMap();
	initKeyMapping();
	initCannon();
	initTimer();
	timer.start();
	
    }
    
    public void paintComponent(Graphics g){
	super.paintComponents(g);
	drawEverything();
	SplitScreen(g);
	drawBullets(g);
	g.dispose();
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
	cannon1.draw(worldMapGraphics);
	drawBullets(worldMapGraphics);
    }
    
    public void initResources(){
	try{
	     background = ImageIO.read(GameWorld.class.getResource("resources/Background.png"));
	     setBB = ImageIO.read(GameWorld.class.getResource("resources/setBB.png"));
	     setRB = ImageIO.read(GameWorld.class.getResource("resources/setRB.png"));
	     setGB = ImageIO.read(GameWorld.class.getResource("resources/setGB.png"));
	     setYB = ImageIO.read(GameWorld.class.getResource("resources/setYB.png"));
	     setOB = ImageIO.read(GameWorld.class.getResource("resources/setOB.png"));
	     setPB = ImageIO.read(GameWorld.class.getResource("resources/setPB.png"));
	     cannonImage = ImageIO.read(GameWorld.class.getResource("resources/cannon.png"));
	     bullet1 = ImageIO.read(GameWorld.class.getResource("resources/bullet1.png"));

	     
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
    
    
    public void SplitScreen(Graphics g){
	//left Screen
	leftScreen = gameMap.getSubimage(0, 0, WINDOW_WIDTH / 2 - 25, 800);
	g.drawImage(leftScreen, 0, 0, null);
	//right Screen
	rightScreen = gameMap.getSubimage(WINDOW_WIDTH / 2 - 21, 0, WINDOW_WIDTH / 2 + 15, 800);
	g.drawImage(rightScreen, (WINDOW_WIDTH / 2 - 20), 0, null);
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
    
    //TODO
    public void randomBubble(){
	Random randomB = new Random();
	for(int i = 0; i <= 5; i++){
	    randomB.nextInt();
		if(i == 0){
		    
	    }
	}
    }
    
    public void setGameLists(){
	BBList = new ArrayList();
	GOList = new ArrayList();
	BSList = new ArrayList();
    }
    
    
    public void initCannon(){
	cannon1 = new Cannon(300, 600, (short) 0, 1, cannonImage, player1, 32, 32);
	cannonControls = new Controller();
	addKeyListener(cannonControls.getKeyAdapter());
	this.cannonControls.addObserver(cannon1);
	//this.cannonControls.addObserver(cannon2);
    }
    
     private void initKeyMapping() {
	player1 = new KeyMapping(KeyEvent.VK_UP, KeyEvent.VK_RIGHT, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_ENTER);
	player2 = new KeyMapping(KeyEvent.VK_W, KeyEvent.VK_D, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_SPACE);
    }
    
    public void checkShooting() {
	if (cannon1.getShoot() == true) {
	    BubbleShot bubbleShot = new BubbleShot(cannon1.getX() + (cannon1.getImageWidth() / 2), cannon1.getY() + (cannon1.getImageHeight() / 2), cannon1.getAngle(), 1, bullet1, 1, 1);
	    BSList.add(bubbleShot);
	    cannon1.setShoot(false);
	}
    }public void drawBullets(Graphics g) {
	Iterator<BubbleShot> iterator = BSList.iterator();
	while (iterator.hasNext()) {
	    BubbleShot bubbleShot = iterator.next();
	    if (!bubbleShot.getVisibility()) {
		iterator.remove();
	    } else {
		bubbleShot.draw(g);
		bubbleShot.move();
	    }
	}
    }
    
    
}
