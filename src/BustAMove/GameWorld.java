
package BustAMove;

import static BustAMove.Main.WINDOW_HEIGHT;
import static BustAMove.Main.WINDOW_WIDTH;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
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
    private BufferedImage background, leftScreen, rightScreen, gameOver;
    private static BufferedImage gameMap, setBB, setRB, setGB, setYB, setOB, setPB, bullet1;
    private int width, height;
    private Timer timer;
    private Graphics2D worldMapGraphics;
    private KeyMapping player1, player2;
    private int randomBubble;
    
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
        randomBubble();
	timer.start();
	
    }
    
    public void paintComponent(Graphics g){
	super.paintComponents(g);
	drawEverything(g);
        g.setColor(Color.red);
        g.fillRect(0, 500, this.getWidth(), 20);
        drawNextBubble(g);
        gameOver(g);
	//SplitScreen(g);
	//g.dispose();
    }
    
    
     private void initTimer() {
	timer = new Timer(1000 / 144, (ActionEvent e) -> {
            GameWorld.this.checkShooting();
            GameWorld.this.checkCollision();
            GameWorld.this.toBreakable();
	    GameWorld.this.repaint();
	});
    }
    
    public void drawEverything(Graphics g){
	worldMapGraphics = gameMap.createGraphics();
	worldMapGraphics.drawImage(background, 0, 0, null);
	drawBubble(worldMapGraphics);
	cannon1.draw(worldMapGraphics);
	drawBullets(worldMapGraphics);
        g.drawImage(gameMap, 0, 0, null);
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
             gameOver = ImageIO.read(GameWorld.class.getResource("resources/gameOver.png"));

	     
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
	for (int y = 0; y < GameMap.length; y++) {
	    for (int x = 0; x < GameMap[0].length; x++) {
		switch (GameMap[y][x]) {
		    case 1:
			breakableBubble = new BreakableBubble(x * 32, y * 32, setBB , 32, 32,"blue");
			BBList.add(breakableBubble);
			break;
		    case 2:
			breakableBubble = new BreakableBubble(x * 32, y * 32, setRB, 32, 32,"red");
			BBList.add(breakableBubble);
			break;
		    case 3:
			breakableBubble = new BreakableBubble(x * 32, y * 32, setGB, 32, 32,"green");
			BBList.add(breakableBubble);
			break;
		    case 4:
			breakableBubble = new BreakableBubble(x * 32, y * 32, setYB, 32, 32,"yellow");
			BBList.add(breakableBubble);
			break;
		    case 5:
			breakableBubble = new BreakableBubble(x * 32, y * 32, setOB, 32, 32,"orange");
			BBList.add(breakableBubble);
			break;
		    case 6:
			breakableBubble = new BreakableBubble(x * 32, y * 32, setPB, 32, 32,"purple");
			BBList.add(breakableBubble);
			break;
		    default:
			break;
		}
	    }
	}
    }
    
    
 /*   public void SplitScreen(Graphics g){
	//left Screen
        
	leftScreen = gameMap.getSubimage(0, 0, WINDOW_WIDTH / 2 - 25, 800);
	g.drawImage(leftScreen, 0, 0, null);
	//right Screen
	rightScreen = gameMap.getSubimage(WINDOW_WIDTH / 2 - 21, 0, WINDOW_WIDTH / 2 + 15, 800);
	g.drawImage(rightScreen, (WINDOW_WIDTH / 2 - 20), 0, null);

    }
*/

    
    
    public void setBackground(){
	width = background.getWidth(this);
	height = background.getHeight(this);
	setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
	gameMap = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }
    
    public void drawBubble(Graphics g){
        Iterator<BreakableBubble> iterator = BBList.iterator();
        while(iterator.hasNext()){
            BreakableBubble bubble = iterator.next();
	    if(!bubble.getVisibility()){
		iterator.remove();
	    }else{
		g.drawImage(bubble.getImage(), bubble.getX(), bubble.getY(), null);
                Graphics2D graphic2D = (Graphics2D) g;
                graphic2D.draw(bubble.getHitBox());
	    }
	}
    }
    
    
    public void randomBubble(){
	Random rand = new Random();
        randomBubble = rand.nextInt((6-1)+1) + 1;
    }
    
    public void setGameLists(){
	BBList = new ArrayList();
	GOList = new ArrayList();
	BSList = new ArrayList();
    }
   
    public void initCannon(){
	cannon1 = new Cannon(300, 600, (short) 270, 1, cannonImage, player1, 32, 32);
	cannonControls = new Controller();
	addKeyListener(cannonControls.getKeyAdapter());
	this.cannonControls.addObserver(cannon1);
    }
    
     private void initKeyMapping() {
	player1 = new KeyMapping(KeyEvent.VK_UP, KeyEvent.VK_RIGHT, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_ENTER);
    }
    
    public void checkShooting() {
	if (cannon1.getShoot() == true) {
            switch (randomBubble) {
		    case 1:
			BubbleShot shotBlue = new BubbleShot(cannon1.getX()+cannon1.getImageWidth()/4, cannon1.getY(), cannon1.getAngle(), 1, setBB, 1, 1, true,"blue");
			BSList.add(shotBlue);
                        shotBlue.setBounds(0,(1417/2)-40);
                        break;
		    case 2:
			BubbleShot shotRed = new BubbleShot(cannon1.getX()+cannon1.getImageWidth()/4, cannon1.getY(), cannon1.getAngle(), 1, setRB, 1, 1, true,"red");
			BSList.add(shotRed);
                        shotRed.setBounds(0,(1417/2)-40);
			break;
		    case 3:
			BubbleShot shotGreen = new BubbleShot(cannon1.getX()+cannon1.getImageWidth()/4, cannon1.getY(), cannon1.getAngle(), 1, setGB, 1, 1, true,"green");
			BSList.add(shotGreen);
                        shotGreen.setBounds(0,(1417/2)-40);
			break;
		    case 4:
			BubbleShot shotYellow = new BubbleShot(cannon1.getX()+cannon1.getImageWidth()/4, cannon1.getY(), cannon1.getAngle(), 1, setYB, 1, 1, true,"yellow");
			BSList.add(shotYellow);
                        shotYellow.setBounds(0,(1417/2)-40);
			break;
		    case 5:
			BubbleShot shotOrange = new BubbleShot(cannon1.getX()+cannon1.getImageWidth()/4, cannon1.getY(), cannon1.getAngle(), 1, setOB, 1, 1, true,"orange");
			BSList.add(shotOrange);
                        shotOrange.setBounds(0,(1417/2)-40);
			break;
		    case 6:
			BubbleShot shotPurple = new BubbleShot(cannon1.getX()+cannon1.getImageWidth()/4, cannon1.getY(), cannon1.getAngle(), 1, setPB, 1, 1, true,"purple");
			BSList.add(shotPurple);
                        shotPurple.setBounds(0,(1417/2)-40);
			break;
		    default:
			break;
	   
	}
        cannon1.setShoot(false);
        randomBubble();
    }
}
    public void drawBullets(Graphics g) {
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
    public void checkCollision(){  
        Iterator<BubbleShot> iterator = BSList.iterator();
        Iterator<BreakableBubble> iteratorBreak = BBList.iterator();  
        while(iterator.hasNext()){
            BubbleShot BS = iterator.next();
            Rectangle bsHitBox = BS.getHitBox();  
            while(iteratorBreak.hasNext()){
                BreakableBubble BB = iteratorBreak.next();
                Rectangle bbHitBox = BB.getHitBox();
                if(bsHitBox.intersects(bbHitBox)){
                    if(BB.getColor().equals(BS.getColor())){
                        if(!breakBubbles(BB, BS)){
                            BS.setMove(false);
                            BS.setCollide(true);
                           // toBreakable();
                        }
                    }
                    else{
                       BS.setMove(false);
                       BS.setCollide(true);
                     //  toBreakable();
                    }
                }
            }
        }
   }
    public void toBreakable(){
        Iterator<BubbleShot> iterator = BSList.iterator();
        while(iterator.hasNext()){
            BubbleShot shot = iterator.next();
            if(shot.getCollide()){
            BreakableBubble breakable = new BreakableBubble(shot.getX(),shot.getY(),shot.getImage(),0,0,shot.getColor());
            BBList.add(breakable);
            shot.setVisibility(false);
            }
        }
    }
    
    public boolean breakBubbles(BreakableBubble bubble, BubbleShot shot){
        ArrayList<BreakableBubble> Critical = new ArrayList();
        Iterator<BreakableBubble> iterator = BBList.iterator();
        while(iterator.hasNext()){
        BreakableBubble bubbles = iterator.next();
         Rectangle bubbleReference = bubble.getHitBox();
         Rectangle bbHitBox = bubbles.getHitBox();
                if(bubbles.getColor().equals(bubble.getColor()) && bbHitBox.intersects(bubbleReference)){
                    Critical.add(bubbles);
                }   
        }
        if(Critical.size()>= 2){
            for(int i = 0; i<Critical.size();i++){
                Critical.get(i).setVisibility(false);
                 shot.setVisibility(false);
            }
            cannon1.setScore(cannon1.getScore() + (Critical.size() * 2 ));
            return true;
        }
        return false;
    }
    public void drawNextBubble(Graphics g){
        
        switch (randomBubble) {
		    case 1:
                        g.drawImage(setBB, cannon1.getX(), cannon1.getY()+cannon1.getImageHeight(), null);
			break;
		    case 2:
                        g.drawImage(setRB,cannon1.getX(), cannon1.getY()+cannon1.getImageHeight(), null);
			break;
		    case 3:
			g.drawImage(setGB,cannon1.getX(), cannon1.getY()+cannon1.getImageHeight(), null);
			break;
		    case 4:
                        g.drawImage(setYB,cannon1.getX(), cannon1.getY()+cannon1.getImageHeight(), null);
			break;
		    case 5:	
                        g.drawImage(setOB,cannon1.getX(), cannon1.getY()+cannon1.getImageHeight(), null);
			break;
		    case 6:
                        g.drawImage(setPB, cannon1.getX(), cannon1.getY()+cannon1.getImageHeight(), null);
			break;
		    default:
			break;
		}
        
    }
    public void gameOver(Graphics g){
        for(BreakableBubble bubble: BBList){
            if(bubble.getY()>500-32){
                g.drawImage(gameOver, 0, 0, null);
                timer.stop();
            }
        }
    }
    
}
