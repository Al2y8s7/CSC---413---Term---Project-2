
package BustAMove;

/**
 *
 * @author Alnguye
 */
public class KeyMapping {
    int upKey, rightKey, downKey, leftKey, shootKey;
    
    public KeyMapping(int up , int right, int down, int left, int shoot){
       this.upKey = up;
       this.rightKey = right;
       this.downKey = down;
       this.leftKey = left;
       this.shootKey = shoot;    
    }
    public int getUpKey(){
        return this.upKey;
    }
    public int getRightKey(){
        return this.rightKey;
    }
    public int getDownKey(){
        return this.downKey;
    }
    public int getLeftKey(){
        return this.leftKey;
    }
    public int getShootKey(){
        return this.shootKey;
    }
}
