import java.util.Scanner;

/**
 * File: TheSorcerersCave.java
 * Date: 24 Jan 2016
 * @author James Moore
 * Purpose: Develop a game called The Sorcerers Cave 
 */

//Treasure is heald by creatures and has a weight and value
public class Treasure extends CaveElement {

    private int creatureIndex;
    private double weight;
    private double value;
    
    public int makeTreasure(Scanner s) {
      s.next();
      setIndex(s.nextInt());
      setType(s.next());
      creatureIndex = s.nextInt();
      weight = s.nextDouble();
      value = s.nextDouble();
      return creatureIndex;
    } // end Treasure
    
    public int getCreatureIndex() {
        return creatureIndex;
    }

    public void setCreatureIndex(int creatureIndex) {
        this.creatureIndex = creatureIndex;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String toString() {
	return getType();
    } // end toString
} // end Treasure
