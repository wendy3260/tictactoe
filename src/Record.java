import java.util.Dictionary;
/**
 * 
 * @author Wendy Li
 *Student number: 251026390
 *CS 2211 Assignment 2
 */
public class Record {
	private String c;
	private int s;
	
	public Record(String config, int score) {
		c = config;
		s = score; 
	}
	
	public String getConfig() {
		return c;
	}
	
	public int getScore() {
		return s;
	}
}

