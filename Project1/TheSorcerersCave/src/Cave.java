import java.util.ArrayList;

/**
 * @author JD
 *
 */
public class Cave {
    
    ArrayList<Party> partyList = new ArrayList<Party>();
    ArrayList<CaveElement> unusedElements = new ArrayList<CaveElement>();
    
        
    public String searchCave(String searchType, String searchName) {

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
