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
    
        
    public String searchCave(String searchType, String searchName) {
	
	
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
	return "\nSearch Complete.";
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
