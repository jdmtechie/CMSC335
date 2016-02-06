import java.util.Scanner;

/**
 * @author JD
 *
 */
public class Artifact extends CaveElement {
    
    int index;
    String type;
    String name = null;
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
