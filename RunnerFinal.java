package constants;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class RunnerFinal
{
	//array of lines as strings
	private static ArrayList<String> inArray = new ArrayList<String>();
	
	//array of strings to array of Lines
	public static ArrayList<Line> lineArray = new ArrayList<Line>();
	
	//map of constants
	public static Map<String, String> constants = new HashMap<String, String>();
	
	//map of labels
	public static Map<String, Integer> labels = new HashMap<String, Integer>();
	
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		
		System.out.println("[Status] Running!");
		System.out.println("Input file full directory and name.");
		
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
				inArray.add(nxtln);
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
			System.out.println("\n\nMass Prints:");
			//print our info
			for(Line line:lineArray)
			{
				System.out.println(line.getWords());
				System.out.println(line);
				System.out.println("\t"+line.getBin());
			}
			System.out.println("\n\nErrors:");
			//output errors
			int errors=0;
			for(Line line:lineArray)
			{
				if(!line.isRecognized()&&!line.getOp().equals(""))
					{
						System.out.println("[Error] \""+line.getOp()+"\" Is not a recognised operation. "+line.getLineNum());
						errors++;
					}
			}
			//collect label definitions
			for(Line line:lineArray)
			{
				if(!(line.getLabel().equals(null)||line.getLabel().equals("")))
				{
					labels.put(line.getLabel(), line.getLineNum());
					System.out.println("Added label: \""+line.getLabel()+"\" for line #"+line.getLineNum());
				}
			}
			
			filename=null;//reseting from infile.
			System.out.println("What is the outfile name?");
			filename=in.nextLine();
			if(filename.equals(""))
			{
				System.out.println("Using the default filename.");
				filename="outfile.txt";
			}


			File outfile = new File(filename);
			if(filename.equals("")){System.out.println("Using default name. ");}
			FileOutputStream outfileStream = new FileOutputStream(outfile);
			BufferedWriter bufOfWrit = new BufferedWriter(new OutputStreamWriter(outfileStream));
			
			System.out.println("Hex/Hexidecimal/Bin/Binary");
			String hexBin = in.nextLine().toLowerCase();
			if(hexBin.equals("")){hexBin="Hex";}
			if(hexBin.equals("hex")||hexBin.equals("hexidecimal"))
			{
				System.out.println("Using Hexidecimal.");

				String pre="v2.0 raw";
				try
				{
					bufOfWrit.write(pre.toCharArray());
					bufOfWrit.write(0x0A);
					
					for(int q=0;q<lineArray.size();q++)
					{
						Line line=lineArray.get(q);
						bufOfWrit.write(line.getHex());
						if(line.getHex()!=""&&lineArray.size()-1!=q)
						{
							bufOfWrit.write(" ");
						}
					}
					bufOfWrit.write(0x0A);
					bufOfWrit.close();
					System.out.println("Wrote to file, \""+filename+"\"");
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			else if(hexBin.equals("bin")||hexBin.equals("binary"))
			{
				System.out.println("Using binary.");
				try
				{
					for(Line line:lineArray)
					{
						if(!line.getBin().equals(""))
						{
							bufOfWrit.write(line.getBin()+"\n");
						}
					}
					bufOfWrit.close();
				}
				catch (IOException e)
				{
					//Couldn't find the file.
					System.out.println("[Error] Could not find the file: "+filename);
					e.printStackTrace();
				}
			}
			else
			{
				System.out.println("Not a recognized responce.");
			}
			//remind of errors
			if(errors>1||errors==0)
			{
				System.out.println("Assembled with "+errors+" errors.");
			}
			else if (errors==1)
			{
				System.out.println("Assembled with "+errors+" error.");
			}
		}
		catch (FileNotFoundException e)
		{
			//Couldn't find the file.
			System.out.println("[Error] Could not find the file: "+filename);
			e.printStackTrace();
		}
		in.close();
	}
		
}
