/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BustAMove;

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
