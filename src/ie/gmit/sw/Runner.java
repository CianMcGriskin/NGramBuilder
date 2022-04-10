package ie.gmit.sw;

import java.util.*;

import javax.swing.JFileChooser;

public class Runner {
private static int nGramSize = 0;
private static String saveDir = "";
private static String openFileDir = "";
	//O(n)
	public static void main(String[] args) throws Exception {
		
		Parser parser = new Parser(); //Parser Object
		Scanner scanner = new Scanner(System.in); //Scanner Object
		
		int selection = 0;
		textOptions();
		while(selection != 5)
		{
			selection = scanner.nextInt();
			if(selection == 1) {
				openFileDir = SpecifyTextDir();
			} else if(selection == 2) {
				System.out.println("Please Enter the n-Gram Size: ");
				nGramSize = scanner.nextInt();
			} else if(selection == 3) {
				saveDir = SpecifyTextDir();
			} else if(selection == 4) {
				//Build n-Grams
				parser.parse(openFileDir);
			}
			textOptions();
		}	
	}
	//O(1)
	public static void textOptions() {
		//You should put the following code into a menu or Menu class
				System.out.println("************************************************************");
				System.out.println("*      GMIT - Dept. Computer Science & Applied Physics     *");
				System.out.println("*                                                          *");
				System.out.println("*                  N-Gram Frequency Builder                *");
				System.out.println("*                                                          *");
				System.out.println("************************************************************");
				System.out.println("(1) Specify Text File Directory");
				System.out.println("(2) Specify n-Gram Size");
				System.out.println("(3) Specify Output File Directory");
				System.out.println("(4) Build n-Grams ");
				System.out.println("(5) Quit");
				
				//Output a menu of options and solicit text from the user
				System.out.print("Select Option [1-4]>\n");
	}
	//O(1)
	public static String SpecifyTextDir() {
		JFileChooser f = new JFileChooser();
		f.setDialogTitle("Choose Save Directory Or Text File");
		f.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		f.showSaveDialog(null);
        System.out.println("Path Chosen: " + f.getSelectedFile().getAbsolutePath());
        String path = f.getSelectedFile().getAbsolutePath();
        return path;
		}
	//O(1)
	public int getNgramSize() {
			return nGramSize;
		}
	//O(1)
	public String getSavePath() {
			return saveDir;
		}
	//O(1)
	public String getOpenFileDir() {
			return openFileDir;
		}
	
	}