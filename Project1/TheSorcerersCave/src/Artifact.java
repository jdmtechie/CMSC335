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

    public int getCreatureIndex() {
        return creatureIndex;
    }

    public void setCreatureIndex(int creatureIndex) {
        this.creatureIndex = creatureIndex;
    }

    public String toString() {
	return type;
    } // end toString()
} // end Artifact
