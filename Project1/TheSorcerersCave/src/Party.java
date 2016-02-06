import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author JD
 *
 */

public class Party extends CaveElement {
    
    ArrayList<Creature> creaturesList = new ArrayList<Creature>();
    
    int index;
    String name;
    String type = null;

    public Party(Scanner s) {
	s.next();
	index = s.nextInt();
	name  = s.next();
    } //end Party constructor

    public void addCreature(Creature c) {
	creaturesList.add(c);
    } // end addCreature

    public String toString() {
	String st = name + "\n        Members:\n";
	for(Creature c: creaturesList) {
	    st += "        " + c + "\n";
	} // end for
	return st;
    } // end toString
} // end Party
