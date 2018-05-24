package BustAMove;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

/**
 *
 * @author Alvin Nguyen & Moses Martinez
 */
public class Cannon extends Movable implements Observer {
    
     //data fields
    private Set<Integer> keys;
    private int ammo;
    private int deltaX, deltaY;
    private int nonCollideX,nonCollideY;
    final int r = 15;
    public short angle;
    private KeyMapping keyMap;
    //for collision detection
    protected int xCollide, yCollide;
    private boolean shotsFired, collided;
    private int player;
    private int playerScore = 0;
    long lastShoot = System.currentTimeMillis();
    final long threshold = 1000;

    public Cannon(int x, int y, short angle, int player, BufferedImage Image, KeyMapping kmap, int width, int height) {
	super(x, y, Image, width, height);
	keys = new HashSet();
	this.keyMap = kmap;
	this.angle = angle;
	this.shotsFired = false;
	collided = false;
        this.player = player;
    }
    
    @Override
    public void update(Observable o, Object arg) {
	Controller controller = (Controller) o;
	keys = controller.getKeys();
	moveCannon();	
    }
    public void shoot() {
	this.shotsFired = true;
    }
    public void setShoot(boolean shoot){
        this.shotsFired  = shoot;
    }
    public boolean getShoot(){
        return this.shotsFired;
    }
    public synchronized void moveLeft() {
	this.angle -= 5;
        if(this.angle <=180)
            this.angle = 180;
    }

    public synchronized void moveRight() {
	this.angle += 5;
        if(this.angle >=360)
            this.angle = 360;
    }

    private void moveCannon() {
	if (keys.contains(keyMap.getRightKey())) {
	    this.moveRight();
	}
//	if (keys.contains(keyMap.getDownKey())) {
//	    this.moveDown();
//	}
	if (keys.contains(keyMap.getLeftKey())) {
	    this.moveLeft();
	}
	if (keys.contains(keyMap.getShootKey())) {
            System.out.println("Shoot");
            long current = System.currentTimeMillis();
            if((current - threshold) > lastShoot){
	    this.shoot();
            lastShoot = current;
            }
	}
    }
    
    public void draw(Graphics g) {
	// super.paintComponent(g);    
	AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
	rotation.rotate(Math.toRadians(angle), this.getImage().getWidth() / 2, this.getImage().getHeight() / 2);
	Graphics2D graphic2D = (Graphics2D) g;
	graphic2D.drawImage(this.getImage(), rotation, null);
    }
    
    public short getAngle(){
        return this.angle;
    }
    
    public int getPlayer(){
        return this.player;
    }
    public void setScore(int score){
        this.playerScore = score;
    }
    public int getScore(){
        return this.playerScore;
    }
    
       
}