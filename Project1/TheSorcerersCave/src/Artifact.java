import java.util.Scanner;

/**
 * File: TheSorcerersCave.java
 * Date: 24 Jan 2016
 * @author James Moore
 * Purpose: Develop a game called The Sorcerers Cave 
 */

//Artifacts are held by creatures
public class Artifact extends CaveElement {
    
    private int creatureIndex;
    
    public int makeArtifact(Scanner s) {
      s.next();
      setIndex(s.nextInt());
      setType(s.next());
      creatureIndex = s.nextInt();
      setName(s.next());
      return creatureIndex;
    } // end makeArtifact
    
    public int getCreatureIndex() {
        return creatureIndex;
    }

    public void setCreatureIndex(int creatureIndex) {
        this.creatureIndex = creatureIndex;
    }

    public String toString() {
	if (creatureIndex == 0) {
	      return String.format ("a:%6d: %s : %6d : %s", getIndex(), getType(),            0, getName());
	}
	return String.format ("a:%6d: %s : %s", getIndex(), getType(), getName());
    } // end toString()
} // end Artifact
