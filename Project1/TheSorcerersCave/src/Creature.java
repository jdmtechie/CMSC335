import java.util.ArrayList;
import java.util.Scanner;

/**
 * File: TheSorcerersCave.java
 * Date: 7 Feb 2016
 * @author James Moore
 * Purpose: Develop a game called The Sorcerers Cave 
 */

//Creatures make up parties in the cave and hold artifacts and 
// treasure
public class Creature extends CaveElement {
    
    ArrayList<Treasure> treasureList = new ArrayList<Treasure>();
    ArrayList<Artifact> artifactList = new ArrayList<Artifact>();
    ArrayList<Job> jobList = new ArrayList<Job>();
    
    private int partyIndex;
    private Double empathy;
    private Double fear;
    private Double capacity;
    private Double age;
    private Double height;
    private Double weight;
    private Boolean busyFlag = false;

    public int makeCreature(Scanner s) {
	s.next();
	setIndex(s.nextInt());
	setType(s.next());
	setName(s.next());
	partyIndex = s.nextInt();
	empathy = s.nextDouble();
	fear = s.nextDouble();
	capacity = s.nextDouble();
	age = s.nextDouble();
	height = s.nextDouble();
	weight = s.nextDouble();
	return partyIndex;
    } // end makeCreature
    
    public void addTreasure(Treasure t) {
	treasureList.add(t);
    } // end addTreasure
    
    public void addArtifact(Artifact a) {
	artifactList.add(a);
    } // end addArtifact
    
    public void addJob(Job j) {
    	jobList.add(j);
    } // end addJob
    
    public int getPartyIndex() {
        return partyIndex;
    }

    public void setPartyIndex(int partyIndex) {
        this.partyIndex = partyIndex;
    }

    public Double getEmpathy() {
        return empathy;
    }

    public void setEmpathy(Double empathy) {
        this.empathy = empathy;
    }

    public Double getFear() {
        return fear;
    }

    public void setFear(Double fear) {
        this.fear = fear;
    }

    public Double getCapacity() {
        return capacity;
    }

    public void setCapacity(Double capacity) {
        this.capacity = capacity;
    }

    public Double getAge() {
        return age;
    }

    public void setAge(Double age) {
        this.age = age;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Boolean isBusyFlag() {
		return busyFlag;
	}

	public void setBusyFlag(Boolean busyFlag) {
		this.busyFlag = busyFlag;
	}

	public String toString() {
	String st = "     " + getName() + ":";
	if (partyIndex == 0) {
	    st += String.format("c:%6d: %s : %s : a-%4.1f : h-%4.1f : w-%4.1f : e-%4.1f : f-%4.1f : c-%4.1f", getIndex(), getType(), getName(), age, height, weight,           0, empathy, fear, capacity);
	}
	st += String.format("c:%6d: %s : %s : a-%4.1f : h-%4.1f : w-%4.1f : e-%4.1f : f-%4.1f : c-%4.1f", getIndex(), getType(), getName(), age, height, weight, empathy, fear, capacity) + "\n";
	for (Artifact a: artifactList) 
	    st += "           " + a + "\n";
	for (Treasure t: treasureList) 
	    st += "           " + t + "\n";
	for (Job j : jobList) {
		st += "           " + j + "\n";
	}
	return st;
    } // end toString
} // end Creature