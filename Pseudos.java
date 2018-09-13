package constants;

import java.util.ArrayList;

public class Pseudos
{
	private String convertORconstant(String in, int pad)
	{
		try
		{
			return toBin(""+Integer.parseInt(in),pad);
		}
		catch(NumberFormatException e)
		{
			e.printStackTrace();
		}
		return convertORconstant(Runner.constants.get(in),16);
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
	public void constant(ArrayList<?> in)
	{
		Runner.constants.put((String) in.get(0), (String) in.get(1));
		System.out.println("Initialized constant:" +(String) in.get(0)+", "+ (String) in.get(1));
	}
}
