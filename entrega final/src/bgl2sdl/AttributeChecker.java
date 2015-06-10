package bgl2sdl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

import jdk.nashorn.internal.runtime.FindProperty;

import com.sun.org.apache.bcel.internal.generic.NEW;

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

			System.out.println("Line "+Ctx.getStart().getLine()+": " + Ctx.getText() + " value too long: " + Value + ". Max 4 Chars!");
			return false;
		}
		return true;

	}

	public boolean checkAirportTestRadius(String Value, XMLParser.AttributeContext Ctx)
	{
		Character testRadiusUnits = new Character(Value.charAt(Value.length()-1));
		if(!testRadiusUnits.equals('M') && !testRadiusUnits.equals('G') && !testRadiusUnits.equals('N'))
		{
			System.out.println("Line "+Ctx.getStart().getLine()+": Wrong " + Ctx.getText() + " format: " + testRadiusUnits +". Use <float(M|G|N)>");
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
			System.out.println("Line "+Ctx.getStart().getLine()+": Wrong " + Ctx.getText() + " value. used: "+ Ctx.attributeValue().getText()
					+ "expecting 0.1<=X<=1.0");
			return false;
		}

		return true;
	}

	public boolean checkOptions(String Value, String[] Options, XMLParser.AttributeContext Ctx)
	{
		if(!Arrays.asList(Options).contains(Value))
		{

			System.out.println("Line "+Ctx.getStart().getLine()+": Wrong " + Ctx.getText() + " type: " + Ctx.attributeValue().getText() + ". Expected: " + Arrays.toString(Options));
			return false;
		}
		return true;
	}

	public boolean checkOptions(String Value, Vector<String> Options, XMLParser.AttributeContext Ctx)
	{
		if(!Options.contains(Value))
		{
			System.out.println("Line "+Ctx.getStart().getLine()+": Wrong " + Ctx.getText() + " surface: " + Value + ". Expected: " + Options);
			return false;
		}
		return true;
	}

	public boolean checkHeading(String Value, XMLParser.AttributeContext Ctx)
	{
		if(Float.parseFloat(Value)<0 || Float.parseFloat(Value)>360)
		{
			System.out.println("Line "+Ctx.getStart().getLine()+": invalid " + Ctx.getText() + " value : " + Value);
			return false;
		}
		return true;
	}



	public boolean checkConnectivity(Vector<Integer> nodes, Vector<Edge> edges)
	{	Edge cena = new Edge();
		
		Vector<Integer> connected= new Vector<Integer>();
		Vector<Integer> tempNodes=nodes;
		Vector<Edge> tempEdges= edges; 
		
		
		System.out.println("connected size: " + connected.size());
		System.out.println("connected: " + connected);
		
		connected.add(tempEdges.get(0).startingNode);
		connected.add(tempEdges.get(0).endingNode);
		
		
		System.out.println("temp edges size: " + tempEdges.size());
		System.out.println("temp edges: " + tempEdges);
		System.out.println("connected size: " + connected.size());
		System.out.println("connected: " + connected);

		boolean isConnected= false;
		tempEdges.remove(0);
		System.out.println("temp edges size: " + tempEdges.size());
		System.out.println("temp edges: " + tempEdges);
		
		
			//andar pelos connected
			for(int i=0; i<connected.size(); i++)
			{
				//percorrer as edges para verificar
				for(int j=0; j<tempEdges.size(); j++)
				{
					
					if(connected.get(i).equals(tempEdges.get(j).startingNode))
					{
						connected.add(tempEdges.get(j).endingNode);
						tempEdges.remove(j);
						i=0;
					}
					else if (connected.get(i).equals(tempEdges.get(j).endingNode))
					{
						connected.add(tempEdges.get(j).startingNode);
						tempEdges.remove(j);
						i=0;
					}

					
				
				
				
				System.out.println("connected size: " + connected.size());
				System.out.println("connected: " + connected);
				System.out.println("temp edges size: " + tempEdges.size());
				System.out.println("temp edges: " + tempEdges);
			}
			
		}
		
		if(connected.size() == nodes.size())
		{
		System.out.println("ta bom");
		return true;
		}
		else
		{
			System.out.println("nao ]e conexo");
			return false;
		}

		
	}





}


