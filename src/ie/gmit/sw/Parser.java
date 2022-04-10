package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Parser {
	private Map<String,Long> frequency = new HashMap<>(); //Creates a Hash Map
	private static boolean DESC = false;
	Runner runnerObj = new Runner();
	int numOfWordsFound = 0;
	//Function parse - O(n)
	public void parse(String dir) throws Exception{
		File f = new File(dir);
		
		FilenameFilter filter = new FilenameFilter() {
            @Override
            public boolean accept(File f, String name) {
                // We want to find only .txt files
                return name.endsWith(".txt");
            }
        };
        
		File[] files = f.listFiles(filter);//new File(dir).list(); List of files in directory
		
		for (int i = 0; i < files.length; i++) {
            String fileName = files[i].getName();
            System.out.println(dir + "/" + fileName);
            process(dir + "/" + fileName);
			}
		}
	
	//O(n^3)?
	public void process(String file) throws Exception{
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))){
			String line = null;
			while((line = br.readLine()) != null) { //While line isn't null
				line = line.trim().replaceAll("[^a-zA-Z]", "");//Replace line with only characters
				String[] words = line.toLowerCase().split(" "); //Removes line spaces and adds it to an array of words
				for(String word : words) { //For each word in array of words
					char [] w = word.toCharArray(); //Add all characters of to char array
					for(int i=0, n = runnerObj.getNgramSize(); i < (w.length+1-n); i++) { //Loop over word and extract n-gram
						//Make string equal to start of character array plus the ngram lenght set by user
						String characterSequence = String.copyValueOf(w, i, n);
						Long numOfWordsFound = frequency.get(characterSequence);
						//Word not found in map
						if(numOfWordsFound == null) {
							numOfWordsFound = Long.valueOf(1);
						} else { //Word in map
							long value = numOfWordsFound.longValue();
							numOfWordsFound = Long.valueOf(value+1);
						}
						frequency.put(characterSequence, numOfWordsFound);
					}//For loop
				}//For loop
			}//While loop
			output(file);
		}
	}//Function process
	
	FileWriter fw; //Filewriter
	//Function Output - O(n)
	private void output(String file) throws Exception{
		Map<String, Long> sortedMapDesc = sortByValue(frequency, DESC);
		fw = new FileWriter(new File(runnerObj.getSavePath() + "/" + "out.csv")); //Output csv file
		//Loop over entries and write out
		for(String sName : sortedMapDesc.keySet()) {
		String key = sName.toString();
		String value = sortedMapDesc.get(sName).toString();
		fw.write(key + "," + value + "\n");
		fw.flush();
		}
		cleanUp(); //Function to close out writer
	} 
	
	//sortByValue() - O(n)
	private static Map<String, Long> sortByValue(Map<String, Long> unsortMap, final boolean order)
    {
        List<Entry<String, Long>> list = new LinkedList<>(unsortMap.entrySet());

        // Sorting the list based on values
        list.sort((o1, o2) -> order ? o1.getValue().compareTo(o2.getValue()) == 0
                ? o1.getKey().compareTo(o2.getKey())
                : o1.getValue().compareTo(o2.getValue()) : o2.getValue().compareTo(o1.getValue()) == 0
                ? o2.getKey().compareTo(o1.getKey())
                : o2.getValue().compareTo(o1.getValue()));
        return list.stream().collect(Collectors.toMap(Entry::getKey, Entry::getValue, (a, b) -> b, LinkedHashMap::new));
    }
         
	public void cleanUp() {
		try {
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//cleaUp()
}//Class Parser