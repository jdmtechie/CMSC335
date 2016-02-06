import java.awt.BorderLayout;
import java.io.FileNotFoundException;
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
 *
 */
public class TheSorcerersCave extends JFrame {

    private static final long serialVersionUID = 1980L;
    
    JTextArea jta = new JTextArea();
    JFileChooser jfc = new JFileChooser(".");

    Cave cave = new Cave();

    public TheSorcerersCave() {
	System.out.println("In Constructor");
	setTitle("The Sorcerer's Cave");
	setSize(600, 800);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setVisible(true);

	JScrollPane jsp = new JScrollPane(jta);
	add(jsp, BorderLayout.CENTER);

	JLabel jl = new JLabel("Target:");
	JTextField jtf = new JTextField(10);

	JComboBox<String> jCombo = new JComboBox<String>();
	jCombo.addItem("Index");
	jCombo.addItem("Type");
	jCombo.addItem("Name");

	JButton jbRead = new JButton("Read");
	jbRead.addActionListener(
		ae -> {readFile();}
		);
	JButton jbDisplay = new JButton("Display");
	jbDisplay.addActionListener(
		ae -> {displayCave();}
		);
	JButton jbSearch = new JButton("Search");
	jbSearch.addActionListener(
		ae -> {search((String)jCombo.getSelectedItem(), jtf.getText());}
		);

	JPanel jp = new JPanel();
	jp.add(jbRead);
	jp.add(jbDisplay);
	jp.add(jl);
	jp.add(jtf);
	jp.add(jCombo);
	jp.add(jbSearch);

	add(jp, BorderLayout.PAGE_START);

	validate();
    } // end TheSorcerersCave constructor

    public void readFile() {
	jta.append ("Read File button pressed\n");
	JFileChooser jfc = new JFileChooser(".");
	int returnVal = jfc.showOpenDialog(null);
	if(returnVal == JFileChooser.APPROVE_OPTION){
	    jta.append("You chose to open this file: " + jfc.getSelectedFile().getName());
	} // end if
	
	Scanner scan = null;
	try {
	    scan = new Scanner(jfc.getSelectedFile());
	    scan.skip("//");
	    while(scan.hasNextLine()) {
		String line = scan.nextLine().trim();
		if(line.length() == 0) continue;
		Scanner inLine = new Scanner(line).useDelimiter("\\s*:\\s*");
		switch(line.charAt(0)){
		case 'p':
		case 'P': cave.addParty(inLine); break;
                case 'c':
                case 'C': cave.addCreature(inLine); break;
                case 't':
                case 'T': cave.addTreasure(inLine); break;
                case 'a':
                case 'A': cave.addArtifact(inLine); break;    
                default: break;
		} // end switch 
	    } // end while
	}catch(FileNotFoundException e) {
	    JOptionPane.showMessageDialog(null, "File not found.");
	} // end try-catch
	scan.close();
    } // end readFile
    
    public void displayCave() {
	jta.append("Display button pressed\n");
	jta.setText(cave.toString());
    } // end displayCave

    public void search(String searchType, String searchName) {
	jta.append(String.format("Searching...\nType: %s\nName: %s\n", searchType, searchName));
	System.out.println(cave.partyList.);
    } // end search
    
    public static void main(String[] args) {
	TheSorcerersCave cave = new TheSorcerersCave();
    } // end main
} // end TheSorerersCave
