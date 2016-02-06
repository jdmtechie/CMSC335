import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author JD
 *
 */
public class Creature extends CaveElement {
    
    ArrayList<Treasure> treasureList = new ArrayList<Treasure>();
    ArrayList<Artifact> artifactList = new ArrayList<Artifact>();
    
    int index = 0;
    String type;
    String name;
    int partyIndex;
    int empathy;
    int fear;
    double capacity;
    
    public int makeCreature(Scanner s) {
	s.next();
	index = s.nextInt();
	type = s.next();
	name = s.next();
	partyIndex = s.nextInt();
	empathy = s.nextInt();
	fear = s.nextInt();
	capacity = s.nextDouble();	
	return partyIndex;
    } // end makeCreature
    
    public String getName() {
	return this.name;
    }
    
    public void addTreasure(Treasure t) {
	treasureList.add(t);
    } // end addTreasure
    
    public void addArtifact(Artifact a) {
	artifactList.add(a);
    } // end addArtifact
    
    public String toString() {
	String st = "        " + name + "\n                  Artifacts: ";
	for (Artifact a: artifactList) 
	    st += a + " ";
	st += "\n                  Treasures: ";
	for (Treasure t: treasureList) 
	    st += t + " ";
	return st;
    } // end toString
} // end Creature