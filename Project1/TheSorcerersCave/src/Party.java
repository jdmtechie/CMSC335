import java.util.ArrayList;
import java.util.Scanner;

/**
 * File: TheSorcerersCave.java
 * Date: 24 Jan 2016
 * @author James Moore
 * Purpose: Develop a game called The Sorcerers Cave 
 */

//Parties contain creatures who are in the cave
public class Party extends CaveElement {
    
    ArrayList<Creature> creaturesList = new ArrayList<Creature>();

    public Party(Scanner s) {
	s.next();
	setIndex(s.nextInt());
	setName(s.next());
    } //end Party constructor

    public void addCreature(Creature c) {
	creaturesList.add(c);
    } // end addCreature

    public String toString() {
	String st = getName() + "\n        Members:\n";
	for(Creature c: creaturesList) {
	    st += "        " + c + "\n";
	} // end for
	return st;
    } // end toString
} // end Party
