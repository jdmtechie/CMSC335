import java.util.Scanner;

/**
 * @author JD
 *
 */
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
    
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
	return type;
    } // end toString
} // end Treasure
