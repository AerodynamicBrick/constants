package constants;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class Line
{
	Pseudos psu = new Pseudos();
	
	private String instructionWords="";
	private String instructionBin="";
	
	private ArrayList<String> operands = new ArrayList<String>();
	private String label = "";
	private String op;
	private String comment;
	private int lineNumber;
	private boolean isRecognisedOp;
	
	
	public Line(String instruction, int lineNumber)
	{
		instructionWords=instruction;
		this.lineNumber=lineNumber;
		this.decode(instruction);
		this.assemble();
	}

	private void assemble()
	{
		java.lang.reflect.Method method;
		try
		{
			if(this.op!="")
			{
				method = psu.getClass().getMethod(this.op, ArrayList.class); //psu.op(operands)
				this.instructionBin = (String) method.invoke(psu, operands);
				
				//if you didn't catch by here than the op is not recognised.
				isRecognisedOp=true;
			}
		}
		catch (Exception e) {isRecognisedOp=false;}
	}

	private void decode(String lin)
	{
		String linarr[] = {"","","",""};
		//"   label: op operand,operand ;comment   "
		lin=lin.trim();
		//"label: op operand,operand ;comment"
		lin=lin.toLowerCase();
		if(lin.contains(";"))
		{
			linarr[3]=lin.substring(lin.indexOf(";")).replaceFirst(";","");
			lin=lin.substring(0,lin.indexOf(";"));
			lin=lin.replaceFirst(";","");
			lin=lin.trim();
		}
		if(lin.contains(":"))
		{
			linarr[0]=lin.substring(0, lin.indexOf(":"));
			lin=lin.substring(lin.indexOf(":"));
			lin=lin.replaceFirst(":","");
			lin=lin.trim();
		}
		for(String seg:linarr)
		{
			seg=seg.trim();
		}
		lin=lin.trim();

		if(!(lin.indexOf(" ")==-1))
		{
			linarr[2]=lin.substring(lin.indexOf(" "));
			//System.out.println(lin);
			linarr[1]=lin.substring(0,lin.indexOf(" "));
		}
		//{"label:", "op", "operand,operand", ";comment"}
		for(String operand:linarr[2].split(","))
		{
			operand=operand.trim();
			operands.add(operand);
		}
		this.label=linarr[0];
		this.op=linarr[1];
		
		this.comment=linarr[3];
	}
	public String getWords()
	{
		return instructionWords;
	}
	public String getBin()
	{
		return instructionBin;
	}
	public String toString()
	{
		String out="";
		if(this.label!="") {out+=" label: "+this.label;}
		if(this.op!="") {out+=" op: "+this.op;}
		if(!this.operands.isEmpty()) {out+=" operands: "+this.operands;}
		if(this.comment!="") {out+=" comment: "+this.comment;}
		if(this.isRecognisedOp) {out+= ", recognised: "+isRecognisedOp;}
		return out;
	}
}
