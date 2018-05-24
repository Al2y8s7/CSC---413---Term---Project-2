/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BustAMove;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * 
 * @author Alvin Nguyen & Moses Martinez
 */

public class Music {

    public static void music() {

	/**
	 * Source code provided on StackOverflow: https://stackoverflow.com/questions/6045384/playing-mp3-and-wav-in-java
	 *
	 */
	try {
	    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/BustAMove/resources/fountain-of-dreams.wav").getAbsoluteFile());
	    Clip clip = AudioSystem.getClip();
	    clip.open(audioInputStream);
	    clip.start();
	    clip.loop(5);
	} catch (Exception ex) {
	    ex.printStackTrace();
	}

    }

}


