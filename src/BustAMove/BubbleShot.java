/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BustAMove;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Observable;

/**
 *
 * @author Alnguye
 */
public class BubbleShot extends Movable {
    int deltaX,deltaY;
    final int r = 6;
    private short angle;
    private int player;
    
    public BubbleShot(int x, int y, short angle, int player, BufferedImage image,int width,int height) {
	super(x, y, image, width, height);
	this.angle = angle;
        this.player = player;
    }
    
    public void draw(Graphics g){
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
	rotation.rotate(Math.toRadians(angle), this.getImage().getWidth() / 2, this.getImage().getHeight() / 2);
	Graphics2D graphic2D = (Graphics2D) g;
	graphic2D.drawImage(this.getImage(), rotation, null);
        graphic2D.draw(this.getHitBox());
    }
    public void move(){
        deltaX = (int) Math.round(r * Math.cos(Math.toRadians(angle)));
	deltaY = (int) Math.round(r * Math.sin(Math.toRadians(angle)));
	x += deltaX;
	y += deltaY;
    }
    public int getPlayer(){
        return this.player;
    }
    
}
