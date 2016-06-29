/*Author: Austin Burger
 * References: Online Documentation on Comparator and Collections libraries.
 * 
* INSTRUCTIONS PLEASE READ!!!!!!!
* ---------------------------------
* 1.In order to run this program, it is necessary to have the input.txt file in the TextFileSort Folder Directory.
* This is where the input data is taken from. 
* 2. Upon running the application, an output.txt file will be created and placed within the TextFileSort Folder Directory
* 	a. **NOTE: If you do not see output.txt, please click the TextFileSort folder in Project Explorer and hit F5/refresh the folder.
*/

import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TextFileSort{
		public static void main(String[] args) throws IOException{
			
			/*
			 * First we take user console input to get the input file name.
			 */
			System.out.print("Enter name of input file(Example:input.txt). :");
			Scanner inputReader = new Scanner(System.in);
			String inputFile = inputReader.next();
			inputReader.close();
			
			/*
			 * First we get the input file(input.txt), then create and fill an
			 * ArrayList with the strings of data.
			 * The try catch will make sure the file entered is in the directory.
			 */
			File inFile = null;
			Scanner fileScanner = null;
			try{
			 inFile = new File (inputFile);
			 fileScanner = new Scanner(inFile);
			}
			catch(Exception e){
				System.out.println("The file you entered does not Exist in the TextFileSort Folder.");
				System.exit(0);
			}
			
			//ArrayList containing the lines of Strings.
			ArrayList<String> linesList = new ArrayList<String>();
						
			while (fileScanner.hasNextLine()){
				linesList.add(fileScanner.nextLine());				
			}			
			fileScanner.close();
			
			/*
			 * We use the Collections library to sort the list first
			 * based on numbers given at the start of the lines.
			 */
			Collections.sort(linesList, new Comparator<String>() {
			    public int compare(String a, String b) {
			        return Integer.signum(extractLineNumber(a) - extractLineNumber(b));
			    }			   
			});
			
			/*
			 * We create a List of integers. each element represents a particular
			 * integer appearing at least once in the text file(No repeats in this list). 
			 */
			ArrayList<Integer> lineNumbers = new ArrayList<Integer>();
			for(int i = 0; i < linesList.size(); i++){
				if(!lineNumbers.contains(extractLineNumber(linesList.get(i)))){
					lineNumbers.add(extractLineNumber(linesList.get(i)));					
				}
			}		

			/*
			 * In this next portion we have the String list sorted according to numbers.
			 * We now sort each set of strings with the same number alphabetically.
			 * We do this by using an index range to determine which strings to sort together
			 * (based on having a common number).
			 */
			int firstElementIndex = 0;
			int lastElementIndex = 0;			
			for(int i = 0; i < lineNumbers.size(); i++){  
				for(int j = firstElementIndex; j < linesList.size(); j++){					
					if(lineNumbers.get(i) == extractLineNumber(linesList.get(j))){
						lastElementIndex++;						
					}
					else{
						sortAlphabetically(linesList, firstElementIndex, lastElementIndex );						
						firstElementIndex = lastElementIndex;
						break;
					}
				}				
			}		
			
			/*
			 * Print the resulting String ArrayList to the output.txt file 
			 */
			PrintWriter printWriter = new PrintWriter("output.txt");
			for(int i = 0; i < linesList.size(); i++){
				printWriter.println(linesList.get(i));
			}
			printWriter.close();
			
			System.out.println("\nTextFileSort Was Successful!  Check output.txt to see the solution.");
		}	
		
		/*
		 * This Method takes in a line and determines its front number
		 */
		private static int extractLineNumber(String str) {			
		    	String[] array = str.split("[^\\d]+");
		    	return Integer.parseInt(array[0]);
		}
		
		/*
		 * This Method takes in an arrayList, a first index and a last index.
		 * It creates a subArrayList of the parameter arrayList based on the 2 indexes.
		 * it then removes numbers and spaces and sorts the sub array alphabetically.
		 */
		private static void sortAlphabetically(ArrayList<String> array, int firstElementIndex, int lastElementIndex ) {			
			Collections.sort(array.subList(firstElementIndex,lastElementIndex),  new Comparator<String>() {
			    public int compare(String a, String b) {			    	
			    	String aWithoutSpaces = a.replaceAll("\\s+", "");					//Temporary remove spaces from string    	
			    	String bWithoutSpaces = b.replaceAll("\\s+", "");
			    	String aWithoutSpaces1 = aWithoutSpaces.replaceAll("\\d+", "");		//Temporary remove number from string			    	
			    	String bWithoutSpaces1 = bWithoutSpaces.replaceAll("\\d+", "");			    	
			    	return aWithoutSpaces1.compareToIgnoreCase(bWithoutSpaces1);
			    }			   
			});
	}		 
}