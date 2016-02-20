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
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;

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
	JFileChooser jfc = new JFileChooser(".");
	HashMap<Integer, CaveElement> readFileHM;
	Cave cave = new Cave();

	public TheSorcerersCave() {
		System.out.println("In Constructor");
		setTitle("The Sorcerer's Cave");
		setSize(800, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		//File and Search functions
		JLabel jlSearch = new JLabel("Search Term:");
		JTextField jtfSearch = new JTextField(10);
		JLabel jlType = new JLabel("Search Type:"); 
		JComboBox<String> jComboSearch = new JComboBox<String>();
		jComboSearch.setModel(new DefaultComboBoxModel(new String[] {"Index", "Type", "Name"}));

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
		getContentPane().add(jpTop, BorderLayout.PAGE_START);

		JPanel jpWest = new JPanel();
		GridBagLayout gbl_jpWest = new GridBagLayout();
		gbl_jpWest.columnWidths = new int[] {100};
		gbl_jpWest.rowHeights = new int[] {20, 20, 20, 0, 20, 20, 20};
		gbl_jpWest.columnWeights = new double[]{0.0};
		gbl_jpWest.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		jpWest.setLayout(gbl_jpWest);

		//Sorting functions
		JLabel jlCSort = new JLabel("Sort Creatures");
		GridBagConstraints gbc_jlCSort = new GridBagConstraints();
		gbc_jlCSort.insets = new Insets(0, 0, 5, 0);
		gbc_jlCSort.gridx = 0;
		gbc_jlCSort.gridy = 0;
		jpWest.add(jlCSort, gbc_jlCSort);

		JComboBox<String> jComboCSort = new JComboBox<String>();
		jlCSort.setLabelFor(jComboCSort);
		jComboCSort.setModel(new DefaultComboBoxModel<String>(new String[] {"Name", "Age", "Height", "Weight", "Empathy", "Fear", "Capacity"}));
		GridBagConstraints gbc_jComboCSort = new GridBagConstraints();
		gbc_jComboCSort.fill = GridBagConstraints.HORIZONTAL;
		gbc_jComboCSort.insets = new Insets(0, 0, 5, 0);
		gbc_jComboCSort.gridx = 0;
		gbc_jComboCSort.gridy = 1;
		jpWest.add(jComboCSort, gbc_jComboCSort);

		JButton jbCSort = new JButton("Sort Creatures");
		jbCSort.addActionListener(
				ae -> {sortCreature((String)jComboCSort.getSelectedItem());}
				);
		GridBagConstraints gbc_jbCSort = new GridBagConstraints();
		gbc_jbCSort.fill = GridBagConstraints.HORIZONTAL;
		gbc_jbCSort.insets = new Insets(0, 0, 5, 0);
		gbc_jbCSort.gridx = 0;
		gbc_jbCSort.gridy = 2;
		jpWest.add(jbCSort, gbc_jbCSort);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 3;
		jpWest.add(separator, gbc_separator);

		JLabel jlTSort = new JLabel("Sort Treasures");
		GridBagConstraints gbc_jlTSort = new GridBagConstraints();
		gbc_jlTSort.insets = new Insets(0, 0, 5, 0);
		gbc_jlTSort.gridx = 0;
		gbc_jlTSort.gridy = 4;
		jpWest.add(jlTSort, gbc_jlTSort);

		JComboBox<String> jComboTSort = new JComboBox<String>();
		jComboTSort.setModel(new DefaultComboBoxModel<String>(new String[] {"Weight", "Value"}));
		GridBagConstraints gbc_jComboTSort = new GridBagConstraints();
		gbc_jComboTSort.fill = GridBagConstraints.HORIZONTAL;
		gbc_jComboTSort.insets = new Insets(0, 0, 5, 0);
		gbc_jComboTSort.gridx = 0;
		gbc_jComboTSort.gridy = 5;
		jpWest.add(jComboTSort, gbc_jComboTSort);
		getContentPane().add(jpWest, BorderLayout.WEST);
		
		JButton jbTSort = new JButton("Sort Treasure");
		jbTSort.addActionListener(
				ae -> {sortTreasure((String)jComboTSort.getSelectedItem());}
				);
		GridBagConstraints gbc_jbTSort = new GridBagConstraints();
		gbc_jbTSort.fill = GridBagConstraints.HORIZONTAL;
		gbc_jbTSort.gridx = 0;
		gbc_jbTSort.gridy = 6;
		jpWest.add(jbTSort, gbc_jbTSort);
		
		JPanel jpCenter = new JPanel();
		getContentPane().add(jpCenter, BorderLayout.CENTER);
		
		JTree tree = new JTree();
		tree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("JTree") {
				{
					DefaultMutableTreeNode node_1;
					node_1 = new DefaultMutableTreeNode("colors");
						node_1.add(new DefaultMutableTreeNode("blue"));
						node_1.add(new DefaultMutableTreeNode("violet"));
						node_1.add(new DefaultMutableTreeNode("red"));
						node_1.add(new DefaultMutableTreeNode("yellow"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("sports");
						node_1.add(new DefaultMutableTreeNode("basketball"));
						node_1.add(new DefaultMutableTreeNode("soccer"));
						node_1.add(new DefaultMutableTreeNode("football"));
						node_1.add(new DefaultMutableTreeNode("hockey"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("food");
						node_1.add(new DefaultMutableTreeNode("hot dogs"));
						node_1.add(new DefaultMutableTreeNode("pizza"));
						node_1.add(new DefaultMutableTreeNode("ravioli"));
						node_1.add(new DefaultMutableTreeNode("bananas"));
					add(node_1);
				}
			}
		));
		jpCenter.add(tree);

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
