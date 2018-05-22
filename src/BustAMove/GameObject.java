
package BustAMove;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author Alnguye
 */
public abstract class GameObject {
    BufferedImage content;
    protected int x, y, width, height;
    boolean visibility = true, collided;

    //constructor
    public GameObject(int x, int y, BufferedImage Image, int width, int height) {
	this.content = Image;
	this.x = x;
	this.y = y;
	this.width = width;
	this.height = height;
    }

    public boolean getVisibility() {
	return this.visibility;
    }

    public void setVisibility(boolean visibility) {
	this.visibility = visibility;
    }

    public BufferedImage getImage() {
	return this.content;
    }
    
    //for collision detection
    public Rectangle getHitBox() {
	return new Rectangle(this.x, this.y, this.content.getWidth()-6, this.content.getHeight()-6);
    }

    public void setX(int x) {
	this.x = x;
    }

    public void setY(int y) {
	this.y = y;
    }

    public int getX() {
	return this.x;
    }

    public int getY() {
	return this.y;
    }

    public int getImageWidth() {
	return this.content.getWidth();
    }

    public int getImageHeight() {
	return this.content.getHeight();
    }
}
