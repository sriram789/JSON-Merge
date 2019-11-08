package jsonMerge;

import java.io.IOException;
import java.util.Scanner;

public class MainClass {

	static Scanner scanner = new Scanner( System.in );
	
	static Merge merge = new Merge();
	
	public static void main(String[] args) throws IOException {
		
		//Initializing the parameters
		String localFolderPath;
		String inputFileBaseName;
		String outputFileBaseName;
		int maxFileSize;
		
		//Getting the input from the user
		localFolderPath = scanner.next();
		inputFileBaseName = scanner.next();
		outputFileBaseName = scanner.next();
		maxFileSize = scanner.nextInt();
		
		
		//Calling the MergeJSON method.
		merge.MergeJSON(localFolderPath, inputFileBaseName, outputFileBaseName, maxFileSize);
		
		merge.ConstructProperJSON();
		
		System.exit(0);
	}

}
