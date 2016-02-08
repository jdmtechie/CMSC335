/**
 * File: TheSorcerersCave.java
 * Date: 7 Feb 2016
 * @author James Moore
 * Purpose: Develop a game called The Sorcerers Cave 
 */

//CaveElements are any element in the cave
public class CaveElement {
    private int index = 0;
    private String name = "";
    private String type = "";
    
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    
    
    public String toString() {
	return "Cave Element: " + name;
    } // end toString()

} // end class CaveElements
