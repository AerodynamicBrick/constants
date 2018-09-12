package constants;

public class Pseudos
{
	private String convertORconstant(String in, int pad)
	{
		try
		{
			return(toBin(""+Integer.parseInt(in),pad));
		}
		catch(NumberFormatException e)
		{
			
		}
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
	public String add(String arg1,String arg2,String arg3)
	{
		return "0000"+convertORconstant(arg1,3)+convertORconstant(arg2,3)+convertORconstant(arg3,3)+"000";
	}
	public String sub(String arg1,String arg2,String arg3)
	{
		return "0000"+convertORconstant(arg1,3)+convertORconstant(arg2,3)+convertORconstant(arg3,3)+"001";
	}
	public String nand(String arg1,String arg2,String arg3)
	{
		return "0000"+convertORconstant(arg1,3)+convertORconstant(arg2,3)+convertORconstant(arg3,3)+"010";
	}
	public String and(String arg1,String arg2,String arg3)
	{
		return "0000"+convertORconstant(arg1,3)+convertORconstant(arg2,3)+convertORconstant(arg3,3)+"011";
	}
	public String xor(String arg1,String arg2,String arg3)
	{
		return "0000"+convertORconstant(arg1,3)+convertORconstant(arg2,3)+convertORconstant(arg3,3)+"100";
	}
	public String LShift(String arg1,String arg2,String arg3)
	{
		return "0000"+convertORconstant(arg1,3)+convertORconstant(arg2,3)+convertORconstant(arg3,3)+"101";
	}
	public String RShift(String arg1,String arg2,String arg3)
	{
		return "0000"+convertORconstant(arg1,3)+convertORconstant(arg2,3)+convertORconstant(arg3,3)+"110";
	}
	
	
	
	
	public String addi(String arg1,String arg2,String arg3)
	{
		return "0001"+convertORconstant(arg1,3)+convertORconstant(arg2, 3)+convertORconstant(arg3, 6);
	}
	public String loadImm(String arg1, String arg2)
	{
		return "0010"+convertORconstant(arg1,3)+convertORconstant(arg2, 9);
	}
	public String syscall()
	{
		return "0011"+"0000"+"0000"+"0000";
	}
	public String lw(String arg1,String arg2,String arg3)
	{
		return "0100"+convertORconstant(arg1, 3)+convertORconstant(arg2, 3)+convertORconstant(arg3, 6);
	}
	public String sw(String arg1, String arg2)
	{
		return "0101"+"000"+convertORconstant(arg1, 3)+convertORconstant(arg2, 3)+"000";
	}
	
	
	public String ifgt(String arg1, String arg2)
	{
		return "0110"+"000"+convertORconstant(arg1, 3)+convertORconstant(arg2, 3)+"011";
	}
	public String iflt(String arg1, String arg2)
	{
		return "0110"+"000"+convertORconstant(arg1, 3)+convertORconstant(arg2, 3)+"001";
	}
	public String ifeq(String arg1,String arg2)
	{
		return "0110"+"000"+convertORconstant(arg1, 3)+convertORconstant(arg2, 3)+"010";
	}
	
	
	public String jumprel(String arg1)
	{
		return "0111"+"000"+convertORconstant(arg1, 3)+"000000";
	}
	public String jumpdir(String arg1)
	{
		return "1000"+"000"+"000"+convertORconstant(arg1, 3)+"000";
	}
	public String gpc(String arg1)
	{
		return "1001"+convertORconstant(arg1, 3)+"000"+"000"+"000";
	}
	public String deel()
	{
		return "1010"+"000"+"000"+"000"+"000";
	}
	
	
	public String noop()
	{
		return "0000000000000000";
	}
}
