import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author JD
 *
 */
public class Creature extends CaveElement {
    
    ArrayList<Treasure> treasureList = new ArrayList<Treasure>();
    ArrayList<Artifact> artifactList = new ArrayList<Artifact>();
    
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
    
    public void addTreasure(Treasure t) {
	treasureList.add(t);
    } // end addTreasure
    
    public void addArtifact(Artifact a) {
	artifactList.add(a);
    } // end addArtifact
    
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

    public int getPartyIndex() {
        return partyIndex;
    }

    public void setPartyIndex(int partyIndex) {
        this.partyIndex = partyIndex;
    }

    public int getEmpathy() {
        return empathy;
    }

    public void setEmpathy(int empathy) {
        this.empathy = empathy;
    }

    public int getFear() {
        return fear;
    }

    public void setFear(int fear) {
        this.fear = fear;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

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