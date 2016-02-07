import java.util.ArrayList;
import java.util.Scanner;

/**
 * File: TheSorcerersCave.java
 * Date: 24 Jan 2016
 * @author James Moore
 * Purpose: Develop a game called The Sorcerers Cave 
 */

//Creatures make up parties in the cave and hold artifacts and 
// treasure
public class Creature extends CaveElement {
    
    ArrayList<Treasure> treasureList = new ArrayList<Treasure>();
    ArrayList<Artifact> artifactList = new ArrayList<Artifact>();
    
    private int partyIndex;
    private int empathy;
    private int fear;
    private double capacity;

    public int makeCreature(Scanner s) {
	s.next();
	setIndex(s.nextInt());
	setType(s.next());
	setName(s.next());
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
	String st = "        " + getName() + "\n                  Artifacts: ";
	for (Artifact a: artifactList) 
	    st += a + " ";
	st += "\n                  Treasures: ";
	for (Treasure t: treasureList) 
	    st += t + " ";
	return st;
    } // end toString
} // end Creature