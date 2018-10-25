package constants;

import java.util.ArrayList;

public class Pseudos
{
	//helpers
	private String convertORconstant(String in, int pad)
	{
		try
		{
			return toBin(""+Integer.parseInt(in),pad);
		}
		catch(NumberFormatException e) //can't be converted to bin, must be constant's name
		{
			if(Runner.constants.containsKey(in)) //if there is a constant by that name
			{
				String constantValue=Runner.constants.get(in);
				if(Integer.parseInt(constantValue)<(2^pad)) //if that constant is within the maximum size
				{
					return toBin(constantValue,pad);
				}
				else
				{
					System.out.println("[Error] Requested constant exceeded len expected by imm.");
				}
			}
			else
			{
				System.out.println("[Error] Not a constant. Returning 0.");
			}
		}
		return this.toBin("0", pad);
	}
	public String toBin(String str, int pad)
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
	public boolean isNumeric(String s) {  
	    return s != null && s.matches("[-+]?\\d*\\.?\\d+");  
	}  
	
	//aluops
	public String add(ArrayList<?> in)
	{
		return "0000"+convertORconstant((String) in.get(0),3)+convertORconstant((String) in.get(1),3)+convertORconstant((String) in.get(2),3)+"000";
	}
	public String sub(ArrayList<?> in)
	{
		return "0000"+convertORconstant((String) in.get(0),3)+convertORconstant((String) in.get(1),3)+convertORconstant((String) in.get(2),3)+"001";
	}
	public String nand(ArrayList<?> in)
	{
		return "0000"+convertORconstant((String) in.get(0),3)+convertORconstant((String) in.get(1),3)+convertORconstant((String) in.get(2),3)+"010";
	}
	public String and(ArrayList<?> in)
	{
		return "0000"+convertORconstant((String) in.get(0),3)+convertORconstant((String) in.get(1),3)+convertORconstant((String) in.get(2),3)+"011";
	}
	public String xor(ArrayList<?> in)
	{
		return "0000"+convertORconstant((String) in.get(0),3)+convertORconstant((String) in.get(1),3)+convertORconstant((String) in.get(2),3)+"100";
	}
	public String LShift(ArrayList<?> in)
	{
		return "0000"+convertORconstant((String) in.get(0),3)+convertORconstant((String) in.get(1),3)+convertORconstant((String) in.get(2),3)+"101";
	}
	public String RShift(ArrayList<?> in)
	{
		return "0000"+convertORconstant((String) in.get(0),3)+convertORconstant((String) in.get(1),3)+convertORconstant((String) in.get(2),3)+"110";
	}
	
	
	
	
	public String addi(ArrayList<?> in)
	{
		return "0001"+convertORconstant((String) in.get(0),3)+convertORconstant((String) in.get(1), 3)+convertORconstant((String) in.get(2), 6);
	}
	public String load(ArrayList<?> in)
	{
		return "0010"+convertORconstant((String) in.get(0),3)+convertORconstant((String) in.get(1), 9);
	}
	public String syscall()
	{
		return "0011"+"0000"+"0000"+"0000";
	}
	public String lw(ArrayList<?> in)
	{
		return "0100"+convertORconstant((String) in.get(0), 3)+convertORconstant((String) in.get(1), 3)+convertORconstant((String) in.get(2), 6);
	}
	public String sw(ArrayList<?> in)
	{
		return "0101"+"000"+convertORconstant((String) in.get(0), 3)+convertORconstant((String) in.get(1), 3)+"000";
	}
	
	
	public String jumpifgt(ArrayList<?> in)
	{
		return "0110"+"000"+convertORconstant((String) in.get(0), 3)+convertORconstant((String) in.get(1), 3)+"011";
	}
	public String jumpiflt(ArrayList<?> in)
	{
		return "0110"+"000"+convertORconstant((String) in.get(0), 3)+convertORconstant((String) in.get(1), 3)+"001";
	}
	public String jumpifeq(ArrayList<?> in)
	{
		return "0110"+"000"+convertORconstant((String) in.get(0), 3)+convertORconstant((String) in.get(1), 3)+"010";
	}
	
	
	public String jumprel(ArrayList<?> in)
	{
		return "0111"+"000"+convertORconstant((String) in.get(0), 3)+"000000";
	}
	public String jumpdir(ArrayList<?> in)
	{
		return "1000"+"000"+"000"+convertORconstant((String) in.get(0), 3)+"000";
	}
	public String gpc(ArrayList<?> in)
	{
		return "1001"+convertORconstant((String) in.get(0), 3)+"000"+"000"+"000";
	}
	public String deel(ArrayList<?> in)
	{
		return "1010"+"000"+"000"+"000"+"000";
	}
	
	
	public String noop(ArrayList<?> in)
	{
		return "0000000000000000";
	}
	public String fill(ArrayList<?> in)
	{
		return convertORconstant((String) in.get(0), 16);
	}
	
	
	//the complicated bits
	public String constant(ArrayList<?> in)
	{
		Runner.constants.put((String) in.get(0), (String) in.get(1));
		System.out.println("Initialized constant:" +(String) in.get(0)+", "+ (String) in.get(1));
		return "";
	}
	public String Goto(ArrayList<?> in)//Goto is capitalized because "goto" is a defined name
	{
		//case 1: goto $#
		//case 2: goto label
		//case 3: goto constant
		//case 4: goto #

		//case 1
		if(((String) in.get(1)).startsWith("$")) //if it's a pointer
		{
			String inbin=((String) in.get(0)).substring(1);
			
			//syscall push
			//*inbin
			//load previous
			//jumpdir loaded_previous
			//edit pushed to line to load at
			//pop
			
		}
		//case 2
		else if(Runner.labels.containsKey(in.get(1))) //if it's an existing label
		{
			//syscall push
			//get pc
			//jumpdir pc+-difference
			//edit pushed to line to load at
			//pop
		}
		//case 3
		else if(Runner.constants.containsKey(in.get(1)))
		{
			//syscall push
			//get pc
			//jumpdir pc+-difference
			//edit pushed to line to load at
			//pop
		}
		//case 4
		else if(((String) in.get(1)).matches("\\d+"))
		{
			//same as case 1
			
			//syscall push
			//*inbin
			//load previous
			//jumpdir loaded_previous
			//edit pushed to line to load at
			//pop
		}
		else {System.out.println("Syntax error on goto.");}
		//if you made it here you had a syntax error.
		System.out.println("syntax error!");
		return "";
	}
}
