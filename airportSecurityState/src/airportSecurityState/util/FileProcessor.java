package airportSecurityState.util;
import 	airportSecurityState.util.MyLogger;


import java.io.File;
import java.util.Scanner;

import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.stream.Stream;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;


public class FileProcessor{
	File inFile;
	File homedir;
	Scanner scanner;
	
	/**
	FileProcessor constructor opens a file 
	@param fileName is the file to be opened
	*/
	public FileProcessor(String fileName){
		MyLogger.writeMessage("FileProcessor("+fileName+")", MyLogger.DebugLevel.CONSTRUCTOR);	
		try{
			inFile = new File(fileName);
			if(!inFile.exists()){System.out.println("File Not Found");}
			//inFile.createNewFile();
			scanner = new Scanner(inFile);
		}catch(FileNotFoundException e){
			System.out.println("fileprocessor FileNotFoundException");
		}
		//catch(IOException e){System.out.println("fileprocessor File.createNewFile threw IOException. couldnt create file?");}
	}
		
		
	/**
	@return true if the is another line to read in the associated file 
	*/
	public boolean hasNext() {
		try{
			return scanner.hasNext();
		}catch(NullPointerException e){
			System.out.println("Scanner.hasNext() threw NullPointerException. file probably not loaded");}
		return false;
	}
	

	/**
	@return returns the next line in the file 	
	*/
	public String readLine(){
		if(scanner.hasNext()){
			//return scanner.nextInt();
			return scanner.nextLine();
		}	
		return "";
	}

}
