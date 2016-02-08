import java.util.Scanner;

/**
 * File: TheSorcerersCave.java
 * Date: 7 Feb 2016
 * @author James Moore
 * Purpose: Develop a game called The Sorcerers Cave 
 */

//Treasure is held by creatures and has a weight and value
public class Treasure extends CaveElement {

    private int creatureIndex;
    private Double weight;
    private Double value;
    
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

    public Double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String toString() {
	if (creatureIndex == 0) {
	      return String.format ("t:%6d: %s : %6d : %4.1f : %4.1f", getIndex(), getType(),            0, weight, value);
	}
	return String.format ("t:%6d: %s : %4.1f : %4.1f", getIndex(), getType(), weight, value);
    } // end toString
} // end Treasure
