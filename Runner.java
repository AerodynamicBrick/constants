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
			for(Line line:lineArray)
			{
				if(!line.isRecognized()&&!line.getOp().equals("")){System.out.println("[Error] \""+line.getOp()+"\" Is not a recognised operation. "+line.getLineNum());}
			}
			//assign and link labels
			
			//output
			System.out.println("Output Hex File for logisim? no/yes");
			boolean logisim = true;
			String logisimInTemp=in.nextLine();
			if(logisimInTemp.equals("yes")){logisim = true;}
			else if(logisimInTemp.equals("no")){logisim = false;}
			else if(logisimInTemp.equals("")){logisim = false;}
			else System.out.println("[Error] Not a recognised responce.");
			
			String hex;
			if(!logisim)
			{
				System.out.println("Binary or Hex? bin/binary/hex/hexadecimal");
				hex = in.nextLine();
			}
			else{hex="hex";} //sets to hex for logisim export
			
			System.out.println("Input outfile name.");
			filename=in.nextLine();
			if(filename.equals(""))
			{
				System.out.println("[Note] Using default filename.");
				filename = "outfile.txt";
			}
			
			
			ArrayList<String> tempBin = new ArrayList<String>();
				for(Line line:lineArray)
				{
					tempBin.add(line.getBin());
				}
			ArrayList<String> tempHex = new ArrayList<String>();
				for(Line line:lineArray)
				{
					System.out.println("TST:"+line.getBin());
					tempHex.add(toHex(line.getBin()));
				}
			
			if(!logisim) //normal op
			{
				File outfile = new File(filename);
				FileOutputStream outfileStream = new FileOutputStream(outfile);
				BufferedWriter bufOfWrit = new BufferedWriter(new OutputStreamWriter(outfileStream));
				try
				{
					System.out.println("\'line\' delimination or \'space\' delimination");
					String delim = in.nextLine();
					if(delim.equals("space"))
					{
						String out="";
						for(int a=0;a<tempBin.size();a++)
						{
							out+=tempBin.get(a)+" ";
						}
						bufOfWrit.write(out);
						bufOfWrit.close();
					}
					else if(delim.equals("line"))
					{
						for(int b=0;b<tempBin.size();b++)
						{
							bufOfWrit.write(tempBin.get(b));
							if(1+b<tempBin.size()){bufOfWrit.newLine();}
						}
						bufOfWrit.close();
					}
					else
					{
						System.out.println("[Note] Not recognised.");
					}
				}
				catch (IOException e)
				{
					System.out.println("[Error] Outfile "+filename+" does not exist.");
					e.printStackTrace();
				}
				System.out.println("[Note] Wrote to "+filename);
			}
			else if(logisim)
			{
				File outfile = new File(filename);
				FileOutputStream outfileStream = new FileOutputStream(outfile);
				
				try
				{
					String pre="v2.0 raw";
					for(char ch:pre.toCharArray())
					{
						outfileStream.write(ch);
					}
					outfileStream.write(0x0A);
					for(int c=0;c<tempHex.size();c++)
					{
						char[] chray = tempHex.get(c).toCharArray();
						for(int j=0;j<chray.length;j++)
						{
							outfileStream.write(chray[j]);
							if(j==chray.length-1&&c!=tempHex.size()-1){outfileStream.write(' ');}
						}
					}
					outfileStream.write(0x0A);
					outfileStream.close();
					System.out.println("[Note] wrote to "+filename);
				}
				catch (IOException e)
				{
					System.out.println("[Error] Outfile "+filename+" does not exist.");
					e.printStackTrace();
				}
			}
			else {System.out.println("[Error] Not a recognised responce.");}
		}
		catch (FileNotFoundException e)
		{
			//Couldn't find the file.
			System.out.println("[Error] Could not find the file: "+filename);
			e.printStackTrace();
		}
	}
	public static String toBin(String str, int pad)
	{
		int no = 0;
		no = Integer.parseInt(str);
		StringBuilder result = new StringBuilder();
		int i = 0;
		while (no > 0)
		{
			result.append(no%2);
			i++;
			no = no/2;
		}
		return String.format("%"+pad+"s", result.reverse().toString()).replace(" ", "0");
	}
	public static String toHex(String in)
	{
		System.out.println(in+":To Hex");
		int decimal = Integer.parseInt(in,2);
		String hex = Integer.toString(decimal,16);
		return hex;
	}
}
