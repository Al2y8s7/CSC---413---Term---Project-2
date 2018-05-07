/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * @author Alnguye
 */
public class Cannon extends Movable implements Observer {
    
     //data fields
    private int health;
    private Set<Integer> keys;
    private int ammo;
    private int lives;
    private int deltaX, deltaY;
    private int nonCollideX,nonCollideY;
    private int spawnX,spawnY;
    final int r = 15;
    public short angle;
    private KeyMapping keyMap;
    //for collision detection
    protected int xCollide, yCollide;
    private boolean shotsFired, collided;
    private int player;
    long lastShoot = System.currentTimeMillis();
    final long threshold = 1000;

    public Cannon(int x, int y, BufferedImage Image, KeyMapping kmap, int width, int height) {
	super(x, y, Image, width, height);
	keys = new HashSet();
	this.keyMap = kmap;
	this.angle = angle;
	this.shotsFired = false;
	collided = false;
        this.health = 100;
        this.spawnX = x;
        this.spawnY = y;
        this.lives = 3;
        this.player = player;
    }
    
    
    @Override
    public void update(Observable o, Object arg) {
	
    }
    
    public int getAmmo(int ammo) {
	return this.ammo = ammo;
    }

    public void setAmmo(int ammo) {
	this.ammo = ammo;
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
     
   public synchronized void moveUp() {
	deltaX = (int) Math.round(r * Math.cos(Math.toRadians(angle)));
	deltaY = (int) Math.round(r * Math.sin(Math.toRadians(angle)));
	x += deltaX;
	y += deltaY;
    }

    public synchronized void moveDown() {
	deltaX = (int) Math.round(r * Math.cos(Math.toRadians(angle)));
	deltaY = (int) Math.round(r * Math.sin(Math.toRadians(angle)));
	x -= deltaX;
	y -= deltaY;
    }

    public synchronized void moveLeft() {
	this.angle -= 10;
    }

    public synchronized void moveRight() {
	this.angle += 10;
    }

    private void moveCannon() {
	if (keys.contains(keyMap.getUpKey())) {
	    this.moveUp();
	}
	if (keys.contains(keyMap.getRightKey())) {
	    this.moveRight();
	}
	if (keys.contains(keyMap.getDownKey())) {
	    this.moveDown();
	}
	if (keys.contains(keyMap.getLeftKey())) {
	    this.moveLeft();
	}
	if (keys.contains(keyMap.getShootKey())) {
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
        //graphic2D.draw(this.getHitBox());
    }
    
    public short getAngle(){
        return this.angle;
    }
    
    public int getPlayer(){
        return this.player;
    }
    
}
