import java.util.Scanner;

/**
 * File: TheSorcerersCave.java
 * Date: 24 Jan 2016
 * @author James Moore
 * Purpose: Develop a game called The Sorcerers Cave 
 */

//Artifacts are held by creatures
public class Artifact extends CaveElement {
    
    int creatureIndex;
    
    public int makeArtifact(Scanner s) {
      s.next();
      index = s.nextInt();
      type = s.next();
      creatureIndex = s.nextInt();
      return creatureIndex;
    } // end makeArtifact
    
    public String toString() {
	return type;
    } // end toString()
} // end Artifact
