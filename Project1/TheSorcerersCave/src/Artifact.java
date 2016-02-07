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
      return creatureIndex;
    } // end makeArtifact
    
    public int getCreatureIndex() {
        return creatureIndex;
    }

    public void setCreatureIndex(int creatureIndex) {
        this.creatureIndex = creatureIndex;
    }

    public String toString() {
	return getType();
    } // end toString()
} // end Artifact
