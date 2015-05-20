package bgl2sdl;

import java.util.Arrays;
import java.util.Vector;

import gen.XMLParser;

public class AttributeChecker {
	

	

	public boolean checkLatitude(String Value, XMLParser.AttributeContext Ctx)
	{
		String attName = Ctx.attributeName().getText();
		if(Value.split("-").length==3)
		{
			String[] attValue = Value.split("-");
			
			
			if(Float.parseFloat(attValue[0])<-90 || Float.parseFloat(attValue[0])>90)
			{
				System.out.println("Line "+Ctx.getStart().getLine()+": invalid " + attName + " value : " + attValue[0]);
				return false;
			}
			
		}
		else{
		
		try
		{
		if(Float.parseFloat(Value)<-90.0 || Float.parseFloat(Value)>90.0)
		{
			System.out.println("Line "+Ctx.getStart().getLine()+": invalid " + attName + " value : " + Ctx.attributeValue().getText());
			return false;
		}
		}
		catch(NumberFormatException e)
		{
			System.out.println("Line "+Ctx.getStart().getLine()+": invalid " + attName + " value : " + Ctx.attributeValue().getText());
			return false;
		}
		}
		return true;
	}

	public boolean checkLongitude(String Value, XMLParser.AttributeContext Ctx)
	{
		String attName = Ctx.attributeName().getText();
		
		if(Value.split("-").length==3)
		{
			String[] attValue = Value.split("-");
			
			if(Float.parseFloat(attValue[0])<-180 || Float.parseFloat(attValue[0])>180)
			{
				System.out.println("Line "+Ctx.getStart().getLine()+": invalid " + attName + " value : " + attValue[0]);
				return false;
			}
			
		}
		else
		{		
		try
		{
		if(Float.parseFloat(Value)<-180 || Float.parseFloat(Value)>180)
		{
			System.out.println("Line "+Ctx.getStart().getLine()+": invalid " + attName + " value : " + Ctx.attributeValue().getText());
			return false;
		}
		}
		catch(NumberFormatException e)
		{
			System.out.println("Line "+Ctx.getStart().getLine()+": invalid " + attName + " value : " + Ctx.attributeValue().getText());
			return false;
		}
		}
		return true;
	}

	public boolean checkAltitude(String Value, XMLParser.AttributeContext Ctx, Boolean def)
	{
		Character altUnits = new Character (Value.charAt(Value.length()-1));
		if(!altUnits.equals('F') && !altUnits.equals('M'))
		{
				System.out.println("Line "+Ctx.getStart().getLine()+": warning: invalid alt units in airport. using default (M)");
				def=true;								
		}
		return true;
	}
	
	public boolean checkIdent(String Value, XMLParser.AttributeContext Ctx)
	{
		if(Value.length()>4)
		{
			
			System.out.println("Line "+Ctx.getStart().getLine()+": Airport ident value too long: " + Value + ". Max 4 Chars!");
			return false;
		}
		return true;
		
	}
	
	public boolean checkAirportTestRadius(String Value, XMLParser.AttributeContext Ctx)
	{
		Character testRadiusUnits = new Character(Value.charAt(Value.length()-1));
		if(!testRadiusUnits.equals('M') && !testRadiusUnits.equals('G') && !testRadiusUnits.equals('N'))
		{
			System.out.println("Line "+Ctx.getStart().getLine()+": Wrong ariportTestRadius format: " + testRadiusUnits +". Use <float(M|G|N)>");
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public boolean checkTrafficScalar(String Value, XMLParser.AttributeContext Ctx)
	{
		if(Float.parseFloat(Ctx.attributeValue().getText())<0.01 ||
				Float.parseFloat(Ctx.attributeValue().getText())>1.00)
		{
			System.out.println("Line "+Ctx.getStart().getLine()+": Wrong airport trafficScalar value. used: "+ Ctx.attributeValue().getText()
								+ "expecting 0.1<=X<=1.0");
			return false;
		}
		
		return true;
	}

	public boolean checkOptions(String Value, String[] Options, XMLParser.AttributeContext Ctx)
	{
		if(!Arrays.asList(Options).contains(Value))
		{
			
			System.out.println("Line "+Ctx.getStart().getLine()+": Wrong fuel type: " + Ctx.attributeValue().getText() + ". Expected: " + Arrays.toString(Options));
			return false;
		}
		return true;
	}
	
	public boolean checkOptions(String Value, Vector<String> Options, XMLParser.AttributeContext Ctx)
	{
		if(!Options.contains(Value))
		{
		System.out.println("Line "+Ctx.getStart().getLine()+": Wrong taxiwayPath surface: " + Value + ". Expected: " + Options);
		return false;
		}
		return true;
	}

	public boolean checkHeading(String Value, XMLParser.AttributeContext Ctx)
	{
		if(Float.parseFloat(Value)<0 || Float.parseFloat(Value)>360)
		{
			System.out.println("Line "+Ctx.getStart().getLine()+": invalid heading value : " + Value);
			return false;
		}
		return true;
	}
}
