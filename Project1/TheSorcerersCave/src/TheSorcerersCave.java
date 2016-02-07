import java.awt.BorderLayout;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
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
 * Date: 24 Jan 2016
 * @author James Moore
 * Purpose: Develop a game called The Sorcerers Cave 
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
	setSize(600, 800);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setVisible(true);

	JScrollPane jsp = new JScrollPane(jta);
	add(jsp, BorderLayout.CENTER);

	JLabel jlSearch = new JLabel("Search Term:");
	JTextField jtf = new JTextField(10);
	JLabel jlType = new JLabel("Search Type:"); 
	JComboBox<String> jCombo = new JComboBox<String>();
	jCombo.addItem("Index");
	jCombo.addItem("Type");
	jCombo.addItem("Name");

	JButton jbRead = new JButton("Open");
	jbRead.addActionListener(
		ae -> {readFile();}
		);
	JButton jbSearch = new JButton("Search");
	jbSearch.addActionListener(
		ae -> {search((String)jCombo.getSelectedItem(), jtf.getText());}
		);

	JPanel jp = new JPanel();
	jp.add(jbRead);
	jp.add(jlSearch);
	jp.add(jtf);
	jp.add(jlType);
	jp.add(jCombo);
	jp.add(jbSearch);

	add(jp, BorderLayout.PAGE_START);

	validate();
    } // end TheSorcerersCave constructor

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

    public void search(String searchType, String searchName) {
	jta.setText(String.format("Searching %s for: %s\n", searchType, searchName));
	jta.append(cave.searchCave(searchType, searchName));
    } // end search
    
    public static void main(String[] args) {
	TheSorcerersCave cave = new TheSorcerersCave();
    } // end main
} // end TheSorerersCave
