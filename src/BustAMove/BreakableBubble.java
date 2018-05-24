
package BustAMove;

import java.awt.image.BufferedImage;

/**
 * 
 * @author Alvin Nguyen & Moses Martinez
 */

public class BreakableBubble extends GameObject {
    String BubbleColor;
    //constructor
    public BreakableBubble(int x, int y, BufferedImage image, int width, int height, String bcolor){
	super(x,y, image, width, height);
        this.BubbleColor = bcolor;
    }
    public String getColor(){
        return this.BubbleColor;
    }
}