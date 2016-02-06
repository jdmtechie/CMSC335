import java.util.Scanner;

/**
 * @author JD
 *
 */
public class Treasure extends CaveElement {

    int index;
    String type;
    String name = null;
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
