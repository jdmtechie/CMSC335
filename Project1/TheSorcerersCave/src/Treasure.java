import java.util.Scanner;

/**
 * File: TheSorcerersCave.java
 * Date: 24 Jan 2016
 * @author James Moore
 * Purpose: Develop a game called The Sorcerers Cave 
 */

//Treasure is heald by creatures and has a weight and value
public class Treasure extends CaveElement {

    int creatureIndex;
    double weight;
    double value;
    
    public int makeTreasure(Scanner s) {
      s.next();
      index = s.nextInt();
      type = s.next();
      creatureIndex = s.nextInt();
      weight = s.nextDouble();
      value = s.nextDouble();
      return creatureIndex;
    } // end Treasure
    
    public String toString() {
	return type;
    } // end toString
} // end Treasure
