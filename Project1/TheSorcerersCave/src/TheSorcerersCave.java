import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 * File: TheSorcerersCave.java
 * Date: 21 Feb 2016
 * @author James Moore
 * Purpose: Develop a game called The Sorcerers Cave 
 * 	Improve IAW Project 3 instructions
 * 	-Show data structure in JTree
 * 	-Implement Job Class
 * 	-Start Jobs at data file read in
 * 	-Use Threading to have creatures perform a single job at a time
 */

//Starts the game and maintains the GUI
public class TheSorcerersCave extends JFrame {

	private static final long serialVersionUID = 1980L;

	HashMap<Integer, CaveElement> readFileHM;
	Cave cave = new Cave();


	JTextArea jta;
	DefaultTreeModel treeModel;
	DefaultMutableTreeNode top;
	JScrollPane jspJobs, jspText, jspTree;
	JPanel jpTop, jpWest, jpJobs;
	JTree tree;
	JFileChooser jfc = new JFileChooser(".");

	public TheSorcerersCave() {
		System.out.println("In Constructor");

		setTitle("The Sorcerer's Cave");
		setSize(1200, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		getContentPane().setLayout(new BorderLayout(0, 0));

		//Top Panel
		JLabel jlSearch = new JLabel("Search Term:");
		JTextField jtfSearch = new JTextField(10);
		JLabel jlType = new JLabel("Search Type:"); 
		JComboBox<String> jComboSearch = new JComboBox<String>();
		jComboSearch.setModel(new DefaultComboBoxModel<String>(new String[] {"Index", "Name", "Type"}));

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

		jpTop = new JPanel();
		jpTop.add(jbRead);
		jpTop.add(jbDisplay);
		jpTop.add(jlSearch);
		jpTop.add(jtfSearch);
		jpTop.add(jlType);
		jpTop.add(jComboSearch);
		jpTop.add(jbSearch);
		getContentPane().add(jpTop, BorderLayout.NORTH);

		//West Panel
		jpWest = new JPanel();
		jpWest.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		jpWest.setMinimumSize(new Dimension(200, 200));
		jpWest.setMaximumSize(new Dimension(200, 200));
		jpWest.setAlignmentY(Component.TOP_ALIGNMENT);
		jpWest.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		getContentPane().add(jpWest, BorderLayout.SOUTH);

		JPanel panel = new JPanel();
		jpWest.add(panel);

		//Creature Sort
		JLabel jlCSort = new JLabel("Sort Creatures");
		panel.add(jlCSort);
		jlCSort.setHorizontalAlignment(SwingConstants.CENTER);

		JComboBox<String> jComboCSort = new JComboBox<String>();
		panel.add(jComboCSort);
		jComboCSort.setSize(new Dimension(150, 30));
		jComboCSort.setMaximumSize(new Dimension(150, 10));
		jComboCSort.setMinimumSize(new Dimension(150, 10));
		jComboCSort.setModel(new DefaultComboBoxModel<String>(new String[] {"Name", "Age", "Height", "Weight", "Empathy", "Fear", "Capacity"}));
		jlCSort.setLabelFor(jComboCSort);

		JButton jbCSort = new JButton("Sort Creatures");
		panel.add(jbCSort);
		jbCSort.setAlignmentX(Component.CENTER_ALIGNMENT);
		jbCSort.addActionListener(
				ae -> {sortCreature((String)jComboCSort.getSelectedItem());}
				);

		JPanel panel_1 = new JPanel();
		jpWest.add(panel_1);

		//Treasure Sort
		JLabel jlTSort = new JLabel("Sort Treasures");
		panel_1.add(jlTSort);
		jlTSort.setHorizontalAlignment(SwingConstants.CENTER);

		JComboBox<String> jComboTSort = new JComboBox<String>();
		panel_1.add(jComboTSort);
		jComboTSort.setSize(new Dimension(150, 10));
		jComboTSort.setMaximumSize(new Dimension(150, 10));
		jComboTSort.setMinimumSize(new Dimension(150, 10));	
		jComboTSort.setModel(new DefaultComboBoxModel<String>(new String[] {"Weight", "Value"}));
		jlTSort.setLabelFor(jComboTSort);

		JButton jbTSort = new JButton("Sort Treasure");
		panel_1.add(jbTSort);
		jbTSort.addActionListener(
				ae -> {sortTreasure((String)jComboTSort.getSelectedItem());}
				);

		//Center Panel
		JSplitPane splitPane = new JSplitPane();
		getContentPane().add(splitPane, BorderLayout.CENTER);

		//Center Left
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(new Rectangle(0, 0, 40, 0));
		splitPane.setLeftComponent(tabbedPane);

		jspText = new JScrollPane();
		jspText.setPreferredSize(new Dimension(300, 10));
		tabbedPane.addTab("Text View", null, jspText, null);

		jta = new JTextArea();
		jspText.setViewportView(jta);

		top = new DefaultMutableTreeNode("Cave");
		treeModel = new DefaultTreeModel(top);
		treeModel.addTreeModelListener(new MyTreeModelListener());
		tree = new JTree(treeModel);

		jspTree = new JScrollPane(tree);
		jspTree.setPreferredSize(new Dimension(300, 10));
		tabbedPane.addTab("Tree View", null, jspTree, null);

		//Center Right
		jpJobs = new JPanel();
		jpJobs.setLayout(new GridLayout(0, 5, 0, 0));

		jspJobs = new JScrollPane();
		jspJobs.setViewportView(jpJobs);
		splitPane.setRightComponent(jspJobs);

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
				case 'j':
				case 'J': addJob(inLine); break;   
				default: break;
				} // end switch 
			} // end while
			inLine.close();
		}catch(FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "File not found.");
		} // end try-catch
		scan.close();
		//		buildTree();
		displayCave();
		readFileHM = null;

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

	public void addJob(Scanner s) {
		Job j = new Job(readFileHM, jpJobs, s);
		int creatureIndex = j.worker.getIndex();
		Creature c = (Creature)(readFileHM.get(creatureIndex));
		if(c == null) {
			cave.unusedElements.add(j);
		} else {
			c.addJob(j);
		} // end if-else
		readFileHM.put(j.getIndex(), j);
	}

	public void displayCave() {
		jta.append("Display button pressed\n");
		jta.setText(cave.toString());
		createNode(top);
	} // end displayCave

	//Iterate through the data tree and populate nodes for the JTree
	public void createNode(DefaultMutableTreeNode top) {
		DefaultMutableTreeNode party = null;
		DefaultMutableTreeNode creature = null;
		DefaultMutableTreeNode artifacts = null;
		DefaultMutableTreeNode treasures = null;
		DefaultMutableTreeNode jobs = null;
		DefaultMutableTreeNode artifact = null;
		DefaultMutableTreeNode treasure = null;
		DefaultMutableTreeNode job = null;

		for(Party p : cave.partyList) {
			party = new DefaultMutableTreeNode(p.getName());
			top.add(party);
			for(Creature c : p.creaturesList) {
				creature = new DefaultMutableTreeNode(c.getName());
				party.add(creature);
				artifacts = new DefaultMutableTreeNode("Artifacts");
				creature.add(artifacts);
				treasures = new DefaultMutableTreeNode("Treasures");
				creature.add(treasures);
				jobs = new DefaultMutableTreeNode("Jobs");
				creature.add(jobs);
				for(Artifact a : c.artifactList) {
					artifact = new DefaultMutableTreeNode(a.getType());
					artifacts.add(artifact);
				} // end for
				for(Treasure t : c.treasureList) {
					treasure = new DefaultMutableTreeNode(t.getType());
					treasures.add(treasure);
				} // end for
				for(Job j : c.jobList) {
					job = new DefaultMutableTreeNode(j);
					jobs.add(job);
				} // end for
			} // end for
		} // end for
		
		
	} // end createNode()

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
		case "Empathy": {
			for(Party p : cave.partyList) {
				Collections.sort(p.creaturesList, (Creature c1, Creature c2) -> c1.getEmpathy().compareTo(c2.getEmpathy()));
			} // end for
			break;
		} // end case
		case "Fear": {
			for(Party p : cave.partyList) {
				Collections.sort(p.creaturesList, (Creature c1, Creature c2) -> c1.getFear().compareTo(c2.getFear()));
			} // end for
			break;
		} // end case
		case "Capacity": {
			for(Party p : cave.partyList) {
				Collections.sort(p.creaturesList, (Creature c1, Creature c2) -> c1.getCapacity().compareTo(c2.getCapacity()));
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

	//Allows treeModel to update when the nodes are changed on the initial readFile()
	class MyTreeModelListener implements TreeModelListener {

		@Override
		public void treeNodesChanged(TreeModelEvent e) {
			DefaultMutableTreeNode node;
			node = (DefaultMutableTreeNode)(e.getTreePath().getLastPathComponent());
			int index = e.getChildIndices()[0];
			node = (DefaultMutableTreeNode)(node.getChildAt(index));
		} // end treeNodesChanged()

		@Override
		public void treeNodesInserted(TreeModelEvent e) {}

		@Override
		public void treeNodesRemoved(TreeModelEvent e) {}

		@Override
		public void treeStructureChanged(TreeModelEvent e) {}
	} // end MyTreeModelListener()
} // end TheSorerersCave
