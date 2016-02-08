import java.awt.BorderLayout;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * File: TheSorcerersCave.java
 * Date: 7 Feb 2016
 * @author James Moore
 * Purpose: Develop a game called The Sorcerers Cave 
 * 	Improve IAW Project 2 instructions
 * 	-Use HashMap data structure for caveElement linking during readFile
 * 	-Add Sort methods for Creatures by Name, Age, Weight, Height
 * 	-Add Sort methods for Treasure by Weight and Value
 * 	-Incorporate sorting into GUI
 */

//Starts the game and maintains the GUI
public class TheSorcerersCave extends JFrame {

    private static final long serialVersionUID = 1980L;
    
    JTextArea jta = new JTextArea();
    JFileChooser jfc = new JFileChooser(".");
    HashMap<Integer, CaveElement> readFileHM;
    Cave cave = new Cave();

    public TheSorcerersCave() {
	System.out.println("In Constructor");
	setTitle("The Sorcerer's Cave");
	setSize(800, 400);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setVisible(true);

	JScrollPane jsp = new JScrollPane(jta);
	add(jsp, BorderLayout.CENTER);

	//File and Search functions
	JLabel jlSearch = new JLabel("Search Term:");
	JTextField jtfSearch = new JTextField(10);
	JLabel jlType = new JLabel("Search Type:"); 
	JComboBox<String> jComboSearch = new JComboBox<String>();
	jComboSearch.addItem("Index");
	jComboSearch.addItem("Type");
	jComboSearch.addItem("Name");

	JButton jbRead = new JButton("Open");
	jbRead.addActionListener(
		ae -> {readFile();}
		);
	JButton jbDisplay = new JButton("Display Cave");
	jbDisplay.addActionListener(
		ae -> {displayCave();}
		);
	JButton jbSearch = new JButton("Search");
	jbSearch.addActionListener(
		ae -> {search((String)jComboSearch.getSelectedItem(), jtfSearch.getText());}
		);
	
	JPanel jpTop = new JPanel();
	jpTop.add(jbRead);
	jpTop.add(jbDisplay);
	jpTop.add(jlSearch);
	jpTop.add(jtfSearch);
	jpTop.add(jlType);
	jpTop.add(jComboSearch);
	jpTop.add(jbSearch);
	add(jpTop, BorderLayout.PAGE_START);
	
	//Sorting functions
	JLabel jlCSort = new JLabel("Sort Creatures");
	JComboBox<String> jComboCSort = new JComboBox<String>();
	jComboCSort.addItem("Name");
	jComboCSort.addItem("Age");
	jComboCSort.addItem("Height");
	jComboCSort.addItem("Weight");
	
	JLabel jlTSort = new JLabel("Sort Treasures");
	JComboBox<String> jComboTSort = new JComboBox<String>();
	jComboTSort.addItem("Weight");
	jComboTSort.addItem("Value");
		
	JButton jbCSort = new JButton("Sort Creatures");
	jbCSort.addActionListener(
		ae -> {sortCreature((String)jComboCSort.getSelectedItem());}
		);
	JButton jbTSort = new JButton("Sort Treasure");
	jbCSort.addActionListener(
		ae -> {sortTreasure((String)jComboTSort.getSelectedItem());}
		);
	
	JPanel jpWest = new JPanel();
	jpWest.setLayout(new BoxLayout(jpWest, BoxLayout.Y_AXIS));
	jpWest.add(jlCSort);
	jpWest.add(jComboCSort);
	jpWest.add(jbCSort);
	jpWest.add(jlTSort);
	jpWest.add(jComboTSort);
	jpWest.add(jbTSort);
	jpWest.add(Box.createVerticalGlue());
	add(jpWest, BorderLayout.WEST);
	
	validate();
    } // end TheSorcerersCave constructor

    // Browse for data file to read starting at "." and 
    // instantiate HashMap for more efficient cave population
    public void readFile() {
	readFileHM = new HashMap<Integer, CaveElement>();
	jta.append ("Read File button pressed\n");
	JFileChooser jfc = new JFileChooser(".");
	int returnVal = jfc.showOpenDialog(null);
	if(returnVal == JFileChooser.APPROVE_OPTION){
	    jta.append("You chose to open this file: " + jfc.getSelectedFile().getName());
	} // end if
	
	Scanner scan = null;
	try {
	    Scanner inLine = null;
	    scan = new Scanner(jfc.getSelectedFile());
	    scan.skip("//");
	    while(scan.hasNextLine()) {
		String line = scan.nextLine().trim();
		if(line.length() == 0) continue;
		inLine = new Scanner(line).useDelimiter("\\s*:\\s*");
		switch(line.charAt(0)){
		case 'p':
		case 'P': addParty(inLine); break;
                case 'c':
                case 'C': addCreature(inLine); break;
                case 't':
                case 'T': addTreasure(inLine); break;
                case 'a':
                case 'A': addArtifact(inLine); break;    
                default: break;
		} // end switch 
	    } // end while
	    inLine.close();
	}catch(FileNotFoundException e) {
	    JOptionPane.showMessageDialog(null, "File not found.");
	} // end try-catch
	scan.close();
	displayCave();
    } // end readFile
    
    public void addParty(Scanner s) {
	Party p = new Party(s);
	cave.partyList.add(p);
	readFileHM.put(p.getIndex(), p);
    } // end addParty
    
    public void addCreature(Scanner s) {
	Creature c = new Creature();
	int partyIndex = c.makeCreature(s);
	Party p = (Party)(readFileHM.get(partyIndex));
	if(p == null)
	    cave.unusedElements.add(c);
	else {
	    p.addCreature(c);
	} // end if-else
	readFileHM.put(c.getIndex(), c);
    } // end addCreature

    public void addTreasure(Scanner s) {
	Treasure t = new Treasure();
	int creatureIndex = t.makeTreasure(s);
	Creature c = (Creature)(readFileHM.get(creatureIndex));
	if(c == null) {
	    cave.unusedElements.add(t);
	} else {
	    c.addTreasure(t);
	} // end if-else
	readFileHM.put(t.getIndex(), t);
    } // end addTreasure
    
    public void addArtifact(Scanner s) {
	Artifact a = new Artifact();
	int creatureIndex = a.makeArtifact(s);
	Creature c = (Creature)(readFileHM.get(creatureIndex));
	if(c == null) {
	    cave.unusedElements.add(a);
	} else {
	    c.addArtifact(a);
	} // end if-else
	readFileHM.put(a.getIndex(), a);
    } // end addArtifact

    public void displayCave() {
	jta.append("Display button pressed\n");
	jta.setText(cave.toString());
    } // end displayCave
    
    // Iterate through the partyList to find creatures and sort by given variable
    public void sortCreature(String s) {
	switch (s) {
	case "Name": {
	    for(Party p : cave.partyList) {
		Collections.sort(p.creaturesList, (Creature c1, Creature c2) -> c1.getName().compareTo(c2.getName()));
	    } // end for
	    break;
	} // end case
	case "Age" : {
	    for(Party p : cave.partyList) {
		Collections.sort(p.creaturesList, (Creature c1, Creature c2) -> c1.getAge().compareTo(c2.getAge()));
	    } // end for
	    break;
	} // end case
	case "Height": {
	    for(Party p : cave.partyList) {
		Collections.sort(p.creaturesList, (Creature c1, Creature c2) -> c1.getHeight().compareTo(c2.getHeight()));
	    } // end for
	    break;
	} // end case
	case "Weight": {
	    for(Party p : cave.partyList) {
		Collections.sort(p.creaturesList, (Creature c1, Creature c2) -> c1.getWeight().compareTo(c2.getWeight()));
	    } // end for
	    break;
	} // end case
	default:
	    break;
	} // end case
	displayCave();
    } // end sortCreature
    
    // Iterate through each Creature's treasureList and sort by given variable
    public void sortTreasure(String s) {
	switch (s) {
	case "Weight": {
	    for(Party p : cave.partyList) {
		for(Creature c : p.creaturesList) {
		    Collections.sort(c.treasureList, (Treasure t1, Treasure t2) -> t1.getWeight().compareTo(t2.getWeight()));
		} // end for
	    } // end for
	    break;
	} // end case
	case "Value" : {
	    for(Party p : cave.partyList) {
		for(Creature c : p.creaturesList) {
		    Collections.sort(c.treasureList, (Treasure t1, Treasure t2) -> t1.getValue().compareTo(t2.getValue()));
		} // end for
	    } // end for
	    break;
	} // end case
	} // end switch
	displayCave();
    } // end sortTreasure

    public void search(String searchType, String searchName) {
	jta.setText(String.format("Searching %s for: %s\n", searchType, searchName));
	jta.append(cave.searchCave(searchType, searchName));
    } // end search
    
    public static void main(String[] args) {
	TheSorcerersCave cave = new TheSorcerersCave();
    } // end main
} // end TheSorerersCave
