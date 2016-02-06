import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author JD
 *
 */
public class Cave {
    
    ArrayList<Party> partyList = new ArrayList<Party>();
    ArrayList<CaveElement> unusedElements = new ArrayList<CaveElement>();
    
    public void addParty(Scanner s) {
	Party p = new Party(s);
	partyList.add(p);
    } // end addParty
    
    public void addCreature(Scanner s) {
	Creature c = new Creature();
	int partyIndex = c.makeCreature(s);
	for(Party p : partyList) {
	    if(partyIndex == p.index) {
		p.addCreature(c);
		System.out.println(c.name + " added to " + p.index);
	    } // end if
	} // end for
    } // end addCreature

    public void addTreasure(Scanner s) {
	Treasure t = new Treasure();
	int creatureIndex = t.makeTreasure(s);
	if(creatureIndex == 0) {
	    unusedElements.add(t);
	}else{
	    for(Party p : partyList) {
		for(Creature c : p.creaturesList) {
		    if(creatureIndex == c.index) {
			c.addTreasure(t);
		    } // end if
		} // end for
	    } // end for
	} // end if-else
    } // end addTreasure
    
    public void addArtifact(Scanner s) {
	Artifact a = new Artifact();
	int creatureIndex = a.makeArtifact(s);
	if(creatureIndex == 0) {
	    unusedElements.add(a);
	}else{
	    for(Party p : partyList) {
		for(Creature c : p.creaturesList) {
		    if(creatureIndex == c.index) {
			c.addArtifact(a);
		    } // end if
		} // end for
	    } // end for
	} // end if-else
    } // end addArtifact
    
    public String searchCave(String searchType, String searchName) {
	if(partyList.
//	String result = "";
//	for(Party p : partyList) {
//	    
//	    for(Creature c : p.creaturesList) {
//		for(Treasure t : c.treasureList) {
//		    if(t.index == Integer.valueOf(searchName)) {
//			result += t.toString();
//		    }
//		    if(t.)
//		}
//		for( Artifact a : c.artifactList){
//		    
//		}
//	    }
//	}
	return result + "\nSearch Complete.";
    }
    
    public String toString() {
	String st = "Cave.toString:\nThe Parties\n";
	for(Party p: partyList) 
	    st += p + "\n";
	st += "\n+++++++\nThe unassociated stuff:\n";
	for(CaveElement c: unusedElements) {
	    st += c.toString() + "\n";
	}
	return st;
    } // end toString
} // end Cave
