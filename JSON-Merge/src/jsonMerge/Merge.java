package jsonMerge;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Merge {

	public void ConstructProperJSON() {
		
	}
	public void MergeJSON(String localPath, String inputFileBaseName, String outputFileBaseName, int maxFileSize) throws IOException {
		
		
		//To read single character from the JSON file.
		int character;
		//To add the counter as suffix.
		Integer counter=1;
		//To initialize folder location.
		File folder=new File(localPath);
		//To initialize all the files with .json extension in an Array.
		File[] allSubFiles=folder.listFiles((dir, name) -> name.endsWith(".json"));
		//Initializing the outputFileName with counter as 1 at the start and with extension as .json.
		String outputFileName = localPath+outputFileBaseName+counter.toString()+".json";
		//Initializing the outputFile location and creating a new outputFile. 
		File outputFile=new File(outputFileName);
		//Initializing the FileWriter as null.
		FileWriter fw = null;
		
		
		/*Loop till all the .json files are covered
		if outputFile's length + current file's length is lesser than maxFileSize append all the content
		else create a new file with increamenting the counter and append the content to the newly created outputFile*/
		
		for (File file : allSubFiles) {	
			
		    if( file.isFile() ) {
		    	
		    	if( file.getName().indexOf(inputFileBaseName) != -1 ) { 
		    		
		    		if( outputFile.length() + file.length() < maxFileSize ) {
		    			
		    			//Appending the contents to the outputFile
		    			fw=new FileWriter(outputFile.getAbsolutePath(),true);
		    			FileReader fr = new FileReader(file.getAbsolutePath());
		    			while ((character=fr.read())!=-1) 
		    				fw.write((char)character); 
		    			fr.close(); 
		    			fw.close();
		    			
		    		}
		    		else {
		    			
		    			//Creation of a new File with increamented suffix ie. counter
		    			
		    			counter++;
		    			outputFileName = localPath+outputFileBaseName+counter.toString()+".json";
		    			outputFile=new File(outputFileName);
		    			
		    			//Appending the contents to the outputFile
		    			
		    			fw=new FileWriter(outputFile.getAbsolutePath(),true);
		    			FileReader fr = new FileReader(file.getAbsolutePath());
		    			while ((character=fr.read())!=-1) 
		    				fw.write((char)character); 
		    			fr.close();
		    			fw.close();	
		    			
		    		}
		    	}
		    }
		}
	}

}
