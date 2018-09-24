package constants;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import asmblr.Preprocessing;

public class Runner
{
	//array of lines as strings
	private static ArrayList<String> inArray = new ArrayList<String>();
	
	//array of strings to array of Lines
	private static ArrayList<Line> lineArray = new ArrayList<Line>();
	
	//map of constants
	public static Map<String, String> constants = new HashMap<String, String>();
	
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		Preprocessing halp = new Preprocessing();
		
		System.out.println("[Status] Running!");
		System.out.println("Input file full directory and name required.");
		String filename=in.nextLine();
		Boolean useDefaultFileName=false;
		if(filename.equals(""))
			{
				System.out.println("[Note] No input file specified. Will use current directory's infile.txt");
				useDefaultFileName = true;
			}
		try
		{
			Scanner infile;
			if(useDefaultFileName)
			{
				// is default
				infile = new Scanner(new FileInputStream("infile.txt"));
				//add me to config, oh yea. make a config too
			}
			else
			{
				// custom name
				infile = new Scanner(new FileInputStream(filename));
			}
			
			// Let's Begin
			
			//file to array of strings
			String nxtln;
			while(infile.hasNextLine())
			{
				nxtln=infile.nextLine().trim();
				if(!nxtln.isEmpty())
				{
					inArray.add(nxtln);
				}
			}
			infile.close();
			
			
			Line temp;
			int i=1;
			for(String line:inArray)
			{
				temp = new Line(line,i);
				lineArray.add(temp);
				i++;
			}
			
			//print our info
			for(Line line:lineArray)
			{
				System.out.println(line.getWords());
				System.out.println(line);
				System.out.println("\t"+line.getBin());
			}
			
			//output errors
			for(Line line:lineArray)
			{
				if(!line.isRecognized()){System.out.println("[Error] \""+line.getOp()+"\" Is not a recognised operation."+line.getLineNum());}
			}
		}
		catch (FileNotFoundException e)
		{
			//Couldn't find the file.
			System.out.println("[Error] Could not find the file: "+filename);
			e.printStackTrace();
		}
	}
		
}
