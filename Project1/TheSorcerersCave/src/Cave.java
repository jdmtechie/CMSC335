import java.util.ArrayList;
import java.util.Scanner;

/**
 * File: TheSorcerersCave.java
 * Date: 7 Feb 2016
 * @author James Moore
 * Purpose: Develop a game called The Sorcerers Cave 
 */

//The cave contains parties and non affiliated elements
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
	    if(partyIndex == p.getIndex()) {
		p.addCreature(c);
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
		    if(creatureIndex == c.getIndex()) {
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
		    if(creatureIndex == c.getIndex()) {
			c.addArtifact(a);
		    } // end if
		} // end for
	    } // end for
	} // end if-else
    } // end addArtifact
    
    public String searchCave(String searchType, String searchName) {
	searchName = searchName.trim();
	searchType = searchType.trim();
	String result = "";
	
	if(searchType.equalsIgnoreCase("index")) {
	    int i = 0;
	    try {
		i = Integer.parseInt(searchName);
	    } catch (NumberFormatException e) {
		result += "Invalid index search. Please enter an integer.";
	    } // end try-catch
	    
	    for(Party p : partyList) {
		if(i == p.getIndex()) {
		    result += p.toString();
		} // end if
		for(Creature c : p.creaturesList) {
		    if(i == c.getIndex()) {
			result += c.toString();
		    } // end if
		    for(Treasure t : c.treasureList) {
			if(i == t.getIndex()) {
			    result += t.toString();
			} // end if
		    } // end for
		    for( Artifact a : c.artifactList){
			if(i == a.getIndex()) {
			    result += a.toString();
			} // end if
		    } // end for
		} // end for
	    } // end for
	} // end if
	
	if(searchType.equalsIgnoreCase("name")) {
	    for(Party p : partyList) {
		if(searchName.equalsIgnoreCase(p.getName())) {
		    result += p.toString();
		} // end if
		for(Creature c : p.creaturesList) {
		    System.out.print("|" + c.getName() + "|");
		    if(searchName.equalsIgnoreCase(c.getName())) {
			result += c.toString();
		    } // end if
		    for(Treasure t : c.treasureList) {
			if(searchName.equalsIgnoreCase(t.getName())) {
			    result += t.toString();
			} // end if
		    } // end for
		    for( Artifact a : c.artifactList){
			if(searchName.equalsIgnoreCase(a.getName())) {
			    result += a.toString();
			} // end if
		    } // end for
		} // end for
	    } // end for
	} // end if
	
	if (searchType.equalsIgnoreCase("type")) {
	    for(Party p : partyList) {
		if(searchName.equalsIgnoreCase(p.getType())) {
		    result += p.toString();
		} // end if
		for(Creature c : p.creaturesList) {
		    if(searchName.equalsIgnoreCase(c.getType())) {
			result += c.toString();
		    } // end if
		    for(Treasure t : c.treasureList) {
			if(searchName.equalsIgnoreCase(t.getType())) {
			    result += t.toString();
			} // end if
		    } // end for
		    for( Artifact a : c.artifactList){
			if(searchName.equalsIgnoreCase(a.getType())) {
			    result += a.toString();
			} //end if
		    } // end for
		} // end for
	    } // end for
	} // end if
	
	if(result.length() < 1){
	    result += "Nothing Found";
	} //end if
	return result + "\nSearch Complete.";
    } // end searchCave

    public String toString() {
	String st = "Cave.toString:\nThe Parties\n";
	for(Party p: partyList) 
	    st += p + "\n";
	st += "\n+++++++\nThe unassociated stuff:\n";
	for(CaveElement c: unusedElements) {
	    st += c.toString() + "\n";
	} // end for
	return st;
    } // end toString
} // end Cave
